import java.sql.Connection;
import java.sql.DriverManager;

public class Test {
	public static void main(String[] args) {
		try {
			String url = "jdbc:mysql://localhost:3306/oferte";
			String username = "root";
			String password = "parolamea";
			Connection conn = DriverManager.getConnection(url, username, password);
			System.out.println(conn.isValid(5));
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
