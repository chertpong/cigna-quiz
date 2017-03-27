package com.kritacademy.cigna.poker.rules;

import com.kritacademy.cigna.poker.DuelResult;
import com.kritacademy.cigna.poker.card.Card;

import java.util.List;

/**
 * @author krit <chertpong.github.io> on 3/27/2017.
 */
public interface RuleList {
    DuelResult duel(String player1, List<Card> cards1, String player2, List<Card> cards2);
}
