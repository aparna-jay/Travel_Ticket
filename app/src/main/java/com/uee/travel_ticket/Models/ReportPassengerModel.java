package com.uee.travel_ticket.Models;

public class ReportPassengerModel {
    String date, description, passengerID, inspectorId, time;

    public ReportPassengerModel(String date, String description, String passengerID, String inspectorId, String time) {
        this.date = date;
        this.description = description;
        this.passengerID = passengerID;
        this.inspectorId = inspectorId;
        this.time = time;
    }

    public ReportPassengerModel() {
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPassengerID() {
        return passengerID;
    }

    public void setPassengerID(String passengerID) {
        this.passengerID = passengerID;
    }

    public String getInspectorId() {
        return inspectorId;
    }

    public void setInspectorId(String inspectorId) {
        this.inspectorId = inspectorId;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
