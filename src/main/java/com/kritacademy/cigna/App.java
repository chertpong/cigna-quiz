package com.kritacademy.cigna;

import com.kritacademy.cigna.common.Reader;
import com.kritacademy.cigna.poker.DuelResult;
import com.kritacademy.cigna.poker.PokerService;
import com.kritacademy.cigna.poker.PokerServiceImpl;
import com.kritacademy.cigna.poker.TieException;
import com.kritacademy.cigna.poker.card.Card;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Main file
 * @author krit <chertpong.github.io> on 3/27/2017.
 */
public class App {
    public static Boolean debug = false;

    public static void main( String[] args ) {
        PokerService pokerService = new PokerServiceImpl();
        String defaultTextPath = "C:\\Users\\krit\\IdeaProjects\\cigna-quiz\\target\\temp.txt";
        try {
            Reader reader = Reader.getInstance();
            List<String> lines = reader
                    .read(defaultTextPath)
                    .orElseThrow(() -> new RuntimeException("Cannot read file"));
            // remove blank
            lines = lines.stream().filter(line -> !line.equals("")).collect(Collectors.toList());

            if (lines.size() % 2 != 0) {
                throw new RuntimeException("Must has 2 players at a time");
            }

            for (int i = 1; i < lines.size(); i+=2) {
                String player1 = lines.get(i - 1);
                String player1Name = player1.split(":")[0];
                List<Card> player1Cards = pokerService.toCards(player1.split(":")[1].trim());
                String player2 = lines.get(i);
                String player2Name = player2.split(":")[0];
                List<Card> player2Cards = pokerService.toCards(player2.split(":")[1].trim());

                if (debug) {
                    System.out.println("P1: " + player1Name + player1Cards);
                    System.out.println("P2: "+ player2Name + player2Cards);
                }

                try {
                    DuelResult duelResult = pokerService.duel(player1Name, player1Cards, player2Name, player2Cards);
                    if (duelResult.getReason().equals("")) {
                        System.out.println(String.format("%s wins - with %s", duelResult.getWinner(), duelResult.getRuleName()));
                    } else {
                        System.out.println(
                            String.format("%s wins - with %s: %s",
                                duelResult.getWinner(),
                                duelResult.getRuleName(),
                                duelResult.getReason()
                            )
                        );
                    }
                }
                catch (TieException tie) {
                    System.out.println(tie.getMessage());
                }
            }
        }
        catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }
    }

}
