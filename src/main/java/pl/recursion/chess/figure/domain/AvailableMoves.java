package pl.recursion.chess.figure.domain;

import io.vavr.collection.HashSet;
import io.vavr.collection.Set;
import pl.recursion.chess.figure.boundary.Board;
import pl.recursion.chess.figure.boundary.Cord;


public class AvailableMoves {
    private final Beating beating;
    private final Move move;

    public AvailableMoves(Beating beating, Move move) {
        this.beating = beating;
        this.move = move;
    }

    public Beating getBeating() {
        return beating;
    }

    public Move getMove() {
        return move;
    }

    public record Beating(Set<Cord> cords, Board board) {

        public static Beating create(Board board) {
            return new Beating(HashSet.of(), board);
        }

        public Beating add(Cord cord) {
            if (cord.valid(board)) {
                return new Beating(cords.add(cord), board);
            }
            return new Beating(cords, board);
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

    }
}
