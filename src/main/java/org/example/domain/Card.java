package org.example.domain;
public record Card(int value, org.example.domain.Card.Suit suit) {

	public enum Suit {
		SPADES,
		HEARTS,
		CLUBS,
		DIAMONDS
	}

	@Override
	public String toString() {
		return value + " of " + suit;
	}
}
