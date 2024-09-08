package org.example.domain;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Setter
@Getter
public class GameState {

	private List<LinkedList<Card>> piles = new ArrayList<>(4);
	private List<LinkedList<Card>> emptyPiles = new LinkedList<>();
	private List<LinkedList<Card>> movablePiles = new LinkedList<>();

	public GameState(){
		piles.add(new LinkedList<>());
		piles.add(new LinkedList<>());
		piles.add(new LinkedList<>());
		piles.add(new LinkedList<>());

	}
	public GameState(LinkedList<Card> pileOne, LinkedList<Card> pileTwo, LinkedList<Card> pileThree,
					 LinkedList<Card> pileFour) {
		piles.add(pileOne);
		piles.add(pileTwo);
		piles.add(pileThree);
		piles.add(pileFour);
	}

	public void checkEmptyAndMovable() {
		emptyPiles.clear();
		movablePiles.clear();

		for (LinkedList<Card> checkPile : getPiles()) {
			if (checkPile.isEmpty()) {
				getEmptyPiles().add(checkPile);
			}
			if (checkPile.size() >= 2) {
				getMovablePiles().add(checkPile);
			}
		}
	}

	public int amountOfCards() {
		return getPiles().stream().mapToInt(List::size).sum();
	}


	public GameState cloneGamestate() {
		List<LinkedList<Card>> copy = new ArrayList<>();

		for (int i = 0; i < piles.size(); i++) {
			LinkedList<Card> tempList = new LinkedList<>();
			copy.add(tempList);

			for (int j = 0; j < piles.get(i).size(); j++) {
				Card tempcard = new Card(piles.get(i).get(j).value, piles.get(i).get(j).suit);
				copy.get(i).add(tempcard);
			}
		}

		return new GameState(copy.get(0), copy.get(1), copy.get(2), copy.get(3));
	}
}