package org.example.strategy;


import org.example.domain.Card;
import org.example.domain.Deck;
import org.example.domain.GameState;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CardMoveStrategyTest {

    private GameState createTestGameState() {
        List<LinkedList<Card>> piles = new ArrayList<>();
        LinkedList<Card> pileOne = new LinkedList<>();
        pileOne.add(new Card(14, Card.Suit.SPADES));
        LinkedList<Card> pileTwo = new LinkedList<>();
        pileTwo.add(new Card(14, Card.Suit.HEARTS));
        LinkedList<Card> pileThree = new LinkedList<>();
        pileThree.add(new Card(14, Card.Suit.CLUBS));
        pileThree.add(new Card(14, Card.Suit.DIAMONDS));
        LinkedList<Card> pileFour = new LinkedList<>();
        piles.add(pileOne);
        piles.add(pileTwo);
        piles.add(pileThree);
        piles.add(pileFour);
        return new GameState(piles);
    }

    // Helper method to run the test assertions
    private void assertStrategyWorks(CardMoveStrategy strategy, GameState gameState, Deck deck) {
        boolean result = strategy.moveCard(gameState, deck);
        assertTrue(result);
        for (LinkedList<Card> pile : gameState.getCardPiles()) {
            assertEquals(1, pile.size());
        }
    }

    @Test
    void testRandomCardStrategy() {
        GameState gameState = createTestGameState();
        RandomCardStrategy strategy = new RandomCardStrategy();
        Deck deck = new Deck();
        assertStrategyWorks(strategy, gameState, deck);
    }

    @Test
    void testSimpleMoveStrategy() {
        GameState gameState = createTestGameState();
        BasicMoveStrategy strategy = new BasicMoveStrategy();
        Deck deck = new Deck();
        assertStrategyWorks(strategy, gameState, deck);
    }

    @Test
    void testSimulationStrategy() {
        GameState gameState = createTestGameState();
        SimulationStrategy strategy = new SimulationStrategy();
        Deck deck = new Deck();
        assertStrategyWorks(strategy, gameState, deck);
    }
}
