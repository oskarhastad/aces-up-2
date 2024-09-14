package org.example.domain;

import lombok.Getter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Getter
public class Deck {

	private List<Card> cards = new ArrayList<>();

	public Deck() {
		addAllCards();
	}

	private void addAllCards() {
		for(Card.Suit suit: Card.Suit.values()) {
			IntStream.rangeClosed(2,14).forEach(value -> cards.add(new Card(value, suit)));
		}
	}

	public void shuffleDeck() {
		Collections.shuffle(cards);
	}

	public Card drawCard() {
		if (cards.isEmpty()) {
			throw new IllegalStateException("Cannot draw from an empty deck");
		}
		return cards.remove(cards.size() - 1);
	}

	public Deck cloneDeck() {
		Deck copy = new Deck();
		copy.cards = cards.stream()
				.map(card -> new Card(card.value(), card.suit()))
				.collect(Collectors.toList());
		return copy;
	}
}
