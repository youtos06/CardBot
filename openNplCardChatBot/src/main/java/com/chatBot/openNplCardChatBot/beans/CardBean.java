package com.chatBot.openNplCardChatBot.beans;

public class CardBean {
    private Long id;

    private String number;
    private String date;
    private String securityNumber;

    private boolean ds ;

    private String continent ;

    private String country ;

    private String type ;

    public CardBean() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSecurityNumber() {
        return securityNumber;
    }

    public void setSecurityNumber(String securityNumber) {
        this.securityNumber = securityNumber;
    }

    public boolean isDs() {
        return ds;
    }

    public void setDs(boolean ds) {
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

    @Override
    public String toString() {
        return "Card {" +

                ", number='" + number + '\'' +
                ", date='" + date + '\'' +
                ", securityNumber='" + securityNumber + '\'' +
                ", ds=" + ds +
                ", continent='" + continent + '\'' +
                ", country='" + country + '\'' +
                ", type='" + type + '\'' +
                "}\n";
    }
}
