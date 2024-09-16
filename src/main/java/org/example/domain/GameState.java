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

	public GameState() {
		for (int i = 0; i < 4; i++) {
			cardPiles.add(new LinkedList<>());
		}
	}

	public GameState(List<LinkedList<Card>> cardPiles) {
		this.cardPiles = cardPiles;

	}

	public List<LinkedList<Card>> getEmptyPiles() {
		return cardPiles.stream()
				.filter(List::isEmpty)
				.collect(Collectors.toList());
	}

	public List<LinkedList<Card>> getMovablePiles() {
		return cardPiles.stream()
				.filter(pile -> pile.size() >= 2)
				.collect(Collectors.toList());
	}

	public int getCardCount() {
		return getCardPiles().stream().mapToInt(List::size).sum();
	}

	public GameState copyGameState() {
		List<LinkedList<Card>> copy = cardPiles.stream()
				.map(LinkedList::new)
				.toList();
		return new GameState(copy);
	}
}