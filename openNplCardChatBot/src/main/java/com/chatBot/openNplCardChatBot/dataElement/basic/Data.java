package com.chatBot.openNplCardChatBot.dataElement.basic;

public class Data {
    private String continent;
    private String country;
    private String type;
    private boolean ds;

    public Data(String continent, String country, String type, boolean ds) {
        this.continent = continent;
        this.country = country;
        this.type = type;
        this.ds = ds;
    }

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isDs() {
        return ds;
    }

    public void setDs(boolean ds) {
        this.ds = ds;
    }
}
