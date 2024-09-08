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

        List<LinkedList<Card>> empty = gamestate.emptyPiles;
        List<LinkedList<Card>> canMove = gamestate.movablePiles;

        if (!gamestate.emptyPiles.isEmpty() && !canMove.isEmpty()) {
            changed = true;

            for (LinkedList<Card> pile : empty) {
                if (!canMove.isEmpty()) {
                    int randomNum = ThreadLocalRandom.current().nextInt(0, canMove.size());

                    pile.add(canMove.get(randomNum).getLast());
                    canMove.get(randomNum).removeLast();

                    if (canMove.get(randomNum).size() < 2) {
                        canMove.remove(randomNum);
                    }
                }
            }
        }

        return changed;
    }
}
