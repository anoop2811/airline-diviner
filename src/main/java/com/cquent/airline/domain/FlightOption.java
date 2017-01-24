package com.cquent.airline.domain;

/**
 * Created by Anoop on 1/8/17.
 */
public class FlightOption {

    private String onwardFlightPath;

    private String returnFlightPath;

    private String price;

    public String getReturnFlightPath() {
        return returnFlightPath;
    }

    public void setReturnFlightPath(String returnFlightPath) {
        this.returnFlightPath = returnFlightPath;
    }
    public String getOnwardFlightPath() {
        return onwardFlightPath;
    }

    public void setOnwardFlightPath(String onwardFlightPath) {
        this.onwardFlightPath = onwardFlightPath;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }



}
