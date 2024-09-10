package org.example.logic;

import org.example.domain.Card;
import org.example.domain.Deck;
import org.example.domain.GameState;
import org.junit.jupiter.api.Test;
import java.util.LinkedList;
import static org.junit.jupiter.api.Assertions.*;

class GameLogicTest {

    private final GameLogic gameLogic = new GameLogic();

    @Test
    void testDealCards() {
        Deck deck = new Deck();
        GameState gameState = new GameState(new LinkedList<>(), new LinkedList<>(), new LinkedList<>(), new LinkedList<>());
        gameLogic.dealCards(gameState, deck);
        for (LinkedList<Card> pile : gameState.getCardPiles()) {
            assertFalse(pile.isEmpty());
        }
    }

    @Test
    void testRemoveCards() {
        LinkedList<Card> pileOne = new LinkedList<>();
        LinkedList<Card> pileTwo = new LinkedList<>();
        pileOne.add(new Card(3, Card.Suit.SPADES));
        pileTwo.add(new Card(4, Card.Suit.SPADES));
        GameState gameState = new GameState(pileOne, pileTwo, new LinkedList<>(), new LinkedList<>());
        boolean result = gameLogic.removeCards(gameState);
        assertTrue(result);
        assertEquals(0, gameState.getCardPiles().get(0).size());
    }

    @Test
    void testCheckIfWin() {
        LinkedList<Card> pileOne = new LinkedList<>();
        pileOne.add(new Card(14, Card.Suit.SPADES));
        LinkedList<Card> pileTwo = new LinkedList<>();
        pileTwo.add(new Card(14, Card.Suit.HEARTS));
        LinkedList<Card> pileThree = new LinkedList<>();
        pileThree.add(new Card(14, Card.Suit.CLUBS));
        LinkedList<Card> pileFour = new LinkedList<>();
        pileFour.add(new Card(14, Card.Suit.DIAMONDS));
        GameState gameState = new GameState(pileOne, pileTwo, pileThree, pileFour);
        assertTrue(gameLogic.checkIfWin(gameState));
    }
}
