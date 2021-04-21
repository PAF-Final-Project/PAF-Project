package model;
import java.sql.*;

public class Adverticement {
	//A common method to connect to the DB
	private Connection connect(){
	Connection con = null;
	try{
	Class.forName("com.mysql.jdbc.Driver");
	//Provide the correct details: DBServer/DBName, username, password
	con = DriverManager.getConnection("jdbc:mysql://localhost/adverticement?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root", "");
	}catch (Exception e){
	e.printStackTrace();}
	return con;
	}
	

	public String insertAdverticement(String id, String type, String title, String description, byte addImg){
	String output = "";
	try{
	Connection con = connect();
	if (con == null){
	return "Error while connecting to the database for inserting.";
	}
	// create a prepared statement
	String query =( "INSERT INTO ADVERTICEMENT_TABLE  VALUES (?, ?, ?, ?, ?)");
	PreparedStatement preparedStmt = con.prepareStatement(query);

	preparedStmt.setString(1, id);
	preparedStmt.setString(2, type);
	preparedStmt.setString(3, title);
	preparedStmt.setString(4, description);
	preparedStmt.setByte(5,addImg);

	preparedStmt.execute();
	con.close();
	output = "Inserted successfully";
	}catch (Exception e){
		output = "" +(e.getMessage());
		//output = "Error while inserting the Adverticement.";
		System.err.println(e.getMessage());
	}
	return output;
	}


	public String readAdverticement(){
	String output = "";
	try{
	Connection con = connect();
	if (con == null){
	return "Error while connecting to the database for reading.";
	}
	// Prepare the html table to be displayed
	output = "<table border='1'><tr><th>Adverticement Type</th><th>Adverticement Title</th>" +
	"<th>Adverticement Description</th>" +
	"<th>Adverticement</th>" +
	"<th>Update</th><th>Remove</th></tr>";
	String query = "select * from ADVERTICEMENT_TABLE";
	Statement stmt = con.createStatement();
	ResultSet rs = stmt.executeQuery(query);
	// iterate through the rows in the result set
	while (rs.next())
	{
	String Add_ID = Integer.toString(rs.getInt("Add_ID"));
	String Add_Type = rs.getString("Add_Type");
	String Add_Title = rs.getString("Add_Title");
	String Add_Description = rs.getString("Add_Description");
	Byte Add_Image = rs.getByte("Add_Image");
	// Add into the html table
	output += "<tr><td>" + Add_Type + "</td>";
	output += "<td>" + Add_Title + "</td>";
	output += "<td>" + Add_Description + "</td>";
	output += "<td>" + Add_Image + "</td>";
	// buttons
	output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>"
	+ "<td><form method='post' action='items.jsp'>"
	+ "<input name='btnRemove' type='submit' value='Remove'class='btn btn-danger'>"
	+ "<input name='Add_ID' type='hidden' value='" + Add_ID
	+ "'>" + "</form></td></tr>";
	}
	con.close();
	// Complete the html table
	output += "</table>";
	}
	catch (Exception e)
	{
	output = "Error while reading the Adverticement.";
	System.err.println(e.getMessage());
	}
	return output;
	}


	public String updateAdverticement(String ID, String type, String title, String description){
	String output = "";
	try{
	Connection con = connect();
	if (con == null){
	return "Error while connecting to the database for updating.";
	}
	// create a prepared statement
	String query = "UPDATE ADVERTICEMENT_TABLE SET Add_Type=?,Add_Title=?,Add_Description=?  WHERE Add_ID= ? ";
	PreparedStatement preparedStmt = con.prepareStatement(query);
	// binding values
	preparedStmt.setString(2, type);
	preparedStmt.setString(3, title);
	preparedStmt.setString(4, description);
	//preparedStmt.setByte(5,image);
	preparedStmt.setString(1, ID);// execute the statement
	preparedStmt.execute();
	con.close();
	output = "Updated successfully";
	}
	catch (Exception e)
	{
		output = "" +e.getMessage();
	//output = "Error while updating the Adverticement.";
	System.err.println(e.getMessage());
	}
	return output;
	}

public String deleteAdd(String add_id){
			String output = "";
		try{	
			Connection con = connect();
		if (con == null){
			return "Error while connecting to the database for deleting.";
			}
		// create a prepared statement
			String query = "delete from ADVERTICEMENT_TABLE where Add_ID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
		// binding values
			preparedStmt.setInt(1, Integer.parseInt(add_id));
		// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Deleted successfully";
		}catch (Exception e){
			output = "Error while deleting the Adverticement.";
			System.err.println(e.getMessage());
		}
			return output;
		}
	}


	