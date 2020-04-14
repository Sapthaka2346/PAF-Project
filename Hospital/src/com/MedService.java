package com;

import model.Med;
//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/Med")
public class MedService {
	
	Med itemObj = new Med();

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
	public String insertMItem(@FormParam("med_name") String med_name, 
			@FormParam("med_code") String med_code, 
			@FormParam("med_price") String med_price,
			@FormParam("med_description") String med_description
			) 
	{
		String output = itemObj.insertMItem( med_name, med_code, med_price,med_description);
		return output;
	}
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateMItem(String itemData) { // Convert the input string to a JSON object
		JsonObject itemObject = new JsonParser().parse(itemData).getAsJsonObject();

		// Read the values from the JSON object
		String med_ID = itemObject.get("med_ID").getAsString();
		String med_name = itemObject.get("med_name").getAsString();
		String med_code = itemObject.get("med_code").getAsString();
		String med_price = itemObject.get("med_price").getAsString();
		String med_description = itemObject.get("med_description").getAsString();

		String output = itemObj.updateMItem(med_ID, med_name, med_code, med_price,med_description);

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
		String med_ID = doc.select("med_ID").text();

		String output = itemObj.deleteMItem(med_ID);

		return output;
	}

}
