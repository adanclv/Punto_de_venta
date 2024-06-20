package controlador;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import vista.vistaMenu;

public class VentaPDF {
	
	private String fechaActual = "";
	private String nombreFilePDF = "";
	
	
	//metodo para generar factura de venta
	public void generarFacturaPDF() {
		try {
			//cargar la fecha actual
			Date date = new Date();
			fechaActual = new SimpleDateFormat("dd/MM/yyyy").format(date);
			
			//cambiar el formato de la fecha para poder ponerle el nombre al pdf (_)
			String fechaNueva = "";
			for(int i = 0; i < fechaActual.length(); i++) {
				if(fechaActual.charAt(i) == '/') {
					fechaNueva = fechaActual.replace("/", "_");
				}
			}
			
			nombreFilePDF = "Ticket_" + vistaMenu.labelTicket.getText() + "_" + fechaNueva + ".pdf";
			
			//crear pdf
			FileOutputStream archivo;
			File file = new File("src/Pdf/" + nombreFilePDF);
			archivo = new FileOutputStream(file);
			
			Document doc = new Document();
			PdfWriter.getInstance(doc, archivo);
			doc.open();
			
			Image img = Image.getInstance("src/Img/agregar32.png");
			Paragraph fecha = new Paragraph();
			Font bold = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD, BaseColor.BLUE);
			fecha.add(Chunk.NEWLINE); //agregar nueva linea
			fecha.add("Ticket: " + vistaMenu.labelTicket.getText() + "\n Fecha: " + fechaActual + "\n\n");
			
			PdfPTable Encabezado = new PdfPTable(4);
			Encabezado.setWidthPercentage(100);
			Encabezado.getDefaultCell().setBorder(0); //quitar borde de la tabla
			//tamaño de las celdas
			float [] columnaEncabezado = new float[]{20f, 30f, 70f, 40f};
			Encabezado.setWidths(columnaEncabezado);
			Encabezado.setHorizontalAlignment(Element.ALIGN_LEFT);
			//agregar celdas
			Encabezado.addCell(img);
			
			String ruc = "1010101";
			String nombre = "CCE-Company";
			String telefono = "8334441372";
			String direccion = "Cd. Madero";
			
			Encabezado.addCell(""); //celda vacia
			Encabezado.addCell(ruc + "\n" + nombre + "\n" + telefono + "\n" + direccion);
			Encabezado.addCell(fecha);
			doc.add(Encabezado);
			
			//Cuerpo
			
			//ESPACIO EN BLANCO
            Paragraph espacio = new Paragraph();
            espacio.add(Chunk.NEWLINE);
            espacio.add("");
            espacio.setAlignment(Element.ALIGN_CENTER);
            doc.add(espacio);
            
            //AGREGAR LOS PRODUCTOS
            PdfPTable tablaProducto = new PdfPTable(3);
            tablaProducto.setWidthPercentage(100);
            tablaProducto.getDefaultCell().setBorder(0);
            //tamaño de celdas
            float[] ColumnaProducto = new float[]{15f, 50f, 15f};
            tablaProducto.setWidths(ColumnaProducto);
            tablaProducto.setHorizontalAlignment(Element.ALIGN_LEFT);
            PdfPCell producto1 = new PdfPCell(new Phrase("Cantidad: ", bold));
            PdfPCell producto2 = new PdfPCell(new Phrase("Descripcion: ", bold));
            PdfPCell producto3 = new PdfPCell(new Phrase("Precio Total: ", bold));
            //quitar bordes
            producto1.setBorder(0);
            producto2.setBorder(0);
            producto3.setBorder(0);
            //agregar color al encabezadi del producto
            producto1.setBackgroundColor(BaseColor.LIGHT_GRAY);
            producto2.setBackgroundColor(BaseColor.LIGHT_GRAY);
            producto3.setBackgroundColor(BaseColor.LIGHT_GRAY);
            //agg celda a la tabla
            tablaProducto.addCell(producto1);
            tablaProducto.addCell(producto2);
            tablaProducto.addCell(producto3);
            
            for(int i = 0; i < vistaMenu.tablaVenta.getRowCount(); i++){
               String producto = vistaMenu.tablaVenta.getValueAt(i, 2).toString();
               String cantidad = vistaMenu.tablaVenta.getValueAt(i, 3).toString();
               String total = vistaMenu.tablaVenta.getValueAt(i, 5).toString();
                
               tablaProducto.addCell(cantidad);
               tablaProducto.addCell(producto);
               tablaProducto.addCell(total);
            }
            
            //agregar al documento
            doc.add(tablaProducto);
            
            //Total pagar
            Paragraph info = new Paragraph();
            info.add(Chunk.NEWLINE);
            info.add("Total a pagar: $" + vistaMenu.labelTotal.getText());
            info.setAlignment(Element.ALIGN_RIGHT);
            doc.add(info);
            
            //Firma
            Paragraph firma = new Paragraph();
            firma.add(Chunk.NEWLINE);
            firma.add("Cancelacion y firma\n\n");
            firma.add("_______________________");
            firma.setAlignment(Element.ALIGN_CENTER);
            doc.add(firma);
           
            //Mensaje
            Paragraph mensaje = new Paragraph();
            mensaje.add(Chunk.NEWLINE);
            mensaje.add("¡Gracias por su compra!");
            mensaje.setAlignment(Element.ALIGN_CENTER);
            doc.add(mensaje);
           
            //cerrar el documento y el archivo
            doc.close();
            archivo.close();
           
            //abrir el documento al ser generado automaticamente
            Desktop.getDesktop().open(file);
			
			
		} catch (DocumentException | IOException e) {
			System.out.println("Error en: " + e);
		}
	}
}
