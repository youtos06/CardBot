package com.cardsMircroservice.dataProvider.daos;

import com.cardsMircroservice.dataProvider.models.Card;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CardDao extends JpaRepository<Card,Long> {
    List<Card> getAllByDs(boolean ds);
    List<Card> getAllByContinentContains(String continent);
    List<Card> getAllByCountryContaining(String country);
    List<Card> getAllByContinentContainsAndDs(String continent,boolean ds);
    List<Card> getAllByCountryContainingAndDs(String country,boolean ds);
    List<Card> getAllByContinentContainsAndTypeContains(String continent,String type);
    List<Card> getAllByCountryContainingAndTypeContains(String country,String type);
    List<Card> getAllByContinentContainsAndTypeContainsAndDs(String continent,String type,boolean ds);
    List<Card> getAllByCountryContainingAndTypeContainsAndDs(String country,String type,boolean ds);


    //--------- for the exception of continent or country as all

    List<Card> getAllByTypeContaining(String type);
    List<Card> getAllByTypeContainingAndDs(String type,boolean ds);
}
