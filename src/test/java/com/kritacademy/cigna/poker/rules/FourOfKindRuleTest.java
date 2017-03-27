package com.kritacademy.cigna.poker.rules;

import com.kritacademy.cigna.poker.card.Card;
import com.kritacademy.cigna.poker.card.CardFactory;
import com.kritacademy.cigna.poker.card.CardFactoryImpl;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
/**
 * @author krit <chertpong.github.io> on 3/27/2017.
 */
public class FourOfKindRuleTest {
    @Test
    public void isValid() throws Exception {
        CardFactory cardFactory = new CardFactoryImpl();
        Rule fourOfKindRule = new FourOfKindRule();

        // given four cards have same rank
        List<Card> case1 = new ArrayList<>();
        case1.add(cardFactory.make("H", "2"));
        case1.add(cardFactory.make("S", "2"));
        case1.add(cardFactory.make("D", "2"));
        case1.add(cardFactory.make("C", "2"));
        case1.add(cardFactory.make("H", "6"));
        // when case1 is valid, then pass
        assertThat(fourOfKindRule.isValid(case1)).isTrue();

        // given four cards have same rank
        List<Card> case2 = new ArrayList<>();
        case2.add(cardFactory.make("H", "Q"));
        case2.add(cardFactory.make("S", "Q"));
        case2.add(cardFactory.make("C", "Q"));
        case2.add(cardFactory.make("D", "Q"));
        case2.add(cardFactory.make("S", "K"));

        // when case2 is valid, then pass
        assertThat(fourOfKindRule.isValid(case2)).isTrue();

        // given three cards have same rank
        List<Card> case3 = new ArrayList<>();
        case3.add(cardFactory.make("H", "2"));
        case3.add(cardFactory.make("C", "2"));
        case3.add(cardFactory.make("S", "2"));
        case3.add(cardFactory.make("S", "5"));
        case3.add(cardFactory.make("S", "6"));

        // when case3 is invalid, then should return false
        assertThat(fourOfKindRule.isValid(case3)).isFalse();
    }
}
