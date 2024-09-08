package org.example.strategy;

import org.example.GameRunner;
import org.example.domain.Deck;
import org.example.domain.GameState;

public class SimulationStrategy implements CardMoveStrategy {

    @Override
    public boolean moveCard(GameState gamestate, Deck deck) {

        boolean changed = false;
        gamestate.checkEmptyAndCanMove();

        int simulationsPerDecision = 1000;

        if (!gamestate.empty.isEmpty() && ! gamestate.canMove.isEmpty()) {
            changed = true;
            GameState candidate =  gamestate.cloneGamestate();
            int success = 0;

            for (int i = 0; i <  gamestate.canMove.size(); i++) {
                GameState temporary =  gamestate.cloneGamestate();

                temporary.checkEmptyAndCanMove();
                temporary.empty.get(0).add(temporary.canMove.get(i).getLast());
                temporary.canMove.get(i).removeLast();
                temporary.removeCards();

                int successTemp = 0;

                if (gamestate.canMove.size() > 1) {
                    successTemp = simulateFinish(temporary, deck, simulationsPerDecision);
                }

                if (successTemp >= success) {
                    candidate = temporary;
                    success = successTemp;
                }
            }

            gamestate.piles = candidate.piles;
        }
        return changed;
    }

    int simulateFinish(GameState gamestate, Deck deck, int simulations) {

        int success = 0;

        for (int i = 0; i < simulations; i++) {
            GameState temp = gamestate.cloneGamestate();
            Deck tempDeck = deck.cloneDeck();
            SimpleMoveStrategy strategy = new SimpleMoveStrategy();

            while (!tempDeck.cards.isEmpty()) {
                processRemovalsAndMoves(temp, tempDeck, strategy);
                temp.dealCards(tempDeck);
                processRemovalsAndMoves(temp, tempDeck, strategy);
            }

            if(temp.checkIfWin()) success++;

        }
        return success;
    }
    private void processRemovalsAndMoves(GameState gamestate, Deck deck, CardMoveStrategy strategy) {

        boolean changed = true;

        while (changed) {
            changed = false;
            if (gamestate.removeCards()) changed = true;
            if (strategy.moveCard(gamestate, deck)) changed = true;
        }
    }
}
