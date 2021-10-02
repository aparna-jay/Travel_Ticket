package com.uee.travel_ticket.Models;

public class journeyclass {
  String JourneyID, Date, Time, End, JourneyStatus, PassengerID, PaymentStatus, Start;


    public journeyclass() {
    }

    public journeyclass(String journeyID, String date, String time, String end, String journeyStatus, String passengerID, String paymentStatus, String start) {
        JourneyID = journeyID;
        Date = date;
        Time = time;
        End = end;
        JourneyStatus = journeyStatus;
        PassengerID = passengerID;
        PaymentStatus = paymentStatus;
        Start = start;
    }

    public String getJourneyID() {
        return JourneyID;
    }

    public void setJourneyID(String journeyID) {
        JourneyID = journeyID;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getEnd() {
        return End;
    }

    public void setEnd(String end) {
        End = end;
    }

    public String getJourneyStatus() {
        return JourneyStatus;
    }

    public void setJourneyStatus(String journeyStatus) {
        JourneyStatus = journeyStatus;
    }

    public String getPassengerID() {
        return PassengerID;
    }

    public void setPassengerID(String passengerID) {
        PassengerID = passengerID;
    }

    public String getPaymentStatus() {
        return PaymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        PaymentStatus = paymentStatus;
    }

    public String getStart() {
        return Start;
    }

    public void setStart(String start) {
        Start = start;
    }

}
