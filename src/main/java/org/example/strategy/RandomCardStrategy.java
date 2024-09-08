package org.example.strategy;

import org.example.domain.Card;
import org.example.domain.Deck;
import org.example.domain.GameState;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class RandomCardStrategy implements CardMoveStrategy{

    @Override
    public boolean moveCard(GameState gamestate, Deck deck) {
        boolean changed = false;
        gamestate.checkEmptyAndMovable();

        if (!gamestate.emptyPiles.isEmpty() && !gamestate.movablePiles.isEmpty()) {
            changed = true;

            int randomNum = ThreadLocalRandom.current().nextInt(0, gamestate.movablePiles.size());
            gamestate.emptyPiles.get(0).add(gamestate.movablePiles.get(randomNum).getLast());
            gamestate.movablePiles.get(randomNum).removeLast();

        }

        return changed;
    }
}
