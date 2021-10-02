package com.uee.travel_ticket.Models;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class UserModel {

    public String id;
    public String username;
    public String type;
    public String email;
    public String phone;
    public String password;
    public String accBalance;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    //
    public UserModel(String id, String username, String type, String email, String phone, String password, String accBalance) {
//        public User(String id, String username) {
            this.id = id;
        this.username = username;
        this.type = type;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.accBalance = accBalance;
    }

    public UserModel() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAccBalance() {
        return accBalance;
    }

    public void setAccBalance(String accBalance) {
        this.accBalance = accBalance;
    }
}
