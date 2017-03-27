package com.kritacademy.cigna.poker.rules;

import com.kritacademy.cigna.poker.card.Card;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author krit <chertpong.github.io> on 3/27/2017.
 */
public class RuleUtils {
    public static Map<Card.Rank, List<Card>> toMapWithKeyOfRank(List<Card> cards) {
        Map<Card.Rank, List<Card>> result = new HashMap<>();
        cards.forEach(card -> {
            if(result.containsKey(card.getRank())) {
                result.get(card.getRank()).add(card);
            }
            else {
                List<Card> temp = new ArrayList<>();
                temp.add(card);
                result.put(card.getRank(), temp);
            }
        });
        return result;
    }

    public static Map<Card.Kind, List<Card>> toMapWithKeyOfKind(List<Card> cards) {
        Map<Card.Kind, List<Card>> result = new HashMap<>();
        cards.forEach(card -> {
            if(result.containsKey(card.getKind())) {
                result.get(card.getKind()).add(card);
            }
            else {
                List<Card> temp = new ArrayList<>();
                temp.add(card);
                result.put(card.getKind(), temp);
            }
        });
        return result;
    }
}
