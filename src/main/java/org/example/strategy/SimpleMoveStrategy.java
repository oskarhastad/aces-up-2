package org.example.strategy;

import org.example.domain.Deck;
import org.example.domain.GameState;

public class SimpleMoveStrategy implements CardMoveStrategy{

    @Override
    public boolean moveCard(GameState gamestate, Deck deck) {

        gamestate.checkEmptyAndCanMove();
        boolean changed = false;

        if (!gamestate.empty.isEmpty() && !gamestate.canMove.isEmpty()) {
            changed = true;
            GameState candidate = gamestate.cloneGamestate();

            for (int i = 0; i < gamestate.canMove.size(); i++) {
                GameState temporary = gamestate.cloneGamestate();
                temporary.checkEmptyAndCanMove();
                temporary.empty.get(0).add(temporary.canMove.get(i).getLast());
                temporary.canMove.get(i).removeLast();
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
