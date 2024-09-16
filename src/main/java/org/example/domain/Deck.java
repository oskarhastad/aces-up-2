package org.example.domain;

import lombok.Getter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

@Getter
public class Deck {

	private List<Card> cards = new ArrayList<>();

	public Deck() {
		addAllCards();
	}

	private Deck(List<Card> cards) {
		this.cards = cards;
	}

	public Deck copyDeck() {
		return new Deck(new ArrayList<>(this.cards));
	}

	private void addAllCards() {
		for(Suit suit: Suit.values()) {
			IntStream.rangeClosed(2,14).forEach(value -> cards.add(new Card(value, suit)));
		}
	}

	public void shuffle() {
		Collections.shuffle(cards, ThreadLocalRandom.current());
	}

	public Card drawCard() {
		if (cards.isEmpty()) {
			throw new IllegalStateException("Cannot draw from an empty deck");
		}
		return cards.remove(cards.size() - 1);
	}
}