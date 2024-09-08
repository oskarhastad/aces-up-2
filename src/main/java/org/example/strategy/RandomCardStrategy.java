package org.example.strategy;

import org.example.logic.GameLogic;
import org.example.domain.Deck;
import org.example.domain.GameState;

import java.util.concurrent.ThreadLocalRandom;

public class RandomCardStrategy implements CardMoveStrategy{

    private final GameLogic gameLogic;

    public RandomCardStrategy() {
        this.gameLogic = new GameLogic();
    }


    @Override
    public boolean moveCard(GameState gamestate, Deck deck) {
        boolean changed = false;

        gamestate.checkEmptyAndMovable();

        if (!gamestate.getEmptyPiles().isEmpty() && !gamestate.getMovablePiles().isEmpty()) {
            changed = true;

            int randomNum = ThreadLocalRandom.current().nextInt(0, gamestate.getMovablePiles().size());
            gamestate.getEmptyPiles().get(0).add(gamestate.getMovablePiles().get(randomNum).getLast());
            gamestate.getMovablePiles().get(randomNum).removeLast();

        }

        return changed;
    }
}
