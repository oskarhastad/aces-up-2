package org.example.runner;

import lombok.extern.slf4j.Slf4j;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

@Slf4j
public class SimulationExecutor {

    private final ExecutorService executor;

    public SimulationExecutor() {
        this.executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    }

    public List<Future<Boolean>> submitTasks(Callable<Boolean> task, int simulations) {
        List<Future<Boolean>> results = new ArrayList<>();

        for (int i = 0; i < simulations; i++) {
            results.add(executor.submit(task));
        }

        return results;
    }

    public int collectResults(List<Future<Boolean>> results) {
        int wins = 0;
        int totalSimulations = results.size();

        try {
            for (int i = 0; i < totalSimulations; i++) {
                Future<Boolean> future = results.get(i);
                try {
                    if (future.get()) {
                        wins++;
                        double winRate = (double) wins / (i + 1) * 100;
                        log.info("Completed {} out of {} simulations. Won: {}. Win rate: {}%",
                                (i + 1), totalSimulations, wins, String.format("%.2f", winRate));
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    log.error("Thread interrupted during simulation: {}", (i + 1), e);
                } catch (ExecutionException e) {
                    log.error("Error executing simulation: {}", (i + 1), e);
                }
            }
        } finally {
            shutdownExecutor();
        }
        return wins;
    }

    public void shutdownExecutor() {
        if (!executor.isShutdown()) {
            log.info("Shutting down executor service.");
            executor.shutdown();
        }
    }
}
