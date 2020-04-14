package com;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import model.User;

@Path("/User")
public class UserService {
	User itemObj = new User();

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readItems() {
		return itemObj.readItems();
		
	}


	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertItem(@FormParam("Ruser_name") String Ruser_name, 
			@FormParam("Ruser_address") String Ruser_address, 
			@FormParam("Ruser_gender") String Ruser_gender,
			@FormParam("Ruser_age") String Ruser_age,
			@FormParam("Ruser_notes") String Ruser_notes
			) 
	{
		String output = itemObj.insertItem( Ruser_name, Ruser_address, Ruser_gender,Ruser_age,Ruser_notes);
		return output;
	}

}
