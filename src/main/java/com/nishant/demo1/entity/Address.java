package com.nishant.demo1.entity;

public class Address {
    
    private String addLine1;
    private String addLine2;
    private String city;
    private String country;
    private long pincode;

    public Address() {
    }
    
    public Address(String addLine1, String addLine2, String city, String country, long pincode) {
        this.addLine1 = addLine1;
        this.addLine2 = addLine2;
        this.city = city;
        this.country = country;
        this.pincode = pincode;
    }

    public String getAddLine1() {
        return addLine1;
    }
    public void setAddLine1(String addLine1) {
        this.addLine1 = addLine1;
    }
    public String getAddLine2() {
        return addLine2;
    }
    public void setAddLine2(String addLine2) {
        this.addLine2 = addLine2;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }
    public long getPincode() {
        return pincode;
    }
    public void setPincode(long pincode) {
        this.pincode = pincode;
    }

    @Override
    public String toString() {
        return "Address [addLine1=" + addLine1 + ", addLine2=" + addLine2 + ", city=" + city + ", country=" + country
                + ", pincode=" + pincode + "]";
    }
}
