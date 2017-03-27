package com.kritacademy.cigna.poker.rules;

import com.kritacademy.cigna.poker.card.Card;
import com.kritacademy.cigna.poker.card.CardFactory;
import com.kritacademy.cigna.poker.card.CardFactoryImpl;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author krit <chertpong.github.io> on 3/27/2017.
 */
public class TwoPairRuleTest {
    @Test
    public void isValid() throws Exception {
        CardFactory cardFactory = new CardFactoryImpl();
        Rule twoPairRule = new TwoPairRule();

        // given two pair
        List<Card> case1 = new ArrayList<>();
        case1.add(cardFactory.make("H", "2"));
        case1.add(cardFactory.make("C", "2"));
        case1.add(cardFactory.make("S", "4"));
        case1.add(cardFactory.make("S", "4"));
        case1.add(cardFactory.make("S", "6"));
        // when case1 is valid, then pass
        assertThat(twoPairRule.isValid(case1)).isTrue();

        // given two pair
        List<Card> case2 = new ArrayList<>();
        case2.add(cardFactory.make("H", "2"));
        case2.add(cardFactory.make("S", "2"));
        case2.add(cardFactory.make("C", "K"));
        case2.add(cardFactory.make("D", "2"));
        case2.add(cardFactory.make("S", "K"));

        // when case2 is valid, then pass
        assertThat(twoPairRule.isValid(case2)).isTrue();

        // given no pair
        List<Card> case3 = new ArrayList<>();
        case3.add(cardFactory.make("S", "2"));
        case3.add(cardFactory.make("S", "3"));
        case3.add(cardFactory.make("S", "4"));
        case3.add(cardFactory.make("S", "5"));
        case3.add(cardFactory.make("S", "6"));

        // when case3 is invalid, then should return false
        assertThat(twoPairRule.isValid(case3)).isFalse();
    }
}
