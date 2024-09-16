package org.example.domain;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

class
GameStateTest {

    @Test
    void testInitialState() {
        GameState gameState = new GameState();
        assertEquals(4, gameState.getCardPiles().size());
    }

    @Test
    void testCloneGameState() {
        LinkedList<Card> pileOne = new LinkedList<>();
        pileOne.add(new Card(3, Suit.SPADES));
        GameState gameState = new GameState();
        gameState.getCardPiles().set(0,pileOne);
        GameState clonedState = gameState.cloneGameState();
        assertEquals(gameState.getCardPiles().get(0).get(0).value(), clonedState.getCardPiles().get(0).get(0).value());
    }

    @Test
    void testCheckEmptyAndMovablePiles() {
        LinkedList<Card> pileOne = new LinkedList<>();
        pileOne.add(new Card(2, Suit.HEARTS));
        pileOne.add(new Card(3, Suit.CLUBS));
        GameState gameState = new GameState();
        gameState.getCardPiles().set(0,pileOne);
        assertEquals(3, gameState.getEmptyPiles().size());
        assertEquals(1, gameState.getMovablePiles().size());
    }
}
