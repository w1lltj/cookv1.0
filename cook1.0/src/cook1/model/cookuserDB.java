package cook1.model;

import java.sql.*;

public final class cookuserDB {
	
	public final cookuser cookUserLoginCheck(String cookUserId, String cookUserPw){
		
		Connection conn = null;
		cookuser cookuser_found = null;
		
		try{
			/*Class.forName("com.mysql.jdbc.Driver");
			String connURL = "jdbc:mysql://localhost/cook1?user=root&password=@Wiwi12345wiwi";
			Connection conn = DriverManager.getConnection(connURL);*/
			conn = (Connection)new DBConnect().dBconnection();
			
			String Qstmt_cookuserlogin = "SELECT * FROM cook_user WHERE user_id=? AND user_pw=?";
			
			PreparedStatement Pstmt_cookuserlogin = conn.prepareStatement(Qstmt_cookuserlogin);
			Pstmt_cookuserlogin.setString(1, cookUserId);
			Pstmt_cookuserlogin.setString(2, cookUserPw);
			
			ResultSet rs_cookuserlogin = Pstmt_cookuserlogin.executeQuery();
			
			while(rs_cookuserlogin.next()){
				cookuser_found = (cookuser)new cookuser(rs_cookuserlogin.getString("user_id"), rs_cookuserlogin.getString("user_pw"));
				//conn.close();
				//return cookuser_found;
			}
			
			//conn.close();
			//return null;
		} catch(Exception err) {
			err.printStackTrace();
			//return null;
		} finally {
			try {
				if(conn!=null) {
					System.out.println("close connection!");
					conn.close();
				}			
			} catch(Exception err) {
				err.printStackTrace();
			}
			
			
		}
		
		return cookuser_found;
	}
	
	

}
