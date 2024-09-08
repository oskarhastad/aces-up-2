package org.example.domain;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CardTest {

    @Test
    void testCardCreation() {
        Card card = new Card(10, Card.Suit.HEARTS);
        assertEquals(10, card.getValue());
        assertEquals(Card.Suit.HEARTS, card.getSuit());
    }

    @Test
    void testCardEquality() {
        Card card1 = new Card(10, Card.Suit.HEARTS);
        Card card2 = new Card(10, Card.Suit.HEARTS);
        assertEquals(card1.getValue(), card2.getValue());
        assertEquals(card1.getSuit(), card2.getSuit());
    }
}
