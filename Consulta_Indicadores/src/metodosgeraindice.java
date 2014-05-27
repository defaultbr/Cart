import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.Normalizer;


public class metodosgeraindice {

	public void geraindice(tela_principal tela) throws Exception {
		
		File file = new File("F:\\LIVROS-CNJ\\IND-PESSOAL\\IND-PESSOAL\\IND-PESSOAL\\");
		File arquivos[] = file.listFiles();
		metodosbancodedados metodosbancodedados = new metodosbancodedados();
		Connection conexao = metodosbancodedados.abreconexao();
		//Statement stmt = conexao.createStatement();
	    PreparedStatement stmt = conexao.prepareStatement("insert into indice (nomearquivo,caminhoimagem) values (?,?)");  

		String destroitabela = "DROP TABLE if exists indice";
		String criatabela = "CREATE TABLE indice (id INTEGER PRIMARY KEY AUTOINCREMENT, nomearquivo, caminhoimagem text)";
		String inseredados = "INSERT INTO indice (caminhoimagem) VALUES('Hello from the database')";
		//stmt.executeUpdate( destroitabela );
		//stmt.executeUpdate( criatabela );	
		int i = 0;
		while(i < arquivos.length -1) {
		String caminho = arquivos[i].getPath();
		String nomearquivo = arquivos[i].getName().toUpperCase().replace(".JPG","");
		//.replace("ó", "o").replace("í", "i").replace("á", "a").replace("ç", "c").replace(".jpg", "").replace("ã","a").replace("õ", "o");
		nomearquivo = Normalizer.normalize(nomearquivo, Normalizer.Form.NFD);
		nomearquivo = nomearquivo.replace("'"," ");
		nomearquivo = nomearquivo.replaceAll("[^\\p{ASCII}]", "");
		System.out.println("Posição: " + i + " Nome: " + nomearquivo);
		//stmt.executeUpdate("INSERT INTO indice (nomearquivo,caminhoimagem) VALUES(' "  + nomearquivo + "','" + caminho + "')");
	    stmt.setString(1, nomearquivo);
	    stmt.setString(2, caminho);  
	    stmt.execute();
	    
		i++;
		}

stmt.close();
conexao.close();
}
}
