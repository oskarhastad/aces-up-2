package org.example.logic;

import org.example.domain.Card;
import org.example.domain.Deck;
import org.example.domain.GameState;

import java.util.ArrayList;
import java.util.List;

public class GameLogic {

    public void dealCards(GameState gameState, Deck deck) {
        for (ArrayList<Card> pile : gameState.getCardPiles()) {
            if (!deck.getCards().isEmpty()) pile.add(deck.drawCard());
        }
    }

    public boolean removeCards(GameState gameState) {
        boolean anyCardsRemoved = false;
        boolean cardsRemovedThisIteration;

        do {
            cardsRemovedThisIteration = false;
            List<ArrayList<Card>> cardPiles = gameState.getCardPiles();
            int numberOfPiles = cardPiles.size();

            for (int i = 0; i < numberOfPiles; i++) {
                for (int j = i + 1; j < numberOfPiles; j++) {
                    ArrayList<Card> firstPile = cardPiles.get(i);
                    ArrayList<Card> secondPile = cardPiles.get(j);

                    if (pilesAreNotEmpty(firstPile, secondPile) && removeLowerCardSameSuit(firstPile, secondPile)) {
                        cardsRemovedThisIteration = true;
                        anyCardsRemoved = true;
                    }
                }
            }
        } while (cardsRemovedThisIteration);

        return anyCardsRemoved;
    }

    private boolean pilesAreNotEmpty(ArrayList<Card> firstPile, ArrayList<Card> secondPile) {
        return !firstPile.isEmpty() && !secondPile.isEmpty();
    }

    private boolean removeLowerCardSameSuit(ArrayList<Card> firstPile, ArrayList<Card> secondPile) {
        Card lastCardPileOne = firstPile.get(firstPile.size() - 1);
        Card lastCardPileTwo = secondPile.get(secondPile.size() - 1);

        if (lastCardPileOne.suit() == lastCardPileTwo.suit()) {
            if (lastCardPileOne.value() > lastCardPileTwo.value()) {
                secondPile.remove(secondPile.size() - 1);
            }
            else {
                firstPile.remove(firstPile.size() - 1);
            }
            return true;
        }
        return false;
    }

    public boolean checkIfWin(GameState gameState) {
        return gameState.getCardPiles().stream()
                .allMatch(pile -> pile.size() == 1 && pile.get(0).value() == 14);
    }
}

