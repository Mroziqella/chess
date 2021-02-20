package pl.recursion.chess.figure.domain;

import pl.recursion.chess.chessboard.Board;
import pl.recursion.chess.figure.boundary.Cord;

class Pawn implements Figure {
    private final Cord cord;
    private final Player player;

    public Pawn(Cord cord, Player player) {
        this.cord = cord;
        this.player = player;
    }

    @Override
    public Figure move(Cord cord, Board board) {
        return null;
    }

    public AvailableMoves obtainAvailableMove() {
        AvailableMoves.Beating beating = AvailableMoves.Beating.create()
                .add(cord.moveHorizontal(player.yDirection()).moveVertical(1))
                .add(cord.moveHorizontal(player.yDirection()).moveVertical(-1));

        AvailableMoves.Move move = AvailableMoves.Move.create(cord.moveHorizontal(player.yDirection()));
        if (cord.isPenultimateRowByY()) {
            move = move.add(cord.moveHorizontal(player.yDirection() * 2));
        }
        return new AvailableMoves(beating, move);
    }

    @Override
    public String toString() {
        return "Pawn [" + player + "] " + cord.x().symbol() + cord.y().value();


    }
}
