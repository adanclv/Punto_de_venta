package vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;
import java.awt.Color;
import java.awt.Toolkit;
import javax.swing.ImageIcon;

public class vistaProductos extends JDialog {

	private final JPanel panelPrincipal = new JPanel();
	public JButton btnAgregar, btnModificar, btnEliminar, btnSalir, btnTabla;
	private JPanel buttonPane, panel1;
	public JTextField txtProducto, txtPrecio, txtCantidad, txtId;
	private JLabel label1, label2, label3, label4, label5;
	public JLabel labelSeguro, label6;
	public JComboBox cbCategoria, cbIva;
	private JLabel lblNewLabel;
	
	public vistaProductos() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(vistaProductos.class.getResource("/Img/productos64.png")));
		setBounds(100, 100, 557, 345);
		getContentPane().setLayout(new BorderLayout());
		panelPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(panelPrincipal, BorderLayout.CENTER);
		setLocationRelativeTo(null);
		panelPrincipal.setLayout(null);
		
		label2 = new JLabel("Producto:");
		label2.setHorizontalAlignment(SwingConstants.RIGHT);
		label2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		label2.setBounds(47, 84, 88, 13);
		panelPrincipal.add(label2);
		
		txtProducto = new JTextField();
		txtProducto.setOpaque(false);
		txtProducto.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		txtProducto.setBounds(157, 81, 168, 19);
		panelPrincipal.add(txtProducto);
		txtProducto.setColumns(10);
		
		txtPrecio = new JTextField();
		txtPrecio.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		txtPrecio.setOpaque(false);
		txtPrecio.setColumns(10);
		txtPrecio.setBounds(157, 115, 96, 19);
		panelPrincipal.add(txtPrecio);
		
		label3 = new JLabel("Precio:");
		label3.setHorizontalAlignment(SwingConstants.RIGHT);
		label3.setFont(new Font("Tahoma", Font.PLAIN, 13));
		label3.setBounds(47, 118, 88, 13);
		panelPrincipal.add(label3);
		
		txtCantidad = new JTextField();
		txtCantidad.setOpaque(false);
		txtCantidad.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		txtCantidad.setColumns(10);
		txtCantidad.setBounds(157, 149, 96, 19);
		panelPrincipal.add(txtCantidad);
		
		label4 = new JLabel("Cantidad:");
		label4.setHorizontalAlignment(SwingConstants.RIGHT);
		label4.setFont(new Font("Tahoma", Font.PLAIN, 13));
		label4.setBounds(47, 153, 88, 13);
		panelPrincipal.add(label4);
		
		label5 = new JLabel("Categoria:");
		label5.setHorizontalAlignment(SwingConstants.RIGHT);
		label5.setFont(new Font("Tahoma", Font.PLAIN, 13));
		label5.setBounds(47, 192, 88, 13);
		panelPrincipal.add(label5);
		
		txtId = new JTextField();
		txtId.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		txtId.setOpaque(false);
		txtId.setColumns(10);
		txtId.setBounds(390, 21, 96, 19);
		panelPrincipal.add(txtId);
		
		label1 = new JLabel("ID:");
		label1.setHorizontalAlignment(SwingConstants.RIGHT);
		label1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		label1.setBounds(322, 24, 45, 13);
		panelPrincipal.add(label1);
		
		btnTabla = new JButton("Tabla");
		btnTabla.setBackground(new Color(176, 224, 230));
		btnTabla.setBorderPainted(false);
		btnTabla.setBounds(400, 50, 66, 23);
		panelPrincipal.add(btnTabla);
		
		cbCategoria = new JComboBox();
		cbCategoria.setModel(new DefaultComboBoxModel(new String[] {"Selecciona categoria:"}));
		cbCategoria.setBounds(157, 189, 190, 21);
		panelPrincipal.add(cbCategoria);
		
		label6 = new JLabel("IVA:");
		label6.setHorizontalAlignment(SwingConstants.RIGHT);
		label6.setFont(new Font("Tahoma", Font.PLAIN, 13));
		label6.setBounds(47, 231, 88, 13);
		panelPrincipal.add(label6);
		
		cbIva = new JComboBox();
		cbIva.setModel(new DefaultComboBoxModel(new String[] {"Selecciona iva:"}));
		cbIva.setBounds(157, 228, 148, 21);
		panelPrincipal.add(cbIva);
		
		panel1 = new JPanel();
		panel1.setBounds(47, 227, 450, 70);
		panelPrincipal.add(panel1);
		
		labelSeguro = new JLabel("");
		panel1.add(labelSeguro);
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(vistaProductos.class.getResource("/Img/2productos64.png")));
		lblNewLabel.setBounds(420, 105, 66, 100);
		panelPrincipal.add(lblNewLabel);
		{
			buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnAgregar = new JButton("Agregar");
				btnAgregar.setBackground(new Color(211, 211, 211));
				btnAgregar.setBorderPainted(false);
				buttonPane.add(btnAgregar);
				getRootPane().setDefaultButton(btnAgregar);
			}
			{
				btnModificar = new JButton("Modificar");
				btnModificar.setBackground(new Color(211, 211, 211));
				btnModificar.setBorderPainted(false);
				buttonPane.add(btnModificar);
			}
			{
				btnEliminar = new JButton("Eliminar");
				btnEliminar.setBackground(new Color(211, 211, 211));
				btnEliminar.setBorderPainted(false);
				buttonPane.add(btnEliminar);
			}
			{
				btnSalir = new JButton("Salir");
				btnSalir.setBackground(new Color(211, 211, 211));
				btnSalir.setBorderPainted(false);
				buttonPane.add(btnSalir);
			}
		}
		setTitle("Productos");
	}
}
