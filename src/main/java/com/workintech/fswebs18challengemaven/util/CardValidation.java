package com.workintech.fswebs18challengemaven.util;


import com.workintech.fswebs18challengemaven.model.Card;

public class CardValidation {
    public static void validate(Card card) {
        if (card.getColor() == null || card.getColor().isEmpty()) {
            throw new IllegalArgumentException("Card color is required.");
        }
        if (card.getType() == null || card.getType().isEmpty()) {
            throw new IllegalArgumentException("Card type is required.");
        }
        if (card.getValue() == null || card.getValue() < 0) {
            throw new IllegalArgumentException("Card value must be a positive integer.");
        }
    }
}
