package org.example.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
public class Card {

	private final int value;
	private final Suit suit;

	public enum Suit {
		SPADES,
		HEARTS,
		CLUBS,
		DIAMONDS
	}

	public void printCard() {
		System.out.println(value + " of " + suit.toString());
	}
}
