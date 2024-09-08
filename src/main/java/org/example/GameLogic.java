package org.example;

import org.example.domain.Card;
import org.example.domain.Deck;
import org.example.domain.GameState;

import java.util.LinkedList;

public class GameLogic {

    public void dealCards(GameState gameState, Deck deck) {
        for (LinkedList<Card> pile : gameState.getPiles()) {
            pile.add(deck.drawCard());
        }
    }

    public boolean removeCards(GameState gameState) {
        boolean wasChanged = false;
        boolean changed = true;

        while (changed) {
            changed = false;
            for (int i = 0; i < gameState.getPiles().size(); i++) {
                for (int j = i + 1; j < gameState.getPiles().size(); j++) {
                    if (!gameState.getPiles().get(i).isEmpty() && !gameState.getPiles().get(j).isEmpty()) {
                        if (compareRemoveCards(gameState.getPiles().get(i), gameState.getPiles().get(j))) {
                            changed = true;
                            wasChanged = true;
                        }
                    }
                }
            }
        }
        return wasChanged;
    }

    private boolean compareRemoveCards(LinkedList<Card> firstPile, LinkedList<Card> secondPile) {
        Card lastPileOne = firstPile.getLast();
        Card lastPileTwo = secondPile.getLast();

        if (lastPileOne.getSuit() == lastPileTwo.getSuit()) {
            if (lastPileOne.getValue() > lastPileTwo.getValue()) {
                secondPile.removeLast();
                return true;
            }
            firstPile.removeLast();
            return true;
        }
        return false;
    }

    public boolean checkIfWin(GameState gameState) {
        for (LinkedList<Card> pile : gameState.getPiles()) {
            if (pile.size() != 1) return false;
        }
        return true;
    }
}

