package modelo;

public class PuntoVenta {
	private String Fecha;
	private String IdTicket;
	private String IdProducto;
	private String Producto;
	private String Cantidad;
	private String PrecioUnitario;
	private String Iva;
	private String SubTotal;
	private String Total;
	
	public PuntoVenta() {
		super();
		//Fecha = "";
		//IdTicket = "";
		IdProducto = "";
		Producto = "";
		Cantidad = "";
		PrecioUnitario = "";
		Iva = "";
		SubTotal = "";
		Total = "";
	}
	
	public PuntoVenta(String idProducto, String producto, String cantidad, String precioUnitario,
			String iva, String subTotal, String total) {
		super();
		//Fecha = fecha;
		//IdTicket = idTicket;
		IdProducto = idProducto;
		Producto = producto;
		Cantidad = cantidad;
		PrecioUnitario = precioUnitario;
		Iva = iva;
		SubTotal = subTotal;
		Total = total;
	}
	
	public PuntoVenta(String fecha,String idTicket, String idProducto, String producto, String cantidad, String total) {
		super();
		Fecha = fecha;
		IdTicket = idTicket;
		IdProducto = idProducto;
		Producto = producto;
		Cantidad = cantidad;
		Total = total;
	}
	
	public String getFecha() {
		return Fecha;
	}
	
	public void setFecha(String fecha) {
		Fecha = fecha;
	}
	
	public String getIdTicket() {
		return IdTicket;
	}
	
	public void setIdTicket(String idTicket) {
		IdTicket = idTicket;
	}
	
	public String getIdProducto() {
		return IdProducto;
	}
	
	public void setIdProducto(String idProducto) {
		IdProducto = idProducto;
	}
	
	public String getCantidad() {
		return Cantidad;
	}
	
	public void setCantidad(String cantidad) {
		Cantidad = cantidad;
	}
	
	public String getPrecioUnitario() {
		return PrecioUnitario;
	}
	
	public void setPrecioUnitario(String precioUnitario) {
		PrecioUnitario = precioUnitario;
	}
	
	public String getIva() {
		return Iva;
	}
	
	public void setIva(String iva) {
		Iva = iva;
	}
	
	public String getSubTotal() {
		return SubTotal;
	}
	
	public void setSubTotal(String subTotal) {
		SubTotal = subTotal;
	}
	
	public String getTotal() {
		return Total;
	}
	
	public void setTotal(String total) {
		Total = total;
	}

	public String getProducto() {
		return Producto;
	}

	public void setProducto(String producto) {
		Producto = producto;
	}
}
