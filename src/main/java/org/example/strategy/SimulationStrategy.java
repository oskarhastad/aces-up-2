package org.example.strategy;

import org.example.logic.GameLogic;
import org.example.domain.Deck;
import org.example.domain.GameState;

public class SimulationStrategy implements CardMoveStrategy {

    private final GameLogic gameLogic;

    public SimulationStrategy() {
        this.gameLogic = new GameLogic();
    }

    @Override
    public boolean moveCard(GameState gamestate, Deck deck) {

        boolean changed = false;
        gamestate.checkEmptyAndMovable();

        int simulationsPerDecision = 500;

        if (!gamestate.getEmptyPiles().isEmpty() && ! gamestate.getMovablePiles().isEmpty()) {
            changed = true;
            GameState candidate =  gamestate;
            int success = 0;

            for (int i = 0; i <  gamestate.getMovablePiles().size(); i++) {
                GameState temporary =  gamestate.cloneGameState();

                temporary.checkEmptyAndMovable();
                temporary.getEmptyPiles().get(0).add(temporary.getMovablePiles().get(i).getLast());
                temporary.getMovablePiles().get(i).removeLast();

                int successTemp = 0;

                if (gamestate.getMovablePiles().size() > 1) {
                    successTemp = simulateFinish(temporary, deck, simulationsPerDecision);
                }

                if (successTemp >= success) {
                    candidate = temporary;
                    success = successTemp;
                }
            }

            gamestate.setCardPiles(candidate.getCardPiles());
        }

        return changed;
    }

    int simulateFinish(GameState gamestate, Deck deck, int simulations) {

        int success = 0;

        for (int i = 0; i < simulations; i++) {
            GameState temp = gamestate.cloneGameState();
            Deck tempDeck = deck.cloneDeck();
            tempDeck.shuffleDeck();
            CardMoveStrategy strategy = new SimpleMoveStrategy();

            while (!tempDeck.getCards().isEmpty()) {
                processRemovalsAndMoves(temp, tempDeck, strategy);
                gameLogic.dealCards(temp, tempDeck);
            }
            processRemovalsAndMoves(temp, tempDeck, strategy);

            if(gameLogic.checkIfWin(temp)) success++;
        }

        return success;
    }
    private void processRemovalsAndMoves(GameState gamestate, Deck deck, CardMoveStrategy strategy) {

        boolean changed = true;

        while (changed) {
            changed = false;

            if (gameLogic.removeCards(gamestate)) changed = true;
            if (strategy.moveCard(gamestate, deck)) changed = true;
        }
    }
}
