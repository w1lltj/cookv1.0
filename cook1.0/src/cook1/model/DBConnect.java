package cook1.model;

import java.sql.*;

public final class DBConnect {
	
	public final Connection dBconnection() {
		Connection conn = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String connURL = "jdbc:mysql://localhost/cook1?user=root&password=@Wiwi12345wiwi";
			conn = DriverManager.getConnection(connURL);
			
			return conn;
			
		} catch(Exception err) {
			err.printStackTrace();
			
			try {
				if(conn!=null) {
					conn.close();
				}				
			} catch(Exception err2) {
				err2.printStackTrace();
			}
			
			return null;
		}
		
	}
	
}
