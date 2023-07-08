package com.hust.cybersec.objects;

import java.util.ArrayList;
import java.util.List;

public class Event {

    private String name;
    private String time;
    private String places;
    private List<String> factions = new ArrayList<>();
    private String result;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPlaces() {
        return (places.equals("")) ? "Đang cập nhật" : places;
    }

    public void setPlaces(String places) {
        this.places = places;
    }

    public List<String> getFactions() {
        return factions;
    }

    public void setFactions(List<String> factions) {
        this.factions = factions;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return (this.getName()+"\n"+this.getPlaces()+"\n"+this.getTime()+"\n"+this.getResult());       
    }
    
    

}
