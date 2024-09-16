package org.example.domain;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CardTest {

    @Test
    public void testValidCardCreation() {
        Card card = new Card(10, Suit.HEARTS);
        assertEquals(10, card.value());
        assertEquals(Suit.HEARTS, card.suit());
    }

    @Test
    public void testInvalidCardCreation() {
        assertThrows(IllegalArgumentException.class, () -> new Card(1, Suit.SPADES));
        assertThrows(IllegalArgumentException.class, () -> new Card(15, Suit.CLUBS));
    }

    @Test
    public void testToStringForFaceCards() {
        Card jack = new Card(11, Suit.DIAMONDS);
        assertEquals("Jack of Diamonds", jack.toString());

        Card queen = new Card(12, Suit.SPADES);
        assertEquals("Queen of Spades", queen.toString());

        Card king = new Card(13, Suit.CLUBS);
        assertEquals("King of Clubs", king.toString());

        Card ace = new Card(14, Suit.HEARTS);
        assertEquals("Ace of Hearts", ace.toString());
    }

    @Test
    public void testToStringForNumberCards() {
        Card seven = new Card(7, Suit.HEARTS);
        assertEquals("7 of Hearts", seven.toString());

        Card ten = new Card(10, Suit.SPADES);
        assertEquals("10 of Spades", ten.toString());
    }
}