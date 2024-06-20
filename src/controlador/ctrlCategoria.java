package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import modelo.CBcadenas;
import modelo.Cbd;
import modelo.Conexion;
import modelo.Lcategoria;
import modelo.Categoria;
import vista.vistaCategoria;
import vista.vistaTabla;

public class ctrlCategoria implements ActionListener{
	vistaCategoria vCategoria;
	ctrlTabla tabla;
	vistaTabla vTabla;
	
	Conexion con = new Conexion();
	CBcadenas cadena = new CBcadenas();
	
	public ctrlCategoria() {
		vCategoria = new vistaCategoria();
	}
	
	public void inicializar() {
		vCategoria.setModal(true);
		
		vCategoria.btnAgregar.addActionListener(this);
		vCategoria.btnModificar.addActionListener(this);
		vCategoria.btnEliminar.addActionListener(this);
		vCategoria.btnSalir.addActionListener(this);
		vCategoria.btnTabla.addActionListener(this);
		
		vCategoria.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == vCategoria.btnAgregar) { //AGREGAR
			Categoria categ = new Categoria();
			
			categ.setId(vCategoria.txtId.getText().trim());
			categ.setCategoria(vCategoria.txtCategoria.getText().trim());
			if(categ.getId().isEmpty() || categ.getCategoria().isEmpty()) {
				JOptionPane.showMessageDialog(vCategoria, "Completa los campos");
			}else {
				if(existeCategoria(vCategoria.txtId.getText().trim()) == true) {
					insertaCategoria(categ);
					limpiar();
					JOptionPane.showMessageDialog(vCategoria, "Categoria añadido");
				}else {
					JOptionPane.showMessageDialog(vCategoria, "ID ya existe");
				}
			}
		}else if(e.getSource() == vCategoria.btnModificar) { //MODIFICAR
			String cadenaBtn = vCategoria.btnModificar.getText();
			Categoria categ = new Categoria();
			
			switch(cadenaBtn) {
			case "Modificar":{
				if(existeCategoria(vCategoria.txtId.getText().trim()) == false) {
					accionMostrar(vCategoria.txtId.getText().trim());
					accionModificarBtnyTxt();
				}else {
					JOptionPane.showMessageDialog(vCategoria, "NO existe ID");
				}
			}break;
			case "Guardar":{
				categ.setId(vCategoria.txtId.getText().trim());
				categ.setCategoria(vCategoria.txtCategoria.getText().trim());
				if(categ.getId().isEmpty() || categ.getCategoria().isEmpty()) {
					JOptionPane.showMessageDialog(vCategoria, "Completa los Campos");
				}else {
					boolean modifico = modificarCategoria(categ);
					if(modifico == true) {
						JOptionPane.showMessageDialog(vCategoria, "Categoria Actualizado");
						accionReset();
						limpiar();
					}else {
						JOptionPane.showMessageDialog(vCategoria, "Nipdo pa");
					}
				}
			}break;
			case "SI":{
				if(eliminarCategoria(vCategoria.txtId.getText().trim())) {
					vCategoria.setBounds(100, 100, 420, 210);
					vCategoria.setLocationRelativeTo(null);
					accionReset();
					limpiar();
				}else {
					JOptionPane.showMessageDialog(vCategoria, "No se pudo eliminar");
				}
			}break;
			}
			
		}else if(e.getSource() == vCategoria.btnEliminar) { //ELIMINAR
			String cadenaBtn = vCategoria.btnEliminar.getText().trim();
			
			switch(cadenaBtn) {
			case "Eliminar":{
				if(vCategoria.txtId.getText().isEmpty()) {
					JOptionPane.showMessageDialog(vCategoria, "Ingresa ID");
				}else {
					if(existeCategoria(vCategoria.txtId.getText().trim()) == false) {
						accionMostrar(vCategoria.txtId.getText().trim());
						accionEliminarBtnyTxt();
						vCategoria.setBounds(100, 100, 420, 275);
						vCategoria.setLocationRelativeTo(null);
						
						vCategoria.labelSeguro.setText("Desea eliminar '" + vCategoria.txtCategoria.getText() + "' ?");
					}else {
						JOptionPane.showMessageDialog(vCategoria, "NO existe ID");
					}
				}
			}break;
			case "Cancelar":{
				vCategoria.setBounds(100, 100, 420, 210);
				vCategoria.setLocationRelativeTo(null);
				accionReset();
				limpiar();
			}break;
			}
		}else if(e.getSource() == vCategoria.btnSalir) {
			vCategoria.dispose();
		}else if(e.getSource() == vCategoria.btnTabla) {
			tabla = new ctrlTabla(2);
			tabla.inicializar();
		}
	}
	
	public void limpiar() {
		vCategoria.txtId.setText("");
		vCategoria.txtCategoria.setText("");
		vCategoria.labelSeguro.setText("");
	}
	
	public void accionModificarBtnyTxt() {
		vCategoria.btnAgregar.setVisible(false);
		vCategoria.btnSalir.setVisible(false);
		vCategoria.btnModificar.setText("Guardar");
		vCategoria.btnEliminar.setText("Cancelar");
		vCategoria.txtId.setEnabled(false);
	}
	
	public void accionEliminarBtnyTxt() {
		vCategoria.btnAgregar.setVisible(false);
		vCategoria.btnSalir.setVisible(false);
		vCategoria.btnModificar.setText("SI");
		vCategoria.btnEliminar.setText("Cancelar");
		vCategoria.txtId.setEnabled(false);
		vCategoria.txtCategoria.setEnabled(false);
	}
	
	public void accionReset() {
		vCategoria.btnAgregar.setVisible(true);
		vCategoria.btnSalir.setVisible(true);
		vCategoria.btnModificar.setText("Modificar");
		vCategoria.btnEliminar.setText("Eliminar");
		vCategoria.txtId.setEnabled(true);
		vCategoria.txtCategoria.setEnabled(true);
	}
	
	public boolean insertaCategoria(Categoria categ) {
		Cbd base = new Cbd(con.getConnection());
		
		boolean realizo = true;
		String id, categoria;
		try {
			id = categ.getId();
			categoria = categ.getCategoria();
			
			realizo = base.insertar(cadena.getInsertarCategoria(id, categoria));
		} catch (Exception e) {
			realizo = false;
			JOptionPane.showMessageDialog(vCategoria, "Error -> " + e);
		}
		return realizo;
	}
	
	public boolean modificarCategoria(Categoria categ) {
		boolean realizo = true;
		Cbd base = new Cbd(con.getConnection());
		
		try {
			realizo = base.modificar(cadena.getUpdateCategoria(categ.getCategoria(), categ.getId()));
		} catch (Exception e) {
			realizo = false;
			JOptionPane.showMessageDialog(vCategoria, "Error --> " + e);
		}
		
		return realizo;
	}
	
	public boolean eliminarCategoria(String id) {
		Cbd base = new Cbd(con.getConnection());
		
		boolean realizo = true;
		
		try {
			realizo = base.eliminar(cadena.getEliminarCategoria(id));
		} catch (Exception e) {
			JOptionPane.showMessageDialog(vCategoria, "Error al eliminar ->" + e);
		}
		return realizo;
	}
	
	public void accionMostrar(String id) {
		Cbd base = new Cbd(con.getConnection());
		
		try {
			ResultSet rs = base.consultaBD(cadena.getCcategoriaId(id));
			
			if(rs.next()) {
				vCategoria.txtCategoria.setText(rs.getString(1));
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(vCategoria, "Error ---> " + e);
		}
	}
	
	public boolean existeCategoria(String id) {
		Cbd base = new Cbd(con.getConnection());
		
		boolean verificar = false;
		try {
			Lcategoria listaCategoria = new Lcategoria(base.consultaBD(cadena.getCcategoriaId(id)));
			if(listaCategoria.getArray().size() == 0) {
				verificar = true;
			}else {
				verificar = false;
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(vCategoria, "Error ->" + e);
		}
		return verificar;
	}
}
