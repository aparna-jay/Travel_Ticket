package com.uee.travel_ticket.Models;

import com.google.firebase.database.IgnoreExtraProperties;

import java.util.Date;

@IgnoreExtraProperties
public class PaymentInfoModel {
    public String id;
    public String cardHolderName;
    public String cardType;
    public String ccNumber;
    public String cvv;
    public String expDate;
    public String rechargeAmount;


    public PaymentInfoModel(String id, String cardHolderName, String cardType, String ccNumber, String cvv, String expDate, String rechargeAmount) {
        this.id = id;
        this.cardHolderName = cardHolderName;
        this.cardType = cardType;
        this.ccNumber = ccNumber;
        this.cvv = cvv;
        this.expDate = expDate;
        this.rechargeAmount = rechargeAmount;
    }

    public PaymentInfoModel() {
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCardHolderName() {
        return cardHolderName;
    }

    public void setCardHolderName(String cardHolderName) {
        this.cardHolderName = cardHolderName;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getCcNumber() {
        return ccNumber;
    }

    public void setCcNumber(String ccNumber) {
        this.ccNumber = ccNumber;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public String getExpDate() {
        return expDate;
    }

    public void setExpDate(String expDate) {
        this.expDate = expDate;
    }

    public String getRechargeAmount() {
        return rechargeAmount;
    }

    public void setRechargeAmount(String rechargeAmount) {
        this.rechargeAmount = rechargeAmount;
    }
}
