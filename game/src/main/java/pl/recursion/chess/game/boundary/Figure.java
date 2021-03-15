package pl.recursion.chess.game.boundary;

import io.vavr.control.Option;


public interface Figure {

    Option<Figure> move(Cord cord, Board board);

    Cord position();

    boolean isPlayer(Player player);
}
