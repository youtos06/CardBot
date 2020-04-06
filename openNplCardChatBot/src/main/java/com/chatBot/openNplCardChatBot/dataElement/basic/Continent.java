package com.chatBot.openNplCardChatBot.dataElement.basic;

public class Continent {
    private String code;
    private String name;

    public Continent() {
    }

    public Continent(String Code, String Name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String Code) {
        this.code = Code;
    }

    public String getName() {
        return name;
    }

    public void setName(String Name) {
        this.name = Name;
    }
}
