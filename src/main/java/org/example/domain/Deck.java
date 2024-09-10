package org.example.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
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
			addSuit(suit);
		}
	}
	private void addSuit(Card.Suit suit) {
		for (int i = 2; i <= 14; i++) {
			cards.add(new Card(i, suit));
		}
	}

	public void shuffleDeck() {
		Collections.shuffle(cards);
	}

	public Card drawCard() {
		return cards.remove(cards.size()-1);
	}

	public Deck cloneDeck() {
		Deck copy = new Deck();
		copy.cards = cards.stream()
				.map(card -> new Card(card.getValue(), card.getSuit()))
				.collect(Collectors.toCollection(LinkedList::new));
		return copy;
	}
}
