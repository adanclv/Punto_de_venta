package modelo;
public class CBcadenas {
	
	private String CproductoCategoria = "SELECT Categoria.IdCategoria, Categorias.Categoria, Productos.IdProducto, Productos.Producto, Productos.Precio, Productos.Cantidad\r\n"
			+ "FROM Categorias INNER JOIN Productos ON Categorias.IdCategoria = Productos.IdCategoria; ";
	private String Ccategoria = "SELECT * FROM Categorias;";
	private String CcategoriaId = "SELECT * FROM Categorias WHERE IdCategoria = ";
	private String CategoriaconID = "SELECT * FROM Categorias WHERE Categoria = ";//mine
	private String Cproducto = "SELECT Productos.IdProducto, Productos.Producto, Productos.Precio, Productos.Cantidad, Categorias.Categoria FROM Categorias INNER JOIN Productos ON Categorias.IdCategoria = Productos.IdCategoria;";
	private String CproductoId = "SELECT * FROM Productos WHERE IdProducto = ";
	private String CproductoCantidad = "SELECT Cantidad FROM Productos WHERE IdProducto = '";
	private String Cproveedor = "SELECT * FROM Proveedores;";
	private String CproveedorId = "SELECT * FROM Proveedores WHERE IdProveedor = ";
	private String CproveedorconName = "SELECT * FROM Proveedores WHERE Proveedor = ";
	private String Calmacen = "SELECT Almacen.Fecha, Almacen.Hora, Proveedores.Proveedor, Almacen.IdProducto, Almacen.Cantidad, Almacen.PrecioCompra FROM Proveedores INNER JOIN Almacen ON Proveedores.IdProveedor = Almacen.IdProveedor;";
	private String Civa = "SELECT IVA FROM IVA WHERE IdProducto = ";
	private String insertarProducto = "INSERT into Productos(IdProducto,Producto,Precio,Cantidad,IdCategoria) VALUES(";
	private String insertarCategoria = "INSERT into Categorias(IdCategoria,Categoria) VALUES (";
	private String insertarProveedor = "INSERT into Proveedores(IdProveedor,Proveedor) VALUES (";
	private String insertarAlmacen = "INSERT into Almacen(Fecha,Hora,IdProveedor,IdProducto,Cantidad,PrecioCompra) VALUES(";
	private String insertarVenta = "INSERT into Ventas(Fecha,IdTicket,IdProducto,Cantidad,Total) VALUES(";
	private String insertarIVA = "INSERT into IVA(IdProducto,IVA) VALUES(";
	private String eliminarCategoria = "DELETE FROM Categorias WHERE IdCategoria = ";
	private String eliminarProducto = "DELETE FROM Productos WHERE IdProducto = ";
	private String eliminarProveedor = "DELETE FROM Proveedores WHERE IdProveedor = ";
	private String eliminarIVA = "DELETE FROM IVA WHERE IdProducto = ";
	private String updateProducto = "UPDATE Productos SET Producto=";
	private String updateProveedor = "UPDATE Proveedores SET Proveedor=";
	private String updateCantidad = "UPDATE Productos SET Cantidad=";
	private String updateCategoria = "UPDATE Categorias SET Categoria=";
	
	private String contador = "SELECT COUNT(*) FROM Productos WHERE Producto LIKE'";
	private String buscarProducto = "SELECT IdProducto, Producto, Precio FROM Productos WHERE Producto LIKE'";
	
	private String ticket = "SELECT IdTicket FROM Ventas WHERE Fecha = '";
	
	public String getCproductoCategoria() {
		return CproductoCategoria;
	}

	public String getCcategoria() {
		return Ccategoria;
	}
	
	public String getCalmacen() {
		return Calmacen;
	}
	
	public String getCiva(String idProducto) {
		return Civa + "'" + idProducto.trim() + "'";
	}

	public String getUpdateProducto(String Producto, String Precio, String Cantidad, String IdCategoria, String idProducto) {
		return updateProducto + "'" + Producto.trim() + "', Precio='" + Precio.trim() + "', Cantidad='" + Cantidad.trim() + "', IdCategoria='" + IdCategoria + "' WHERE IdProducto = '" + idProducto.trim() + "'";
	}
	
	public String getUpdateProveedor(String Proveedor, String idProveedor) {
		return updateProveedor + "'" + Proveedor.trim() + "' WHERE IdProveedor = '" + idProveedor.trim() + "'";
	}
	
	public String getUpdateCantidad(String cantidad, String idProducto) {
		return updateCantidad + "'" + cantidad.trim() + "' WHERE IdProducto = '" + idProducto.trim() + "'";
	}
	
	public String getUpdateCategoria(String categoria, String idCategoria) {
		return updateCategoria + "'" + categoria.trim() + "' WHERE IdCategoria = '" + idCategoria.trim() + "'";
	}
	
	public String getCcategoriaId(String idProducto) {
		return CcategoriaId + "'" + idProducto.trim() + "'";
	}
	
	public String getCproveedorId(String idProveedor) {
		return CproveedorId + "'" + idProveedor.trim() + "'";
	}

	public String getCproducto() {
		return Cproducto;
	}
	
	public String getEliminarProducto(String idProducto) {
		return eliminarProducto + "'" + idProducto.trim() + "'";
	}

	public String getEliminarProveedor(String idProveedor) {
		return eliminarProveedor + "'" + idProveedor.trim() + "'";
	}
	
	//Como dije en el principio, el String se autocompleta con los parametros que le mandemos
	public String getinsertarProducto(String IdProducto, String Producto, String Precio, String Cantidad,
			String IdCategoria) {
		return insertarProducto + "'" + IdProducto + "','" + Producto + "','" + Precio + "','" + Cantidad + "','"
				+ IdCategoria + "');";
	}

	public String getCproductoId(String idProducto) {
		return CproductoId + "'" + idProducto.trim() + "'";
	}
	
	public String getCproductoCantidad(String idProducto) {
		return CproductoCantidad + idProducto.trim() + "'";
	}

	public String getInsertarCategoria(String idCategoria, String categoria) {
		return insertarCategoria + "'" + idCategoria.trim() + "','" + categoria.trim() + "');";
	}
	
	public String getInsertarAlmacen(String fecha, String hora, String idProveedor, String idProducto, String cantidad, String precio) {
		return insertarAlmacen + "'" + fecha.trim() + "','" + hora.trim() + "','" + idProveedor.trim() + "','" + idProducto.trim() + "','" + cantidad.trim() + "','" + precio.trim() + "');";
	}
	
	public String getInsertarVenta(String fecha, String idTicket, String idProducto, String cantidad, String total) {
		return insertarVenta + "'" + fecha.trim() + "','" + idTicket.trim() + "','" + idProducto + "','" + cantidad.trim() + "','" + total.trim() + "');";
	}

	public String getEliminarCategoria(String idCategoria) {
		return eliminarCategoria + "'" + idCategoria.trim() + "'";
	}
	
	public String getEliminarIVA(String idProducto) {
		return eliminarIVA + "'" + idProducto.trim() + "'";
	}
	
	public String getCategoriaconID(String Categoria) {
		return CategoriaconID + "'" + Categoria.trim() + "'";
	}
	
	public String getCproveedorconName(String Proveedor) {
		return CproveedorconName + "'" + Proveedor.trim() + "'";
	}
	
	public String getCproveedor() {
		return Cproveedor;
	}
	
	public String getInsertarProveedor(String IdProveedor, String Proveedor) {
		return insertarProveedor + "'" + IdProveedor + "','" + Proveedor + "');";
	}
	
	public String getInsertarIVA(String idProducto, String iva) {
		return insertarIVA + "'" + idProducto.trim() + "','" + iva.trim() + "');";
	}
	
	///Buscar Producto
	public String getContador(String producto) {
		return contador + producto + "%'";
	}
	
	public String getBuscarProducto(String producto) {
		return buscarProducto + producto + "%'";
	}
	
	public String getTicket(String fecha) {
		return ticket + fecha + "'";
	}
}