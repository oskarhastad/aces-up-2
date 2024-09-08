package org.example.domain;
import org.junit.jupiter.api.Test;
import java.util.LinkedList;
import static org.junit.jupiter.api.Assertions.*;

class
GameStateTest {

    @Test
    void testInitialState() {
        GameState gameState = new GameState(new LinkedList<>(), new LinkedList<>(), new LinkedList<>(), new LinkedList<>());
        assertEquals(4, gameState.getPiles().size());
    }

    @Test
    void testCloneGameState() {
        LinkedList<Card> pileOne = new LinkedList<>();
        pileOne.add(new Card(3, Card.Suit.SPADES));
        GameState gameState = new GameState(pileOne, new LinkedList<>(), new LinkedList<>(), new LinkedList<>());
        GameState clonedState = gameState.cloneGamestate();
        assertEquals(gameState.getPiles().get(0).get(0).getValue(), clonedState.getPiles().get(0).get(0).getValue());
    }

    @Test
    void testCheckEmptyAndMovablePiles() {
        LinkedList<Card> pileOne = new LinkedList<>();
        pileOne.add(new Card(2, Card.Suit.HEARTS));
        pileOne.add(new Card(3, Card.Suit.CLUBS));
        LinkedList<Card> pileTwo = new LinkedList<>();
        GameState gameState = new GameState(pileOne, pileTwo, new LinkedList<>(), new LinkedList<>());
        gameState.checkEmptyAndMovable();
        assertEquals(3, gameState.getEmptyPiles().size());
        assertEquals(1, gameState.getMovablePiles().size());
    }
}
