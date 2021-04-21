package com;
import model.Login;

 


//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

 

//For JSON
import com.google.gson.*;

 

//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

 

@Path("/Login")
public class LoginService
{
    Login lgObj = new Login();
    @GET
    @Path("/")
    @Produces(MediaType.TEXT_HTML)
    public String readItems()
    {
        return lgObj.readItems();
    }
    @POST
    @Path("/")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_PLAIN)
    public String insertLogin(
            @FormParam("Email") String Email,
            @FormParam("Password") String Password
            )
            {
            String output = lgObj.insertLogin(Email, Password);
            return output;
            }
    /*
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String updateemail(String lgData)
    {
    //Convert the input string to a JSON object
    JsonObject FBObject = new JsonParser().parse(lgData).getAsJsonObject();
    
    //Read the values from the JSON object
    int ID = FBObject.get("ID").getAsInt();
    String Email = FBObject.get("Email").getAsString();
    
    String output = FBObj.updateFeedback(ID, Email);
    return output;
    }*/
    
    /*@DELETE
    @Path("/")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.TEXT_PLAIN)
    public String deleteFeedback(String FBData)
    {
    
    //Convert the input string to an XML document
    Document doc = Jsoup.parse(FBData, "", Parser.xmlParser());
    
    //Read the value from the element <ID>
    String ID = doc.select("ID").text().toString();
    String id = doc.select("ID").text().toString();
    String output = FBObj.deleteFeedback(ID);
    return output;
    }*/
}
 