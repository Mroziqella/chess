package pl.recursion.chess.figure.domain;

import pl.recursion.chess.chessboard.Board;
import pl.recursion.chess.figure.boundary.Cord;

public interface Figure {

    Figure move(Cord cord, Board board);
}
