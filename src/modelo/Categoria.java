package modelo;

public class Categoria {
	private String id;
	private String categoria;
	
	public Categoria(String id, String categoria) {
		super();
		this.id = id;
		this.categoria = categoria;
	}
	
	public Categoria() {
		super();
		this.id = "";
		this.categoria = "";
	}
	
	public Categoria(String[] datos) {
		this.id = datos[0];
		this.categoria = datos[1];
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	
	public String getLineaArchivo() {
		return this.getId() + "," + this.getCategoria();
	}
	
	public Object[] getDatosCategoria() {
		Object[] datos = new Object[1];
		datos[0] = this.categoria;
		return datos;
	}
}
