package com;
import model.User;




//For REST Service
import javax.ws.rs.*; 
import javax.ws.rs.core.MediaType; 
//For JSON
import com.google.gson.*;



//For XML
import org.jsoup.*; 
import org.jsoup.parser.*; 
import org.jsoup.nodes.Document;


@Path("/Users") 
public class UserService 
{
	
	User userobj = new User();
	
	
	@GET
	@Path("/") 
	@Produces(MediaType.TEXT_HTML) 
	public String readItems() 
	{ 
		return userobj. readItems(); 
	}
	
	
	@POST
	@Path("/") 
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String insertUser(@FormParam("NIC") String NIC, 
	 @FormParam("Name") String Name, 
	 @FormParam("Type") String Type, 
	 @FormParam("Phone") String Phone,
	@FormParam("Email") String Email,
	@FormParam("Address") String Address,
	@FormParam("History") String History)
	{ 
	 
			String output = userobj.insertUser (NIC,Name, Type, Phone, Email, Address, History); 
			return output; 
	}
	
	
	@PUT
	@Path("/") 
	@Consumes(MediaType.APPLICATION_JSON) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String updateUser(String userData)
	{ 
	
		//Convert the input string to a JSON object 
		JsonObject userObject = new JsonParser().parse(userData).getAsJsonObject();
		
		//Read the values from the JSON object
		String NIC = userObject.get("NIC").getAsString();
		String Name = userObject.get("Name").getAsString();
		String Type = userObject.get("Type").getAsString();
		String Phone = userObject.get("Phone").getAsString();
		String Email = userObject.get("Email").getAsString();
		String Address = userObject.get("Address").getAsString();
		String History= userObject.get("History").getAsString();
		String output = userobj.updateUser(NIC, Type, Phone, Email, Address);
		return output; 
	}
	
	
	@DELETE
	@Path("/") 
	@Consumes(MediaType.APPLICATION_XML) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String deleteUser(String userData) 
	{ 
		//Convert the input string to an XML document
		//int nic = Integer.parseInt(userData);
		Document doc = Jsoup.parse(userData, "", Parser.xmlParser()); 
		 
		//Read the value from the element <itemID>
		String NIC = doc.select("NIC").text();
		String output = userobj.deleteUser(NIC);
		 return output; 
	}


	
}
