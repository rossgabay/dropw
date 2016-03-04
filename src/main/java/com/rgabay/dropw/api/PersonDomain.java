package com.rgabay.dropw.api;

import io.searchbox.annotations.JestId;


public class PersonDomain {

	@JestId
    private String id;
   
    private String fname;

    private String lname;
   

    public PersonDomain(final String fname, final String lname) {
        this.fname = fname;
        this.lname = lname;
    }


    @Override
    public String toString() {
        return "Person [id=" + id + ", fname=" + fname + ",lname=" + lname + "]";
    }
    
    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

}
