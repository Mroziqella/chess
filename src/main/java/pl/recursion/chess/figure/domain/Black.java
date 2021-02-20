package pl.recursion.chess.figure.domain;

public class Black implements Player{

    @Override
    public int yDirection() {
        return -1;
    }
}
