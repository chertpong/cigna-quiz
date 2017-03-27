package com.kritacademy.cigna.poker.rules;

import com.kritacademy.cigna.poker.card.Card;

import java.util.List;

/**
 * @author krit <chertpong.github.io> on 3/27/2017.
 */
public interface Rule {
    Boolean isValid(List<Card> cards);

    /**
     * if both players are tied, this number will tell who is the winner (the larger is the better)
     * @param cards list of cards
     * @return virtual value (larger is the better)
     */
    Integer tieValue(List<Card> cards);
}
