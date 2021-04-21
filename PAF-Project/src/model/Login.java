package model;
import java.sql.*;
public class Login {
   
    //A common method to connect to the DB
    private Connection connect()
    {
    Connection con = null;
    try
    {
    Class.forName("com.mysql.cj.jdbc.Driver");
   
    //Provide the correct details: DBServer/DBName, username, password
   
    con = DriverManager.getConnection("jdbc:mysql://localhost/usermanagement?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root", "");
    }
    catch (Exception e)
    {e.printStackTrace();}
    return con;
    }
    //insert
    public String insertLogin(String email, String pw)
    {
    String output = "";
    try
    {
        Connection con = connect();
        if (con == null)
    {return "Error while connecting to the database for reading."; }
   
    // create a prepared statement
    String query = " insert into logt (`Email`,`Password`)"+ "values (?,?)";
    PreparedStatement preparedStmt = con.prepareStatement(query);
   
    // binding values
   
    preparedStmt.setString(1, email);
    preparedStmt.setString(2, pw);
   
    // execute the statement
    preparedStmt.execute();
    con.close();
    output = "Successfully Logged";
    }
        catch (Exception e)
    {
    output = "Error while logging.";
    System.err.println(e.getMessage());
    }
        return output;
    }
   
    //read
    public String readItems()
    {
        String output = "";
    try
    {
        Connection con = connect();
        if (con == null)
    {return "Error while connecting to the database for reading."; }
   
    // Prepare the html table to be displayed
    output = "<table border='1'><tr><th>Email</th></tr><th>Password</th></tr>";
   
    String query = "select * from logt";
    Statement stmt = con.createStatement();
    ResultSet rs = stmt.executeQuery(query);
   
    // iterate through the rows in the result set
    while (rs.next())
    {
   
    String Email = rs.getString("Email");
    String Password = rs.getString("Password");
   
   
    String pw = null;
    // Add into the html table
    //output += "<td>" +  ID  + "</td>";
    output += "<tr><td>" +  Email  + "</td>";
    output += "<td>" + Password + "</td>";
   
   
    // buttons
    //output += "<td><input name='btnUpdate' type='button' value='Update'class='btn btn-secondary'></td>+"<td><form method='post' action='items.jsp'>" + "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"+ "<input name='ID' type='hidden' value='" + ID + "'>" + "</form></td></tr>";
    }
    con.close();
   
    // Complete the html table
    output += "</table>";
    }
    catch (Exception e)
    {
    output = "Error while reading details.";
    System.err.println(e.getMessage());
    }
    return output;
    }
    /*public String updateFeedback(int id,String email )
    {
        String output = "";
        try
        {
        Connection con = connect();
        if (con == null)
        {return "Error while connecting to the database for updating."; }
       
        String query = "UPDATE fbt SET ID=?, Email=? WHERE ID=?";
                PreparedStatement preparedStmt = con.prepareStatement(query);
               
                // binding values
                preparedStmt.setString(1, email);
                preparedStmt.setInt(2, id);
               
               
                // execute the statement
                preparedStmt.execute();
                con.close();
                output = "Updated successfully";
                }
                catch (Exception e)
                {
                output = "Error while updating the email.";
                System.err.println(e.getMessage());
                }
                return output;
                }
   
               
    public String deleteFeedback(int ID)
    {
    String output = "";
    try
    {
    Connection con = connect();
    if (con == null)
    {return "Error while connecting to the database for deleting."; }
   
    // create a prepared statement
    String query = "delete from fbt where Email=?";
    PreparedStatement preparedStmt = con.prepareStatement(query);
   
    String Email = null;
    // binding values
    preparedStmt.setString(1,email2);
   
    // execute the statement
    preparedStmt.execute();
    con.close();
    output = "Deleted successfully";
    }
    catch (Exception e)
    {
    output = "Error while deleting the feedback.";
    System.err.println(e.getMessage());
    }
    return output;
    }*/
   /* public String insertLogin(String email, String password) {
        // TODO Auto-generated method stub
        return null;
    }*/
   
   
}
