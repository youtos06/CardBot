package com.chatBot.openNplCardChatBot.controllers;


import com.chatBot.openNplCardChatBot.beans.CardBean;
import com.chatBot.openNplCardChatBot.dataElement.basic.Data;
import com.chatBot.openNplCardChatBot.firstChatBot.CardChatBot;
import com.chatBot.openNplCardChatBot.proxy.MicroServiceCardProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/message")
public class bootRest {

    private CardChatBot cardChatBot;

    @Autowired
    MicroServiceCardProxy microServiceCardProxy;

    public bootRest() {
        this.cardChatBot = new CardChatBot();
    }


    @PostMapping("")
    public ResponseEntity<String> getResponseForMessage(@RequestBody String message) {


        message = message.toLowerCase();

        String category = cardChatBot.getCategoryOfUserInput(message);
            if (category.equals("conversation-complete")) {
                return new ResponseEntity<>("goodbye", HttpStatus.OK);
            }
            if (category.equals("greeting")) {
                return new ResponseEntity<>("hey! this is a  bot about credit cards! my understanding is basic so please be specific", HttpStatus.OK);
            }
            if (category.equals("getCard")) {
                Data dataToSend = cardChatBot.getDataInsideInput(message);
                String response = "";
                response = response+"Continent : " + dataToSend.getContinent();
                response = response+"\nCountry : " + dataToSend.getContinent();
                response = response+"\nCard Type :" + dataToSend.getType();
                response = response+"\n3Ds enabled :" + dataToSend.isDs();
                List<CardBean> cards = microServiceCardProxy.getCardsAccordingToData(dataToSend.getContinent(),dataToSend.getCountry(),dataToSend.getType(),dataToSend.isDs());
                if(cards.size()>0){
                    response = response + " \n\nCARDS : \n";
                    for(CardBean cardBean : cards){
                        response = response + cardBean.toString();
                    }
                    return new ResponseEntity<>(response, HttpStatus.OK);
                }else{
                    return new ResponseEntity<>(response+" sadly database has no card with requested parameters", HttpStatus.OK);
                }
            }

            return new ResponseEntity<>(category, HttpStatus.OK);



    }
}
