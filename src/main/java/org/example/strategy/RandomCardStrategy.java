package org.example.strategy;

import org.example.domain.Card;
import org.example.logic.GameLogic;
import org.example.domain.Deck;
import org.example.domain.GameState;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class RandomCardStrategy implements CardMoveStrategy{

    @Override
    public boolean moveCard(GameState gameState, Deck deck) {
        List<LinkedList<Card>> emptyPiles = gameState.getEmptyPiles();
        List<LinkedList<Card>> movablePiles = gameState.getMovablePiles();

        if (emptyPiles.isEmpty() || movablePiles.isEmpty()) {
            return false;
        }

        int randomIndex = ThreadLocalRandom.current().nextInt(0, movablePiles.size());
        emptyPiles.get(0).add(movablePiles.get(randomIndex).getLast());
        movablePiles.get(randomIndex).removeLast();

        return true;
    }
    @Override
    public String toString() {
        return "Random Card Strategy";
    }
}
