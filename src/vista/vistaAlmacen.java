package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.SystemColor;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import javax.swing.border.MatteBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;

public class vistaAlmacen extends JDialog {

	private final JPanel panelPrincipal = new JPanel();
	public JButton btnAgregar, btnSalir, btnTabla, btnAlmacen;
	private JLabel label1, label3, label4, label5;
	private JPanel buttonPane;
	public JTextField txtId, txtPrecio, txtCantidad;
	public JComboBox cbProveedor;
	
	public vistaAlmacen() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("src\\Img\\almacen64.png"));
		setTitle("Almacen");
		setBounds(100, 100, 450, 280);
		setModal(true);
		getContentPane().setLayout(new BorderLayout());
		setLocationRelativeTo(null);
		panelPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(panelPrincipal, BorderLayout.CENTER);
		panelPrincipal.setLayout(null);
		{
			btnTabla = new JButton("Tabla");
			btnTabla.setBorderPainted(false);
			btnTabla.setBackground(new Color(176, 224, 230));
			btnTabla.setBounds(177, 19, 66, 23);
			panelPrincipal.add(btnTabla);
		}
		{
			txtId = new JTextField();
			txtId.setOpaque(false);
			txtId.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
			txtId.setBounds(71, 21, 96, 19);
			txtId.setColumns(10);
			panelPrincipal.add(txtId);
		}
		{
			label1 = new JLabel("ID:");
			label1.setBounds(21, 25, 40, 15);
			panelPrincipal.add(label1);
		}
		
		label3 = new JLabel("Precio:");
		label3.setBounds(179, 86, 45, 13);
		panelPrincipal.add(label3);
		
		txtPrecio = new JTextField();
		txtPrecio.setOpaque(false);
		txtPrecio.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		txtPrecio.setColumns(10);
		txtPrecio.setBounds(234, 84, 96, 19);
		panelPrincipal.add(txtPrecio);
		
		txtCantidad = new JTextField();
		txtCantidad.setOpaque(false);
		txtCantidad.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		txtCantidad.setColumns(10);
		txtCantidad.setBounds(234, 117, 96, 19);
		panelPrincipal.add(txtCantidad);
		
		label4 = new JLabel("Cantidad:");
		label4.setBounds(167, 121, 57, 13);
		panelPrincipal.add(label4);
		
		label5 = new JLabel("Proveedor:");
		label5.setBounds(156, 159, 68, 13);
		panelPrincipal.add(label5);
		
		cbProveedor = new JComboBox();
		cbProveedor.setBorder(null);
		cbProveedor.setBounds(233, 155, 190, 21);
		panelPrincipal.add(cbProveedor);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("src\\Img\\Almacenmenu80.png"));
		lblNewLabel.setBounds(34, 81, 112, 91);
		panelPrincipal.add(lblNewLabel);
		{
			buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			
			btnAlmacen = new JButton("Registro Almacen");
			btnAlmacen.setBorderPainted(false);
			btnAlmacen.setForeground(SystemColor.textText);
			btnAlmacen.setBackground(new Color(211, 211, 211));
			buttonPane.add(btnAlmacen);
			{
				btnAgregar = new JButton("Agregar");
				btnAgregar.setBorderPainted(false);
				btnAgregar.setForeground(SystemColor.textText);
				btnAgregar.setBackground(new Color(211, 211, 211));
				buttonPane.add(btnAgregar);
			}
			{
				btnSalir = new JButton("Salir");
				btnSalir.setBorderPainted(false);
				btnSalir.setForeground(SystemColor.textText);
				btnSalir.setBackground(new Color(211, 211, 211));
				buttonPane.add(btnSalir);
			}
		}
	}
}
