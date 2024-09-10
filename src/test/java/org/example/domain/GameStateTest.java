package org.example.domain;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

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
        pileOne.add(new Card(3, Card.Suit.SPADES));
        GameState gameState = new GameState();
        gameState.getCardPiles().set(0,pileOne);
        GameState clonedState = gameState.cloneGameState();
        assertEquals(gameState.getCardPiles().get(0).get(0).getValue(), clonedState.getCardPiles().get(0).get(0).getValue());
    }

    @Test
    void testCheckEmptyAndMovablePiles() {
        LinkedList<Card> pileOne = new LinkedList<>();
        pileOne.add(new Card(2, Card.Suit.HEARTS));
        pileOne.add(new Card(3, Card.Suit.CLUBS));
        GameState gameState = new GameState();
        gameState.getCardPiles().set(0,pileOne);
        gameState.checkEmptyAndMovable();
        assertEquals(3, gameState.getEmptyPiles().size());
        assertEquals(1, gameState.getMovablePiles().size());
    }
}
