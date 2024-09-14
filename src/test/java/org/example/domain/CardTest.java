package org.example.domain;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CardTest {

    @Test
    void testCardCreation() {
        Card card = new Card(10, Card.Suit.HEARTS);
        assertEquals(10, card.value());
        assertEquals(Card.Suit.HEARTS, card.suit());
    }

    @Test
    void testCardEquality() {
        Card card1 = new Card(10, Card.Suit.HEARTS);
        Card card2 = new Card(10, Card.Suit.HEARTS);
        assertEquals(card1.value(), card2.value());
        assertEquals(card1.suit(), card2.suit());
    }
}
