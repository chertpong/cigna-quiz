package com.kritacademy.cigna.poker;

/**
 * @author krit <chertpong.github.io> on 3/27/2017.
 */
public class TieException extends RuntimeException{
    public TieException() {
        super("Tie.");
    }
}
