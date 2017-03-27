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
public class RoyalFlushRuleTest {
    @Test
    public void isValid() throws Exception {
        CardFactory cardFactory = new CardFactoryImpl();
        Rule royalFlushRule = new RoyalFlushRule();

        // given all valid
        List<Card> case1 = new ArrayList<>();
        case1.add(cardFactory.make("S", "A"));
        case1.add(cardFactory.make("S", "K"));
        case1.add(cardFactory.make("S", "Q"));
        case1.add(cardFactory.make("S", "J"));
        case1.add(cardFactory.make("S", "10"));
        // when case1 is valid, then pass
        assertThat(royalFlushRule.isValid(case1)).isTrue();

        // given all valid
        List<Card> case2 = new ArrayList<>();
        case2.add(cardFactory.make("C", "K"));
        case2.add(cardFactory.make("C", "Q"));
        case2.add(cardFactory.make("C", "A"));
        case2.add(cardFactory.make("C", "J"));
        case2.add(cardFactory.make("C", "10"));

        // when case2 is valid, then pass
        assertThat(royalFlushRule.isValid(case2)).isTrue();

        // given wrong kind
        List<Card> case3 = new ArrayList<>();
        case3.add(cardFactory.make("S", "A"));
        case3.add(cardFactory.make("S", "K"));
        case3.add(cardFactory.make("S", "J"));
        case3.add(cardFactory.make("S", "Q"));
        case3.add(cardFactory.make("H", "10"));

        // when case3 is invalid, then should return false
        assertThat(royalFlushRule.isValid(case3)).isFalse();

        // given wrong rank
        List<Card> case4 = new ArrayList<>();
        case4.add(cardFactory.make("S", "A"));
        case4.add(cardFactory.make("S", "K"));
        case4.add(cardFactory.make("S", "J"));
        case4.add(cardFactory.make("S", "Q"));
        case4.add(cardFactory.make("S", "9"));

        // when case4 is invalid, then should return false
        assertThat(royalFlushRule.isValid(case4)).isFalse();
    }

}