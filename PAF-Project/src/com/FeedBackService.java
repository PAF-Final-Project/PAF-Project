package com;
import model.FeedBack;


//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

//For JSON
import com.google.gson.*;

//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/FeedBacks")
public class FeedBackService
{
FeedBack FBObj = new FeedBack();
@GET
@Path("/")
@Produces(MediaType.TEXT_HTML)
public String readItems()
{
return FBObj.readItems();
}
@POST
@Path("/")
@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
@Produces(MediaType.TEXT_PLAIN)
public String insertFeedback(@FormParam("Email") String Email,
@FormParam("Message") String Message
)
{
String output = FBObj.insertFeedBack(Email, Message);
return output;
}
/*@PUT
@Path("/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.TEXT_PLAIN)
public String updateemail(String fbData)
{
//Convert the input string to a JSON object
JsonObject FBObject = new JsonParser().parse(fbData).getAsJsonObject();
//Read the values from the JSON object
String Email = FBObject.get("Email").getAsString();
String output = FBObj.updateFeedBack(Email);
return output;
}*/
@DELETE
@Path("/")
@Consumes(MediaType.APPLICATION_XML)
@Produces(MediaType.TEXT_PLAIN)
public String deleteFeedBack(String FBData)
{
//Convert the input string to an XML document
Document doc = Jsoup.parse(FBData, "", Parser.xmlParser());
//Read the value from the element <ID>

String Email = doc.select("Email").text().toString();
String output = FBObj.deleteFeedBack(Email);
return output;
}
}