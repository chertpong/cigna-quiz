package com.kritacademy.cigna.poker.card;

/**
 * @author krit <chertpong.github.io> on 3/27/2017.
 */
public interface CardFactory {
    Card make(String kind, String rank);
    Card make(String rank);
    Card make(Card card, String rank);
}
