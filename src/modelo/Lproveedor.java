package modelo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

public class Lproveedor {
	ArrayList<Proveedor> lProv;
	
	public Lproveedor() {
		this.lProv = new ArrayList<Proveedor>();
	}
	
	public Lproveedor(ResultSet datos) throws SQLException{
		this.lProv = new ArrayList<Proveedor>();
		while(datos.next()) {
			Proveedor nodo = new Proveedor(datos.getString(1), datos.getString(2));
			lProv.add(nodo);
		}
	}
	
	public Lproveedor(ArrayList<String[]> datos) {
		this.lProv = new ArrayList<Proveedor>();
		for(String[] info : datos) {
			Proveedor nodo = new Proveedor(info);
			lProv.add(nodo);
		}
	}
	
	public ArrayList<Proveedor> getArray(){
		return this.lProv;
	}
	
	public int getPosProveedor(String id) {
		int pos = 0;
		for (Proveedor info : this.lProv) {
			if (info.getId().compareTo(id) == 0) {
				break;
			}
			pos++;
		}
		return pos;
	}
	
	public boolean existeProveedor(String id) {
		for (Proveedor info : this.lProv) {
			if (info.getId().compareTo(id) == 0)
				return true;
		}
		return false;
	}
	
	public void insertar(Proveedor nodo) {
		this.lProv.add(nodo);
	}

	public void eliminar(String id) {
		this.lProv.remove(getPosProveedor(id));
	}

	public void modificar(String id, Proveedor datos) {
		Proveedor info = this.lProv.get(getPosProveedor(id));
		info.setProveedor(datos.getProveedor());
		this.lProv.set(getPosProveedor(id), info);
	}

	public String guardar() {
		String archivo = "";
		for (Proveedor info : this.lProv) {
			archivo += info.getLineaArchivo() + "\n";
		}
		return archivo;
	}
	
	public Proveedor getProveedor(String id) {
		for (Proveedor info : this.lProv) {
			if (info.getId().compareTo(id)==0) {
				return info;
			}
		}
		return null;
	}

	public DefaultTableModel getModelProveedores() {
		String[] columnas = { "ID", "Proveedor" };
		Object[][] datos = new Object[this.lProv.size()][2];
		int pos = 0;
		for (Proveedor prov : this.lProv) {
			datos[pos][0] = prov.getProveedor();
			datos[pos][1] = prov.getId();
			pos++;
		}
		return new DefaultTableModel(datos, columnas);
	}
	
	public DefaultTableModel getModelFiltrado(String provedor) {
		DefaultTableModel modelo = new DefaultTableModel();
		modelo.addColumn("Codigo");
		modelo.addColumn("Proveedor");
		Object[] datos = new Object[2];
		for (Proveedor prov : this.lProv) {
			if (prov.getProveedor().startsWith(provedor)) {
				datos[0] = prov.getId();
				datos[1] = prov.getProveedor();
				modelo.addRow(datos);
			}
		}
		return modelo;
	}
	
	public String buscarIdProv(String provedor) {
		for (Proveedor info : this.lProv) {
			if (info.getProveedor().compareTo(provedor)==0) {
				return info.getId();
			}
		}
		return null;
	}
}
