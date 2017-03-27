package com.kritacademy.cigna.poker.card;

/**
 * @author krit <chertpong.github.io> on 3/27/2017.
 */
public class CardFactoryImpl implements CardFactory {
    private static final String WRONG_RANK_MESSAGE = "Rank must be in this range [2,..,14] or [\"J\", \"Q\", \"K\", \"A\"]";
    public Card make(String kind, String rank) {
        Card card = createByKind(kind);
        return make(card, rank);
    }

    @Override
    public Card make(String rank) {
        return make(new Card(), rank);
    }

    @Override
    public Card make(Card card, String rank) {
        switch (rank) {
            case "A" :
                card.setRank(Card.Rank.ACE);
                break;
            case "K" :
                card.setRank(Card.Rank.KING);
                break;
            case "Q" :
                card.setRank(Card.Rank.QUEEN);
                break;
            case "J":
                card.setRank(Card.Rank.JACK);
                break;
            case "10":
                card.setRank(Card.Rank.TEN);
                break;
            case "9":
                card.setRank(Card.Rank.NINE);
                break;
            case "8":
                card.setRank(Card.Rank.EIGHT);
                break;
            case "7":
                card.setRank(Card.Rank.SEVEN);
                break;
            case "6":
                card.setRank(Card.Rank.SIX);
                break;
            case "5":
                card.setRank(Card.Rank.FIVE);
                break;
            case "4":
                card.setRank(Card.Rank.FOUR);
                break;
            case "3":
                card.setRank(Card.Rank.THREE);
                break;
            case "2":
                card.setRank(Card.Rank.TWO);
                break;
            default:
                throw new RuntimeException(WRONG_RANK_MESSAGE);
        }
        return card;
    }

    private Card createByKind(String kindName) {
        Card.Kind kind;
        switch (kindName) {
            case "C":
                kind = Card.Kind.CLUB;
                break;
            case "D":
                kind = Card.Kind.DIAMOND;
                break;
            case "H":
                kind = Card.Kind.HEART;
                break;
            case "S":
                kind = Card.Kind.SPADE;
                break;
            default:
                throw new RuntimeException("Kind name must be [C, D, H, S]");
        }
        Card card = new Card();
        card.setKind(kind);
        return card;
    }
}
