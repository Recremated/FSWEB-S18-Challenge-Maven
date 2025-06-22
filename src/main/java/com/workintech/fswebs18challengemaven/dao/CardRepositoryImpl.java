package com.workintech.fswebs18challengemaven.dao;

import com.workintech.fswebs18challengemaven.entity.Card;
import com.workintech.fswebs18challengemaven.entity.Color;
import com.workintech.fswebs18challengemaven.entity.Type;
import com.workintech.fswebs18challengemaven.exceptions.CardException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class CardRepositoryImpl implements CardRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public CardRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Card save(Card card) {
        entityManager.persist(card);
        return card;
    }

    @Override
    public List<Card> findAll() {
        TypedQuery<Card> query = entityManager.createQuery("SELECT c FROM Card c", Card.class);
        return query.getResultList();
    }

    @Override
    public List<Card> findByColor(String color) {
        Color colorEnum = Color.valueOf(color.toUpperCase());
        TypedQuery<Card> query = entityManager.createQuery("SELECT c FROM Card c WHERE c.color = :color", Card.class);
        query.setParameter("color", colorEnum);
        List<Card> cards = query.getResultList();

        if (cards.isEmpty()) {
            throw new CardException("Cards with color " + color + " not found", HttpStatus.NOT_FOUND);
        }

        return cards;
    }

    @Override
    public List<Card> findByValue(Integer value) {
        TypedQuery<Card> query = entityManager.createQuery("SELECT c FROM Card c WHERE c.value = :value", Card.class);
        query.setParameter("value", value);
        return query.getResultList();
    }

    @Override
    public List<Card> findByType(String type) {
        Type typeEnum = Type.valueOf(type.toUpperCase());
        TypedQuery<Card> query = entityManager.createQuery("SELECT c FROM Card c WHERE c.type = :type", Card.class);
        query.setParameter("type", typeEnum);
        return query.getResultList();
    }

    @Override
    public Card update(Card card) {
        return entityManager.merge(card);
    }

    @Override
    public Card remove(Long id) {
        Card card = entityManager.find(Card.class, id);
        if (card != null) {
            entityManager.remove(card);
        }
        return card;
    }
}