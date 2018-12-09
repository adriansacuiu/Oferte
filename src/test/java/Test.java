import java.sql.Connection;
import java.sql.DriverManager;

public class Test {
	public static void main(String[] args) {
		try {
			String url = "jdbc:mysql://192.168.137.164:3306/AssetsManagement";
			String username = "root";
			String password = "parola";
			Connection conn = DriverManager.getConnection(url, username, password);
			System.out.println(conn.isValid(5));
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
