package org.example.strategy;


import org.example.domain.Card;
import org.example.domain.Deck;
import org.example.domain.GameState;
import org.example.domain.Suit;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
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
    void testMonteCarloStrategy() {
        GameState gameState = createTestGameState();
        MonteCarloStrategy strategy = new MonteCarloStrategy(1);
        Deck deck = new Deck();
        assertStrategyWorks(strategy, gameState, deck);
    }

    private void assertStrategyWorks(CardMoveStrategy strategy, GameState gameState, Deck deck) {
        boolean result = strategy.moveCard(gameState, deck);
        assertTrue(result);
        for (ArrayList<Card> pile : gameState.getCardPiles()) {
            assertEquals(1, pile.size());
        }
    }

    private GameState createTestGameState() {
        List<ArrayList<Card>> piles = new ArrayList<>();
        ArrayList<Card> pileOne = new ArrayList<>();
        pileOne.add(new Card(14, Suit.SPADES));
        ArrayList<Card> pileTwo = new ArrayList<>();
        pileTwo.add(new Card(14, Suit.HEARTS));
        ArrayList<Card> pileThree = new ArrayList<>();
        pileThree.add(new Card(14, Suit.CLUBS));
        pileThree.add(new Card(14, Suit.DIAMONDS));
        ArrayList<Card> pileFour = new ArrayList<>();
        piles.add(pileOne);
        piles.add(pileTwo);
        piles.add(pileThree);
        piles.add(pileFour);
        return new GameState(piles);
    }
}
