package com.kritacademy.cigna.poker.rules;

import com.kritacademy.cigna.poker.DuelResult;
import com.kritacademy.cigna.poker.TieException;
import com.kritacademy.cigna.poker.card.Card;
import com.kritacademy.cigna.poker.card.CardFactory;
import com.kritacademy.cigna.poker.card.CardFactoryImpl;

import java.util.*;

/**
 * @author krit <chertpong.github.io> on 3/27/2017.
 */
public class DefaultRuleList implements RuleList{
    private Rule royalFlushRule = new RoyalFlushRule();
    private Rule straightFlushRule = new StraightFlushRule();
    private Rule fourOfKindRule = new FourOfKindRule();
    private Rule fullHouseRule = new FullHouseRule();
    private Rule flushRule = new FlushRule();
    private Rule straightRule = new StraightRule();
    private Rule threeOfKindRule = new ThreeOfKindRule();
    private Rule twoPairRule = new TwoPairRule();
    private Rule pairRule = new PairRule();
    private CardFactory cardFactory = new CardFactoryImpl();
    public DuelResult duel(String player1, List<Card> cards1, String player2, List<Card> cards2) {
        Optional<DuelResult> ruleResult;

        ruleResult = duelByRule(royalFlushRule,"royal flush", player1, cards1, player2, cards2);
        if (ruleResult.isPresent()) {
            return ruleResult.get();
        }

        ruleResult = duelByRule(straightFlushRule,"straight flush", player1, cards1, player2, cards2);
        if (ruleResult.isPresent()) {
            return ruleResult.get();
        }

        ruleResult = duelByRule(fourOfKindRule,"four of kind", player1, cards1, player2, cards2);
        if (ruleResult.isPresent()) {
            return ruleResult.get();
        }

        ruleResult = duelByRule(fullHouseRule,"full house", player1, cards1, player2, cards2);
        if (ruleResult.isPresent()) {
            return ruleResult.get();
        }

        ruleResult = duelByRule(flushRule,"flush", player1, cards1, player2, cards2);
        if (ruleResult.isPresent()) {
            return ruleResult.get();
        }

        ruleResult = duelByRule(straightRule,"straight", player1, cards1, player2, cards2);
        if (ruleResult.isPresent()) {
            return ruleResult.get();
        }

        ruleResult = duelByRule(threeOfKindRule,"three of kind", player1, cards1, player2, cards2);
        if (ruleResult.isPresent()) {
            return ruleResult.get();
        }

        ruleResult = duelByRule(twoPairRule,"two pair", player1, cards1, player2, cards2);
        if (ruleResult.isPresent()) {
            return ruleResult.get();
        }

        ruleResult = duelByRule(pairRule,"one pair", player1, cards1, player2, cards2);
        if (ruleResult.isPresent()) {
            return ruleResult.get();
        }

        // default case, use highest card rule
        DuelResult duelResult = new DuelResult();
        Set<Card> player1CardSet = new HashSet<>(cards1);
        Set<Card> player2CardSet = new HashSet<>(cards2);
        for (int i = 0; i < 5; i ++) {
            // can't find winner
            if(player1CardSet.size() == 0 || player2CardSet.size() == 0) {
                throw new TieException();
            }

            Card player1HighestCard = this.findHighestCard(player1CardSet);
            Card player2HighestCard = this.findHighestCard(player2CardSet);

            if (player1HighestCard.getRank().getValue() > player2HighestCard.getRank().getValue()) {
                duelResult.setReason(player1HighestCard.getRank().toString());
                duelResult.setWinner(player1);
                duelResult.setRuleName("high card");
                return duelResult;
            }
            else if (player2HighestCard.getRank().getValue() > player1HighestCard.getRank().getValue()) {
                duelResult.setReason(player2HighestCard.getRank().toString());
                duelResult.setWinner(player2);
                duelResult.setRuleName("high card");
                return duelResult;
            }
            else {
                player1CardSet.remove(player1HighestCard);
                player2CardSet.remove(player2HighestCard);
            }
        }
        // can't find winner
        throw new TieException();
    }

    private Card findHighestCard(Set<Card> cards) {
        Card highestCard = new ArrayList<>(cards).get(0);
        for (Card card : cards) {
            if (card.getRank().getValue() > highestCard.getRank().getValue()) {
                highestCard = card;
            }
        }
        return highestCard;
    }

    /**
     * @param rule Rule to check
     * @param cards1 card of player 1
     * @param cards2 card of player 2
     * @return 1 if player 1 win, 2 if player 2 win, 0 if both player fail the rule, -1 if win with reason
     */
    private int checkByRule(Rule rule, List<Card> cards1, List<Card> cards2) {
        Boolean player1IsPass = rule.isValid(cards1);
        Boolean player2IsPass = rule.isValid(cards2);

        // check if both fail the rule
        if (!player1IsPass && !player2IsPass){
            return 0;
        }

        // check if both pass the rule
        if (player1IsPass && player2IsPass) {
            if (rule.tieValue(cards1).equals(rule.tieValue(cards2))) {
                throw new TieException();
            }
            return -1;
        }

        // one of them pass
        if (player1IsPass) {
            return 1;
        }
        else {
            return 2;
        }
    }

    private Optional<DuelResult> duelByRule(Rule rule, String ruleName, String player1, List<Card> cards1, String player2, List<Card> cards2) {
        DuelResult result;
        int ruleResult = this.checkByRule(rule, cards1, cards2);
        if(ruleResult == 0) {
            return Optional.empty();
        }
        // if win with reason, then add reason (both pass same rule, but one of them has higher rank)
        else if (ruleResult == -1) {
            result = new DuelResult();
            result.setRuleName(ruleName);
            if (rule.tieValue(cards1) > rule.tieValue(cards2)) {
                result.setWinner(player1);
                result.setReason(cardFactory.make(rule.tieValue(cards1) + "").getRank().toString());
            } else {
                result.setWinner(player2);
                result.setReason(cardFactory.make(rule.tieValue(cards2) + "").getRank().toString());
            }
            return Optional.of(result);
        }
        else {
            result = new DuelResult();
            result.setRuleName(ruleName);
            result.setReason("");

            if(ruleResult == 1) {
                result.setWinner(player1);
            }
            else {
                result.setWinner(player2);
            }
            return Optional.of(result);
        }
    }
}
