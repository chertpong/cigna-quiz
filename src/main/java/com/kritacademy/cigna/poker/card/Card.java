package com.kritacademy.cigna.poker.card;

import lombok.Data;


/**
 * @author krit <chertpong.github.io> on 3/27/2017.
 */
@Data
public class Card {
    private Kind kind;
    private Rank rank;



    public enum Kind {
        CLUB (1),
        DIAMOND (2),
        HEART (3),
        SPADE (4);

        private final int value;
        Kind(int i) {
            this.value = i;
        }

        @Override
        public String toString() {
            if(value == 1) return "CLUB";
            if(value == 2) return "DIAMOND";
            if(value == 3) return "HEART";
            return "SPADE";
        }

        public int getValue() {return value;}
    }

    public enum Rank {
        TWO(2),
        THREE(3),
        FOUR(4),
        FIVE(5),
        SIX(6),
        SEVEN(7),
        EIGHT(8),
        NINE(9),
        TEN(10),
        JACK(11),
        QUEEN(12),
        KING(13),
        ACE(14);

        private final int value;
        Rank(int i) {
            this.value = i;
        }

        public int getValue() {return value;}

        @Override
        public String toString() {
            if (this.value <= 10) {
                return this.value + "";
            }
            if (this.value == 11) {
                return "Jack";
            }
            if (this.value == 12) {
                return "Queen";
            }
            if (this.value == 13) {
                return "King";
            }
            return "Ace";

        }
    }
}
