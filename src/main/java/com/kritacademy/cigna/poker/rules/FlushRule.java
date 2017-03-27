package com.kritacademy.cigna.poker.rules;

import com.kritacademy.cigna.poker.card.Card;
import com.kritacademy.cigna.poker.card.CardComparator;

import java.util.List;
import java.util.Map;

/**
 * @author krit <chertpong.github.io> on 3/27/2017.
 */
public class FlushRule implements Rule {
    private CardComparator cardComaparator;

    public FlushRule() {
        this.cardComaparator = new CardComparator();
    }

    public FlushRule(CardComparator cardComaparator) {
        this.cardComaparator = cardComaparator;
    }

    public Boolean isValid(List<Card> cards) {
        final Card.Kind baseKind = cards.get(0).getKind();
        return cards.stream().filter(card -> card.getKind().equals(baseKind)).count() == cards.size();
    }

    @Override
    public Integer tieValue(List<Card> cards) {
        if (this.isValid(cards)) {
            cards.sort(cardComaparator);
            return cards.get(4).getRank().getValue();
        }
        throw new RuntimeException("Not valid input");

    }
}
