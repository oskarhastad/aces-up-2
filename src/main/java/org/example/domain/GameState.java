package org.example.domain;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Setter
@Getter
public class GameState {

	private List<LinkedList<Card>> cardPiles = new ArrayList<>(4);
	private List<LinkedList<Card>> emptyPiles = new ArrayList<>();
	private List<LinkedList<Card>> movablePiles = new ArrayList<>();

	public GameState() {
		for (int i = 0; i < 4; i++) {
			cardPiles.add(new LinkedList<>());
		}

	}

	public GameState(List<LinkedList<Card>> cardPiles) {
		this.cardPiles = cardPiles;

	}

	public void checkEmptyAndMovable() {
		emptyPiles = cardPiles.stream()
				.filter(List::isEmpty)
				.collect(Collectors.toList());

		movablePiles = cardPiles.stream()
				.filter(pile -> pile.size() >= 2)
				.collect(Collectors.toList());
	}


	public int amountOfCards() {
		return getCardPiles().stream().mapToInt(List::size).sum();
	}


	public GameState cloneGameState() {
		List<LinkedList<Card>> copy = cardPiles.stream()
				.map(this::clonePile)
				.collect(Collectors.toList());

		return new GameState(copy);
	}

	private LinkedList<Card> clonePile(LinkedList<Card> pile) {
		return pile.stream()
				.map(card -> new Card(card.getValue(), card.getSuit()))
				.collect(Collectors.toCollection(LinkedList::new));
	}
}