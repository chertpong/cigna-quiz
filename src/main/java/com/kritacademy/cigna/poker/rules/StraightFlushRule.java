package com.kritacademy.cigna.poker.rules;

import com.kritacademy.cigna.poker.card.Card;

import java.util.List;

/**
 * @author krit <chertpong.github.io> on 3/27/2017.
 */
public class StraightFlushRule implements Rule {
    private Rule flushRule;
    private Rule straightRule;

    public StraightFlushRule() {
        this.flushRule = new FlushRule();
        this.straightRule = new StraightRule();
    }

    public StraightFlushRule(Rule flushRule, Rule straightRule) {
        this.flushRule = flushRule;
        this.straightRule = straightRule;
    }

    @Override
    public Boolean isValid(List<Card> cards) {
        if (flushRule.isValid(cards)) {
            return straightRule.isValid(cards);
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
