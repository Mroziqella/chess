package pl.recursion.chess.pawn.domain;

import io.vavr.control.Option;
import lombok.EqualsAndHashCode;
import pl.recursion.chess.figure.boundary.Board;
import pl.recursion.chess.figure.boundary.Cord;
import pl.recursion.chess.figure.domain.Figure;
import pl.recursion.chess.figure.domain.Player;

@EqualsAndHashCode
class Pawn implements Figure {
    private final Cord cord;
    private final Player player;

    public Pawn(Cord cord, Player player) {
        this.cord = cord;
        this.player = player;
    }

    @Override
    public Option<Figure> move(Cord cord, Board board) {
        AvailableMoves availableMoves = obtainAvailableMove(board);
        if (availableMoves.contains(cord)) {
            return Option.of(new Pawn(cord, player));
        }
        return Option.none();
    }

    @Override
    public Cord position() {
        return cord;
    }

    @Override
    public boolean isPlayer(Player player) {
        return this.player == player;
    }

    private AvailableMoves obtainAvailableMove(Board board) {
        AvailableMoves.Beating beating = AvailableMoves.Beating.create(board)
                .beat(cord.moveHorizontal(player.yDirection()).moveVertical(1), player)
                .beat(cord.moveHorizontal(player.yDirection()).moveVertical(-1), player);

        AvailableMoves.Move move = AvailableMoves.Move.create(cord.moveHorizontal(player.yDirection()), board);
        if (cord.isPenultimateRowByY(board)) {
            move = move.add(cord.moveHorizontal(player.yDirection() * 2));
        }
        return new AvailableMoves(beating, move);
    }

    @Override
    public String toString() {
        return "Pawn [" + player + "] " + cord.x().symbol() + cord.y().value();


    }
}
