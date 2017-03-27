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
public class ThreeOfKindRuleTest {
    @Test
    public void isValid() throws Exception {
        CardFactory cardFactory = new CardFactoryImpl();
        Rule threeOfKindRule = new ThreeOfKindRule();

        // given three cards have same rank
        List<Card> case1 = new ArrayList<>();
        case1.add(cardFactory.make("C", "2"));
        case1.add(cardFactory.make("H", "2"));
        case1.add(cardFactory.make("S", "2"));
        case1.add(cardFactory.make("H", "5"));
        case1.add(cardFactory.make("H", "6"));
        // when case1 is valid, then pass
        assertThat(threeOfKindRule.isValid(case1)).isTrue();

        // given four cards have same rank
        List<Card> case2 = new ArrayList<>();
        case2.add(cardFactory.make("H", "K"));
        case2.add(cardFactory.make("S", "K"));
        case2.add(cardFactory.make("C", "K"));
        case2.add(cardFactory.make("D", "K"));
        case2.add(cardFactory.make("S", "5"));

        // when case2 is valid, then pass
        assertThat(threeOfKindRule.isValid(case2)).isTrue();

        // given two cards have same rank
        List<Card> case3 = new ArrayList<>();
        case3.add(cardFactory.make("H", "2"));
        case3.add(cardFactory.make("C", "2"));
        case3.add(cardFactory.make("S", "4"));
        case3.add(cardFactory.make("S", "5"));
        case3.add(cardFactory.make("S", "6"));

        // when case3 is invalid, then should fail
        assertThat(threeOfKindRule.isValid(case3)).isFalse();
    }
}
