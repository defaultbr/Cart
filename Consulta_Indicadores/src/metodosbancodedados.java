import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class metodosbancodedados { 
	Connection conn = null;
	Statement stm = null;
	ResultSet results = null;
	public Connection abreconexao() throws Exception { 
		
		String sDriverName = "org.sqlite.JDBC";
		Class.forName(sDriverName);
		String sTempDb = "F:\\BAIXA\\ATOS DOS GRUPOS\\bancodedados\\indice\\indice.db";
		String sJdbc = "jdbc:sqlite";
		String sDbUrl = sJdbc + ":" + sTempDb;
		Connection conn = DriverManager.getConnection(sDbUrl);

		
		
		
		
		
		
		return conn;
	}
	
	public ArrayList consulta(String nome) throws Exception { 
		Connection conexao = abreconexao();
	     ArrayList dados = new ArrayList();
		Statement stmt = conexao.createStatement();
		
		//SELECT * FROM tabela WHERE uri LIKE CONCAT('%', uriNoBanco, '%');

		String sMakeSelect = "SELECT * FROM indice WHERE nomearquivo LIKE '%" + nome + "%' ORDER BY  nomearquivo  ASC";
		results = stmt.executeQuery(sMakeSelect);
		while(results.next()) {
	
 			
				dados.add(new String[] { 
			    	
						results.getString("nomearquivo").replace("-", ""),
						results.getString("caminhoimagem")
			    });  
    		
		}
		
		return dados;

		
		
		
	}
	
	
}