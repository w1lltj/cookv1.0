package cook1.model;

import java.sql.*;
import java.util.ArrayList;

public class cookproductDB {
	
	public ArrayList<cookproduct> cookUserDashboardTableView(String cookUserId){
		
		ArrayList<cookproduct> cookUserDashboardTableViewResult = new ArrayList<cookproduct>();
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
			String connURL = "jdbc:mysql://localhost/cook1?user=root&password=wiwi12345";
			Connection conn = DriverManager.getConnection(connURL);
			
			String Qstmt_cookuserDashboardTableView = "SELECT * FROM cook_product WHERE user_id=?";
			
			PreparedStatement Pstmt_cookuserDashboardTableView = conn.prepareStatement(Qstmt_cookuserDashboardTableView);
			
			Pstmt_cookuserDashboardTableView.setString(1, cookUserId);
			
			ResultSet rs_cookuserDashboardTableView = Pstmt_cookuserDashboardTableView.executeQuery();
			
			while(rs_cookuserDashboardTableView.next()){
				cookproduct cookUserDashboardTableViewRecord = new cookproduct();
				
				cookUserDashboardTableViewRecord.setMenu_id(rs_cookuserDashboardTableView.getInt("menu_id"));
				cookUserDashboardTableViewRecord.setUser_id(rs_cookuserDashboardTableView.getString("user_id"));
				cookUserDashboardTableViewRecord.setMenu_date(rs_cookuserDashboardTableView.getDate("menu_date")+"");
				cookUserDashboardTableViewRecord.setMenu_name(rs_cookuserDashboardTableView.getString("menu_name"));
				cookUserDashboardTableViewRecord.setMenu_category(rs_cookuserDashboardTableView.getString("menu_category"));
				cookUserDashboardTableViewRecord.setMenu_pic(rs_cookuserDashboardTableView.getString("menu_pic"));
				cookUserDashboardTableViewRecord.setMenu_price(rs_cookuserDashboardTableView.getDouble("menu_price"));
				cookUserDashboardTableViewRecord.setMenu_area(rs_cookuserDashboardTableView.getString("menu_area"));
				cookUserDashboardTableViewRecord.setMenu_comment(rs_cookuserDashboardTableView.getString("menu_comment"));
				
				cookUserDashboardTableViewResult.add(cookUserDashboardTableViewRecord);
			}
			
			conn.close();
			return cookUserDashboardTableViewResult;
			
		}catch(Exception err){
			return null;
		}
		
	}
	
	
	public ArrayList<String> cookUserMenuCategory (String cookUserId){
		
		ArrayList<String> cookUserMenuCategoryResult = new ArrayList<String>();
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
			String connURL = "jdbc:mysql://localhost/cook1?user=root&password=wiwi12345";
			Connection conn = DriverManager.getConnection(connURL);
			
			String Qstmt_cookuserMenuCategory = "SELECT menu_category FROM cook_product WHERE user_id=?";
			
			PreparedStatement Pstmt_cookuserMenuCategory = conn.prepareStatement(Qstmt_cookuserMenuCategory);
			
			Pstmt_cookuserMenuCategory.setString(1, cookUserId);
			
			ResultSet rs_cookuserMenuCategory = Pstmt_cookuserMenuCategory.executeQuery();
			
			while(rs_cookuserMenuCategory.next()){
				String cookUserMenuCategoryItem = new String();
				cookUserMenuCategoryItem = rs_cookuserMenuCategory.getString("menu_category");
				int similarCount = 0;
				
				if(cookUserMenuCategoryResult!=null){
					for(String obj: cookUserMenuCategoryResult){
						if(obj.equalsIgnoreCase(cookUserMenuCategoryItem)){
							similarCount +=1;
						}
					}
				}
				
				if(similarCount<=0){
					cookUserMenuCategoryResult.add(cookUserMenuCategoryItem);
				}
								
			}
			
			conn.close();
			return cookUserMenuCategoryResult;
			
		}catch(Exception err){
			return null;
		}
		
	}
	
	
	public double cookUserMenuMaxPrice(String cookUserId){
		
		double cookUserMenuMaxPriceResult = 0.0;
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
			String connURL = "jdbc:mysql://localhost/cook1?user=root&password=wiwi12345";
			Connection conn = DriverManager.getConnection(connURL);
			
			String Qstmt_cookUserMenuMaxPrice = "SELECT MAX(menu_price) 'max_menu_price' FROM cook_product WHERE user_id=?";
			PreparedStatement Pstmt_cookUserMenuMaxPrice = conn.prepareStatement(Qstmt_cookUserMenuMaxPrice);
			
			Pstmt_cookUserMenuMaxPrice.setString(1, cookUserId);
			
			ResultSet rs_cookUserMenuMaxPrice = Pstmt_cookUserMenuMaxPrice.executeQuery();
			
			while(rs_cookUserMenuMaxPrice.next()){
				cookUserMenuMaxPriceResult = rs_cookUserMenuMaxPrice.getDouble("max_menu_price");
			}
			
			conn.close();
			return cookUserMenuMaxPriceResult;
			
		}catch(Exception err){
			return 0.0;
		}
	}

	
	public ArrayList<cookproduct> cookUserSearchMenu (String cookUserId, String menuDate, String menuName, String menuCategory, 
			double menuPrice, String menuArea){
		
		ArrayList<cookproduct> cookUserSearchResult = new ArrayList<cookproduct>();
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
			String connURL = "jdbc:mysql://localhost/cook1?user=root&password=wiwi12345";
			Connection conn = DriverManager.getConnection(connURL);
			
			String Qstmt_cookUserSearch;
			PreparedStatement Pstmt_cookUserSearch;
			if(menuCategory.equalsIgnoreCase("All")){
				Qstmt_cookUserSearch = "SELECT * FROM cook_product WHERE user_id=? AND "
						+ "menu_date LIKE ? AND menu_name LIKE ? AND menu_price<=? AND menu_area LIKE ?";
				Pstmt_cookUserSearch = conn.prepareStatement(Qstmt_cookUserSearch);
				Pstmt_cookUserSearch.setString(1, cookUserId);
				Pstmt_cookUserSearch.setString(2, '%'+menuDate+'%');		
				Pstmt_cookUserSearch.setString(3, '%'+menuName+'%');
				Pstmt_cookUserSearch.setDouble(4, menuPrice);
				Pstmt_cookUserSearch.setString(5, '%'+menuArea+'%');
			}else{
				Qstmt_cookUserSearch = "SELECT * FROM cook_product WHERE user_id=? AND "
						+ "menu_date LIKE ? AND menu_name LIKE ? AND menu_category=? AND menu_price<=? AND menu_area LIKE ?";
				Pstmt_cookUserSearch = conn.prepareStatement(Qstmt_cookUserSearch);
				Pstmt_cookUserSearch.setString(1, cookUserId);
				Pstmt_cookUserSearch.setString(2, '%'+menuDate+'%');		
				Pstmt_cookUserSearch.setString(3, '%'+menuName+'%');
				Pstmt_cookUserSearch.setString(4, menuCategory);
				Pstmt_cookUserSearch.setDouble(5, menuPrice);
				Pstmt_cookUserSearch.setString(6, '%'+menuArea+'%');
			}
			
			ResultSet rs_cookUserSearch = Pstmt_cookUserSearch.executeQuery();
			
			while(rs_cookUserSearch.next()){
				cookproduct cookUserSearchRecord = new cookproduct();
				
				cookUserSearchRecord.setMenu_id(rs_cookUserSearch.getInt("menu_id"));
				cookUserSearchRecord.setUser_id(rs_cookUserSearch.getString("user_id"));
				cookUserSearchRecord.setMenu_date(rs_cookUserSearch.getDate("menu_date")+"");
				cookUserSearchRecord.setMenu_name(rs_cookUserSearch.getString("menu_name"));
				cookUserSearchRecord.setMenu_category(rs_cookUserSearch.getString("menu_category"));
				cookUserSearchRecord.setMenu_pic(rs_cookUserSearch.getString("menu_pic"));
				cookUserSearchRecord.setMenu_price(rs_cookUserSearch.getDouble("menu_price"));
				cookUserSearchRecord.setMenu_area(rs_cookUserSearch.getString("menu_area"));
				cookUserSearchRecord.setMenu_comment(rs_cookUserSearch.getString("menu_comment"));
				
				cookUserSearchResult.add(cookUserSearchRecord);
			}
			
			conn.close();
			return cookUserSearchResult;
			
		}catch(Exception err){
			return null;
		}
	}
	
	
	public int cookUserAddMenu (String cookUserId, String menuDate, String menuName, String menuCategory, String menuPic, double menuPrice, 
			String menuArea, String menuComment){
		
		int cookUserAddResult = 0;
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
			String connURL = "jdbc:mysql://localhost/cook1?user=root&password=wiwi12345";
			Connection conn = DriverManager.getConnection(connURL);
			
			String Qstmt_cookUserAdd = "INSERT INTO cook_product(user_id, menu_date, menu_name, menu_category, menu_pic, menu_price, "
					+ "menu_area, menu_comment) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
			
			PreparedStatement Pstmt_cookUserAdd = conn.prepareStatement(Qstmt_cookUserAdd);
			Pstmt_cookUserAdd.setString(1, cookUserId);
			Pstmt_cookUserAdd.setString(2, menuDate);
			Pstmt_cookUserAdd.setString(3, menuName);
			Pstmt_cookUserAdd.setString(4, menuCategory);
			Pstmt_cookUserAdd.setString(5, menuPic);
			Pstmt_cookUserAdd.setDouble(6, menuPrice);
			Pstmt_cookUserAdd.setString(7, menuArea);
			Pstmt_cookUserAdd.setString(8, menuComment);
			
			cookUserAddResult = Pstmt_cookUserAdd.executeUpdate();
			
			conn.close();			
		}catch(Exception err){
			System.out.println(err.getMessage());
		}
		
		return cookUserAddResult;
	}
	
	
	public int cookUserDeleteMenu (String cookUserId, String[] menuId){
		
		int cookUserDeleteResult = 0;
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
			String connURL = "jdbc:mysql://localhost/cook1?user=root&password=wiwi12345";
			Connection conn = DriverManager.getConnection(connURL);
			
			String Qstmt_cookUserDelete = "DELETE FROM cook_product WHERE user_id=? AND menu_id IN(";
			for(int i=0; i<menuId.length; i++){
				if(i==(menuId.length-1)){
					Qstmt_cookUserDelete += "?)";
				}else{
					Qstmt_cookUserDelete += "?,";
				}					
			}
			
			PreparedStatement Pstmt_cookUserDelete = conn.prepareStatement(Qstmt_cookUserDelete);
			Pstmt_cookUserDelete.setString(1, cookUserId);
			for(int j=0; j<menuId.length; j++){
				Pstmt_cookUserDelete.setInt(j+2, Integer.parseInt(menuId[j]));
			}
			
			cookUserDeleteResult = Pstmt_cookUserDelete.executeUpdate();
			
			conn.close();
		}catch(Exception err){
			System.out.println(err.getMessage());
		}
		
		return cookUserDeleteResult;
	}
	
	
	public cookproduct cookUserEditMenuView(String cookUserId, int menuId){
		
		cookproduct cookUserEditViewResult = new cookproduct();
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
			String connURL ="jdbc:mysql://localhost/cook1?user=root&password=wiwi12345";
			Connection conn = DriverManager.getConnection(connURL);
			
			String Qstmt_cookUserEditView = "SELECT * FROM cook_product WHERE user_id=? AND menu_id=?";
			
			PreparedStatement Pstmt_cookUserEditView = conn.prepareStatement(Qstmt_cookUserEditView);
			Pstmt_cookUserEditView.setString(1, cookUserId);
			Pstmt_cookUserEditView.setInt(2, menuId);
			
			ResultSet rs_cookUserEdit = Pstmt_cookUserEditView.executeQuery();
			while(rs_cookUserEdit.next()){		
				cookUserEditViewResult.setMenu_id(rs_cookUserEdit.getInt("menu_id"));
				cookUserEditViewResult.setUser_id(rs_cookUserEdit.getString("user_id"));
				cookUserEditViewResult.setMenu_date(rs_cookUserEdit.getDate("menu_date")+"");
				cookUserEditViewResult.setMenu_name(rs_cookUserEdit.getString("menu_name"));
				cookUserEditViewResult.setMenu_category(rs_cookUserEdit.getString("menu_category"));
				cookUserEditViewResult.setMenu_pic(rs_cookUserEdit.getString("menu_pic"));
				cookUserEditViewResult.setMenu_price(rs_cookUserEdit.getDouble("menu_price"));
				cookUserEditViewResult.setMenu_area(rs_cookUserEdit.getString("menu_area"));
				cookUserEditViewResult.setMenu_comment(rs_cookUserEdit.getString("menu_comment"));
			}
			
			conn.close();
			return cookUserEditViewResult;
			
		}catch(Exception err){
			System.out.println(err.getMessage());
			return null;
		}
		
	}
	
	
	public int cookUserEditMenu(int menuId, String cookUserId, String menuDate, String menuName, String menuCategory, String menuPic, double menuPrice, 
			String menuArea, String menuComment){
		
		int cookUserEditResult = 0;
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
			String connURL = "jdbc:mysql://localhost/cook1?user=root&password=wiwi12345";
			Connection conn = DriverManager.getConnection(connURL);
			
			String Qstmt_cookUserEdit = "UPDATE cook_product "
					+ "SET menu_date=?, menu_name=?, menu_category=?, menu_pic=?, menu_price=?, menu_area=?, menu_comment=? "
					+ "WHERE menu_id=? AND user_id=?";
			
			PreparedStatement Pstmt_cookUserEdit = conn.prepareStatement(Qstmt_cookUserEdit);
			Pstmt_cookUserEdit.setString(1, menuDate);
			Pstmt_cookUserEdit.setString(2, menuName);
			Pstmt_cookUserEdit.setString(3, menuCategory);
			Pstmt_cookUserEdit.setString(4, menuPic);
			Pstmt_cookUserEdit.setDouble(5, menuPrice);
			Pstmt_cookUserEdit.setString(6, menuArea);
			Pstmt_cookUserEdit.setString(7, menuComment);
			Pstmt_cookUserEdit.setInt(8, menuId);
			Pstmt_cookUserEdit.setString(9, cookUserId);
			
			cookUserEditResult = Pstmt_cookUserEdit.executeUpdate();
			
			conn.close();
		}catch(Exception err){
			System.out.println(err.getMessage());
		}
		
		return cookUserEditResult;
				
	}

}
