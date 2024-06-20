package vista;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.CardLayout;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JSpinner;
import javax.swing.JScrollPane;
import javax.swing.border.EtchedBorder;
import javax.swing.JTable;
import javax.swing.ImageIcon;
import java.awt.GridLayout;
import java.awt.Color;
import javax.swing.border.MatteBorder;
import javax.swing.UIManager;
import java.awt.SystemColor;
import javax.swing.border.LineBorder;
import java.awt.Toolkit;

public class vistaMenu extends JFrame {

	private JPanel panelPrincipal, panel1, panel2, panel3, panel4, panel5;
	public JTextField txtBuscar ,txtIdProducto;
	public static JTable tablaVenta; 
	public JTable tablaBuscar;
	private JScrollPane scrollPane, scrollPane_1;
	public JButton btnProductos, btnCategoria, btnProveedor, btnAlmacen, btnAgregar, btnVenta, btnSalir;
	private JLabel label1, label2, label3, label4, label5, label6, label7, label8, label9, label10, label11, label12, label13,label14;
	public JLabel labelSubTotal, labelIva, labelFecha;
	public static JLabel labelTicket, labelTotal;
	public JSpinner spinner;

	public vistaMenu() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(vistaMenu.class.getResource("/Img/carrito32.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1500, 800);
		panelPrincipal = new JPanel();
		panelPrincipal.setBackground(new Color(211, 211, 211));
		panelPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panelPrincipal);
		panelPrincipal.setLayout(null);
		
		panel1 = new JPanel();
		panel1.setBackground(new Color(211, 211, 211));
		panel1.setBounds(15, 88, 224, 550);
		panelPrincipal.add(panel1);
		panel1.setLayout(new GridLayout(0, 1, 0, 0));
		
		btnProductos = new JButton("Productos");
		btnProductos.setForeground(Color.BLACK);
		btnProductos.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		btnProductos.setBackground(new Color(153, 153, 255));
		btnProductos.setFont(new Font("Tw Cen MT", Font.PLAIN, 25));
		btnProductos.setIcon(new ImageIcon(vistaMenu.class.getResource("/Img/productos64.png")));
		panel1.add(btnProductos);
		
		label2 = new JLabel("");
		panel1.add(label2);
		
		btnCategoria = new JButton("Categoria");
		btnCategoria.setForeground(Color.BLACK);
		btnCategoria.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		btnCategoria.setBackground(new Color(153, 153, 255));
		btnCategoria.setFont(new Font("Tw Cen MT", Font.PLAIN, 25));
		btnCategoria.setIcon(new ImageIcon(vistaMenu.class.getResource("/Img/categoria64.png")));
		panel1.add(btnCategoria);
		
		label3 = new JLabel("");
		panel1.add(label3);
		
		btnProveedor = new JButton("Proveedor");
		btnProveedor.setForeground(Color.BLACK);
		btnProveedor.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		btnProveedor.setBackground(new Color(153, 153, 255));
		btnProveedor.setFont(new Font("Tw Cen MT", Font.PLAIN, 25));
		btnProveedor.setIcon(new ImageIcon(vistaMenu.class.getResource("/Img/proveedor64.png")));
		panel1.add(btnProveedor);
		
		label4 = new JLabel("");
		panel1.add(label4);
		
		btnAlmacen = new JButton("Almacen");
		btnAlmacen.setForeground(Color.BLACK);
		btnAlmacen.setBackground(new Color(153, 153, 255));
		btnAlmacen.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		btnAlmacen.setFont(new Font("Tw Cen MT", Font.PLAIN, 25));
		btnAlmacen.setIcon(new ImageIcon(vistaMenu.class.getResource("/Img/almacen64.png")));
		panel1.add(btnAlmacen);
		
		label1 = new JLabel("CCE-Company");
		label1.setHorizontalAlignment(SwingConstants.CENTER);
		label1.setFont(new Font("DialogInput", Font.ITALIC, 25));
		label1.setBounds(15, 10, 224, 59);
		panelPrincipal.add(label1);
		
		panel2 = new JPanel();
		panel2.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel2.setBounds(264, 48, 590, 658);
		panelPrincipal.add(panel2);
		panel2.setLayout(null);
		
		label5 = new JLabel("Punto de Venta");
		label5.setFont(new Font("Tw Cen MT Condensed", Font.PLAIN, 25));
		label5.setBounds(10, 10, 192, 38);
		panel2.add(label5);
		
		txtIdProducto = new JTextField();
		txtIdProducto.setBorder(new LineBorder(new Color(171, 173, 179), 1, true));
		txtIdProducto.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtIdProducto.setBounds(87, 74, 115, 25);
		panel2.add(txtIdProducto);
		txtIdProducto.setColumns(10);
		
		label6 = new JLabel("ID:");
		label6.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 18));
		label6.setHorizontalAlignment(SwingConstants.RIGHT);
		label6.setBounds(22, 74, 55, 38);
		panel2.add(label6);
		
		btnAgregar = new JButton("Agregar");
		btnAgregar.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		btnAgregar.setBackground(new Color(153, 255, 153));
		btnAgregar.setFont(new Font("Tw Cen MT", Font.PLAIN, 24));
		btnAgregar.setIcon(new ImageIcon(vistaMenu.class.getResource("/Img/agregar32.png")));
		btnAgregar.setBounds(234, 142, 141, 67);
		panel2.add(btnAgregar);
		
		label7 = new JLabel("Cantidad:");
		label7.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 18));
		label7.setHorizontalAlignment(SwingConstants.RIGHT);
		label7.setBounds(387, 74, 87, 38);
		panel2.add(label7);
		
		spinner = new JSpinner();
		spinner.setFont(new Font("Tahoma", Font.PLAIN, 16));
		spinner.setBounds(484, 74, 55, 25);
		spinner.setValue((int) 1);
		panel2.add(spinner);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(30, 290, 527, 338);
		panel2.add(scrollPane);
		
		tablaVenta = new JTable();
		tablaVenta.setShowHorizontalLines(false);
		scrollPane.setViewportView(tablaVenta);
		
		label8 = new JLabel("Productos");
		label8.setFont(new Font("Tw Cen MT Condensed", Font.PLAIN, 25));
		label8.setBounds(30, 248, 192, 38);
		panel2.add(label8);
		
		label9 = new JLabel("Ticket:");
		label9.setFont(new Font("Yu Gothic UI", Font.PLAIN, 18));
		label9.setHorizontalAlignment(SwingConstants.RIGHT);
		label9.setBounds(431, 255, 55, 25);
		panel2.add(label9);
		
		labelTicket = new JLabel("000");
		labelTicket.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		labelTicket.setFont(new Font("Tahoma", Font.PLAIN, 18));
		labelTicket.setHorizontalAlignment(SwingConstants.CENTER);
		labelTicket.setBounds(496, 255, 43, 25);
		panel2.add(labelTicket);
		
		panel5 = new JPanel();
		panel5.setBounds(894, 624, 202, 77);
		panelPrincipal.add(panel5);
		panel5.setLayout(new CardLayout(0, 0));
		
		btnVenta = new JButton("Pagar");
		btnVenta.setBackground(new Color(255, 153, 153));
		btnVenta.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		btnVenta.setFont(new Font("Tw Cen MT", Font.PLAIN, 25));
		btnVenta.setIcon(new ImageIcon(vistaMenu.class.getResource("/Img/pagar64.png")));
		panel5.add(btnVenta, "name_8691655294200");
		
		panel4 = new JPanel();
		panel4.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel4.setBounds(894, 443, 441, 170);
		panelPrincipal.add(panel4);
		panel4.setLayout(null);
		
		label10 = new JLabel("SubTotal:");
		label10.setHorizontalAlignment(SwingConstants.RIGHT);
		label10.setFont(new Font("Trebuchet MS", Font.PLAIN, 18));
		label10.setBounds(145, 25, 108, 30);
		panel4.add(label10);
		
		labelSubTotal = new JLabel("00.00");
		labelSubTotal.setHorizontalAlignment(SwingConstants.LEFT);
		labelSubTotal.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 18));
		labelSubTotal.setBounds(263, 30, 108, 30);
		panel4.add(labelSubTotal);
		
		labelIva = new JLabel("00.00");
		labelIva.setHorizontalAlignment(SwingConstants.LEFT);
		labelIva.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 18));
		labelIva.setBounds(263, 82, 108, 27);
		panel4.add(labelIva);
		
		label11 = new JLabel("IVA:");
		label11.setHorizontalAlignment(SwingConstants.RIGHT);
		label11.setFont(new Font("Trebuchet MS", Font.PLAIN, 18));
		label11.setBounds(145, 75, 108, 30);
		panel4.add(label11);
		
		labelTotal = new JLabel("00.00");
		labelTotal.setHorizontalAlignment(SwingConstants.LEFT);
		labelTotal.setFont(new Font("Yu Gothic Medium", Font.BOLD, 18));
		labelTotal.setBounds(263, 130, 108, 30);
		panel4.add(labelTotal);
		
		label12 = new JLabel("Total:");
		label12.setHorizontalAlignment(SwingConstants.RIGHT);
		label12.setFont(new Font("Trebuchet MS", Font.BOLD | Font.ITALIC, 18));
		label12.setBounds(145, 125, 108, 30);
		panel4.add(label12);
		
		label14 = new JLabel("");
		label14.setIcon(new ImageIcon(vistaMenu.class.getResource("/Img/dinero.png")));
		label14.setHorizontalAlignment(SwingConstants.CENTER);
		label14.setBounds(15, 25, 135, 120);
		panel4.add(label14);
		
		panel3 = new JPanel();
		panel3.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel3.setBounds(894, 48, 441, 374);
		panelPrincipal.add(panel3);
		panel3.setLayout(null);
		
		label13 = new JLabel("Buscar Producto:");
		label13.setHorizontalAlignment(SwingConstants.CENTER);
		label13.setBounds(10, 21, 175, 26);
		label13.setFont(new Font("Tw Cen MT Condensed", Font.PLAIN, 24));
		panel3.add(label13);
		
		txtBuscar = new JTextField();
		txtBuscar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtBuscar.setColumns(10);
		txtBuscar.setBounds(171, 23, 260, 25);
		panel3.add(txtBuscar);
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 58, 421, 305);
		panel3.add(scrollPane_1);
		
		tablaBuscar = new JTable();
		tablaBuscar.setShowHorizontalLines(false);
		scrollPane_1.setViewportView(tablaBuscar);
		
		labelFecha = new JLabel("dd-mm-yyyy");
		labelFecha.setHorizontalAlignment(SwingConstants.RIGHT);
		labelFecha.setFont(new Font("Yu Gothic UI", Font.PLAIN, 18));
		labelFecha.setBounds(1218, 10, 117, 25);
		panelPrincipal.add(labelFecha);
		
		btnSalir = new JButton("Salir");
		btnSalir.setIcon(new ImageIcon(vistaMenu.class.getResource("/Img/cerrar.png")));
		btnSalir.setBackground(new Color(255, 204, 153));
		btnSalir.setFont(new Font("Tw Cen MT", Font.PLAIN, 25));
		btnSalir.setBounds(1114, 624, 209, 77);
		panelPrincipal.add(btnSalir);
		
		setSize(new Dimension(1364, 800));
		setLocationRelativeTo(null);
		setTitle("Punto de Venta");
	}
}
