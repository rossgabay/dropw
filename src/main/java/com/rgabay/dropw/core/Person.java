package com.rgabay.dropw.core;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


@Entity
@Table(name = "person")
@NamedQueries({
    @NamedQuery(name = "com.rgabay.dropw.core.Person.findAll",
            query = "select u from Person u"),
})
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
   
    @Column(name = "fname")
    private String fname;

    @Column(name = "lname")
    private String lname;
   
    public Person() {
    }

    public Person(String fname, String lname) {
        this.fname = fname;
        this.lname = lname;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + (int) (this.id ^ (this.id >>> 32));
        hash = 59 * hash + Objects.hashCode(this.fname);
        hash = 59 * hash + Objects.hashCode(this.lname);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Person other = (Person) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.fname, other.fname)) {
            return false;
        }
        if (!Objects.equals(this.lname, other.lname)) {
            return false;
        }
        return true;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

}
