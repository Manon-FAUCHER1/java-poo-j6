package fr.diginamic.recensement.props;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ResourceBundle;

import com.mysql.cj.jdbc.Driver;

public class DbMgr {
	
	static {
		try {
			DriverManager.registerDriver(new Driver());
		} catch (Exception e) {
			System.out.println("Impossible de charger le driver");
		}
	}
	
	public static Connection connection() {
		
		ResourceBundle props = ResourceBundle.getBundle("data");
		String url = props.getString("data.url");
		String login = props.getString("data.login");
		String password = props.getString("data.password");
		
		Connection connection = null;
		
		try {			
			connection = DriverManager.getConnection(url, login, password);
		}catch (Exception e) {
			System.out.println("Impossible de se connecter a la base de données" + e.getMessage());
		}
		
		return connection;
	}

}
