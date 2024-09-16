package org.example.logic;

import org.example.domain.Card;
import org.example.domain.Deck;
import org.example.domain.GameState;
import org.example.domain.Suit;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GameLogicTest {

    private final GameLogic gameLogic = new GameLogic();

    @Test
    void testDealCards() {
        Deck deck = new Deck();
        GameState gameState = new GameState();
        gameLogic.dealCards(gameState, deck);
        for (LinkedList<Card> pile : gameState.getCardPiles()) {
            assertFalse(pile.isEmpty());
        }
    }

    @Test
    void testRemoveCards() {
        List<LinkedList<Card>> piles = new ArrayList<>();
        LinkedList<Card> pileOne = new LinkedList<>();
        LinkedList<Card> pileTwo = new LinkedList<>();
        pileOne.add(new Card(3, Suit.SPADES));
        pileTwo.add(new Card(4, Suit.SPADES));
        piles.add(pileOne);
        piles.add(pileTwo);
        GameState gameState = new GameState(piles);
        boolean result = gameLogic.removeCards(gameState);
        assertTrue(result);
        assertEquals(0, gameState.getCardPiles().get(0).size());
    }

    @Test
    void testCheckIfWin() {
        List<LinkedList<Card>> piles = new ArrayList<>();
        LinkedList<Card> pileOne = new LinkedList<>();
        pileOne.add(new Card(14, Suit.SPADES));
        LinkedList<Card> pileTwo = new LinkedList<>();
        pileTwo.add(new Card(14, Suit.HEARTS));
        LinkedList<Card> pileThree = new LinkedList<>();
        pileThree.add(new Card(14, Suit.CLUBS));
        LinkedList<Card> pileFour = new LinkedList<>();
        pileFour.add(new Card(14, Suit.DIAMONDS));
        piles.add(pileOne);
        piles.add(pileTwo);
        piles.add(pileThree);
        piles.add(pileFour);
        GameState gameState = new GameState(piles);
        assertTrue(gameLogic.checkIfWin(gameState));
    }
}
