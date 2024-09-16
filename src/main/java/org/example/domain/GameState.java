package org.example.domain;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Setter
@Getter
public class GameState {

	private List<ArrayList<Card>> cardPiles = new ArrayList<>(4);

	public GameState() {
		for (int i = 0; i < 4; i++) {
			cardPiles.add(new ArrayList<>());
		}
	}

	public GameState(List<ArrayList<Card>> cardPiles) {
		this.cardPiles = cardPiles;

	}

	public List<ArrayList<Card>> getEmptyPiles() {
		return cardPiles.stream()
				.filter(List::isEmpty)
				.collect(Collectors.toList());
	}

	public List<ArrayList<Card>> getMovablePiles() {
		return cardPiles.stream()
				.filter(pile -> pile.size() >= 2)
				.collect(Collectors.toList());
	}

	public int getCardCount() {
		return getCardPiles().stream().mapToInt(List::size).sum();
	}

	public GameState copyGameState() {
		List<ArrayList<Card>> copy = cardPiles.stream()
				.map(ArrayList::new)
				.toList();
		return new GameState(copy);
	}
}