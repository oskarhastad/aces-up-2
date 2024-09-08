package org.example;
import lombok.extern.slf4j.Slf4j;
import org.example.domain.Deck;
import org.example.domain.GameState;
import org.example.strategy.CardMoveStrategy;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Slf4j
public class GameRunner {

    public int runSimulations(CardMoveStrategy cardMoveStrategy, int simulations) {

        ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        List<Future<Boolean>> results = new ArrayList<>();

        for (int i = 0; i < simulations; i++) {
            Deck deck = new Deck();
            GameState gameState = new GameState(new LinkedList<>(), new LinkedList<>(), new LinkedList<>(), new LinkedList<>());
            results.add(executor.submit(() -> runSingleGame(cardMoveStrategy, gameState, deck)));
        }

        executor.shutdown();

        return collectResults(results, executor);

    }

    private int collectResults(List<Future<Boolean>> results, ExecutorService executor) {
        int completed = 0;
        try {
            for (int i = 0; i < results.size(); i++) {
                log.info("Fetching result for simulation no: {}", (i + 1));
                try {
                    if (results.get(i).get()) completed++;

                } catch (InterruptedException e) {
                    log.error("Thread interrupted while fetching result for simulation {}.", (i + 1), e);
                    Thread.currentThread().interrupt();

                } catch (ExecutionException e) {
                    log.error("Error executing simulation {}: {}", (i + 1), e.getMessage(), e);
                }
            }
        } finally {

            if (!executor.isShutdown()) {
                executor.shutdownNow();
            }
        }
        return completed;
    }

    private boolean runSingleGame(CardMoveStrategy cardMoveStrategy, GameState gameState, Deck deck) {

        while (!deck.cards.isEmpty()) {
            gameState.dealCards(deck);

            boolean changed = true;
            while(changed) {
                changed = gameState.removeCards();

                if(cardMoveStrategy.moveCard(gameState, deck)) changed = true;
            }
        }

        return gameState.checkIfWin();
    }
}
