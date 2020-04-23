package com.example.tripvault.data;

import androidx.annotation.NonNull;

import java.util.Date;

public class userTrip {

    int tripId;
    String username;
    String tittle;
    String location;
    String accomodation;
    String notes;
    Date date;

    public int getTripId() {
        return tripId;
    }

    public void setTripId(int tripId) {
        this.tripId = tripId;
    }

    public userTrip() {
    }

    ;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public userTrip(String username, String tittle, String location, String accomodation, String notes, Date date) {
        this.tittle = tittle;
        this.location = location;
        this.accomodation = accomodation;
        this.notes = notes;
        this.date = date;
        this.username = username;
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getAccomodation() {
        return accomodation;
    }

    public void setAccomodation(String accomodation) {
        this.accomodation = accomodation;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @NonNull
    @Override
    public String toString() {
        return super.toString();
    }
}
