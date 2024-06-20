package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import modelo.Producto;
import modelo.CBcadenas;
import modelo.Cbd;
import modelo.Conexion;
import modelo.Lproductos;
import vista.vistaProductos;
import vista.vistaTabla;

public class ctrlProductos implements ActionListener{
	vistaProductos vProductos;
	ctrlTabla tabla;
	vistaTabla vTabla;
	private String idCategoria;
	
	Conexion con = new Conexion();
	CBcadenas cadena = new CBcadenas();
	
	public ctrlProductos() {
		this.idCategoria = "00";
		vProductos = new vistaProductos();
		ComboCategoria();
		ComboIva();
	}
	
	public void inicializar() {
		vProductos.setModal(true);
		
		vProductos.btnAgregar.addActionListener(this);
		vProductos.btnModificar.addActionListener(this);
		vProductos.btnEliminar.addActionListener(this);
		vProductos.btnSalir.addActionListener(this);
		vProductos.btnTabla.addActionListener(this);	
		
		vProductos.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == vProductos.btnAgregar) { //AGREGAR
			Producto prod = new Producto();
			
			if (vProductos.cbCategoria.getSelectedItem().toString().equalsIgnoreCase("Selecciona categoria:")) {
				JOptionPane.showMessageDialog(vProductos, "Selecciona una Categoria");
			}else {
				if(!vProductos.cbIva.getSelectedItem().toString().equalsIgnoreCase("Selecciona iva:")) {
					String idC = idCategoriaXNombre(vProductos.cbCategoria.getSelectedItem().toString());
					String iva = vProductos.cbIva.getSelectedItem().toString();
					prod.setId(vProductos.txtId.getText().trim());
					prod.setDescripcion(vProductos.txtProducto.getText().trim());
					prod.setPrecio(vProductos.txtPrecio.getText().trim());
					prod.setCantidad(vProductos.txtCantidad.getText().trim());
					prod.setCategoria(idC);
					if (prod.getId().isEmpty() || prod.getDescripcion().isEmpty() || prod.getPrecio().isEmpty() || prod.getCantidad().isEmpty()) {
						JOptionPane.showMessageDialog(vProductos, "Completa los Campos");
					}else {
						if(existeProducto(vProductos.txtId.getText().trim()) == true) {
							if(iva.equals("No IVA")) {
								
							}else {
								insertarIVA(prod.getId(), iva);
							}
							insertarProducto(prod);
							limpiar();
							JOptionPane.showMessageDialog(vProductos, "Producto Añadido");
						}else {
							JOptionPane.showMessageDialog(vProductos, "ID ya existe");
						}
					}
				}else {
					JOptionPane.showMessageDialog(vProductos, "Selecciona un Porcentaje de Iva");
				}
				
			}			
		}else if(e.getSource() == vProductos.btnModificar) { //MODIFICAR
			String cadenaBtn = vProductos.btnModificar.getText().trim();
			Producto prod = new Producto();
			vProductos.label6.setVisible(false);
			
			switch(cadenaBtn){
			case "Modificar":{
				if (existeProducto(vProductos.txtId.getText().trim()) == false) {
					accionModificar(vProductos.txtId.getText().trim());
					seleccionarCategoriaModificar(idCategoria);
					accionModificarBtnyTxt();
					this.seleccionarIVAModificar(vProductos.txtId.getText().trim());
				}else {
					JOptionPane.showMessageDialog(vProductos, "NO existe ID");
				}
			}break;
			case "Guardar":{
				vProductos.label6.setVisible(true);
				vProductos.cbIva.setVisible(true);
				if (vProductos.cbCategoria.getSelectedItem().toString().equalsIgnoreCase("Selecciona categoria:")) {
					JOptionPane.showMessageDialog(vProductos, "Selecciona una Categoria");
				}else {
					String idC = idCategoriaXNombre(vProductos.cbCategoria.getSelectedItem().toString()); 
					String iva = vProductos.cbIva.getSelectedItem().toString();
					prod.setId(vProductos.txtId.getText().trim());
					prod.setDescripcion(vProductos.txtProducto.getText().trim());
					prod.setPrecio(vProductos.txtPrecio.getText().trim());
					prod.setCantidad(vProductos.txtCantidad.getText().trim());
					prod.setCategoria(idC);
					if (prod.getId().isEmpty() || prod.getDescripcion().isEmpty() || prod.getPrecio().isEmpty()) {
						JOptionPane.showMessageDialog(vProductos, "Completa los Campos");
					}else {
						if(vProductos.cbIva.getSelectedItem().toString().equalsIgnoreCase("Selecciona iva:")) {
							JOptionPane.showMessageDialog(vProductos, "Selecciona IVA");
						}else {
							
							boolean modifico = modificarProducto(prod);
							System.out.println(modifico + " y " + prod.getCategoria());
							if (modifico == true) {
								if(iva.equals("No IVA")) {
									eliminarIva(vProductos.txtId.getText().trim());
								}else {
									insertarIVA(vProductos.txtId.getText().trim(), iva);
								}
								JOptionPane.showMessageDialog(vProductos, "Producto Actualizado");
								accionReset();
								limpiar();
							}else {
								JOptionPane.showMessageDialog(vProductos, "Nipdo pa");
							}
						}
					}
				}
			}break;
			case "SI":{
				vProductos.setBounds(100, 100, 557, 345);
				vProductos.setLocationRelativeTo(null);
				vProductos.label6.setVisible(true);
				vProductos.cbIva.setVisible(true);
				eliminarProducto(vProductos.txtId.getText().trim());
				eliminarIva(vProductos.txtId.getText().trim());
				accionReset();
				limpiar();
			}break;
			}
		}else if(e.getSource() == vProductos.btnEliminar) { //ELIMINAR
			String cadenaBtn = vProductos.btnEliminar.getText().trim();
			
			switch(cadenaBtn) {
			case "Eliminar":{
				vProductos.label6.setVisible(false);
				vProductos.cbIva.setVisible(false);
				
				if (vProductos.txtId.getText().trim().isEmpty()) {
					JOptionPane.showMessageDialog(vProductos, "Ingresa ID");
				}else {
					if (existeProducto(vProductos.txtId.getText().trim()) == false) {
						accionModificar(vProductos.txtId.getText().trim());
						seleccionarCategoriaModificar(idCategoria);
						accionEliminarBtnyTxt();
						vProductos.setBounds(100, 100, 557, 370);
						vProductos.setLocationRelativeTo(null);
						
						vProductos.labelSeguro.setText("Desea eliminar '" + vProductos.txtProducto.getText() + "' ?");
					}else {
						JOptionPane.showMessageDialog(vProductos, "NO existe ID");
					}
				}
			}break;
			case "Cancelar":{
				vProductos.label6.setVisible(true);
				vProductos.cbIva.setVisible(true);
				accionReset();
				limpiar();
				vProductos.setBounds(100, 100, 557, 345);
				vProductos.setLocationRelativeTo(null);
			}break;
			}
			
		}else if(e.getSource() == vProductos.btnSalir) { //SALIR
			vProductos.dispose();
		}else if(e.getSource() == vProductos.btnTabla) { //MOSTRAR VENTANA TABLA
			tabla = new ctrlTabla(1);
			tabla.inicializar();
		}
	}
	
	public void accionModificar(String id) {
		Cbd base = new Cbd(con.getConnection());

		idCategoria = "";
		try {
			ResultSet rs = base.consultaBD(cadena.getCproductoId(id));
			
			if(rs.next()) {
				vProductos.txtProducto.setText(rs.getString("Producto"));
				vProductos.txtPrecio.setText(rs.getString("Precio"));
				vProductos.txtCantidad.setText(rs.getString("Cantidad"));
				idCategoria = rs.getString("IdCategoria");
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(vProductos, "Error ---> " + e);
		}
	}
	
	public boolean eliminarProducto(String id) {
		Cbd base = new Cbd(con.getConnection());
		
		boolean realizo = true;
		
		try {
			realizo = base.eliminar(cadena.getEliminarProducto(id));
		} catch (Exception e) {
			JOptionPane.showMessageDialog(vProductos, "ERROR al eliminar -> " + e);
		}
		return realizo;
	}
	
	public boolean eliminarIva(String id) {
		Cbd base = new Cbd(con.getConnection());
		
		boolean realizo = true;
		try {
			realizo = base.eliminar(cadena.getEliminarIVA(id));
		} catch (Exception e) {
			JOptionPane.showMessageDialog(vProductos, "ERROR al eliminar -> " + e);
		}
		return realizo;
	}
	
	public void accionModificarBtnyTxt() {
		vProductos.btnAgregar.setVisible(false);
		vProductos.btnSalir.setVisible(false);
		vProductos.btnModificar.setText("Guardar");
		vProductos.btnEliminar.setText("Cancelar");
		vProductos.txtId.setEnabled(false);
		vProductos.txtProducto.setEnabled(false);
		vProductos.txtCantidad.setEnabled(false);
	}
	
	public void accionEliminarBtnyTxt() {
		vProductos.btnAgregar.setVisible(false);
		vProductos.btnSalir.setVisible(false);
		vProductos.btnModificar.setText("SI");
		vProductos.btnEliminar.setText("Cancelar");
		vProductos.txtId.setEnabled(false);
		vProductos.txtProducto.setEnabled(false);
		vProductos.txtPrecio.setEnabled(false);
		vProductos.txtCantidad.setEnabled(false);
		vProductos.cbCategoria.setEnabled(false);
	}
	
	public void accionReset() {
		vProductos.btnAgregar.setVisible(true);
		vProductos.btnSalir.setVisible(true);
		vProductos.btnModificar.setText("Modificar");
		vProductos.btnEliminar.setText("Eliminar");
		vProductos.txtId.setEnabled(true);
		vProductos.txtProducto.setEnabled(true);
		vProductos.txtPrecio.setEnabled(true);
		vProductos.txtCantidad.setEnabled(true);
		vProductos.cbCategoria.setEnabled(true);
	}
	
	public void limpiar() {
		vProductos.txtId.setText("");
		vProductos.txtProducto.setText("");
		vProductos.txtPrecio.setText("");
		vProductos.txtCantidad.setText("");
		vProductos.cbCategoria.setSelectedItem("Selecciona categoria:");
		vProductos.labelSeguro.setText("");
		vProductos.cbIva.setSelectedItem("Selecciona iva:");
	}
	
	public boolean insertarProducto(Producto prod) {
		Cbd base = new Cbd(con.getConnection());
		
		boolean realizo = true;
		String id, producto, precio, cantidad, categoria;
		
		try {
			id = prod.getId();
			producto = prod.getDescripcion();
			precio = prod.getPrecio();
			cantidad = prod.getCantidad();
			categoria = prod.getCategoria();
			
			realizo = base.insertar(cadena.getinsertarProducto(id, producto, precio, cantidad, categoria));
		} catch (Exception e) {
			realizo = false;
			JOptionPane.showMessageDialog(vProductos, "ERROR -> " + e);
		}
		return realizo;
	}
	
	public boolean insertarIVA(String id, String iva) {
		Cbd base = new Cbd(con.getConnection()); 
		
		boolean realizo = true;
		
		try {
			realizo = base.insertar(cadena.getInsertarIVA(id, iva));
		} catch (Exception e) {
			JOptionPane.showMessageDialog(vProductos, "ERROR -> " + e);
		}
		return realizo;
	}
	
	public boolean modificarProducto(Producto prod) { 
		boolean realizo = true;
		Cbd base = new Cbd(con.getConnection());
		
		String id, producto, precio, cantidad, categoria;
		
		try {
			id = prod.getId();
			producto = prod.getDescripcion();
			precio = prod.getPrecio();
			cantidad = prod.getCantidad();
			categoria = prod.getCategoria();
			realizo = base.modificar(cadena.getUpdateProducto(producto, precio, cantidad, categoria, id)); 
			System.out.println("Verificar xdd - " + prod.getDescripcion() + " " + prod.getPrecio() + " " + prod.getCantidad() + " " + prod.getCategoria());
		} catch (Exception e) {
			realizo = false;
			JOptionPane.showMessageDialog(vProductos, "Error al Modificar" + e);
		}
		return realizo;
	}
	
	public String idCategoriaXNombre(String nombreCategoria) { 
		String Categoria = "";
		Cbd base = new Cbd(con.getConnection());
		
		try {
			ResultSet rs = base.consultaBD(cadena.getCategoriaconID(nombreCategoria));
			
			while(rs.next()) {
				return Categoria = rs.getString("IdCategoria");
			}
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(vProductos, "ERROR -> " + e);
		}
		return Categoria;
	}
	
	
	public void seleccionarCategoriaModificar(String id) {
		Cbd base = new Cbd(con.getConnection());
		
		idCategoria = "";
		try {
			ResultSet rs = base.consultaBD(cadena.getCcategoriaId(id));
			
			if(rs.next()) {
				vProductos.cbCategoria.setSelectedItem(rs.getString(1));
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(vProductos, "Error ---> " + e);
		}
	}
	
	public void seleccionarIVAModificar(String id) {
		Cbd base = new Cbd(con.getConnection());
		String iva = "";
		try {
			ResultSet rs = base.consultaBD(cadena.getCiva(id));
			
			if(rs.next()) {
				iva = rs.getString(1);
			}
			if(!iva.isEmpty()) {
				vProductos.cbIva.setSelectedItem(iva);
			}else {
				vProductos.cbIva.setSelectedItem("No IVA");
			}
				
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public void ComboCategoria() { 
		Cbd base = new Cbd(con.getConnection());
		try {
			ResultSet rs = base.consultaBD(cadena.getCcategoria());

			vProductos.cbCategoria.removeAllItems();
			vProductos.cbCategoria.addItem("Selecciona categoria:");
			while (rs.next()) {
				vProductos.cbCategoria.addItem(rs.getString(1));
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(vProductos, "Error ->" + e);
		}
	}
	
	public void ComboIva() {
		Cbd base = new Cbd(con.getConnection());
		try {
			ResultSet rs = base.consultaBD("SELECT *FROM IVA");
			
			vProductos.cbIva.removeAllItems();
			vProductos.cbIva.addItem("Selecciona iva:");
			if(rs.next()) {
				vProductos.cbIva.addItem(rs.getString("IVA"));
			}
			vProductos.cbIva.addItem("No IVA");
		} catch (SQLException e) {
			System.out.println("Error -> " + e);
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
			JOptionPane.showMessageDialog(vProductos, "Error ->" + e);
		}
		return verificar;
	}
}
