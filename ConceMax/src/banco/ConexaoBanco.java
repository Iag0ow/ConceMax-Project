package banco;
import java.sql.*;


public class ConexaoBanco {
	
	//metodo responsavel por estabelecer conexao com o banco
public static Connection conector() {
	java.sql.Connection conexao = null; 
	//a linha abaixo "chama" o driver
	String driver = "com.mysql.cj.jdbc.Driver";
	//armazenamento de informaçoes referentes ao branco e seu endereço
	String url = "jdbc:mysql://localhost/concemax?";
	String user ="root";
	String password = "13091992";
	// Estabelecendo de fato a conexão/junção das informações na variavel conexao
	try {
		Class.forName(driver);
		conexao = DriverManager.getConnection(url, user, password);
		return conexao;
		
	} catch (Exception e) {
		//A linha abaixo mostra o erro
		System.out.println(e);
		return null;
	}

	
}

}
