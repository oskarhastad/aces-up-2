package org.example.runner;

import org.example.logic.GameLogic;
import org.example.domain.Deck;
import org.example.domain.GameState;
import org.example.strategy.CardMoveStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class GameRunnerTest {

    @Mock
    private GameLogic gameLogic;

    @Mock
    private CardMoveStrategy cardMoveStrategy;

    @InjectMocks
    private GameRunner gameRunner;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testIncompleteSimulations() {
        when(gameLogic.checkIfWin(any(GameState.class))).thenReturn(false);

        int simulations = 5;
        int completed = gameRunner.runSimulations(cardMoveStrategy, simulations);

        assertEquals(0, completed);
    }
}
