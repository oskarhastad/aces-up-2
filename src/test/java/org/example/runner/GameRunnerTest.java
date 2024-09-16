package org.example.runner;

import org.example.logic.GameLogic;
import org.example.domain.Deck;
import org.example.domain.GameState;
import org.example.strategy.BasicMoveStrategy;
import org.example.strategy.CardMoveStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class GameRunnerTest {

    @Test
    public void testRunSimulations() {
        GameRunner gameRunner = new GameRunner();
        CardMoveStrategy strategy = new BasicMoveStrategy();

        int simulations = 10;
        int wins = gameRunner.runSimulations(strategy, simulations);
        
        assertTrue(wins >= 0 && wins <= simulations);
    }
}