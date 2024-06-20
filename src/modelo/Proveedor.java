package modelo;

public class Proveedor {
	private String id;
	private String proveedor;
	
	public Proveedor(String id, String proveedor) {
		super();
		this.id = id;
		this.proveedor = proveedor;
	}
	
	public Proveedor() {
		super();
		this.id = "";
		this.proveedor = "";
	}
	
	public Proveedor(String[] datos) {
		this.id = datos[0];
		this.proveedor = datos[1];
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProveedor() {
		return proveedor;
	}

	public void setProveedor(String proveedor) {
		this.proveedor = proveedor;
	}
	
	public String getLineaArchivo() {
		return this.getId() + "," + this.getProveedor();
	}
	
	public Object[] getDatosProveedor() {
		Object[] datos = new Object[1];
		datos[0] = this.proveedor;
		return datos;
	}
}
