package vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import java.awt.CardLayout;
import javax.swing.JTable;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.SystemColor;

public class vistaTabla extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JPanel buttonPane;
	public JButton btnCopiar, btnSalir;
	public JScrollPane scrollPane;
	public JTable tabla;

	public vistaTabla() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(vistaTabla.class.getResource("/Img/tabla.png")));
		setBounds(100, 100, 522, 477);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		setLocationRelativeTo(null);
		contentPanel.setLayout(new CardLayout(0, 0));
		{
			scrollPane = new JScrollPane();
			contentPanel.add(scrollPane, "name_24707900629500");
			{
				tabla = new JTable();
				tabla.setShowHorizontalLines(false);
				tabla.setGridColor(SystemColor.activeCaption);
				tabla.setFont(new Font("Segoe UI", Font.PLAIN, 13));
				scrollPane.setViewportView(tabla);
			}
		}
		{
			buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnCopiar = new JButton("Copiar");
				btnCopiar.setBorderPainted(false);
				btnCopiar.setBackground(new Color(153, 204, 204));
				btnCopiar.setFont(new Font("Tw Cen MT", Font.PLAIN, 20));
				buttonPane.add(btnCopiar);
				getRootPane().setDefaultButton(btnCopiar);
			}
			{
				btnSalir = new JButton("Salir");
				btnSalir.setBorderPainted(false);
				btnSalir.setBackground(new Color(255, 153, 153));
				btnSalir.setFont(new Font("Tw Cen MT", Font.PLAIN, 20));
				buttonPane.add(btnSalir);
			}
		}
	}

}
