package com.springboot.id1212project.service.impl;

import com.springboot.id1212project.exception.ResourceNotFoundException;
import com.springboot.id1212project.model.Card;
import com.springboot.id1212project.repository.CardRepository;
import com.springboot.id1212project.service.CardService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class CardServiceImpl implements CardService {

    private CardRepository cardRepository;
    //We do not have to add autowired annotation to this constructor whenever springboot finds spring bean it has only
    //one constructor then springboot will automatically autowired this dependency
    public CardServiceImpl(CardRepository cardRepository){
        super();
        this.cardRepository = cardRepository;
    }

    @Override
    public List<Card> getCardByUserId(long id) {

        List<Card> card = cardRepository.findByUserId(id);
        if(!card.isEmpty()){
            return card;
        }else{
            throw new ResourceNotFoundException("Card", "UserId", id);
        }
    }

    @Override
    public List<Card> getCardWithState(String status) {
        List<Card> card = cardRepository.findByState(status);
        if(card != null){
            return card;
        }else{
            throw new ResourceNotFoundException("Card", "UserId", status);
        }
    }

    @Override
    public Card updateCardState(long userId, String status) {
        Card  existingCard = cardRepository.findByUserId(userId).get(0);
        existingCard.setStatus(status);
        cardRepository.save(existingCard);
        return existingCard;
    }

    @Override
    public Card updateCardStateById(long id, String status) {
        Card  existingCard = cardRepository.findById(id).get();
        existingCard.setStatus(status);
        cardRepository.save(existingCard);
        return existingCard;
    }

    @Override
    public Card updateCardPriceByEmail(long userId, String newPrice) {
        Card  existingCard = cardRepository.findByUserId(userId).get(0);
        existingCard.setPrice(newPrice);
        cardRepository.save(existingCard);
        return existingCard;

    }
}
