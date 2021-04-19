package com;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;


import model.FundRequesting;


@Path("FundRequesting") 
public class FundRequestingService {
	
	FundRequesting frObj = new FundRequesting();
	
	
	@GET
	@Path("get") 
	@Produces(MediaType.TEXT_PLAIN) 
	public String readFundReqs() 
	 { 
		
		return frObj.readFundReqs();
	 
	 } 
	
	
	@POST
	@Path("post") 
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String insertDetails(@FormParam("rID") String rID, @FormParam("rName") String rName, @FormParam("rAge") String rAge,  @FormParam("rAddress") String rAddress,  @FormParam("rEmail") String rEmail,  @FormParam("rPhoneNum") String rPhoneNum,  @FormParam("projName") String projName,  @FormParam("projDesc") String projDesc,  @FormParam("fund") String fund) 
	{ 
		
	      String output = frObj.insertDetails(rID, rName, rAge, rAddress, rEmail, rPhoneNum, projName, projDesc, fund); 
	      return output;
		
	}
	
	@PUT
	@Path("put") 
	@Consumes(MediaType.APPLICATION_JSON) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String updateItem(String rData) 
	{ 
	   //Convert the input string to a JSON object 
	     JsonObject rObject = new JsonParser().parse(rData).getAsJsonObject(); 
	   //Read the values from the JSON object
	      
	     String r_ID = rObject.get("rID").getAsString(); 
	     String r_name = rObject.get("rName").getAsString(); 
	     String r_age = rObject.get("rAge").getAsString(); 
	     String r_address = rObject.get("rAddress").getAsString(); 
	     String r_email = rObject.get("rEmail").getAsString(); 
	     String r_phonenum = rObject.get("rPhoneNum").getAsString(); 
	     String proj_name = rObject.get("projName").getAsString(); 
	     String proj_desc = rObject.get("projDesc").getAsString(); 
	     String fund = rObject.get("fund").getAsString(); 
	 
	     String output = frObj.updateResearcher(r_ID, r_name, r_age, r_address, r_email, r_phonenum, proj_name, proj_desc, fund); 
	
	    return output; 
	}
	
	@DELETE
	@Path("delete") 
	@Consumes(MediaType.APPLICATION_XML) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String deleteResearcher(String r_ID) { 
	
		//Convert the input string to an XML document
		
	    Document doc = Jsoup.parse(r_ID, "", Parser.xmlParser()); 
	 
	    //Read the value from the element <itemID>
	 
	    String rid = doc.select("rID").text(); 
	    String output = frObj.deleteResearcher(rid); 
		
		
		return output;
		
		
		
	}
 
}
