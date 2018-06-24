package trabalhobd;
import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


/**
 * 
 * @author Marcelo de Moraes
 *
 */

//Estabelece conexao entre usuario e banco de dados

public class Connect {
	private String url;
	private String user;
	private String pass;
	private Connection c;
	private Statement stmt; 
	
	//construtores
	public Connect() { //deafult
        this.url = "jdbc:oracle:thin:@grad.icmc.usp.br:15215:orcl";
        this.user = "M9763151";
        this.pass = "mk21011998*";	     
	}
	
	public Connect(String user, String pass) {
		this.user = user;
		this.pass = pass;
	}
	
	public Connect(String user, String pass, String url) {
		this.user = user;
		this.pass = pass;
		this.url = url;
	}
	
	//realiza conexao
	public Statement makeConnection() {
		System.out.println("Conectando...");
		
		try {
			c = DriverManager.getConnection(url, user, pass);
			stmt = c.createStatement();
		}
		catch(SQLException sqle){
			sqle.printStackTrace();
			stmt = null;
		}
		
		return stmt;	
	}
	
	//fecha conexao
	public void close() {
		try {
			c.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
