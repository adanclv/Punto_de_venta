package vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import javax.swing.border.MatteBorder;
import javax.swing.ImageIcon;

public class vistaPagar extends JDialog {

	private final JPanel panelPrincipal = new JPanel();
	public JTextField txtTotal, txtEfectivo, txtCambio;
	private JLabel label1, label2, label3;
	private JPanel buttonPane;
	public JButton btnRealizar, btnCancelar;

	public vistaPagar() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(vistaPagar.class.getResource("/Img/pagoefectivo.png")));
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		panelPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(panelPrincipal, BorderLayout.CENTER);
		setLocationRelativeTo(null);
		panelPrincipal.setLayout(null);
		{
			label1 = new JLabel("Total a Pagar:");
			label1.setFont(new Font("Candara", Font.ITALIC, 20));
			label1.setHorizontalAlignment(SwingConstants.RIGHT);
			label1.setBounds(31, 8, 120, 39);
			panelPrincipal.add(label1);
		}
		
		txtTotal = new JTextField();
		txtTotal.setHorizontalAlignment(SwingConstants.RIGHT);
		txtTotal.setDisabledTextColor(Color.DARK_GRAY);
		txtTotal.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		txtTotal.setFont(new Font("Consolas", Font.PLAIN, 18));
		txtTotal.setEditable(false);
		txtTotal.setBounds(177, 13, 82, 26);
		panelPrincipal.add(txtTotal);
		txtTotal.setColumns(10);
		
		label2 = new JLabel("Efectivo:");
		label2.setFont(new Font("Candara", Font.PLAIN, 20));
		label2.setHorizontalAlignment(SwingConstants.CENTER);
		label2.setBounds(230, 123, 82, 26);
		panelPrincipal.add(label2);
		
		txtEfectivo = new JTextField();
		txtEfectivo.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		txtEfectivo.setHorizontalAlignment(SwingConstants.CENTER);
		txtEfectivo.setFont(new Font("Consolas", Font.PLAIN, 18));
		txtEfectivo.setBounds(326, 122, 82, 26);
		panelPrincipal.add(txtEfectivo);
		txtEfectivo.setColumns(10);
		
		label3 = new JLabel("Cambio:");
		label3.setFont(new Font("Candara", Font.PLAIN, 20));
		label3.setHorizontalAlignment(SwingConstants.CENTER);
		label3.setBounds(230, 169, 82, 27);
		panelPrincipal.add(label3);
		
		txtCambio = new JTextField();
		txtCambio.setDisabledTextColor(Color.DARK_GRAY);
		txtCambio.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		txtCambio.setHorizontalAlignment(SwingConstants.CENTER);
		txtCambio.setFont(new Font("Consolas", Font.PLAIN, 18));
		txtCambio.setEditable(false);
		txtCambio.setColumns(10);
		txtCambio.setBounds(326, 168, 82, 26);
		panelPrincipal.add(txtCambio);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(vistaPagar.class.getResource("/Img/2pagar64.png")));
		lblNewLabel.setBounds(76, 94, 75, 96);
		panelPrincipal.add(lblNewLabel);
		{
			buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnRealizar = new JButton("Realizar Venta");
				btnRealizar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					}
				});
				btnRealizar.setBackground(new Color(255, 204, 153));
				btnRealizar.setFont(new Font("Tw Cen MT", Font.PLAIN, 19));
				buttonPane.add(btnRealizar);
			}
			{
				btnCancelar = new JButton("Cancelar");
				btnCancelar.setBackground(new Color(255, 153, 153));
				btnCancelar.setFont(new Font("Tw Cen MT", Font.PLAIN, 19));
				buttonPane.add(btnCancelar);
			}
		}
	}
}
