package com.kritacademy.cigna.poker.rules;

import com.kritacademy.cigna.poker.card.Card;
import com.kritacademy.cigna.poker.card.CardComparator;

import java.util.*;

/**
 * @author krit <chertpong.github.io> on 3/27/2017.
 */
public class FullHouseRule implements Rule {
    private Rule pairRule;
    private Comparator cardComparator;

    public FullHouseRule() {
        this.cardComparator = new CardComparator();
        this.pairRule = new PairRule();
    }

    public FullHouseRule(Rule pairRule, Comparator cardComparator) {
        this.cardComparator = cardComparator;
        this.pairRule = pairRule;
    }

    public Boolean isValid(List<Card> cards) {
        Map<Card.Rank, List<Card>> kinds = RuleUtils.toMapWithKeyOfRank(cards);
        Set<Card> cardSet = new HashSet<>(cards);
        Card.Rank threeOfKindType = null;
        for (Card.Rank rank : kinds.keySet()) {
            // if they are three of kind, then remove from card set
            if(kinds.get(rank).size() >= 3) {
                threeOfKindType = rank;
                cardSet.removeAll(kinds.get(rank));
            }
        }

        if(threeOfKindType == null) {
            return false;
        }

        // if no card in set left, then pair is in kinds
        if(cardSet.size() == 0) {
            return pairRule.isValid(cards);
        }
        // if one card in set left, another one is in kind
        else if (cardSet.size() == 1) {
            Card pair = new ArrayList<Card>(cardSet).get(0);
            return cards.stream().filter(card -> card.getRank().getValue() == pair.getRank().getValue()).count() >= 1;
        }
        // check if the left are pair
        else {
            List<Card> pair =  new ArrayList<Card>(cardSet);
            return pair.get(0).getRank().getValue() == pair.get(1).getRank().getValue();
        }

    }

    @Override
    public Integer tieValue(List<Card> cards) {
        if (this.isValid(cards)) {
            Map<Card.Rank, List<Card>> fullHouse = RuleUtils.toMapWithKeyOfRank(cards);
            List<Card.Rank> ranks = new ArrayList<>(fullHouse.keySet());
            // return rank value of three-of-kind
            if(fullHouse.get(ranks.get(0)).size() == 3) {
                return fullHouse.get(ranks.get(0)).get(0).getRank().getValue();
            }
            else {
                return fullHouse.get(ranks.get(1)).get(0).getRank().getValue();
            }
        }
        throw new RuntimeException("Not valid input");
    }
}
