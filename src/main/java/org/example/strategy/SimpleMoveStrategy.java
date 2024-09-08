package org.example.strategy;

import org.example.domain.Deck;
import org.example.domain.GameState;

public class SimpleMoveStrategy implements CardMoveStrategy{

    @Override
    public boolean moveCard(GameState gamestate, Deck deck) {

        gamestate.checkEmptyAndMovable();
        boolean changed = false;

        if (!gamestate.emptyPiles.isEmpty() && !gamestate.movablePiles.isEmpty()) {
            changed = true;
            GameState candidate = gamestate.cloneGamestate();

            for (int i = 0; i < gamestate.movablePiles.size(); i++) {
                GameState temporary = gamestate.cloneGamestate();

                temporary.checkEmptyAndMovable();
                temporary.emptyPiles.get(0).add(temporary.movablePiles.get(i).getLast());
                temporary.movablePiles.get(i).removeLast();
                temporary.removeCards();

                if (temporary.amountOfCards() <= candidate.amountOfCards()) {
                    candidate = temporary;
                }
            }

            gamestate.piles = candidate.piles;
        }

        return changed;
    }
}
