package org.example.strategy;

import org.example.domain.Deck;
import org.example.domain.GameState;

public interface CardMoveStrategy {

    boolean moveCard(GameState gamestate, Deck deck);

}