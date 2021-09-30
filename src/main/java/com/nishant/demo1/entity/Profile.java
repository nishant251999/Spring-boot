package com.nishant.demo1.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Profile {
    
    @Id
    @GeneratedValue
    private long id;

    private String firstName;
    private String lastName;
    private String phoneNumber;
    private Boolean isSBWSCompliant;
    private Boolean areMandatoryCoursesDone;

    public Profile() {
    }
    
    public Profile(long id, String firstName, String lastName, String phoneNumber,
            Boolean isSBWSCompliant, Boolean areMandatoryCoursesDone) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.isSBWSCompliant = isSBWSCompliant;
        this.areMandatoryCoursesDone = areMandatoryCoursesDone;
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
