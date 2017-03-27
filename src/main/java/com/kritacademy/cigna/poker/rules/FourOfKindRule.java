package com.kritacademy.cigna.poker.rules;

import com.kritacademy.cigna.poker.card.Card;
import com.kritacademy.cigna.poker.card.CardComparator;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author krit <chertpong.github.io> on 3/27/2017.
 */
public class FourOfKindRule implements Rule {
    private CardComparator cardComparator;

    public FourOfKindRule() {
        this.cardComparator = new CardComparator();
    }

    public FourOfKindRule(CardComparator cardComparator) {
        this.cardComparator = cardComparator;
    }
    @Override
    public Boolean isValid(List<Card> cards) {
        Map<Card.Rank, List<Card>> ranks = RuleUtils.toMapWithKeyOfRank(cards);
        return ranks.entrySet().stream().anyMatch(entry -> entry.getValue().size() >= 4);
    }

    @Override
    public Integer tieValue(List<Card> cards) {
        if (this.isValid(cards)) {
            Map<Card.Rank, List<Card>> ranks = RuleUtils.toMapWithKeyOfRank(cards);
            List<Card.Rank> keys = ranks.entrySet().stream().map(Map.Entry::getKey).collect(Collectors.toList());
            // if key 0 is four of kind, then return value of rank of key 1
            if (ranks.get(keys.get(0)).size() == 4) {
                return ranks.get(keys.get(1)).get(0).getRank().getValue();
            }
            // if key 1 is four of kind, then return value of rank of key 0
            else {
                return ranks.get(keys.get(0)).get(0).getRank().getValue();
            }
        }
        throw new RuntimeException("Not valid input");
    }
}
