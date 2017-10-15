package cook1.model;

public class cookproduct {
	
	private int menu_id;
	private String user_id, menu_date, menu_name, menu_category, menu_pic, menu_area, menu_comment;
	private double menu_price;
	
		
	public cookproduct(int menu_id, String user_id, String menu_date, String menu_name, String menu_category,
			String menu_pic, String menu_area, String menu_comment, double menu_price) {
		super();
		this.menu_id = menu_id;
		this.user_id = user_id;
		this.menu_date = menu_date;
		this.menu_name = menu_name;
		this.menu_category = menu_category;
		this.menu_pic = menu_pic;
		this.menu_area = menu_area;
		this.menu_comment = menu_comment;
		this.menu_price = menu_price;
	}
	
		
	public cookproduct() {
		super();
	}
	

	public int getMenu_id() {
		return menu_id;
	}
	public void setMenu_id(int menu_id) {
		this.menu_id = menu_id;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getMenu_date() {
		return menu_date;
	}
	public void setMenu_date(String menu_date) {
		this.menu_date = menu_date;
	}
	public String getMenu_name() {
		return menu_name;
	}
	public void setMenu_name(String menu_name) {
		this.menu_name = menu_name;
	}
	public String getMenu_category() {
		return menu_category;
	}
	public void setMenu_category(String menu_category) {
		this.menu_category = menu_category;
	}
	public String getMenu_pic() {
		return menu_pic;
	}
	public void setMenu_pic(String menu_pic) {
		this.menu_pic = menu_pic;
	}
	public String getMenu_area() {
		return menu_area;
	}
	public void setMenu_area(String menu_area) {
		this.menu_area = menu_area;
	}
	public String getMenu_comment() {
		return menu_comment;
	}
	public void setMenu_comment(String menu_comment) {
		this.menu_comment = menu_comment;
	}
	public double getMenu_price() {
		return menu_price;
	}
	public void setMenu_price(double menu_price) {
		this.menu_price = menu_price;
	}
	
}
