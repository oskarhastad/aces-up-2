package org.example.strategy;

import org.example.domain.Card;
import org.example.logic.GameLogic;
import org.example.domain.Deck;
import org.example.domain.GameState;

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
        List<LinkedList<Card>> emptyPiles = gameState.getEmptyPiles();
        List<LinkedList<Card>> movablePiles = gameState.getMovablePiles();

        if (!emptyPiles.isEmpty() && !movablePiles.isEmpty()) {
            GameState candidate = null;
            int bestSuccessCount = 0;

            for (int i = 0; i < movablePiles.size(); i++) {
                GameState temp =  gameState.copyGameState();
                List<LinkedList<Card>> tempEmptyPiles = temp.getEmptyPiles();
                List<LinkedList<Card>> tempMovablePiles = temp.getMovablePiles();
                tempEmptyPiles.get(0).add(tempMovablePiles.get(i).getLast());
                tempMovablePiles.get(i).removeLast();

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
        boolean changed = true;
        while (changed) {
            boolean changed1 = gameLogic.removeCards(gameState);
            boolean changed2 = strategy.moveCard(gameState, deck);
            changed = changed1 || changed2;
        }
    }

    @Override
    public String toString() {
        return "Simulation Strategy";
    }
}
