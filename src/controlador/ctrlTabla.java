package controlador;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.apache.commons.lang3.Validate;

import modelo.Lproductos;
import modelo.Lproveedor;
import modelo.Conexion;
import modelo.Lcategoria;
import modelo.CBcadenas;
import modelo.Cbd;
import vista.vistaTabla;

public class ctrlTabla implements ActionListener, ClipboardOwner {
	vistaTabla vTabla;
	int tipo;
	
	Conexion con = new Conexion();
	CBcadenas cadena = new CBcadenas();
	
	public ctrlTabla(int tipo) {
		this.tipo = tipo;
		
		vTabla = new vistaTabla();
	}
	
	public void inicializar() {
		vTabla.setModal(true);
		
		vTabla.btnCopiar.addActionListener(this);
		vTabla.btnSalir.addActionListener(this);
		
		tipoTabla();
		vTabla.setVisible(true);
	}
	
	public void tipoTabla() {
		switch(tipo) {
		case 1:{
			vTabla.setTitle("Tabla Productos");
			vTabla.setBounds(100, 100, 700, 477);
			vTabla.setLocationRelativeTo(null);
			this.mostrarTablaProductos();
		}break;
		case 2:{
			vTabla.setTitle("Tabla Categorias");
			this.mostrarTablaCategorias();
		}break;
		case 3:{
			vTabla.setTitle("Tabla Proveedores");
			this.mostrarTablaProveedores();
		}break;
		case 4:{
			vTabla.setTitle("Registro Almacen");
			this.mostrarTablaAlmacen();
			this.accionAlmacen();
		}break;
		}
	}
	
	public void setClipBoard(String texto) { //metodo para copiar el id de producto
		StringSelection txt = new StringSelection(texto);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(txt, this);
	}
		
	@Override
	public void actionPerformed(ActionEvent e) { //eventos
		if(e.getSource() == vTabla.btnCopiar) {
			int fila = vTabla.tabla.getSelectedRow();
			
			if(fila > -1) { //comprobar si selecciono una fila
				setClipBoard((String)vTabla.tabla.getValueAt(fila, 0));
				System.out.println((String)vTabla.tabla.getValueAt(fila, 0));
				vTabla.tabla.clearSelection();
			}else {
				JOptionPane.showMessageDialog(vTabla, "Selecciona un Producto");
			}			
		}else if(e.getSource() == vTabla.btnSalir) {
			vTabla.setModal(false);
			vTabla.dispose();
		}
	}

	@Override
	public void lostOwnership(Clipboard clipboard, Transferable contents) {		
	}
	
	public void mostrarTablaProductos() {
		try {
			Cbd base = new Cbd(con.getConnection());
			
			Lproductos listaProductos = new Lproductos(base.consultaBD(cadena.getCproducto()));
			
			vTabla.tabla.setModel(listaProductos.getModelProductos());
		} catch (SQLException e) {
			System.out.println("Error ---> " + e);
		}
	}
	
	public void mostrarTablaProveedores() {
		try {
			Cbd base = new Cbd(con.getConnection());
			
			Lproveedor listaProveedores = new Lproveedor(base.consultaBD(cadena.getCproveedor()));
			
			vTabla.tabla.setModel(listaProveedores.getModelProveedores());
		} catch (SQLException e) {
			System.out.println("Error --> " + e);
		}
	}
	
	public void mostrarTablaCategorias() {
		try {
		Cbd base = new Cbd(con.getConnection());
		
		Lcategoria listaCategorias = new Lcategoria(base.consultaBD(cadena.getCcategoria()));
		
		vTabla.tabla.setModel(listaCategorias.getModelCategoria());
		}catch(SQLException e) {
			System.out.println("Error -> " + e);
		}
	}
	
	public void mostrarTablaAlmacen() {
		Cbd base = new Cbd(con.getConnection());
		DefaultTableModel model = new DefaultTableModel();
		
		try {
			ResultSet rs = base.consultaBD(cadena.getCalmacen());
			vTabla.tabla = new JTable(model);
			vTabla.scrollPane.setViewportView(vTabla.tabla);
			
			model.addColumn("Fecha");
			model.addColumn("Hora");
			model.addColumn("Proveedor");
			model.addColumn("Id");
			model.addColumn("Cantidad");
			model.addColumn("Precio");
			
			while(rs.next()) {
				Object fila[] = new Object[6];
				
				for(int i = 0; i < 6; i++) {
					fila[i] = rs.getObject(i + 1);
				}
				model.addRow(fila);
			}
		} catch (SQLException e) {
			System.out.println("Error al llenar la tabla de almacen " + e);
		}
	}
	
	public void accionAlmacen() {
		vTabla.btnCopiar.setVisible(false);
	}
	
}
