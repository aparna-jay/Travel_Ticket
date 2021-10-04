package com.uee.travel_ticket.Models;
import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class ForeignUserModel {
    public String id;
    public String name;
    public String passportID;
    public String packageName;
    public String address;
    public String country;
    public String password;

    public ForeignUserModel(String id, String name, String passportID, String address, String country, String password, String packageName) {
        this.id = id;
        this.name = name;
        this.passportID = passportID;
        this.packageName = packageName;
        this.address = address;
        this.country = country;
        this.password = password;
    }

    public ForeignUserModel() {
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassportID() {
        return passportID;
    }

    public void setPassportID(String passportID) {
        this.passportID = passportID;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
