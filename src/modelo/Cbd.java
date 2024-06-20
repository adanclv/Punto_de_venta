package modelo;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Cbd {
	Connection conexion;

	public Cbd(Connection conexion) {
		this.conexion = conexion;
	}

	//Este metodo es el que usaremos para cualquier consulta, recide de parametor unos de los String de la clase CBcadenas, devulve un ResultSet
	public ResultSet consultaBD(String cadena) {
		ResultSet datosRegistros = null;
		Statement st;
		String cadenaSQL = cadena;
		try {
			st = conexion.createStatement();
			datosRegistros = st.executeQuery(cadenaSQL);
			conexion.close();   //En la clase Conexion no se usa el metodo desconexion porque despues de realizar alguna accion aqui automaticamente se cierra la conexion
		} catch (Exception e) {
			System.out.println("E R R O R : " + e.getCause());
		}
		return datosRegistros;
	}

	//Los 3 metodos siguientes son exactamente el mismo, solo les cambia el nombre, nose porque Navarro los puso diferentes si lo que va a cambiar el funcionamiento del metodo
	//es el String que le mandemos de la clase CBcadenas, solo necesitan 1 porque los 3 hacen los mismo, que es mandarle una instruccion a la base de datos
	
	public boolean insertar(String cadena) {
		boolean realizo = true;
		Statement st;
		String cadenaSQL = cadena;
		try {
			st = conexion.createStatement();
			int i = st.executeUpdate(cadenaSQL);
			conexion.close();  //En la clase Conexion no se usa el metodo desconexion porque despues de realizar alguna accion aqui automaticamente se cierra la conexion
		} catch (Exception e) {
			realizo = false;
			System.out.println("E R R O R : " + e.getCause());
		}
		return realizo;
	}

	public boolean eliminar(String cadena) {
		boolean realizo = true;
		Statement st;
		String cadenaSQL = cadena;
		try {
			st = conexion.createStatement();
			int i = st.executeUpdate(cadenaSQL);
			conexion.close();
		} catch (Exception e) {
			realizo = false;
			System.out.println("E R R O R : " + e.getCause());
		}
		return realizo;
	}

	public boolean modificar(String cadena) {
		boolean realizo = true;
		Statement st;
		String cadenaSQL = cadena;
		try {
			st = conexion.createStatement();
			int i = st.executeUpdate(cadenaSQL);
			conexion.close();
		} catch (Exception e) {
			realizo = false;
			System.out.println("E R R O R : " + e.getCause());
		}
		return realizo;
	}
}
