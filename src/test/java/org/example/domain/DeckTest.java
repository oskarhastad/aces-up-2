package org.example.domain;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DeckTest {

    @Test
    public void testDeckInitialization() {
        Deck deck = new Deck();
        List<Card> cards = deck.getCards();
        assertEquals(52, cards.size());

        HashSet<Card> uniqueCards = new HashSet<>(cards);
        assertEquals(52, uniqueCards.size());
    }

    @Test
    public void testShuffleDeck() {
        Deck deck1 = new Deck();
        Deck deck2 = new Deck();

        deck1.shuffle();
        deck2.shuffle();

        assertNotEquals(deck1.getCards(), deck2.getCards());
    }

    @Test
    public void testDrawCard() {
        Deck deck = new Deck();
        deck.shuffle();
        int initialSize = deck.getCards().size();

        Card drawnCard = deck.drawCard();
        assertNotNull(drawnCard);
        assertEquals(initialSize - 1, deck.getCards().size());
    }

    @Test
    public void testDrawFromEmptyDeck() {
        Deck deck = new Deck();

        while (!deck.getCards().isEmpty()) {
            deck.drawCard();
        }

        assertThrows(IllegalStateException.class, deck::drawCard);
    }

    @Test
    public void testCloneDeck() {
        Deck originalDeck = new Deck();
        originalDeck.shuffle();
        Deck clonedDeck = originalDeck.copyDeck();

        assertEquals(originalDeck.getCards(), clonedDeck.getCards());

        clonedDeck.drawCard();

        assertNotEquals(originalDeck.getCards().size(), clonedDeck.getCards().size());
    }
}