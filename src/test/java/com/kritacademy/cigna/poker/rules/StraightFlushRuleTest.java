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
public class StraightFlushRuleTest {
    @Test
    public void isValid() throws Exception {
        CardFactory cardFactory = new CardFactoryImpl();
        Rule straightFlush = new StraightFlushRule();

        // given all valid
        List<Card> case1 = new ArrayList<>();
        case1.add(cardFactory.make("S", "A"));
        case1.add(cardFactory.make("S", "K"));
        case1.add(cardFactory.make("S", "Q"));
        case1.add(cardFactory.make("S", "J"));
        case1.add(cardFactory.make("S", "10"));
        // when case1 is valid, then pass
        assertThat(straightFlush.isValid(case1)).isTrue();

        // given all valid
        List<Card> case2 = new ArrayList<>();
        case2.add(cardFactory.make("C", "K"));
        case2.add(cardFactory.make("C", "Q"));
        case2.add(cardFactory.make("C", "A"));
        case2.add(cardFactory.make("C", "J"));
        case2.add(cardFactory.make("C", "10"));

        // when case2 is valid, then pass
        assertThat(straightFlush.isValid(case2)).isTrue();

        // given wrong kind
        List<Card> case3 = new ArrayList<>();
        case3.add(cardFactory.make("S", "A"));
        case3.add(cardFactory.make("S", "K"));
        case3.add(cardFactory.make("S", "J"));
        case3.add(cardFactory.make("S", "Q"));
        case3.add(cardFactory.make("H", "10"));

        // when case3 is invalid, then should return false
        assertThat(straightFlush.isValid(case3)).isFalse();

        // given wrong rank
        List<Card> case4 = new ArrayList<>();
        case4.add(cardFactory.make("S", "A"));
        case4.add(cardFactory.make("S", "K"));
        case4.add(cardFactory.make("S", "J"));
        case4.add(cardFactory.make("S", "Q"));
        case4.add(cardFactory.make("S", "9"));

        // when case4 is invalid, then should return false
        assertThat(straightFlush.isValid(case4)).isFalse();

        // given wrong rank
        List<Card> case5 = new ArrayList<>();
        case5.add(cardFactory.make("S", "A"));
        case5.add(cardFactory.make("S", "K"));
        case5.add(cardFactory.make("S", "J"));
        case5.add(cardFactory.make("S", "Q"));
        case5.add(cardFactory.make("S", "9"));

        // when case5 is invalid, then should return false
        assertThat(straightFlush.isValid(case5)).isFalse();

        // given wrong rank, wrong kind
        List<Card> case6 = new ArrayList<>();
        case6.add(cardFactory.make("S", "A"));
        case6.add(cardFactory.make("S", "K"));
        case6.add(cardFactory.make("S", "J"));
        case6.add(cardFactory.make("S", "4"));
        case6.add(cardFactory.make("H", "9"));

        // when case6 is invalid, then should return false
        assertThat(straightFlush.isValid(case6)).isFalse();
    }
}
