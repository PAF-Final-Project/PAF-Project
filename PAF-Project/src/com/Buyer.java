package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Buyer 
{
	
	private Connection connect() 
	{ 
			
			//connect to database
			Connection con = null; 
			try
			{ 
				Class.forName("com.mysql.cj.jdbc.Driver"); 
	 
				//Provide the correct details: DBServer/DBName, username, password 
				con = DriverManager.getConnection("jdbc:mysql://localhost/buyermanage?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root", ""); 
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
			} 
			return con; 
	 }
	
	//Insert
	public String insertintoBuyer(String buyer_id, String buyer_name, String product_name, String product_quantity) 
	{
		String output = ""; 
		
		try
		{
			Connection con = connect();
			
			if (con == null) 
			{
				return "Error while connecting to the database for reading."; 
			}
			
			// create a prepared statement
			 String query = " insert into buyer (`buyerId`,`buyerName`, `productName`,`quentity`)" + " values (?, ?, ?,?)";
			 
			 PreparedStatement preparedStmt = con.prepareStatement(query);
			 
			// binding values
			 preparedStmt.setString(1, buyer_id);
			 preparedStmt.setString(2, buyer_name);
			 preparedStmt.setString(3, product_name); 
			 preparedStmt.setInt(4, Integer.parseInt(product_quantity));
			 
			 //execute the statement
			 preparedStmt.execute(); 
			 con.close(); 
			 output = "Inserted successfully";
			 
			 
		}
		catch (Exception e) 
		{ 
			output = "Error while inserting the data."; 
			System.err.println(e.getMessage()); 
		}
		
		return output;
	}
	
	
	public String readBuyerData()
	{
		String output = "";
		
		try
		{
			
			Connection con = connect();
			
			if (con == null) 
			{
				return "Error while connecting to the database for reading."; 
			}
			
			// Prepare the table to be displayed
			 output = "<table border='1'><tr><th>Buyer Id</th><th> Buyer Name</th>" +
			 "<th>Product Name</th>" + "<th>Quentity</th>" +
			 "<th>Update</th><th>Delete</th></tr>";
			 
			 String query = "select * from buyer"; 
			 Statement stmt = con.createStatement(); 
			 ResultSet rs = stmt.executeQuery(query);
			 
			// iterate through the rows in the result set
			 while (rs.next()) 
			 {
				 
				 String buyerId = rs.getString("buyerId");  
				 String buyerName = rs.getString("buyerName");
				 String productName = rs.getString("productName");
				 String quentity = Integer.toString(rs.getInt("quentity"));
				 
				// Add into the table
				 output += "<tr><td>" + buyerId + "</td>";
				 output += "<td>" + buyerName + "</td>";
				 output += "<td>" + productName + "</td>";
				 output += "<td>" + quentity + "</td>";
				 
				// buttons
				 output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>"
				 + "<td><form method='post' action='items.jsp'>" 
				 + "<input name='btnDelete' type='submit' value='Delete' class='btn btn-danger'>"
				 + "<input name='cartId' type='hidden' value='" + buyerId + "'>" + "</form></td></tr>"; 
				 
			 }
			 
			 con.close(); 
			 
			 // Complete the html table
			 output += "</table>";
			 
			 
			
		}
		catch (Exception e) 
		{ 
			output = "Error while reading the data."; 
			System.err.println(e.getMessage()); 
		}
		
		return output;
	}
	
	
	public String updateBuyerData(String buyer_id, String product_name, int product_quentity)
	{
		
		String output = "";
		
		try
		{
			
			Connection con = connect();
			
			if (con == null) 
			{
				return "Error while connecting to the database for updating."; 
			}
			
			// create a prepared statement
			 String query = "UPDATE buyer SET productName=?, quentity=? WHERE buyerId=?";
			 
			 PreparedStatement preparedStmt = con.prepareStatement(query);
			 
			// binding values 
			 preparedStmt.setString(1, product_name); 
			 preparedStmt.setInt(2, product_quentity);
			 preparedStmt.setString(3, buyer_id);
			 
			
			 
			// execute the statement
			 preparedStmt.execute(); 
			 con.close(); 
			 output = "Updated successfully"; 
			 
		}
		catch (Exception e) 
		{ 
			output = "Error while updating the data."; 
			System.err.println(e.getMessage()); 
		} 
		
		return output; 
		
	}
	
	
	public String deleteBuyerData(String buyer_id)
	{
		
		String output = "";
		
		try
		{
			
			Connection con = connect();
			
			if (con == null) 
			{
				return "Error while connecting to the database for deleting."; 
			}
			
			// create a prepared statement
			 String query = "delete from buyer where buyerId=?";
			 
			 PreparedStatement preparedStmt = con.prepareStatement(query);
			 
			// binding values
			 preparedStmt.setString(1, buyer_id);
			 
			 
			// execute the statement
			 preparedStmt.execute(); 
			 con.close(); 
			 output = "Deleted successfully";
			
		}
		catch (Exception e) 
		{ 
			output = "Error while deleting the item."; 
			System.err.println(e.getMessage()); 
		} 
		
		return output;
	}
	
}

