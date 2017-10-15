package cook1.model;

import java.sql.*;

public class cookuserDB {
	
	public cookuser cookUserLoginCheck(String cookUserId, String cookUserPw){
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
			String connURL = "jdbc:mysql://localhost/cook1?user=root&password=wiwi12345";
			Connection conn = DriverManager.getConnection(connURL);
			
			String Qstmt_cookuserlogin = "SELECT * FROM cook_user WHERE user_id=? AND user_pw=?";
			
			PreparedStatement Pstmt_cookuserlogin = conn.prepareStatement(Qstmt_cookuserlogin);
			Pstmt_cookuserlogin.setString(1, cookUserId);
			Pstmt_cookuserlogin.setString(2, cookUserPw);
			
			ResultSet rs_cookuserlogin = Pstmt_cookuserlogin.executeQuery();
			
			while(rs_cookuserlogin.next()){
				cookuser cookuser_found = new cookuser(rs_cookuserlogin.getString("user_id"), rs_cookuserlogin.getString("user_pw"));
				conn.close();
				return cookuser_found;
			}
			
			conn.close();
			return null;
		}catch(Exception err){
			return null;
		}
		
	}
	
	

}
