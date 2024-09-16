package org.example.strategy;

import org.example.domain.Card;
import org.example.logic.GameLogic;
import org.example.domain.Deck;
import org.example.domain.GameState;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MonteCarloStrategy implements CardMoveStrategy {

    private final GameLogic gameLogic;
    private final int simulationsPerDecision;

    public MonteCarloStrategy(int simulationsPerDecision) {
        this.simulationsPerDecision = simulationsPerDecision;
        this.gameLogic = new GameLogic();
    }

    @Override
    public boolean moveCard(GameState gameState, Deck deck) {
        List<ArrayList<Card>> emptyPiles = gameState.getEmptyPiles();
        List<ArrayList<Card>> movablePiles = gameState.getMovablePiles();

        if (!emptyPiles.isEmpty() && !movablePiles.isEmpty()) {
            GameState candidate = null;
            int bestSuccessCount = 0;

            for (int i = 0; i < movablePiles.size(); i++) {
                GameState temp =  gameState.copyGameState();
                List<ArrayList<Card>> tempEmptyPiles = temp.getEmptyPiles();
                List<ArrayList<Card>> tempMovablePiles = temp.getMovablePiles();
                tempEmptyPiles.get(0).add(tempMovablePiles.get(i).get(tempMovablePiles.get(i).size() - 1));
                tempMovablePiles.get(i).remove(tempMovablePiles.get(i).size() - 1);

                int successTemp = 0;
                if (movablePiles.size() > 1) {
                    successTemp = simulateFinish(temp, deck, simulationsPerDecision);
                }

                if (successTemp >= bestSuccessCount) {
                    candidate = temp;
                    bestSuccessCount = successTemp;
                }
            }
            gameState.setCardPiles(candidate.getCardPiles());
            return true;
        }
        return false;
    }

    int simulateFinish(GameState gameState, Deck deck, int simulations) {
        int success = 0;

        for (int i = 0; i < simulations; i++) {
            GameState temp = gameState.copyGameState();
            Deck tempDeck = deck.copyDeck();
            tempDeck.shuffle();
            CardMoveStrategy strategy = new BasicMoveStrategy();

            while (!tempDeck.getCards().isEmpty()) {
                processRemovalsAndMoves(temp, tempDeck, strategy);
                gameLogic.dealCards(temp, tempDeck);
            }

            processRemovalsAndMoves(temp, tempDeck, strategy);

            if(gameLogic.checkIfWin(temp)) success++;
        }
        return success;
    }

    private void processRemovalsAndMoves(GameState gameState, Deck deck, CardMoveStrategy strategy) {
        boolean changed;
        do {
            changed = gameLogic.removeCards(gameState) | strategy.moveCard(gameState, deck);
        } while (changed);
    }

    @Override
    public String toString() {
        return "Monte Carlo Strategy";
    }
}
