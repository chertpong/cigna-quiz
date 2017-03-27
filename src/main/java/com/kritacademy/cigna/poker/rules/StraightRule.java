package com.kritacademy.cigna.poker.rules;

import com.kritacademy.cigna.poker.card.Card;
import com.kritacademy.cigna.poker.card.CardComparator;
import com.kritacademy.cigna.poker.card.CardFactory;
import com.kritacademy.cigna.poker.card.CardFactoryImpl;

import java.util.Comparator;
import java.util.List;

/**
 * @author krit <chertpong.github.io> on 3/27/2017.
 */
public class StraightRule implements Rule {
    private CardComparator cardComparator;

    public StraightRule() {
        this.cardComparator = new CardComparator();
    }

    public StraightRule(CardComparator cardComparator) {
        this.cardComparator = cardComparator;
    }

    public Boolean isValid(List<Card> cards) {
        boolean result = true;
        cards.sort(cardComparator);
        for(int i = 1; i < cards.size(); i++) {
            // if the diff is not equal 1
            if((cards.get(i).getRank().getValue() - cards.get(i - 1).getRank().getValue()) != 1) {
                result = false;
            }
        }
        // special case, ace can act like highest or lowest
        if (cards.get(4).getRank() == Card.Rank.ACE) {
            // ignore ace if it act as lowesr
           if (cards.get(0).getRank() == Card.Rank.TWO) {
               for(int i = 1; i < cards.size() - 1; i++) {
                   // if the diff is not equal 1
                   if((cards.get(i).getRank().getValue() - cards.get(i - 1).getRank().getValue()) != 1) {
                       return false;
                   }
               }
               return true;
           }
        }
        return result;
    }

    @Override
    public Integer tieValue(List<Card> cards) {
        cards.sort(cardComparator);
        return cards.get(4).getRank().getValue();
    }
}
