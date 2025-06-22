package com.workintech.fswebs18challengemaven.controller;

import com.workintech.fswebs18challengemaven.entity.Card;
import com.workintech.fswebs18challengemaven.dao.CardRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cards")
@Slf4j
public class CardController {

    private final CardRepository cardRepository;

    public CardController(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    @GetMapping
    public List<Card> getAllCards() {
        return cardRepository.findAll();
    }

    @GetMapping("/byColor/{color}")
    public List<Card> getByColor(@PathVariable String color) {
        return cardRepository.findByColor(color);
    }

    @GetMapping("/byValue/{value}")
    public List<Card> getByValue(@PathVariable Integer value) {
        return cardRepository.findByValue(value);
    }

    @GetMapping("/byType/{type}")
    public List<Card> getByType(@PathVariable String type) {
        return cardRepository.findByType(type);
    }

    @PostMapping
    public Card saveCard(@RequestBody Card card) {
        return cardRepository.save(card);
    }

    @PutMapping
    public Card updateCard(@RequestBody Card card) {
        return cardRepository.update(card);
    }

    @DeleteMapping("/{id}")
    public Card deleteCard(@PathVariable Long id) {
        return cardRepository.remove(id);
    }
}