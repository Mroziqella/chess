package pl.recursion.chess.pawn.domain;

import io.vavr.collection.HashSet;
import io.vavr.collection.Set;
import pl.recursion.chess.game.boundary.Board;
import pl.recursion.chess.game.boundary.Cord;
import pl.recursion.chess.game.boundary.Player;


class AvailableMoves {
    private final Beating beating;
    private final Move move;

    public AvailableMoves(Beating beating, Move move) {
        this.beating = beating;
        this.move = move;
    }


    public boolean contains(Cord cord) {
        return beating.contains(cord) || move.contains(cord);
    }

    public record Beating(Set<Cord> cords, Board board) {

        public static Beating create(Board board) {
            return new Beating(HashSet.of(), board);
        }

        public Beating beat(Cord cord, Player player) {
            if (cord.valid(board) && board.canBeat(cord, player)) {
                return new Beating(cords.add(cord), board);
            }
            return new Beating(cords, board);
        }

        public boolean contains(Cord cord) {
            return cords.contains(cord);
        }
    }

    public record Move(Set<Cord> cords, Board board) {
        public static Move create(Cord cord, Board board) {
            if (cord.valid(board)) {
                return new Move(HashSet.of(cord), board);
            }
            return new Move(HashSet.empty(), board);
        }

        public Move add(Cord cord) {
            if (cord.valid(board)) {
                return new Move(cords.add(cord), board);
            }
            return new Move(cords, board);
        }

        public boolean contains(Cord cord) {
            return cords.contains(cord);
        }
    }
}
