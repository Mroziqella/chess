package pl.recursion.chess.game.domain;

import pl.recursion.chess.game.boundary.Player;

public class Black implements Player {

    @Override
    public int yDirection() {
        return -1;
    }
}
