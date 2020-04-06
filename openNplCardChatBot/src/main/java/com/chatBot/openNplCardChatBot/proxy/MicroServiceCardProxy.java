package com.chatBot.openNplCardChatBot.proxy;

import com.chatBot.openNplCardChatBot.beans.CardBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name="microservice-Cards",url = "localhost:8080")
public interface MicroServiceCardProxy {

    @GetMapping("/continent/{continent}/country/{country}/type/{type}/ds/{ds}")
    List<CardBean> getCardsAccordingToData(@PathVariable("continent") String continent, @PathVariable("country") String country, @PathVariable("type") String type, @PathVariable("ds") boolean ds);

    @GetMapping("/")
    List<CardBean> getAllCards();
}
