package org.example.logic;

import org.example.domain.Card;
import org.example.domain.Deck;
import org.example.domain.GameState;

import java.util.LinkedList;
import java.util.List;

public class GameLogic {

    public void dealCards(GameState gameState, Deck deck) {
        for (LinkedList<Card> pile : gameState.getCardPiles()) {
            if (!deck.getCards().isEmpty()) pile.add(deck.drawCard());
        }
    }

    public boolean removeCards(GameState gameState) {
        boolean anyCardsRemoved = false;
        boolean cardsRemovedThisIteration;

        do {
            cardsRemovedThisIteration = false;
            List<LinkedList<Card>> cardPiles = gameState.getCardPiles();
            int numberOfPiles = cardPiles.size();

            for (int i = 0; i < numberOfPiles; i++) {
                for (int j = i + 1; j < numberOfPiles; j++) {
                    LinkedList<Card> firstPile = cardPiles.get(i);
                    LinkedList<Card> secondPile = cardPiles.get(j);

                    if (pilesAreNotEmpty(firstPile, secondPile) && removeLowerCardSameSuit(firstPile, secondPile)) {
                        cardsRemovedThisIteration = true;
                        anyCardsRemoved = true;
                    }
                }
            }
        } while (cardsRemovedThisIteration);

        return anyCardsRemoved;
    }

    private boolean pilesAreNotEmpty(LinkedList<Card> firstPile, LinkedList<Card> secondPile) {
        return !firstPile.isEmpty() && !secondPile.isEmpty();
    }

    private boolean removeLowerCardSameSuit(LinkedList<Card> firstPile, LinkedList<Card> secondPile) {
        Card lastCardPileOne = firstPile.getLast();
        Card lastCardPileTwo = secondPile.getLast();

        if (lastCardPileOne.suit() == lastCardPileTwo.suit()) {
            if (lastCardPileOne.value() > lastCardPileTwo.value()) {
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
                .allMatch(pile -> pile.size() == 1 && pile.getFirst().value() == 14);
    }
}

