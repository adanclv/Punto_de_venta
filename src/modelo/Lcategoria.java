package modelo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

public class Lcategoria {
	ArrayList<Categoria> lCateg;
	
	public Lcategoria() {
		this.lCateg = new ArrayList<Categoria>();
	}
	
	public Lcategoria(ResultSet datos) throws SQLException{
		this.lCateg = new ArrayList<Categoria>();
		while(datos.next()) {
			Categoria nodo = new Categoria(datos.getString(1), datos.getString(2));
			lCateg.add(nodo);
		}
	}
	
	public Lcategoria(ArrayList<String[]> datos) {
		this.lCateg = new ArrayList<Categoria>();
		for(String[] info : datos) {
			Categoria nodo = new Categoria(info);
			lCateg.add(nodo);
		}
	}
	
	public ArrayList<Categoria> getArray(){
		return this.lCateg;
	}
	
	public int getPosCategoria(String id) {
		int pos = 0;
		for (Categoria info : this.lCateg) {
			if (info.getId().compareTo(id) == 0) {
				break;
			}
			pos++;
		}
		return pos;
	}
	
	public boolean existeCategoria(String id) {
		for (Categoria info : this.lCateg) {
			if (info.getId().compareTo(id) == 0)
				return true;
		}
		return false;
	}
	
	public void insertar(Categoria nodo) {
		this.lCateg.add(nodo);
	}

	public void eliminar(String id) {
		this.lCateg.remove(getPosCategoria(id));
	}

	public void modificar(String id, Categoria datos) {
		Categoria info = this.lCateg.get(getPosCategoria(id));
		info.setCategoria(datos.getCategoria());
		this.lCateg.set(getPosCategoria(id), info);
	}

	public String guardar() {
		String archivo = "";
		for (Categoria info : this.lCateg) {
			archivo += info.getLineaArchivo() + "\n";
		}
		return archivo;
	}
	
	public Categoria getCategoria(String id) {
		for (Categoria info : this.lCateg) {
			if (info.getId().compareTo(id)==0) {
				return info;
			}
		}
		return null;
	}

	public DefaultTableModel getModelCategoria() {
		String[] columnas = { "ID", "Categoria" };
		Object[][] datos = new Object[this.lCateg.size()][2];
		int pos = 0;
		for (Categoria categ : this.lCateg) {
			datos[pos][0] = categ.getCategoria();
			datos[pos][1] = categ.getId();
			pos++;
		}
		return new DefaultTableModel(datos, columnas);
	}
	
	public DefaultTableModel getModelFiltrado(String categoria) {
		DefaultTableModel modelo = new DefaultTableModel();
		modelo.addColumn("Codigo");
		modelo.addColumn("Categoria");
		Object[] datos = new Object[2];
		for (Categoria categ : this.lCateg) {
			if (categ.getCategoria().startsWith(categoria)) {
				datos[0] = categ.getId();
				datos[1] = categ.getCategoria();
				modelo.addRow(datos);
			}
		}
		return modelo;
	}
	
	public String buscarIdCateg(String categ) {
		for (Categoria info : this.lCateg) {
			if (info.getCategoria().compareTo(categ)==0) {
				return info.getId();
			}
		}
		return null;
	}
}
