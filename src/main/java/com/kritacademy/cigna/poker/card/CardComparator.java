package com.kritacademy.cigna.poker.card;

import java.util.Comparator;

/**
 * @author krit <chertpong.github.io> on 3/27/2017.
 */
public class CardComparator implements Comparator<Card> {
    @Override
    public int compare(Card card1, Card card2) {
        return card1.getRank().getValue() - card2.getRank().getValue();
    }
}
