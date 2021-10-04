package com.uee.travel_ticket.Models;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class UserModel {

    public String id;
    public String name;
    public String address;
    public String nic;
    public String password;
    public String accStatus;
    public String accBalance;


    public UserModel() {
    }

    public UserModel(String id, String name, String address, String nic, String password, String accBalance, String accStatus) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.nic = nic;
        this.password = password;
        this.accBalance = accBalance;
        this.accStatus = accStatus;

    }

//    public String getRechargeAmount() {
//        return rechargeAmount;
//    }
//
//    public void setRechargeAmount(String rechargeAmount) {
//        this.rechargeAmount = rechargeAmount;
//    }

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getAccStatus() {
        return accStatus;
    }

    public void setAccStatus(String accStatus) {
        this.accStatus = accStatus;
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
