package org.example.domain;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DeckTest {

    @Test
    void testDeckSize() {
        Deck deck = new Deck();
        assertEquals(52, deck.getCards().size());
    }

    @Test
    void testDrawCardReducesDeckSize() {
        Deck deck = new Deck();
        deck.drawCard();
        assertEquals(51, deck.getCards().size());
    }

    @Test
    void testShuffleDeck() {
        Deck deck = new Deck();
        Deck unshuffledDeck = new Deck();
        deck.shuffle();
        assertNotEquals(deck.getCards(), unshuffledDeck.getCards());
    }
}
