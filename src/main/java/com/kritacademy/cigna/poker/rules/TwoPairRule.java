package com.kritacademy.cigna.poker.rules;

import com.kritacademy.cigna.poker.card.Card;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author krit <chertpong.github.io> on 3/27/2017.
 */
public class TwoPairRule implements Rule {
    public Boolean isValid(List<Card> cards) {
        Map<Card.Rank, List<Card>> pairs = RuleUtils.toMapWithKeyOfRank(cards);
        return pairs.entrySet().stream().filter(entry -> entry.getValue().size() >= 2).count() >= 2;
    }

    @Override
    public Integer tieValue(List<Card> cards) {
        if (this.isValid(cards)) {
            Map<Card.Rank, List<Card>> pairMap = RuleUtils.toMapWithKeyOfRank(cards);
            return pairMap.entrySet().stream()
                    .filter(entry -> entry.getValue().size() >= 2)
                    .map(entry -> entry.getKey().getValue())
                    .reduce(Integer::max).orElse(0);
        }
        throw new RuntimeException("Not valid input");
    }
}
