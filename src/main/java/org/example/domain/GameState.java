package org.example.domain;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class GameState {

	public List<LinkedList<Card>> piles = new ArrayList<>(4);
	public List<LinkedList<Card>> emptyPiles = new LinkedList<>();
	public List<LinkedList<Card>> movablePiles = new LinkedList<>();

	public GameState(LinkedList<Card> pileOne, LinkedList<Card> pileTwo, LinkedList<Card> pileThree,
					 LinkedList<Card> pileFour) {
		piles.add(pileOne);
		piles.add(pileTwo);
		piles.add(pileThree);
		piles.add(pileFour);
	}

	public void dealCards(Deck deck) {
		for (LinkedList<Card> pile : piles)
			pile.add(deck.drawCard());
	}

	public boolean removeCards() {
		boolean wasChanged = false;
		boolean changed = true;

		while (changed) {
			changed = false;
			for (int i = 0; i < piles.size(); i++) {
				for (int j = i + 1; j < piles.size(); j++) {
					if (!piles.get(i).isEmpty() && !piles.get(j).isEmpty()) {
						if (compareRemoveCards(piles.get(i), piles.get(j))) {
							changed = true;
							wasChanged = true;
						}
					}
				}
			}
		}

		return wasChanged;

	}

	boolean compareRemoveCards(LinkedList<Card> firstPile, LinkedList<Card> secondPile) {
		Card lastPileOne = firstPile.getLast();
		Card lastPileTwo = secondPile.getLast();

		if (lastPileOne.suit == lastPileTwo.suit) {
			if (lastPileOne.value > lastPileTwo.value) {
				secondPile.removeLast();
				return true;
			}
			firstPile.removeLast();
			return true;
		}

		return false;
	}

	public void checkEmptyAndMovable() {
		emptyPiles.clear();
		movablePiles.clear();

		for (LinkedList<Card> checkPile : piles) {
			if (checkPile.isEmpty()) {
				emptyPiles.add(checkPile);
			}
			if (checkPile.size() >= 2) {
				movablePiles.add(checkPile);
			}
		}
	}

	public int amountOfCards() {
		int amount = 0;

		for (LinkedList<Card> pile : piles) {
			amount = amount + pile.size();
		}

		return amount;
	}

	public boolean checkIfWin() {
		for(LinkedList<Card> pile: piles) {
			if(pile.size() != 1) return false;
		}
		return true;
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