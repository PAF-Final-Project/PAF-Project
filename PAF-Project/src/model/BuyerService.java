package model;

//For REST Service
import javax.ws.rs.*; 
import javax.ws.rs.core.MediaType;

import com.Buyer;
//For JSON
import com.google.gson.*;



//For XML
import org.jsoup.*; 
import org.jsoup.parser.*; 
import org.jsoup.nodes.Document;


@Path("/Buyer") 
public class BuyerService 
{
	
	Buyer buyerobj = new Buyer();
	
	
	@GET
	@Path("/") 
	@Produces(MediaType.TEXT_HTML) 
	public String readBuyerData() 
	{ 
		return buyerobj.readBuyerData(); 
	}
	
	
	@POST
	@Path("/") 
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String insertintoBuyer(@FormParam("buyerId") String buyerId, 
	 @FormParam("buyerName") String buyerName, 
	 @FormParam("productName") String productName, 
	 @FormParam("quentity") String quentity) 
	{ 
	 
			String output = buyerobj.insertintoBuyer(buyerId, buyerName, productName, quentity); 
			return output; 
	}
	
	
	@PUT
	@Path("/") 
	@Consumes(MediaType.APPLICATION_JSON) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String updateBuyerData(String buyerData) 
	{ 
	
		//Convert the input string to a JSON object 
		JsonObject buyerObject = new JsonParser().parse(buyerData).getAsJsonObject();
		
		//Read the values from the JSON object
		String buyerId = buyerObject.get("buyerId").getAsString(); 
		String buyerName = buyerObject.get("buyerName").getAsString(); 
		String productName = buyerObject.get("productName").getAsString(); 
		int quentity = buyerObject.get("quentity").getAsInt();
		
		String output = buyerobj.updateBuyerData(buyerId, productName, quentity); 
		return output; 
	}
	
	
	@DELETE
	@Path("/") 
	@Consumes(MediaType.APPLICATION_XML) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String deleteBuyerData(String buyerData) 
	{ 
		//Convert the input string to an XML document
		 Document doc = Jsoup.parse(buyerData, "", Parser.xmlParser()); 
		 
		//Read the value from the element <itemID>
		 String buyerId = doc.select("buyerId").text();
		 
		 
		 String output = buyerobj.deleteBuyerData(buyerId); 
		 return output; 
	}


	
}
