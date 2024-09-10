package org.example.strategy;

import org.example.domain.Deck;
import org.example.domain.GameState;
import org.junit.jupiter.api.Test;
import java.util.LinkedList;
import static org.junit.jupiter.api.Assertions.*;

class SimulationStrategyTest {

    @Test
    void testMoveCard() {
        SimulationStrategy strategy = new SimulationStrategy();
        Deck deck = new Deck();
        GameState gameState = new GameState();
        boolean result = strategy.moveCard(gameState, deck);
        assertFalse(result);
    }
}
