package org.example.strategy;


import org.example.domain.Card;
import org.example.domain.Deck;
import org.example.domain.GameState;
import org.example.domain.Suit;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CardMoveStrategyTest {

    @Test
    void testRandomCardStrategy() {
        GameState gameState = createTestGameState();
        RandomCardStrategy strategy = new RandomCardStrategy();
        Deck deck = new Deck();
        assertStrategyWorks(strategy, gameState, deck);
    }

    @Test
    void testBasicMoveStrategy() {
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

    private void assertStrategyWorks(CardMoveStrategy strategy, GameState gameState, Deck deck) {
        boolean result = strategy.moveCard(gameState, deck);
        assertTrue(result);
        for (LinkedList<Card> pile : gameState.getCardPiles()) {
            assertEquals(1, pile.size());
        }
    }

    private GameState createTestGameState() {
        List<LinkedList<Card>> piles = new ArrayList<>();
        LinkedList<Card> pileOne = new LinkedList<>();
        pileOne.add(new Card(14, Suit.SPADES));
        LinkedList<Card> pileTwo = new LinkedList<>();
        pileTwo.add(new Card(14, Suit.HEARTS));
        LinkedList<Card> pileThree = new LinkedList<>();
        pileThree.add(new Card(14, Suit.CLUBS));
        pileThree.add(new Card(14, Suit.DIAMONDS));
        LinkedList<Card> pileFour = new LinkedList<>();
        piles.add(pileOne);
        piles.add(pileTwo);
        piles.add(pileThree);
        piles.add(pileFour);
        return new GameState(piles);
    }
}
