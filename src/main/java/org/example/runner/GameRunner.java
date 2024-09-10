package org.example.runner;
import lombok.extern.slf4j.Slf4j;
import org.example.domain.Deck;
import org.example.domain.GameState;
import org.example.logic.GameLogic;
import org.example.strategy.CardMoveStrategy;

import java.util.List;
import java.util.concurrent.*;

public class GameRunner {

    private final GameLogic gameLogic;
    private final SimulationExecutor simulationExecutor;

    public GameRunner() {
        this.gameLogic = new GameLogic();
        this.simulationExecutor = new SimulationExecutor();
    }

    public int runSimulations(CardMoveStrategy cardMoveStrategy, int simulations) {

        Callable<Boolean> task = createSimulationTask(cardMoveStrategy);
        List<Future<Boolean>> results = simulationExecutor.submitTasks(task, simulations);

        return simulationExecutor.collectResults(results);
    }
    private Callable<Boolean> createSimulationTask(CardMoveStrategy cardMoveStrategy) {
        return () -> {
            Deck deck = new Deck();
            deck.shuffleDeck();
            GameState gameState = new GameState();
            return simulateSingleGame(cardMoveStrategy, gameState, deck);
        };
    }

    private boolean simulateSingleGame(CardMoveStrategy cardMoveStrategy, GameState gameState, Deck deck) {
        while (!deck.getCards().isEmpty()) {
            gameLogic.dealCards(gameState, deck);
            boolean changed = true;
            while (changed) {
                changed = gameLogic.removeCards(gameState);
                if (cardMoveStrategy.moveCard(gameState, deck)) changed = true;
            }
        }
        return gameLogic.checkIfWin(gameState);
    }
}