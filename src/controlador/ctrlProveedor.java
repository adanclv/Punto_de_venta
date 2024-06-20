package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import modelo.CBcadenas;
import modelo.Cbd;
import modelo.Conexion;
import modelo.Lproveedor;
import modelo.Proveedor;
import vista.vistaProveedor;
import vista.vistaTabla;

public class ctrlProveedor implements ActionListener{
	vistaProveedor vProveedor;
	ctrlTabla tabla;
	vistaTabla vTabla;
	
	Conexion con = new Conexion();
	CBcadenas cadena = new CBcadenas();
	
	public ctrlProveedor() {
		vProveedor = new vistaProveedor();
	}
	
	public void inicializar() {
		vProveedor.setModal(true);
		
		vProveedor.btnAgregar.addActionListener(this);
		vProveedor.btnModificar.addActionListener(this);
		vProveedor.btnEliminar.addActionListener(this);
		vProveedor.btnSalir.addActionListener(this);
		vProveedor.btnTabla.addActionListener(this);
		
		vProveedor.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == vProveedor.btnAgregar) { //AGREGAR
			Proveedor prov = new Proveedor();
			
			prov.setId(vProveedor.txtId.getText().trim());
			prov.setProveedor(vProveedor.txtProveedor.getText().trim());
			if(prov.getId().isEmpty() || prov.getProveedor().isEmpty()) {
				JOptionPane.showMessageDialog(vProveedor, "Completa los campos");
			}else {
				if(existeProveedor(vProveedor.txtId.getText().trim()) == true) {
					insertaProveedor(prov);
					limpiar();
					JOptionPane.showMessageDialog(vProveedor, "Proveedor añadido");
				}else {
					JOptionPane.showMessageDialog(vProveedor, "ID ya existe");
				}
			}
		}else if(e.getSource() == vProveedor.btnModificar) { //MODIFICAR
			String cadenaBtn = vProveedor.btnModificar.getText();
			Proveedor prov = new Proveedor();
			
			switch(cadenaBtn) {
			case "Modificar":{
				if(existeProveedor(vProveedor.txtId.getText().trim()) == false) {
					accionMostrar(vProveedor.txtId.getText().trim());
					accionModificarBtnyTxt();
				}else {
					JOptionPane.showMessageDialog(vProveedor, "NO existe ID");
				}
			}break;
			case "Guardar":{
				prov.setId(vProveedor.txtId.getText().trim());
				prov.setProveedor(vProveedor.txtProveedor.getText().trim());
				if(prov.getId().isEmpty() || prov.getProveedor().isEmpty()) {
					JOptionPane.showMessageDialog(vProveedor, "Completa los Campos");
				}else {
					boolean modifico = modificarProveedor(prov);
					if(modifico == true) {
						JOptionPane.showMessageDialog(vProveedor, "Proveedor Actualizado");
						accionReset();
						limpiar();
					}else {
						JOptionPane.showMessageDialog(vProveedor, "Nipdo pa");
					}
				}
			}break;
			case "SI":{
				if(eliminarProducto(vProveedor.txtId.getText().trim())) {
					vProveedor.setBounds(100, 100, 420, 210);
					vProveedor.setLocationRelativeTo(null);
					accionReset();
					limpiar();
				}else {
					JOptionPane.showMessageDialog(vProveedor, "No se pudo eliminar");
				}
			}break;
			}
			
		}else if(e.getSource() == vProveedor.btnEliminar) { //ELIMINAR
			String cadenaBtn = vProveedor.btnEliminar.getText().trim();
			
			switch(cadenaBtn) {
			case "Eliminar":{
				if(vProveedor.txtId.getText().isEmpty()) {
					JOptionPane.showMessageDialog(vProveedor, "Ingresa ID");
				}else {
					if(existeProveedor(vProveedor.txtId.getText().trim()) == false) {
						accionMostrar(vProveedor.txtId.getText().trim());
						accionEliminarBtnyTxt();
						vProveedor.setBounds(100, 100, 420, 275);
						vProveedor.setLocationRelativeTo(null);
						
						vProveedor.labelSeguro.setText("Desea eliminar '" + vProveedor.txtProveedor.getText() + "' ?");
					}else {
						JOptionPane.showMessageDialog(vProveedor, "NO existe ID");
					}
				}
			}break;
			case "Cancelar":{
				vProveedor.setBounds(100, 100, 420, 210);
				vProveedor.setLocationRelativeTo(null);
				accionReset();
				limpiar();
			}break;
			}
		}else if(e.getSource() == vProveedor.btnSalir) {
			vProveedor.dispose();
		}else if(e.getSource() == vProveedor.btnTabla) {
			tabla = new ctrlTabla(3);
			tabla.inicializar();
		}
	}
	
	public void limpiar() {
		vProveedor.txtId.setText("");
		vProveedor.txtProveedor.setText("");
		vProveedor.labelSeguro.setText("");
	}
	
	public void accionModificarBtnyTxt() {
		vProveedor.btnAgregar.setVisible(false);
		vProveedor.btnSalir.setVisible(false);
		vProveedor.btnModificar.setText("Guardar");
		vProveedor.btnEliminar.setText("Cancelar");
		vProveedor.txtId.setEnabled(false);
	}
	
	public void accionEliminarBtnyTxt() {
		vProveedor.btnAgregar.setVisible(false);
		vProveedor.btnSalir.setVisible(false);
		vProveedor.btnModificar.setText("SI");
		vProveedor.btnEliminar.setText("Cancelar");
		vProveedor.txtId.setEnabled(false);
		vProveedor.txtProveedor.setEnabled(false);
	}
	
	public void accionReset() {
		vProveedor.btnAgregar.setVisible(true);
		vProveedor.btnSalir.setVisible(true);
		vProveedor.btnModificar.setText("Modificar");
		vProveedor.btnEliminar.setText("Eliminar");
		vProveedor.txtId.setEnabled(true);
		vProveedor.txtProveedor.setEnabled(true);
	}
	
	public boolean insertaProveedor(Proveedor prov) {
		Cbd base = new Cbd(con.getConnection());
		
		boolean realizo = true;
		String id, proveedor;
		try {
			id = prov.getId();
			proveedor = prov.getProveedor();
			
			realizo = base.insertar(cadena.getInsertarProveedor(id, proveedor));
		} catch (Exception e) {
			realizo = false;
			JOptionPane.showMessageDialog(vProveedor, "Error -> " + e);
		}
		return realizo;
	}
	public boolean modificarProveedor(Proveedor prov) {
		boolean realizo = true;
		Cbd base = new Cbd(con.getConnection());
		
		try {
			realizo = base.modificar(cadena.getUpdateProveedor(prov.getProveedor(), prov.getId()));
		} catch (Exception e) {
			realizo = false;
			JOptionPane.showMessageDialog(vProveedor, "Error --> " + e);
		}
		
		return realizo;
	}
	
	public boolean eliminarProducto(String id) {
		Cbd base = new Cbd(con.getConnection());
		
		boolean realizo = true;
		
		try {
			realizo = base.eliminar(cadena.getEliminarProveedor(id));
		} catch (Exception e) {
			JOptionPane.showMessageDialog(vProveedor, "Error al eliminar ->" + e);
		}
		return realizo;
	}
	
	public void accionMostrar(String id) {
		Cbd base = new Cbd(con.getConnection());
		
		try {
			ResultSet rs = base.consultaBD(cadena.getCproveedorId(id));
			
			if(rs.next()) {
				vProveedor.txtProveedor.setText(rs.getString(1));
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(vProveedor, "Error ---> " + e);
		}
	}
	
	public boolean existeProveedor(String id) {
		Cbd base = new Cbd(con.getConnection());
		
		boolean verificar = false;
		try {
			Lproveedor listaProveedor = new Lproveedor(base.consultaBD(cadena.getCproveedorId(id)));
			if(listaProveedor.getArray().size() == 0) {
				verificar = true;
			}else {
				verificar = false;
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(vProveedor, "Error ->" + e);
		}
		return verificar;
	}
}
