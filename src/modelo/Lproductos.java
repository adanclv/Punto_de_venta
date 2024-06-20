package modelo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

public class Lproductos {
	ArrayList<Producto> lProd;

	public Lproductos() {
		this.lProd = new ArrayList<Producto>();
	}
	
	//Este es el constructor agregado en clase, recibe un ResultSet y va fila por fila obteniendo los datos de fila y añadiendolos al ArrayList
	//En caso de que el ResultSet este vacio, el ArrayList quedara vacio
	public Lproductos(ResultSet datos) throws SQLException {
		this.lProd = new ArrayList<Producto>();
		while (datos.next()) {
			Producto nodo = new Producto(datos.getString(1), datos.getString(2), datos.getString(3), datos.getString(4), datos.getString(5));
			lProd.add(nodo);
		}
	}

	public Lproductos(ArrayList<String[]> datos) {
		this.lProd = new ArrayList<Producto>();
		for (String[] info : datos) {
			Producto nodo = new Producto(info);
			lProd.add(nodo);
		}
	}

	public ArrayList<Producto> getArray() {
		return this.lProd;
	}
	
	public int getPosProducto(String id) {
		int pos = 0;
		for (Producto info : this.lProd) {
			if (info.getId().compareTo(id) == 0) {
				break;
			}
			pos++;
		}
		return pos;
	}
	
	public boolean existeProducto(String id) {
		for (Producto info : this.lProd) {
			if (info.getId().compareTo(id) == 0)
				return true;
		}
		return false;
	}

	public void insertar(Producto nodo) {
		this.lProd.add(nodo);
	}

	public void eliminar(String id) {
		this.lProd.remove(getPosProducto(id));
	}

	public void modificar(String id, Producto datos) {
		Producto info = this.lProd.get(getPosProducto(id));
		info.setDescripcion(datos.getDescripcion());
		info.setPrecio(datos.getPrecio());
		info.setCategoria(datos.getCategoria());
		info.setCantidad(datos.getCantidad());
		this.lProd.set(getPosProducto(id), info);
	}

	public String guardar() {
		String archivo = "";
		for (Producto info : this.lProd) {
			archivo += info.getLineaArchivo() + "\n";
		}
		return archivo;
	}
	
	public Producto getProducto(String id) {
		for (Producto info : this.lProd) {
			if (info.getId().compareTo(id)==0) {
				return info;
			}
		}
		return null;
	}

	public DefaultTableModel getModelProductos() {
		String[] columnas = { "Codigo", "Descripcion", "Precio", "Cantidad", "Categoria" };
		Object[][] datos = new Object[this.lProd.size()][5];
		int pos = 0;
		for (Producto prod : this.lProd) {
			datos[pos][0] = prod.getId();
			datos[pos][1] = prod.getDescripcion();
			datos[pos][2] = prod.getPrecio();
			datos[pos][3] = prod.getCantidad();
			datos[pos][4] = prod.getCategoria();
			pos++;
		}
		return new DefaultTableModel(datos, columnas);
	}
	
	public DefaultTableModel getModelFiltrado(String desc) {
		DefaultTableModel modelo = new DefaultTableModel();
		modelo.addColumn("Codigo");
		modelo.addColumn("Descripcion");
		modelo.addColumn("Precio");
		modelo.addColumn("Categoria");
		modelo.addColumn("Cantidad");
		Object[] datos = new Object[5];
		for (Producto prod : this.lProd) {
			if (prod.getDescripcion().startsWith(desc)) {
				datos[0] = prod.getId();
				datos[1] = prod.getDescripcion();
				datos[2] = prod.getPrecio();
				datos[3] = prod.getCategoria();
				datos[4] = prod.getCantidad();
				modelo.addRow(datos);
			}
		}
		return modelo;
	}
	
	public String buscarIdProd(String desc) {
		for (Producto info : this.lProd) {
			if (info.getDescripcion().compareTo(desc)==0) {
				return info.getId();
			}
		}
		return null;
	}

	public void vender(String id, String cant) { 
		Producto info = lProd.get(getPosProducto(id));
		info.setCantidad(String.valueOf(Integer.parseInt(info.getCantidad()) - Integer.parseInt(cant)));
		modificar(id, info);
	}

	public void agregar(String id, String cant) { 
		Producto info = lProd.get(getPosProducto(id));
		info.setCantidad(String.valueOf(Integer.parseInt(info.getCantidad()) + Integer.parseInt(cant)));
		modificar(id, info);
	}
}
