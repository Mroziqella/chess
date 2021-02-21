package pl.recursion.chess.figure.domain;

import io.vavr.control.Option;
import pl.recursion.chess.figure.boundary.Board;
import pl.recursion.chess.figure.boundary.Cord;


public interface Figure {

    Option<Figure> move(Cord cord, Board board);

    Cord position();

    boolean isPlayer(Player player);
}
