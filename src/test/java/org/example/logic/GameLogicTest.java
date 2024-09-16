package org.example.logic;

import org.example.domain.Card;
import org.example.domain.Deck;
import org.example.domain.GameState;
import org.example.domain.Suit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GameLogicTest {

    private GameLogic gameLogic;
    private GameState gameState;
    private Deck deck;

    @BeforeEach
    public void setup() {
        gameLogic = new GameLogic();
        gameState = new GameState();
        deck = new Deck();
        deck.shuffle();
    }

    @Test
    public void testDealCards() {
        gameLogic.dealCards(gameState, deck);

        for (List<Card> pile : gameState.getCardPiles()) {
            assertEquals(1, pile.size());
        }

        assertEquals(48, deck.getCards().size());
    }

    @Test
    public void testRemoveCardsWithSameSuit() {
        gameState.getCardPiles().get(0).add(new Card(10, Suit.HEARTS));
        gameState.getCardPiles().get(1).add(new Card(9, Suit.HEARTS));

        boolean removed = gameLogic.removeCards(gameState);

        assertTrue(removed);
        assertEquals(1, gameState.getCardPiles().get(0).size());
        assertEquals(0, gameState.getCardPiles().get(1).size());
    }

    @Test
    public void testRemoveCardsWithDifferentSuits() {
        gameState.getCardPiles().get(0).add(new Card(10, Suit.HEARTS));
        gameState.getCardPiles().get(1).add(new Card(9, Suit.SPADES));

        boolean removed = gameLogic.removeCards(gameState);

        assertFalse(removed);
        assertEquals(1, gameState.getCardPiles().get(0).size());
        assertEquals(1, gameState.getCardPiles().get(1).size());
    }

    @Test
    public void testCheckIfWinTrue() {
        for (List<Card> pile : gameState.getCardPiles()) {
            pile.add(new Card(14, Suit.HEARTS));
        }

        assertTrue(gameLogic.checkIfWin(gameState));
    }

    @Test
    public void testCheckIfWinFalse() {
        gameLogic.dealCards(gameState, deck);
        assertFalse(gameLogic.checkIfWin(gameState));
    }
}
