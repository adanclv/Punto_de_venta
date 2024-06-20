package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;

import modelo.CBcadenas;
import modelo.Cbd;
import modelo.Conexion;
import modelo.Lproductos;
import modelo.Producto;
import vista.vistaAlmacen;
import vista.vistaTabla;

public class ctrlAlmacen implements ActionListener{
	vistaAlmacen vAlmacen;
	vistaTabla vTabla;
	ctrlTabla tabla;
	private String idProveedor, Producto;
	
	Conexion con = new Conexion();
	CBcadenas cadena = new CBcadenas();
	
	public ctrlAlmacen() {
		this.idProveedor = "00";
		this.Producto = "";
		vAlmacen = new vistaAlmacen();
		
		ComboProveedores();
	}
	
	public void inicializar() {
		vAlmacen.setModal(true);
		
		vAlmacen.btnAgregar.addActionListener(this);
		vAlmacen.btnSalir.addActionListener(this);
		vAlmacen.btnTabla.addActionListener(this);
		vAlmacen.btnAlmacen.addActionListener(this);
		
		vAlmacen.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == vAlmacen.btnAgregar) { //AGREGAR
			Producto prod = new Producto();
			int cantidadActual, cantidadNueva;
			String id = vAlmacen.txtId.getText().trim();
			String proveedor = vAlmacen.cbProveedor.getSelectedItem().toString(); 
			String precio = vAlmacen.txtPrecio.getText().trim();
			String cantidad = vAlmacen.txtCantidad.getText().trim();
			String idP = idProveedorXNombre(proveedor);
			if(existeProducto(id) == false) {
				if(vAlmacen.cbProveedor.getSelectedItem().toString().equalsIgnoreCase("Selecciona proveedor:")) {
					JOptionPane.showMessageDialog(vAlmacen, "Seleccion un Proveedor");
				}else {
					System.out.println(idP);
					if(id.isEmpty() || precio.isEmpty() || cantidad.isEmpty()) {
						JOptionPane.showMessageDialog(vAlmacen, "Completa los Campos");
					}else {
						if(insertarAlmacen(idP, id, cantidad, precio) == true) {
							cantidadActual = cantidadProducto(id);
							cantidadNueva = Integer.parseInt(cantidad);
							cantidadNueva = cantidadActual + cantidadNueva;
							updateCantidad(String.valueOf(cantidadNueva), id);
							limpiar();
							JOptionPane.showMessageDialog(vAlmacen, "Almacen");
							System.out.println(cantidadNueva);
						}else {
							JOptionPane.showMessageDialog(vAlmacen, "NO");
						}
						
						
					}
				}				
			}else {
				JOptionPane.showMessageDialog(vAlmacen, "NO existe ID");
			}
		}else if(e.getSource() == vAlmacen.btnSalir) { //SALIR
			vAlmacen.dispose();
		}else if(e.getSource() == vAlmacen.btnTabla) { //TABLA
			vAlmacen.setModal(false);
			tabla = new ctrlTabla(1);
			tabla.inicializar();
			
		}else if(e.getSource() == vAlmacen.btnAlmacen) {
			tabla = new ctrlTabla(4);
			tabla.inicializar();
		}
	}
	
	public void limpiar() {
		vAlmacen.txtId.setText("");
		vAlmacen.txtPrecio.setText("");
		vAlmacen.txtCantidad.setText("");
		vAlmacen.cbProveedor.setSelectedItem("Selecciona proveedor:");
	}
	
	public boolean insertarAlmacen(String idProveedor, String idProducto, String cantidad, String precio) {
		Cbd base = new Cbd(con.getConnection());
		
		boolean realizo = true;
		
		try {
			realizo = base.insertar(cadena.getInsertarAlmacen(Fecha(), Hora(), idProveedor, idProducto, cantidad, precio));
		} catch (Exception e) {
			realizo = false;
			JOptionPane.showMessageDialog(vAlmacen, "Error -> " + e);
		}
		return realizo;
	}
	
	public boolean updateCantidad(String cantidad, String id) {
		boolean realizo = true;
		Cbd base = new Cbd(con.getConnection());
		
		try {
			realizo = base.modificar(cadena.getUpdateCantidad(cantidad, id));
		} catch (Exception e) {
			JOptionPane.showMessageDialog(vAlmacen, "Error_->" + e);
		}
		
		return realizo;
	}
	
	public int cantidadProducto(String id) {
		int cantidadActual = 0;
		Cbd base = new Cbd(con.getConnection());
		
		try {
			ResultSet rs = base.consultaBD(cadena.getCproductoId(id));
			
			if(rs.next()) {
				cantidadActual = Integer.parseInt(rs.getString("Cantidad"));
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(vAlmacen, "Error -> " + e);
		}
		return cantidadActual;
	}
	
	public String idProveedorXNombre(String nombreProveedor) {
		String Proveedor = "";
		Cbd base = new Cbd(con.getConnection());
		
		try {
			ResultSet rs = base.consultaBD(cadena.getCproveedorconName(nombreProveedor));
			
			if(rs.next()) {
				return Proveedor = rs.getString("IdProveedor");
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(vAlmacen, "ERROR -> " + e);
		}
		return Proveedor;
	}
	
	public void ComboProveedores() {
		Cbd base = new Cbd(con.getConnection());
		try {
			ResultSet rs = base.consultaBD(cadena.getCproveedor());
			
			vAlmacen.cbProveedor.removeAllItems();
			vAlmacen.cbProveedor.addItem("Selecciona proveedor:");
			while(rs.next()) {
				vAlmacen.cbProveedor.addItem(rs.getString("Proveedor"));
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(vAlmacen, "Error ->" + e);
		}
	}
	
	public boolean existeProducto(String id) {
		Cbd base = new Cbd(con.getConnection());
		boolean verificar = false;
		
		try {
			Lproductos listaProductos = new Lproductos(base.consultaBD(cadena.getCproductoId(id)));
			if (listaProductos.getArray().size() == 0) {
				verificar = true;
			}else {
				verificar = false;
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(vAlmacen, "Error ->" + e);
		}
		return verificar;
	}
	
	public String Fecha() {
		String fechaActual = "";
		Date date = new Date();
		fechaActual = new SimpleDateFormat("dd/MM/yyyy").format(date);
		
		return fechaActual;
	}
	
	public String Hora() {
		String horaActual = "";
		Date date = new Date(); 
		horaActual = new SimpleDateFormat("HH:mm:ss").format(date);
		 
		return horaActual;
	}
}
