package com.rgabay.dropw.resources;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.rgabay.dropw.core.Person;
import com.rgabay.dropw.db.PersonDAO;

import io.dropwizard.hibernate.UnitOfWork;

@Path("/people")
@Produces(MediaType.APPLICATION_JSON)
public class PersonResource {

	private final PersonDAO personDAO;
	
	public PersonResource(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

@GET
@UnitOfWork
public List<Person> listPeople() {
	       return personDAO.findAll();
	    }
}
