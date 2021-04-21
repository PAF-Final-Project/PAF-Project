package com;
import model.Adverticement;

//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/Adverticement")
public class AdverticementService{

Adverticement AddverticementObject = new Adverticement();
@GET
@Path("/")
@Produces(MediaType.TEXT_HTML)
public String readAdverticement()
{
return AddverticementObject.readAdverticement();
}
@POST
@Path("/")
@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
@Produces(MediaType.TEXT_PLAIN)
public String insertAdverticement(
@FormParam("Add_ID") String id,
@FormParam("Add_Type") String type,
@FormParam("Add_Title") String title,
@FormParam("Add_Description") String description,
@FormParam("Add_Image") byte addImg)
{
String output = AddverticementObject.insertAdverticement(id, type, title, description, addImg);
return output;
}

@PUT
@Path("/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.TEXT_PLAIN)
public String updateAdverticement(String Add_Data){
//Convert the input string to a JSON object
JsonObject AddObjct = new JsonParser().parse(Add_Data).getAsJsonObject();
//Read the values from the JSON object
String id = AddObjct.get("Add_ID").getAsString();
String type = AddObjct.get("Add_Type").getAsString();
String title = AddObjct.get("Add_Title").getAsString();
String description = AddObjct.get("Add_Description").getAsString();
//byte image = AddObjct.get("Add_Image").getAsByte();
String output =AddverticementObject .updateAdverticement(id, type, title, description);
return output;
}

@DELETE
@Path("/")
@Consumes(MediaType.APPLICATION_XML)
@Produces(MediaType.TEXT_PLAIN)
public String deleteAdd(String AdverticementData)
{
//Convert the input string to an XML document
Document doc = Jsoup.parse(AdverticementData, "", Parser.xmlParser());
//Read the value from the element <itemID>
String Ad_ID = doc.select("Add_ID").text();
String output = AddverticementObject.deleteAdd(Ad_ID);
return output;
}

}