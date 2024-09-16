package org.example.domain;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GameStateTest {

    @Test
    public void testGameStateInitialization() {
        GameState gameState = new GameState();
        List<ArrayList<Card>> piles = gameState.getCardPiles();

        assertEquals(4, piles.size());
        for (List<Card> pile : piles) {
            assertTrue(pile.isEmpty());
        }
    }

    @Test
    public void testGetEmptyPiles() {
        GameState gameState = new GameState();
        List<ArrayList<Card>> emptyPiles = gameState.getEmptyPiles();
        assertEquals(4, emptyPiles.size());

        gameState.getCardPiles().get(0).add(new Card(10, Suit.HEARTS));

        emptyPiles = gameState.getEmptyPiles();
        assertEquals(3, emptyPiles.size());
    }

    @Test
    public void testGetMovablePiles() {
        GameState gameState = new GameState();

        gameState.getCardPiles().get(0).add(new Card(10, Suit.HEARTS));

        gameState.getCardPiles().get(1).add(new Card(9, Suit.SPADES));
        gameState.getCardPiles().get(1).add(new Card(8, Suit.DIAMONDS));

        List<ArrayList<Card>> movablePiles = gameState.getMovablePiles();
        assertEquals(1, movablePiles.size());
        assertEquals(gameState.getCardPiles().get(1), movablePiles.get(0));
    }

    @Test
    public void testCloneGameState() {
        GameState originalState = new GameState();
        originalState.getCardPiles().get(0).add(new Card(10, Suit.HEARTS));

        GameState clonedState = originalState.copyGameState();

        assertEquals(originalState.getCardPiles(), clonedState.getCardPiles());

        clonedState.getCardPiles().get(0).add(new Card(9, Suit.SPADES));

        assertNotEquals(originalState.getCardPiles().get(0).size(), clonedState.getCardPiles().get(0).size());
    }

    @Test
    public void testGetTotalCardCount() {
        GameState gameState = new GameState();

        assertEquals(0, gameState.getCardCount());

        gameState.getCardPiles().get(0).add(new Card(10, Suit.HEARTS));
        gameState.getCardPiles().get(1).add(new Card(9, Suit.SPADES));
        gameState.getCardPiles().get(1).add(new Card(8, Suit.DIAMONDS));

        assertEquals(3, gameState.getCardCount());
    }
}
