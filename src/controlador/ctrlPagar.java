package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import controlador.VentaPDF;
import modelo.CBcadenas;
import modelo.Cbd;
import modelo.Conexion;
import modelo.PuntoVenta;
import vista.vistaMenu;
import vista.vistaPagar;

public class ctrlPagar implements ActionListener, KeyListener {
	vistaPagar vPagar;
	vistaMenu vMenu;
	ctrlMenu cMenu;
	ArrayList<PuntoVenta> listaVenta = new ArrayList<>();
	private PuntoVenta venta;
	private String idTicket;
	
	private double TotalGral = 0.0;
	
	Conexion con = new Conexion();
	CBcadenas cadena = new CBcadenas();
		
	public ctrlPagar(ArrayList<PuntoVenta> listaVenta, PuntoVenta venta, String idTicket) {
		vPagar = new vistaPagar();
		this.listaVenta = listaVenta;
		this.venta = venta;
		this.idTicket = idTicket;
	}

	public void inicializar() {
		vPagar.setModal(true);
		
		vPagar.btnRealizar.addActionListener(this);
		vPagar.btnCancelar.addActionListener(this);
		
		vPagar.txtEfectivo.addKeyListener(this);
		
		vPagar.btnRealizar.setEnabled(false);
		this.calcularTotal();
		
		vPagar.setVisible(true);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		char ch = e.getKeyChar();
		try {
			if (!isNumber(ch) && !validatePoint(ch)  && ch != '\b') {
	            e.consume();
	        }
		} catch (NumberFormatException e2) {
			System.out.println("Error " + e2);
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {
		double aux = Double.parseDouble(vPagar.txtEfectivo.getText());
		
		if(aux >= TotalGral) {
			calcularCambio(aux);
			vPagar.btnRealizar.setEnabled(true);
		}else {
			vPagar.txtCambio.setText("");
			vPagar.btnRealizar.setEnabled(false);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == vPagar.btnRealizar) { //REALIZAR
			for(PuntoVenta elemento : this.listaVenta) {
				venta.setFecha(Fecha());
				venta.setIdTicket(this.idTicket);
				venta.setIdProducto(elemento.getIdProducto());
				venta.setCantidad(elemento.getCantidad());
				venta.setTotal(elemento.getTotal());
				if(insertarVenta(venta) == true) {
					this.restarCantidadProductos(elemento.getIdProducto(), Integer.parseInt(elemento.getCantidad()));
										
				}else {
					System.out.println("No se realizo");
				}
			}
			vPagar.dispose();
			VentaPDF pdf = new VentaPDF();
			pdf.generarFacturaPDF();
		}else if(e.getSource() == vPagar.btnCancelar) { //CANCELAR
			vPagar.dispose();
		}
	}
	
	public boolean insertarVenta(PuntoVenta pventa) {
		boolean realizo = true;
		String fecha, ticket, producto, cantidad, total;
		Cbd base = new Cbd(con.getConnection());
		
		try {
			fecha = pventa.getFecha();
			ticket = pventa.getIdTicket();
			producto = pventa.getIdProducto();
			cantidad = pventa.getCantidad();
			total = pventa.getTotal();
			realizo = base.insertar(cadena.getInsertarVenta(fecha, ticket, producto, cantidad, total));
		} catch (Exception e) {
			realizo = false;
			System.out.println("Error -> " + e);
		}
		return realizo;
	}
	
	public boolean restarCantidadProductos(String id, int cantidad) {
		int cantidadBD = 0;
		boolean realizo = true;
		try {
			Cbd base = new Cbd(con.getConnection());
			
			ResultSet rs = base.consultaBD(cadena.getCproductoCantidad(id));
			
			if(rs.next()) {
				cantidadBD = Integer.parseInt(rs.getString(1));
			}
		} catch (SQLException e) {
			System.out.println("Error -> " + e);
		}
		try {
			Cbd base = new Cbd(con.getConnection());
			int cantidadNueva = cantidadBD - cantidad;
			
			realizo = base.modificar(cadena.getUpdateCantidad(String.valueOf(cantidadNueva), id));		
		} catch (Exception e) {
			System.out.println("Error -> " + e);
		}
		return realizo;
	}
	
	public void calcularTotal() {
		TotalGral = 0;
		
		for(PuntoVenta elemento : listaVenta) {
			TotalGral += Double.parseDouble(elemento.getTotal());
		}
		TotalGral = (double)Math.round(TotalGral * 100) / 100;
		
		vPagar.txtTotal.setText(String.valueOf(TotalGral));
	}
	
	public void calcularCambio(double efectivo) {
		double cambio = 0.0;
		
		cambio = efectivo - TotalGral;
		cambio = (double)Math.round(cambio * 100) / 100;
		
		vPagar.txtCambio.setText(String.valueOf(cambio));
	}
	
	
	private boolean isNumber(char ch){
        return ch >= '0' && ch <= '9';
    }
	
	 private boolean validatePoint(char ch){
		 if(ch != '.'){
			 return false;
		 }

		 if(vPagar.txtEfectivo.getText() == null || "".equals(vPagar.txtEfectivo.getText().trim())){
			 vPagar.txtEfectivo.setText("0.");
			 return false;
		 }else if("-".equals(vPagar.txtEfectivo.getText())){
			 vPagar.txtEfectivo.setText("-0.");
		 }

		 return true;
	 }	
	
	public String Fecha() {
		String fechaActual = "";
		Date date = new Date();
		fechaActual = new SimpleDateFormat("dd/MM/yyyy").format(date);
		
		return fechaActual;
	}
}
