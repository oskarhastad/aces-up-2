package org.example;
import org.example.domain.Deck;
import org.example.domain.GameState;
import org.example.strategy.CardMoveStrategy;

import java.util.LinkedList;

public class GameRunner {

    public int runSimulations(CardMoveStrategy cardMoveStrategy, int simulations) {

        int amountOfCompleted = 0;

        for(int i = 0; i < simulations; i++) {
            System.out.println("Running simulation no: " + (i+1));
            Deck deck = new Deck();
            GameState gameState = new GameState(new LinkedList<>(), new LinkedList<>(), new LinkedList<>(), new LinkedList<>());
            if(playGame(cardMoveStrategy, gameState, deck)) amountOfCompleted++;
        }

        return amountOfCompleted;
    }

    private boolean playGame(CardMoveStrategy cardMoveStrategy, GameState gameState, Deck deck) {

        while (!deck.cards.isEmpty()) {
            gameState.dealCards(deck);
            boolean changed = true;
            while(changed) {
                changed = gameState.removeCards();
                if(cardMoveStrategy.moveCard(gameState, deck)) changed = true;
            }
        }

        return gameState.checkIfWin();
    }
}
