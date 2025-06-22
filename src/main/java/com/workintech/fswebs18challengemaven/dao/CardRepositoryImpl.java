package com.workintech.fswebs18challengemaven.dao;

import com.workintech.fswebs18challengemaven.model.Card;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class CardRepositoryImpl implements CardRepository {

    private final ConcurrentHashMap<Long, Card> cardDb = new ConcurrentHashMap<>();
    private long idCounter = 1;

    @Override
    public Card save(Card card) {
        card.setId(idCounter++);
        cardDb.put(card.getId(), card);
        return card;
    }

    @Override
    public List<Card> findByColor(String color) {
        return cardDb.values().stream()
                .filter(card -> card.getColor().equalsIgnoreCase(color))
                .toList();
    }

    @Override
    public List<Card> findAll() {
        return new ArrayList<>(cardDb.values());
    }

    @Override
    public List<Card> findByValue(Integer value) {
        return cardDb.values().stream()
                .filter(card -> card.getValue().equals(value))
                .toList();
    }

    @Override
    public List<Card> findByType(String type) {
        return cardDb.values().stream()
                .filter(card -> card.getType().equalsIgnoreCase(type))
                .toList();
    }

    @Override
    public Card update(Card card) {
        if (!cardDb.containsKey(card.getId())) {
            throw new RuntimeException("Card not found with ID: " + card.getId());
        }
        cardDb.put(card.getId(), card);
        return card;
    }

    @Override
    public void remove(Long id) {
        cardDb.remove(id);
    }
}
