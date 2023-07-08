package com.hust.cybersec.objects;

import java.util.List;
import java.util.Objects;

public class Dynasty {

    private String startYear;
    private String endYear;
    private String name;
    private List<King> kings;
    private String capital;
    private King founder;

    public Dynasty() {

    }

    public Dynasty(String name) {
        super();
        this.name = name;
    }

    public Dynasty(String startYear, String endYear, String name, List<King> kings, String capital,
            King founder) {
        super();
        this.startYear = startYear;
        this.endYear = endYear;
        this.name = name;
        this.kings = kings;
        this.capital = capital;
        this.founder = founder;
    }

    public Dynasty(String startYear, String endYear, String name) {
        super();
        this.startYear = startYear;
        this.endYear = endYear;
        this.name = name;
    }

    public King getFounder() {
        return founder;
    }

    public void setFounder(King founder) {
        this.founder = founder;
    }

    public String getStartYear() {
        return startYear;
    }

    public void setStartYear(String startYear) {
        this.startYear = startYear;
    }

    public String getEndYear() {
        return endYear;
    }

    public void setEndYear(String endYear) {
        this.endYear = endYear;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<King> getKings() {
        return kings;
    }

    public void setKings(List<King> kings) {
        this.kings = kings;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String location) {
        this.capital = location;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Dynasty)) {
            return false;
        }
        Dynasty d = (Dynasty) obj;
        return (d.getName().toLowerCase().compareToIgnoreCase(name) == 0);
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + Objects.hashCode(this.name);
        return hash;
    }

}
