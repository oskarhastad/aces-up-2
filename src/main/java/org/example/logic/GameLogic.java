package org.example.logic;

import org.example.domain.Card;
import org.example.domain.Deck;
import org.example.domain.GameState;

import java.util.LinkedList;

public class GameLogic {

    public void dealCards(GameState gameState, Deck deck) {
        for (LinkedList<Card> pile : gameState.getCardPiles()) {
            if(!deck.getCards().isEmpty()) pile.add(deck.drawCard());
        }
    }

    public boolean removeCards(GameState gameState) {

        boolean anyCardsRemoved = false;
        boolean cardsRemovedThisIteration = true;

        while (cardsRemovedThisIteration) {
            cardsRemovedThisIteration = false;
            for (int i = 0; i < gameState.getCardPiles().size(); i++) {
                for (int j = i + 1; j < gameState.getCardPiles().size(); j++) {
                    if (!gameState.getCardPiles().get(i).isEmpty() && !gameState.getCardPiles().get(j).isEmpty()) {
                        if (removeLowerCardSameSuit(gameState.getCardPiles().get(i), gameState.getCardPiles().get(j))) {
                            cardsRemovedThisIteration = true;
                            anyCardsRemoved = true;
                        }
                    }
                }
            }
        }
        return anyCardsRemoved;
    }

    private boolean removeLowerCardSameSuit(LinkedList<Card> firstPile, LinkedList<Card> secondPile) {
        Card lastCardPileOne = firstPile.getLast();
        Card lastCardPileTwo = secondPile.getLast();

        if (lastCardPileOne.getSuit() == lastCardPileTwo.getSuit()) {
            if (lastCardPileOne.getValue() > lastCardPileTwo.getValue()) {
                secondPile.removeLast();
            }
            else {
                firstPile.removeLast();
            }
            return true;
        }
        return false;
    }

    public boolean checkIfWin(GameState gameState) {
        return gameState.getCardPiles().stream()
                .allMatch(pile -> pile.size() == 1 && pile.getFirst().getValue() == 14);
    }

}

