package com.chatBot.openNplCardChatBot.dataElement;


import com.chatBot.openNplCardChatBot.dataElement.basic.Country;
import com.chatBot.openNplCardChatBot.reader.FileConvert;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;


public class CountriesList {
    private List<Country> countries;


    public CountriesList(){
        try{
            FileConvert fileConvert = new FileConvert();

            ObjectMapper objectMapper = new ObjectMapper();
            this.countries = objectMapper.readValue(fileConvert.getFile("countries.json"),new TypeReference<List<Country>>() {});
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public String getCountries(String text) {
        for (Country continent : this.countries) {
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
