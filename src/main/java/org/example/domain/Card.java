package org.example.domain;
public record Card(int value, Suit suit) {

	public Card {
		if (value < 2 || value > 14) {
			throw new IllegalArgumentException("Card value must be between 2 and 14.");
		}
	}

	@Override
	public String toString() {
		String faceValue;
		switch (value) {
			case 11 -> faceValue = "Jack";
			case 12 -> faceValue = "Queen";
			case 13 -> faceValue = "King";
			case 14 -> faceValue = "Ace";
			default -> faceValue = String.valueOf(value);
		}
		return faceValue + " of " + suit;
	}
}