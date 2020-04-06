package com.chatBot.openNplCardChatBot.firstChatBot;

public class LearnerSide {
    private String question;
    private String isRelatedToCard;
    private String continent;
    private String country;
    private String CardType;
    private boolean isDsEnabled;

    public LearnerSide(String question, String isRelatedToCard, String continent, String country, String cardType, boolean isDsEnabled) {
        this.question = question;
        this.isRelatedToCard = isRelatedToCard;
        this.continent = continent;
        this.country = country;
        CardType = cardType;
        this.isDsEnabled = isDsEnabled;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getIsRelatedToCard() {
        return isRelatedToCard;
    }

    public void setIsRelatedToCard(String isRelatedToCard) {
        this.isRelatedToCard = isRelatedToCard;
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

    public String getCardType() {
        return CardType;
    }

    public void setCardType(String cardType) {
        CardType = cardType;
    }

    public boolean isDsEnabled() {
        return isDsEnabled;
    }

    public void setDsEnabled(boolean dsEnabled) {
        isDsEnabled = dsEnabled;
    }
}
