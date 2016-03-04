package com.rgabay.dropw.db;

import io.dropwizard.hibernate.AbstractDAO;
import java.util.List;
import org.hibernate.SessionFactory;

import com.rgabay.dropw.core.Person;


public class PersonDAO extends AbstractDAO<Person> {

   
    public PersonDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public List<Person> findAll() {
        return list(
                namedQuery(
                		"com.rgabay.dropw.core.Person.findAll"
                )
        );
    }

}
