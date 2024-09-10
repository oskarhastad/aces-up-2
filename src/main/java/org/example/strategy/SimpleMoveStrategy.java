package org.example.strategy;

import org.example.logic.GameLogic;
import org.example.domain.Deck;
import org.example.domain.GameState;

public class SimpleMoveStrategy implements CardMoveStrategy{

    private final GameLogic gameLogic;

    public SimpleMoveStrategy() {
        this.gameLogic = new GameLogic();
    }


    @Override
    public boolean moveCard(GameState gamestate, Deck deck) {

        gamestate.checkEmptyAndMovable();
        boolean movedCard = false;

        if (!gamestate.getEmptyPiles().isEmpty() && !gamestate.getMovablePiles().isEmpty()) {
            movedCard = true;
            GameState candidate = gamestate;

            for (int i = 0; i < gamestate.getMovablePiles().size(); i++) {
                GameState temporary = gamestate.cloneGameState();

                temporary.checkEmptyAndMovable();
                temporary.getEmptyPiles().get(0).add(temporary.getMovablePiles().get(i).getLast());
                temporary.getMovablePiles().get(i).removeLast();
                gameLogic.removeCards(temporary);

                if (temporary.amountOfCards() <= candidate.amountOfCards()) {
                    candidate = temporary;
                }
            }

            gamestate.setCardPiles(candidate.getCardPiles());
        }

        return movedCard;
    }
}
