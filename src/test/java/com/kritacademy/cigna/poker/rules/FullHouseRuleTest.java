package com.kritacademy.cigna.poker.rules;

import com.kritacademy.cigna.poker.card.Card;
import com.kritacademy.cigna.poker.card.CardComparator;
import com.kritacademy.cigna.poker.card.CardFactory;
import com.kritacademy.cigna.poker.card.CardFactoryImpl;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

/**
 * @author krit <chertpong.github.io> on 3/27/2017.
 */
public class FullHouseRuleTest {
    @Test
    public void isValid() throws Exception {
        CardFactory cardFactory = new CardFactoryImpl();
        Rule fullHouseRule = new FullHouseRule();

        // given one three-of-kind and one pair
        List<Card> case1 = new ArrayList<>();
        case1.add(cardFactory.make("H", "2"));
        case1.add(cardFactory.make("S", "2"));
        case1.add(cardFactory.make("D", "2"));
        case1.add(cardFactory.make("S", "5"));
        case1.add(cardFactory.make("C", "5"));
        // when case1 is valid, then pass
        assertThat(fullHouseRule.isValid(case1)).isTrue();

        // given one three-of-kind and one pair
        List<Card> case2 = new ArrayList<>();
        case2.add(cardFactory.make("H", "2"));
        case2.add(cardFactory.make("S", "2"));
        case2.add(cardFactory.make("H", "K"));
        case2.add(cardFactory.make("S", "K"));
        case2.add(cardFactory.make("D", "K"));

        // when case2 is valid, then pass
        assertThat(fullHouseRule.isValid(case2)).isTrue();

        // given three-of-kind and else are not pair
        List<Card> case3 = new ArrayList<>();
        case3.add(cardFactory.make("H", "2"));
        case3.add(cardFactory.make("C", "2"));
        case3.add(cardFactory.make("S", "2"));
        case3.add(cardFactory.make("S", "5"));
        case3.add(cardFactory.make("S", "6"));

        // when case3 is invalid, then should return false
        assertThat(fullHouseRule.isValid(case3)).isFalse();


        // given two pair
        List<Card> case4 = new ArrayList<>();
        case4.add(cardFactory.make("H", "2"));
        case4.add(cardFactory.make("C", "2"));
        case4.add(cardFactory.make("D", "3"));
        case4.add(cardFactory.make("S", "4"));
        case4.add(cardFactory.make("S", "6"));

        // when case4 is invalid, then should return false
        assertThat(fullHouseRule.isValid(case4)).isFalse();
    }
}
