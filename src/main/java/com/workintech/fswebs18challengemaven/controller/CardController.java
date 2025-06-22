package com.workintech.fswebs18challengemaven.controller;
import com.workintech.fswebs18challengemaven.dao.CardRepositoryImpl;
import com.workintech.fswebs18challengemaven.model.Card;
import com.workintech.fswebs18challengemaven.util.CardValidation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/workintech/cards")
@Slf4j
public class CardController {

    private final CardRepositoryImpl cardRepository;

    public CardController(CardRepositoryImpl cardRepository) {
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
        CardValidation.validate(card);
        return cardRepository.save(card);
    }

    @PutMapping
    public Card updateCard(@RequestBody Card card) {
        CardValidation.validate(card);
        return cardRepository.update(card);
    }

    @DeleteMapping("/{id}")
    public void deleteCard(@PathVariable Long id) {
        cardRepository.remove(id);
    }
}
