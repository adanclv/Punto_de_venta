import java.awt.EventQueue;

import controlador.ctrlMenu;

public class principal {
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ctrlMenu ventana = new ctrlMenu();
					ventana.inicializar();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
