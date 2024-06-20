package modelo;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

public class Conexion {
	String proveedor = "jdbc:ucanaccess://";  //Este String va a cambiar dependiendo si esta usando Access, SQLServer o MySQL
	String basedatos = "BaseDatosProyectoIntegrador.accdb";  //Igualmente este String cambiara dependiendo de donde se encuentre y como se llame su base de datos
	Connection ccn;
	
	public Conexion() {}

	//Este metodo me abre la conexion con la base de datos
	public Connection getConnection() {
		try {
			ccn = DriverManager.getConnection(proveedor + basedatos);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "CONEXION ERRONEA " + e);
		}
		return ccn;
	}

	//Este metodo me cierra la concexion con la base de datos, pero con la forma que este proyecto esta estructurado, este metodo no se utiliza
	public void Desconexion() {
		try {
			ccn.close();
			System.exit(0);
		} catch (SQLException ex) {
			Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
}