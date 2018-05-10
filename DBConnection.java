import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBConnection{ //veritabaný baðlantýsý saðlanacak
	private static String dbkuladi = "root";
	private static String dbparola = "";
	private static String host = "127.0.0.1";
	private static String db = "dbuyeler";
	private static int port =3306;
	
	private static Connection cn=null;
	
	public static Connection baglanti() throws Exception{
		
		String url = "jdbc:mysql://" + host +":"+port+"/"+db;
		
		try{
			String driver = "com.mysql.jdbc.Driver";
			Class.forName(driver);
			cn =DriverManager.getConnection(url,dbkuladi,dbparola);
			return cn;
		}
		catch(SQLException e){System.out.println(e);}
		return null;
	}
}