package pl.recursion.chess.figure.boundary;

import pl.recursion.chess.figure.domain.Player;

public class Black implements Player {

    @Override
    public int yDirection() {
        return -1;
    }
}
