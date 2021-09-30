package com.nishant.demo1.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Profile {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String firstName;
    private String lastName;
    private String phoneNumber;

    @OneToOne(cascade = {CascadeType.ALL})
    private Address address;
    private Boolean isSBWSCompliant;
    private Boolean areMandatoryCoursesDone;

    public Profile() {
    }
    
    public Profile(long id, String firstName, String lastName, String phoneNumber, Address address,
            Boolean isSBWSCompliant, Boolean areMandatoryCoursesDone) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.isSBWSCompliant = isSBWSCompliant;
        this.areMandatoryCoursesDone = areMandatoryCoursesDone;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public long getid() {
        return id;
    }
    public void setid(long id) {
        this.id = id;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public Boolean getIsSBWSCompliant() {
        return isSBWSCompliant;
    }
    public void setIsSBWSCompliant(Boolean isSBWSCompliant) {
        this.isSBWSCompliant = isSBWSCompliant;
    }
    public Boolean getAreMandatoryCoursesDone() {
        return areMandatoryCoursesDone;
    }
    public void setAreMandatoryCoursesDone(Boolean areMandatoryCoursesDone) {
        this.areMandatoryCoursesDone = areMandatoryCoursesDone;
    }

    
}
