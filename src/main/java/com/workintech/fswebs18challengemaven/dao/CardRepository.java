package com.workintech.fswebs18challengemaven.dao;
import com.workintech.fswebs18challengemaven.model.Card;

import java.util.List;

public interface CardRepository {
    Card save(Card card);
    List<Card> findByColor(String color);
    List<Card> findAll();
    List<Card> findByValue(Integer value);
    List<Card> findByType(String type);
    Card update(Card card);
    void remove(Long id);
}
