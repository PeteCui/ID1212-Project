package com.springboot.id1212project.service;

import com.springboot.id1212project.model.Card;

import java.util.List;

public interface CardService {

    List<Card> getCardByUserId(long id);
    List<Card> getCardWithState(String status);
    Card updateCardState(long userId, String status);
    Card updateCardStateById(long id, String operation);
    Card updateCardPriceByEmail(long id, String newPrice);
}
