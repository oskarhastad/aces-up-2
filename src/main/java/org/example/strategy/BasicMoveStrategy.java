package org.example.strategy;

import org.example.domain.Card;
import org.example.logic.GameLogic;
import org.example.domain.Deck;
import org.example.domain.GameState;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class BasicMoveStrategy implements CardMoveStrategy{

    private final GameLogic gameLogic;

    public BasicMoveStrategy() {
        this.gameLogic = new GameLogic();
    }

    @Override
    public boolean moveCard(GameState gameState, Deck deck) {
        List<ArrayList<Card>> emptyPiles = gameState.getEmptyPiles();
        List<ArrayList<Card>> movablePiles = gameState.getMovablePiles();

        if (!emptyPiles.isEmpty() && !movablePiles.isEmpty()) {
            GameState candidate = gameState;

            for (int i = 0; i < movablePiles.size(); i++) {
                GameState temp = gameState.copyGameState();
                List<ArrayList<Card>> tempEmptyPiles = temp.getEmptyPiles();
                List<ArrayList<Card>> tempMovablePiles = temp.getMovablePiles();
                tempEmptyPiles.get(0).add(tempMovablePiles.get(i).get(tempMovablePiles.get(i).size() - 1));
                tempMovablePiles.get(i).remove(tempMovablePiles.get(i).size() - 1);
                gameLogic.removeCards(temp);

                if (temp.getCardCount() <= candidate.getCardCount()) {
                    candidate = temp;
                }
            }
            gameState.setCardPiles(candidate.getCardPiles());
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Basic Move Strategy";
    }
}
