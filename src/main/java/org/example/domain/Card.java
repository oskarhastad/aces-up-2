package org.example.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class Card {

	int value;
	Suit suit;

	public enum Suit {
		SPADES,
		HEARTS,
		CLUBS,
		DIAMONDS
	}

	void printCard() {
		System.out.println(value + " of " + suit.toString());
	}
}
