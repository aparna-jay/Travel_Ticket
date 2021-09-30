package com.uee.travel_ticket.Models;

public class journeyclass {
    String name,start,end;
    double price;


//    public journeyclass(String name, String start, String end, double price) {
//        this.name = name;
//        this.start = start;
//        this.end = end;
//        this.price = price;
//    }

    public journeyclass(String name, String start) {
        this.name = name;
        this.start = start;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
