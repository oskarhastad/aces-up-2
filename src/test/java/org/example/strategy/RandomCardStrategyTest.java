package org.example.strategy;

import org.example.domain.Deck;
import org.example.domain.GameState;
import org.junit.jupiter.api.Test;
import java.util.LinkedList;
import static org.junit.jupiter.api.Assertions.*;

class RandomCardStrategyTest {

    @Test
    void testMoveCard() {
        RandomCardStrategy strategy = new RandomCardStrategy();
        Deck deck = new Deck();
        GameState gameState = new GameState(new LinkedList<>(), new LinkedList<>(), new LinkedList<>(), new LinkedList<>());
        boolean result = strategy.moveCard(gameState, deck);
        assertFalse(result);
    }
}
