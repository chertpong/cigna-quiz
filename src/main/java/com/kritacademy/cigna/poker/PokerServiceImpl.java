package com.kritacademy.cigna.poker;

import com.kritacademy.cigna.poker.card.Card;
import com.kritacademy.cigna.poker.card.CardFactory;
import com.kritacademy.cigna.poker.card.CardFactoryImpl;
import com.kritacademy.cigna.poker.rules.DefaultRuleList;
import com.kritacademy.cigna.poker.rules.RuleList;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author krit <chertpong.github.io> on 3/27/2017.
 */
public class PokerServiceImpl implements PokerService {
    private CardFactory cardFactory;
    private RuleList ruleList;

    public PokerServiceImpl() {
        this.cardFactory = new CardFactoryImpl();
        this.ruleList = new DefaultRuleList();
    }

    public PokerServiceImpl(CardFactory cardFactory, RuleList ruleList) {
        this.cardFactory = cardFactory;
        this.ruleList = ruleList;
    }

    @Override
    public List<Card> toCards(String cards) {
        return Stream.of(cards.split(" "))
                .map(card -> cardFactory.make(card.substring(1,2), card.substring(0,1)))
                .collect(Collectors.toList());
    }

    /**
     * Use instance of rule list to compute rather than compute here, because it would be easier to change instance
     * @param player1 Player 1 name
     * @param cards1 Player 1 cards
     * @param player2 Player 2 name
     * @param cards2 Player 2 cards
     * @return duel result or TieException
     */
    @Override
    public DuelResult duel(String player1, List<Card> cards1, String player2, List<Card> cards2) {
       return this.ruleList.duel(player1, cards1, player2, cards2);
    }

}
