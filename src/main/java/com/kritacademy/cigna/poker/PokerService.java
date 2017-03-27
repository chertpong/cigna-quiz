package com.kritacademy.cigna.poker;

import com.kritacademy.cigna.poker.card.Card;

import java.util.List;

/**
 * @author krit <chertpong.github.io> on 3/27/2017.
 */
public interface PokerService {
    List<Card> toCards(String cards);
    DuelResult duel(String player1, List<Card> cards1, String player2, List<Card> cards2);
}
