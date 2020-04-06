package com.chatBot.openNplCardChatBot.dataElement;

import com.chatBot.openNplCardChatBot.dataElement.basic.Continent;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;


import java.util.List;

public class ContinentList {
    private List<Continent> continents;

    public ContinentList() {
        try {
            String jsonText = "[{\"code\": \"AF\", \"name\": \"Africa\"},{\"code\": \"NA\", \"name\": \"North America\"},{\"code\": \"OC\", \"name\": \"Oceania\"},{\"code\": \"AN\", \"name\": \"Antarctica\"},{\"code\": \"AS\", \"name\": \"Asia\"},{\"code\": \"EU\", \"name\": \"Europe\"},{\"code\": \"SA\", \"name\": \"South America\"}]";
            ObjectMapper mapper = new ObjectMapper();
            List<Continent> continents = mapper.readValue(jsonText, new TypeReference<List<Continent>>() {
            });
            this.continents = continents;
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public String getContinent(String text) {
        for (Continent continent : this.continents) {
            if (text.contains(continent.getName().toLowerCase())) {
                return continent.getName().toLowerCase();
            }else {
                if (text.contains(continent.getCode().toLowerCase())){

                    int index = text.indexOf(continent.getCode().toLowerCase());
                    boolean firstPositionCondition=true ;
                    if(index -1 >=0){
                        firstPositionCondition=text.charAt(index - 1) == ' ' || text.charAt(index - 1) == '.' || text.charAt(index -1) == '?' || text.charAt(index -1) == '!';
                    }

                    boolean lastPositionCondition=true;
                    if (index+2<text.length()){
                        lastPositionCondition =  text.charAt(index + 2) == ' ' || text.charAt(index + 2) == '.' || text.charAt(index +2) == '?' || text.charAt(index +2) == '!';
                    }

                    if(firstPositionCondition && lastPositionCondition){
                        return continent.getName().toLowerCase();
                    }
                    if(index == 0){
                        if (lastPositionCondition){
                            return continent.getName().toLowerCase();
                        }
                    }
                    if(index == text.length()){
                        if (firstPositionCondition){
                            return continent.getName().toLowerCase();
                        }
                    }
                }
            }
        }
        return "all";
    }
}
