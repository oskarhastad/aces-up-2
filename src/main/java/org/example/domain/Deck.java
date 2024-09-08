package org.example.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Setter
@Getter
public class Deck {

	public List<Card> cards = new LinkedList<>();

	public Deck() {
		addSuit(Card.Suit.HEARTS);
		addSuit(Card.Suit.DIAMONDS);
		addSuit(Card.Suit.SPADES);
		addSuit(Card.Suit.CLUBS);

		shuffleDeck();
	}

	void addSuit(Card.Suit suit) {
		for (int i = 2; i <= 14; i++) {
			cards.add(new Card(i, suit));
		}
	}

	private void shuffleDeck() {
		Collections.shuffle(cards);
	}

	public Card drawCard() {
		return cards.remove(0);
	}

	public Deck cloneDeck() {
		Deck copy = new Deck();

		List<Card> temp = new LinkedList<Card>();
		for (Card c : cards) {
			temp.add(new Card(c.value, c.suit));
		}

		copy.cards = temp;
		return copy;

	}

	public void printDeck() {
		System.out.println("Deck starts:");

		for (Card i : cards) {
			System.out.println(i.value + " of " + i.suit);
		}

		System.out.println("Deck ends");
	}

}
