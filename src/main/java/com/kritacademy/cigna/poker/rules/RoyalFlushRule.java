package com.kritacademy.cigna.poker.rules;

import com.kritacademy.cigna.poker.card.Card;
import com.kritacademy.cigna.poker.card.CardComparator;

import java.util.List;

/**
 * @author krit <chertpong.github.io> on 3/27/2017.
 */
public class RoyalFlushRule implements Rule {
    private Rule flushRule;

    public RoyalFlushRule() {
        this.flushRule = new FlushRule();
    }

    public RoyalFlushRule(Rule flushRule) {
        this.flushRule = flushRule;
    }

    @Override
    public Boolean isValid(List<Card> cards) {
        final Card.Kind baseKind = cards.get(0).getKind();
        if(flushRule.isValid(cards)) {
            cards.sort(new CardComparator());
            if(cards.get(0).getRank() != Card.Rank.TEN) {
                return false;
            }
            if(cards.get(1).getRank() != Card.Rank.JACK) {
                return false;
            }
            if(cards.get(2).getRank() != Card.Rank.QUEEN) {
                return false;
            }
            if(cards.get(3).getRank() != Card.Rank.KING) {
                return false;
            }
            if(cards.get(4).getRank() != Card.Rank.ACE) {
                return false;
            }
            return true;
        }
        return false;
    }

    /**
     * @param cards list of cards
     * @return 0 because all suit are ranked equally
     */
    @Override
    public Integer tieValue(List<Card> cards) {
        return 0;
    }
}
