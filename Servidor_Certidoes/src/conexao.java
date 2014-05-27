

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class conexao {
	Connection conn = null;
	Statement stm = null;
	ResultSet results = null;
	public Connection abreconexao() throws Exception { 
		
		String sDriverName = "org.sqlite.JDBC";
		Class.forName(sDriverName);
		String sTempDb = "F:\\GeradorCertidoesNegativas\\banco de dados\\database.db";
		String sJdbc = "jdbc:sqlite";
		String sDbUrl = sJdbc + ":" + sTempDb;
		Connection conn = DriverManager.getConnection(sDbUrl);	
		return conn;
	}
	
}
