package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import modelo.CBcadenas;
import modelo.Cbd;
import modelo.Conexion;
import modelo.Lproductos;
import modelo.PuntoVenta;
import vista.vistaMenu;

public class ctrlMenu implements ActionListener, KeyListener, MouseListener {
	vistaMenu vMenu;
	ctrlProductos productos;
	ctrlCategoria categoria;
	ctrlProveedor proveedor;
	ctrlAlmacen almacen;
	ctrlPagar pagar;
	
	ArrayList<PuntoVenta> listaVenta = new ArrayList<>();
	private PuntoVenta venta;
	private DefaultTableModel modeloDatosVenta;
	
	private String IdProducto = "";
	private String Producto = "";
	private int cantidadBD = 0;
	private double PrecioUnitario = 0.0;
	private int porcentajeIva = 0;
	private int cantidad = 0;
	private double SubTotal = 0.0;
	private double iva = 0.0;
	private double Total = 0.0;
	
	private double SubTotalGral = 0.0;
	private double ivaGral = 0.0;
	private double TotalGral = 0.0;
	
	private int idArray = 0;
	private int aux = 0;
	
	Conexion con = new Conexion();
	CBcadenas cadena = new CBcadenas();
	
	public ctrlMenu() {
		vMenu = new vistaMenu();
	}
	
	public void inicializar() {
		vMenu.btnProductos.addActionListener(this);
		vMenu.btnVenta.addActionListener(this);
		vMenu.btnCategoria.addActionListener(this);
		vMenu.btnProveedor.addActionListener(this);
		vMenu.btnAlmacen.addActionListener(this);		
		vMenu.btnAgregar.addActionListener(this);
		vMenu.btnSalir.addActionListener(this);
		
		vMenu.labelFecha.setText(this.Fecha());
		
		vMenu.txtBuscar.addKeyListener(this);
		vMenu.tablaVenta.addMouseListener(this);
		
		vMenu.labelTicket.setText(this.ultimoTicket());
		
		vMenu.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == vMenu.btnProductos) { //PRODUCTOS
			productos = new ctrlProductos();
			productos.inicializar();
		}else if(e.getSource() == vMenu.btnCategoria) { //CATEGORIAS
			categoria = new ctrlCategoria();
			categoria.inicializar();
		}else if(e.getSource() == vMenu.btnProveedor) {//PROVEEDORES
			proveedor = new ctrlProveedor();
			proveedor.inicializar();
		}else if(e.getSource() == vMenu.btnAlmacen) { //ALMACEN
			almacen = new ctrlAlmacen();
			almacen.inicializar();
		}else if(e.getSource() == vMenu.btnVenta) { //REALIZAR VENTA
			PuntoVenta puntoVenta = new PuntoVenta();
			String idTick = this.ultimoTicket();
			pagar = new ctrlPagar(listaVenta, puntoVenta, idTick);
			
			if(listaVenta.size() > 0) {
				pagar.inicializar();
				if(!idTick.equals(this.ultimoTicket())) {
					vMenu.labelTicket.setText(this.ultimoTicket());
					listaVenta.clear();
					this.inicializarTablaProductos();
					this.listaTablaVenta();
					this.calcularTotal();
					
				}else {
				}
			}else {
				JOptionPane.showMessageDialog(vMenu, "Agrega Productos");
			}
		}else if(e.getSource() == vMenu.btnAgregar) { //AGREGAR PRODUCTO A LA VENTA
			if(vMenu.txtIdProducto.getText().isEmpty() == true) {
				JOptionPane.showMessageDialog(vMenu, "Ingresa ID");
			}else {
				if(existeProducto(vMenu.txtIdProducto.getText().trim()) == false) {
					if((int) vMenu.spinner.getValue() > 0) {
						cantidad = (int)vMenu.spinner.getValue();
						this.DatosProducto();
						if(cantidad <= cantidadBD) {
							SubTotal = PrecioUnitario * cantidad;
							Total = SubTotal + iva;
							
							SubTotal = (double)Math.round(SubTotal * 100) / 100;
							iva = (double)Math.round(iva * 100) / 100;
							Total = (double)Math.round(Total * 100) / 100;
							
							venta = new PuntoVenta(IdProducto, Producto, String.valueOf(vMenu.spinner.getValue()), String.valueOf(PrecioUnitario), String.valueOf(iva), String.valueOf(SubTotal), String.valueOf(Total));
							listaVenta.add(venta);
							this.limpiar();
							
							this.calcularTotal();
						}else {
							JOptionPane.showMessageDialog(vMenu, "La Cantidad supera el Stock");
						}
					}else {
						JOptionPane.showMessageDialog(vMenu, "Cantidad invalida");
					}
				}else {
					JOptionPane.showMessageDialog(vMenu, "NO existe ID");
				}
				this.inicializarTablaProductos();
				this.listaTablaVenta();
			}
		}else if(e.getSource() == vMenu.btnSalir) {
			vMenu.dispose();
		}
	}
	
	public void buscarProducto(String producto) {
		String[][] M_datos;
		String[] Titulos = {"Id", "Producto", "Precio"};
		DefaultTableModel dtm_datos = new DefaultTableModel();
		TableRowSorter<TableModel> trs;
		int contador = 0;
		Connection con = new Conexion().getConnection();
		CBcadenas cadena = new CBcadenas();
		Statement st, st_count;
		ResultSet rs, rs_count;
		
		try {
			st_count = con.createStatement();
			rs_count = st_count.executeQuery(cadena.getContador(producto));
			if(rs_count.next()) {
				contador = rs_count.getInt(1);
			}
			
			st = con.createStatement();
			rs = st.executeQuery(cadena.getBuscarProducto(producto));
			
			int count = 0;
			M_datos = new String[contador][3];
			while(rs.next()) {
				M_datos[count][0] = rs.getString("IdProducto");
				M_datos[count][1] = rs.getString("Producto");
				M_datos[count][2] = rs.getString("Precio");
				count++;
			}
			dtm_datos = new DefaultTableModel(M_datos, Titulos);
			vMenu.tablaBuscar.setModel(dtm_datos);
			trs = new TableRowSorter<>(dtm_datos);
			vMenu.tablaBuscar.setRowSorter(trs);
		} catch (SQLException e) {
			System.out.println("ERROR -> " + e);
		}
	}
	
	public String Fecha() {
		String fechaActual = "";
		Date date = new Date();
		fechaActual = new SimpleDateFormat("dd/MM/yyyy").format(date);
		
		return fechaActual;
	}
	
	private void inicializarTablaProductos() {
		modeloDatosVenta = new DefaultTableModel();
		modeloDatosVenta.addColumn("No.");
		modeloDatosVenta.addColumn("Id");
		modeloDatosVenta.addColumn("Producto");
		modeloDatosVenta.addColumn("Cantidad");
		modeloDatosVenta.addColumn("IVA");
		modeloDatosVenta.addColumn("Total");
		//agregar los datos a la tabla
		vMenu.tablaVenta.setModel(modeloDatosVenta);	
	}
	
	//metodo para presentar la info de la tabla
	private void listaTablaVenta() {
		this.modeloDatosVenta.setRowCount(listaVenta.size());
		for(int i = 0; i < listaVenta.size(); i++) {
			this.modeloDatosVenta.setValueAt(i + 1, i, 0);
			this.modeloDatosVenta.setValueAt(listaVenta.get(i).getIdProducto(), i, 1);
			this.modeloDatosVenta.setValueAt(listaVenta.get(i).getProducto(), i, 2);
			this.modeloDatosVenta.setValueAt(listaVenta.get(i).getCantidad(), i, 3);
			this.modeloDatosVenta.setValueAt(listaVenta.get(i).getIva(), i, 4);
			this.modeloDatosVenta.setValueAt(listaVenta.get(i).getTotal(), i, 5);
		}
		vMenu.tablaVenta.setModel(modeloDatosVenta);
	}
	
	private void DatosProducto() {
		Cbd base = new Cbd(con.getConnection());
		try {
			ResultSet rs = base.consultaBD(cadena.getCproductoId(vMenu.txtIdProducto.getText()));
			
			while(rs.next()) {
				IdProducto = rs.getString("IdProducto");
				Producto = rs.getString("Producto");
				PrecioUnitario = Double.parseDouble(rs.getString("Precio"));
				cantidadBD = Integer.parseInt(rs.getString("Cantidad"));
				porcentajeIva = this.consultaIVA(IdProducto);
				
				this.calcularIva(PrecioUnitario, porcentajeIva);
			}
		} catch (SQLException e) {
			System.out.println("Error al obtener datos -> " + e);
		}
	}
	
	private double calcularIva(double precio, int ivaP) {
		System.out.println(ivaP);
		switch(ivaP) {
		case 0:
			iva = 0.0;
			break;
		case 16:
			iva = (precio * cantidad) * 0.16;
			break;
		default:
			break;
		}
		
		return iva;
	}
	
	public void calcularTotal() {
		SubTotalGral = 0;
		ivaGral = 0;
		TotalGral = 0;
		
		for(PuntoVenta elemento : listaVenta) {
			SubTotalGral += Double.parseDouble(elemento.getSubTotal());
			ivaGral += Double.parseDouble(elemento.getIva());
			TotalGral += Double.parseDouble(elemento.getTotal());
		}
		
		SubTotalGral = (double)Math.round(SubTotalGral * 100) / 100;
		ivaGral = (double)Math.round(ivaGral * 100) / 100;
		TotalGral = (double)Math.round(TotalGral * 100) / 100;
		
		vMenu.labelSubTotal.setText(String.valueOf(SubTotalGral));
		vMenu.labelIva.setText(String.valueOf(ivaGral));
		vMenu.labelTotal.setText(String.valueOf(TotalGral));
	}
	
	public int consultaIVA(String id) {
		Cbd base = new Cbd(con.getConnection());
		int pIva = 0;
		try {
			ResultSet rs = base.consultaBD(cadena.getCiva(id));
			
			if(rs.next()) {
				pIva = Integer.parseInt(rs.getString(1));
			}
			return pIva;
		} catch (SQLException e) {
			System.out.println("Error -> " + e);
		}
		return pIva;
	}

	@Override
	public void keyTyped(KeyEvent e) {		
	}

	@Override
	public void keyPressed(KeyEvent e) {		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int valor = 0;
		int cont = 0;
		String aux = "" + vMenu.txtBuscar.getText(); 
		buscarProducto(aux);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getSource() == vMenu.tablaVenta) {
			int fila_point = vMenu.tablaVenta.rowAtPoint(e.getPoint());
			int columna_point = 0;
			try {
				if(fila_point > -1) {
					idArray = (int)modeloDatosVenta.getValueAt(fila_point, columna_point);
				}
				int opcion = JOptionPane.showConfirmDialog(vMenu, "Eliminar Producto?");
				switch(opcion) {
				case 0:
					listaVenta.remove(idArray - 1);
					this.calcularTotal();
					this.listaTablaVenta();
					break;
				case 1:
					break;
				default:
					break;
				}
			} catch (Exception e2) {
				System.out.println("Error -> " + e);
			}
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}
	
	public void limpiar() {
		vMenu.txtIdProducto.setText("");
		vMenu.spinner.setValue((int) 1);
		vMenu.labelSubTotal.setText("");
		vMenu.labelIva.setText("");
		vMenu.labelTotal.setText("");
	}
	
	public boolean existeProducto(String id) {
		Cbd base = new Cbd(con.getConnection());
		boolean verificar = false;
		
		try {
			Lproductos listaProductos = new Lproductos(base.consultaBD(cadena.getCproductoId(id)));
			if (listaProductos.getArray().size() == 0) {
				verificar = true;
			}else {
				verificar = false;
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(vMenu, "Error ->" + e);
		}
		return verificar;
	}
	
	public String ultimoTicket() {
		String idTicket = "000";
		String fecha = this.Fecha();
		int ticket = 0;

		Cbd base = new Cbd(con.getConnection());
		try {
			ResultSet rs;
			rs = base.consultaBD(cadena.getTicket(fecha));
			
			while(rs.next()) {
				idTicket = rs.getString(1);
			}
			if(idTicket.isEmpty()) {
				idTicket = "001";
			}else {
				ticket = Integer.parseInt(idTicket) + 1;
				if (ticket < 10) {
					idTicket = "00" + String.valueOf(ticket);
				}else {
					if((ticket > 9) && (ticket) < 100)
						idTicket = "0" + String.valueOf(ticket);
					else
						idTicket = String.valueOf(ticket);
				}
			}
		} catch (SQLException e) {
			System.out.println("Error Ticket -> " + e);
		}
		return idTicket;
	}
}
