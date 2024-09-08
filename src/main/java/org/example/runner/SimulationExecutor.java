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
        int completed = 0;

        try {
            for (int i = 0; i < results.size(); i++) {
                try {
                    if (results.get(i).get()) {
                        completed++;
                        log.info("Simulation {} completed successfully.", (i + 1));
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

        return completed;
    }

    public void shutdownExecutor() {
        if (!executor.isShutdown()) {
            log.info("Shutting down executor service.");
            executor.shutdown();
        }
    }
}
