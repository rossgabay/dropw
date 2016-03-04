package com.rgabay.dropw.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.rgabay.dropw.core.User;

import io.dropwizard.auth.Auth;

@Path("/hello")
public class HelloResource {
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getGreeting(){
		return "Hello Dropwizard";
	}
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/protected")
	public String getSecuredGreeting(@Auth User user){
		return "Top Secret data";
	}
}
