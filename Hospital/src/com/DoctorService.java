package com;

import model.Doctor;

//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/Doctor")

public class DoctorService {
	
	Doctor itemObj = new Doctor();

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readItems() {
		return itemObj.readMItems();
		
	}
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertMItem(@FormParam("Rdoctor_name") String Rdoctor_name, 
			@FormParam("Rdoctor_address") String Rdoctor_address, 
			@FormParam("Rdoctor_specilaization") String Rdoctor_specilaization,
			@FormParam("Rdoctor_email") String Rdoctor_email
			) 
	{
		String output = itemObj.insertMItem( Rdoctor_name, Rdoctor_address, Rdoctor_specilaization,Rdoctor_email);
		return output;
	}
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateMItem(String itemData) { // Convert the input string to a JSON object
		JsonObject itemObject = new JsonParser().parse(itemData).getAsJsonObject();

		// Read the values from the JSON object
		String Rdoctor_ID = itemObject.get("Rdoctor_ID").getAsString();
		String Rdoctor_name = itemObject.get("Rdoctor_name").getAsString();
		String Rdoctor_address = itemObject.get("Rdoctor_address").getAsString();
		String Rdoctor_specilaization = itemObject.get("Rdoctor_specilaization").getAsString();
		String Rdoctor_email = itemObject.get("Rdoctor_email").getAsString();

		String output = itemObj.updateMItem(Rdoctor_ID, Rdoctor_name, Rdoctor_address, Rdoctor_specilaization,Rdoctor_email);

		return output;
	}
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteMItem(String itemData)
	
  {
		// Convert the input string to an XML document
		Document doc = Jsoup.parse(itemData, "", Parser.xmlParser()); 
		//Read the value from the element <itemID> 
		String Rdoctor_ID = doc.select("Rdoctor_ID").text();

		String output = itemObj.deleteMItem(Rdoctor_ID);

		return output;
	}

	
	
	

}
