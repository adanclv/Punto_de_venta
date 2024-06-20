package modelo;

public class Producto {
	private String id;
	private String descripcion;
	private String precio;
	private String cantidad;
	private String categoria;
	
	public Producto(String id, String descripcion, String precio, String cantidad, String categoria) {
		super();
		this.id = id;
		this.descripcion = descripcion;
		this.precio = precio;
		this.categoria = categoria;
		this.cantidad = cantidad;
	}
	
	public Producto() {
		super();
		this.id = "";
		this.descripcion = "";
		this.precio = "";
		this.categoria = "";
		this.cantidad = "";
	}
	
	public Producto(String[] datos) {
		this.id = datos[0];
		this.descripcion = datos[1];
		this.precio = datos[2];
		this.categoria = datos[3];
		this.cantidad = datos[4];		
	}	
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getPrecio() {
		return precio;
	}

	public void setPrecio(String precio) {
		this.precio = precio;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getCantidad() {
		return cantidad;
	}

	public void setCantidad(String cantidad) {
		this.cantidad = cantidad;
	}

	public String getLineaArchivo() {
		return this.getId() + "," + this.getDescripcion() + "," + this.getPrecio() + "," + this.getCategoria() + "," + this.getCantidad();
	}
	
	public Object[] getDatosProducto() {
		Object[] datos = new Object[2];
		datos[0] = this.descripcion;
		datos[1] = this.precio;
		return datos;
	}	
}
