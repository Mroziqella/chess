package pl.recursion.chess;

import io.vavr.collection.HashMap;
import pl.recursion.chess.game.boundary.Board;

public class Application {

    public static void main(String[] args) {
        Board board = new Board(3, 3, HashMap.empty());

    }
}
