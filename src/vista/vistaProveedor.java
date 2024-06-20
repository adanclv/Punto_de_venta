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
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import java.awt.SystemColor;
import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.border.MatteBorder;
import java.awt.Window.Type;
import java.awt.Font;
import javax.swing.SwingConstants;

public class vistaProveedor extends JDialog {

	private final JPanel contentPanel = new JPanel();
	public JTextField txtId, txtProveedor;
	private JLabel label1, label2;
	public JLabel labelSeguro;
	private JPanel buttonPane,panel1;
	public JButton btnAgregar, btnModificar, btnEliminar, btnSalir, btnTabla;

	public vistaProveedor() {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage("src\\Img\\proveedor64.png"));
		setTitle("Proveedores");
		setBounds(100, 100, 420, 210);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		label1 = new JLabel("ID:");
		label1.setHorizontalAlignment(SwingConstants.RIGHT);
		label1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		label1.setBounds(10, 24, 60, 13);
		contentPanel.add(label1);
		
		txtId = new JTextField();
		txtId.setDisabledTextColor(Color.DARK_GRAY);
		txtId.setOpaque(false);
		txtId.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		txtId.setColumns(10);
		txtId.setBounds(80, 20, 96, 19);
		contentPanel.add(txtId);
		
		btnTabla = new JButton("Tabla");
		btnTabla.setBorderPainted(false);
		btnTabla.setBackground(new Color(176, 224, 230));
		btnTabla.setBounds(185, 19, 66, 23);
		contentPanel.add(btnTabla);
		
		label2 = new JLabel("Proveedor:");
		label2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		label2.setBounds(10, 98, 66, 13);
		contentPanel.add(label2);
		
		txtProveedor = new JTextField();
		txtProveedor.setDisabledTextColor(Color.DARK_GRAY);
		txtProveedor.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		txtProveedor.setOpaque(false);
		txtProveedor.setColumns(10);
		txtProveedor.setBounds(80, 95, 168, 19);
		contentPanel.add(txtProveedor);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("src\\Img\\Proveedormenu80.png"));
		lblNewLabel.setBounds(289, 26, 96, 87);
		contentPanel.add(lblNewLabel);
		
		panel1 = new JPanel();
		panel1.setBounds(10, 147, 386, 50);
		contentPanel.add(panel1);
		
		labelSeguro = new JLabel("");
		panel1.add(labelSeguro);
		{
			buttonPane = new JPanel();
			FlowLayout fl_buttonPane = new FlowLayout(FlowLayout.CENTER);
			buttonPane.setLayout(fl_buttonPane);
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnAgregar = new JButton("Agregar");
				btnAgregar.setBorderPainted(false);
				btnAgregar.setForeground(SystemColor.textText);
				btnAgregar.setBackground(new Color(211, 211, 211));
				btnAgregar.setActionCommand("");
				buttonPane.add(btnAgregar);
				getRootPane().setDefaultButton(btnAgregar);
			}
			
			btnModificar = new JButton("Modificar");
			btnModificar.setBorderPainted(false);
			btnModificar.setForeground(SystemColor.textText);
			btnModificar.setBackground(new Color(211, 211, 211));
			buttonPane.add(btnModificar);
			
			btnEliminar = new JButton("Eliminar");
			btnEliminar.setBorderPainted(false);
			btnEliminar.setForeground(SystemColor.textText);
			btnEliminar.setBackground(new Color(211, 211, 211));
			buttonPane.add(btnEliminar);
			{
				btnSalir = new JButton("Salir");
				btnSalir.setBorderPainted(false);
				btnSalir.setForeground(SystemColor.textText);
				btnSalir.setBackground(new Color(211, 211, 211));
				btnSalir.setActionCommand("Cancel");
				buttonPane.add(btnSalir);
			}
		}
	}
}
