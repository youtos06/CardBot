package com.cardsMircroservice.dataProvider.controllers;

import com.cardsMircroservice.dataProvider.daos.CardDao;
import com.cardsMircroservice.dataProvider.models.Card;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("")
public class CardController {

    final
    private CardDao cardDao;

    public CardController(CardDao cardDao) {
        this.cardDao = cardDao;
    }

    @GetMapping("/")
    public List<Card> getAllCards() {
        return cardDao.findAll();
    }

    @GetMapping("/ds/{ds}")
    public ResponseEntity<List<Card>> getAllCardsWIthDs(@PathVariable("ds") boolean ds) {

        return ResponseEntity.ok().body(cardDao.getAllByDs(ds));
    }

    @GetMapping("/continent/{continent}")
    public ResponseEntity<List<Card>> getAllCardsInContinent(@PathVariable("continent") String continent) {
        continent = continent.toLowerCase();
        List<Card> cards;
        if(continent.equals("all")){
            cards = cardDao.findAll();
        }else{
            cards = cardDao.getAllByContinentContains(continent);
        }


        return ResponseEntity.ok().body(cards);
    }

    @GetMapping("/country/{country}")
    public ResponseEntity<List<Card>> getAllCardsInCountry(@PathVariable("country") String country) {
        country = country.toLowerCase();
        List<Card> cards;
        if(country.equals("all")){
            cards = cardDao.findAll();
        }else {
            cards = cardDao.getAllByCountryContaining(country);
        }
        return ResponseEntity.ok().body(cards);
    }

    @GetMapping("/continent/{continent}/ds/{ds}")
    public ResponseEntity<List<Card>> getAllCardsInContinentWithDs(@PathVariable("continent") String continent, @PathVariable("ds") boolean ds) {
        continent = continent.toLowerCase();
        List<Card> cards;
        if(continent.equals("all")){
            cards = cardDao.getAllByDs(ds);
        }else {
            cards = cardDao.getAllByContinentContainsAndDs(continent, ds);
        }
        return ResponseEntity.ok().body(cards);
    }

    @GetMapping("/country/{country}/ds/{ds}")
    public ResponseEntity<List<Card>> getAllCardsInCountryWithDs(@PathVariable("country") String country, @PathVariable("ds") boolean ds) {
        country = country.toLowerCase();
        List<Card> cards;
        if(country.equals("all")){
            cards = cardDao.getAllByDs(ds);
        }else {
            cards = cardDao.getAllByCountryContainingAndDs(country, ds);
        }
        return ResponseEntity.ok().body(cards);
    }

    @GetMapping("/continent/{continent}/type/{type}")
    public ResponseEntity<List<Card>> getAllCardsInContinentWithType(@PathVariable("continent") String continent, @PathVariable("type") String type) {
        continent = continent.toLowerCase();
        type = type.toLowerCase();
        List<Card> cards;
        if(continent.equals("all")){
            cards = cardDao.getAllByTypeContaining(type);
        }else {
            cards = cardDao.getAllByContinentContainsAndTypeContains(continent, type);
        }
        return ResponseEntity.ok().body(cards);
    }

    @GetMapping("/country/{country}/type/{type}")
    public ResponseEntity<List<Card>> getAllCardsInCountryWithType(@PathVariable("country") String country, @PathVariable("type") String type) {
        country = country.toLowerCase();
        type = type.toLowerCase();
        List<Card> cards;
        if(country.equals("all")){
            cards = cardDao.getAllByTypeContaining(type);
        }else {
            cards = cardDao.getAllByCountryContainingAndTypeContains(country, type);
        }
        return ResponseEntity.ok().body(cards);
    }

    @GetMapping("/continent/{continent}/type/{type}/ds/{ds}")
    public ResponseEntity<List<Card>> getAllCardsInContinentWithType(@PathVariable("continent") String continent, @PathVariable("type") String type,@PathVariable("ds") boolean ds) {
        continent = continent.toLowerCase();
        type = type.toLowerCase();
        List<Card> cards;
        if(continent.equals("all")){
            cards = cardDao.getAllByTypeContainingAndDs(type,ds);
        }else {
            cards = cardDao.getAllByContinentContainsAndTypeContainsAndDs(continent, type, ds);
        }
        return ResponseEntity.ok().body(cards);
    }

    @GetMapping("/country/{country}/type/{type}/ds/{ds}")
    public ResponseEntity<List<Card>> getAllCardsInCountryWithType(@PathVariable("country") String country, @PathVariable("type") String type,@PathVariable("ds") boolean ds) {
        country = country.toLowerCase();
        type = type.toLowerCase();
        List<Card> cards;
        if(country.equals("all")){
            cards = cardDao.getAllByTypeContainingAndDs(type,ds);
        }else {
            cards = cardDao.getAllByCountryContainingAndTypeContainsAndDs(country, type, ds);
        }
        return ResponseEntity.ok().body(cards);
    }

    @GetMapping("/continent/{continent}/country/{country}/type/{type}/ds/{ds}")
    public List<Card> getCardsAccordingToData(@PathVariable("continent") String continent,@PathVariable("country") String country, @PathVariable("type") String type,@PathVariable("ds") boolean ds){
        continent = continent.toLowerCase();
        country = country.toLowerCase();
        type = type.toLowerCase();

        if (continent.equals("all") && country.equals("all") && type.equals("all")){
            return cardDao.getAllByDs(ds);
        }
        if (continent.equals("all") && country.equals("all")){
            return cardDao.getAllByTypeContainingAndDs(type,ds);
        }
        if(continent.equals("all") && type.equals("all")){
            return cardDao.getAllByCountryContainingAndDs(type,ds);
        }if (country.equals("all") && type.equals("all")){
            return cardDao.getAllByContinentContainsAndDs(type,ds);
        }if (type.equals("all")){
            return cardDao.getAllByContinentContainsAndDs(continent,ds);
        }
        if (continent.equals("all")){
            return cardDao.getAllByCountryContainingAndTypeContainsAndDs(country,type,ds);
        }
        if (country.equals("all")){
            return cardDao.getAllByContinentContainsAndTypeContainsAndDs(continent,type,ds);
        }
        return cardDao.findAll();
    }


}
