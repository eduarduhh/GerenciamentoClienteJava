package db;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

import javax.swing.JOptionPane;

public class Conexao {
	
	public static final Connection getConexao() {
		
		try {
			Properties prop = new Properties();
			prop.load(new FileInputStream("database.properties"));
			
			Connection conn = null;
			String host    = prop.getProperty("host");
			String port    = prop.getProperty("port");
			String db      = prop.getProperty("db");
			String driver  = "com.mysql.jdbc.Driver" ;
			String url     = "jdbc:mysql://"+host+":"+port+"/"+db;
			String user    =  prop.getProperty("username");
			String pwd     =  prop.getProperty("password");
			
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, pwd);
			return conn;
		} catch (Exception erro) {
			JOptionPane.showMessageDialog(null, "PROBLEMAS NA CONEXÃO COM BANCO DE DADOS");
			return null;
		}
	}
}

