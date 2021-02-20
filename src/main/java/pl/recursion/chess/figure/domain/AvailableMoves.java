package pl.recursion.chess.figure.domain;

import io.vavr.collection.HashSet;
import io.vavr.collection.Set;
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

    public record Beating(Set<Cord> cords) {

        public static Beating create() {
            return new Beating(HashSet.of());
        }

        public Beating add(Cord cord) {
            if (cord.valid()) {
                return new Beating(cords.add(cord));
            }
            return new Beating(cords);
        }
    }

    public record Move(Set<Cord> cords) {
        public static Move create(Cord cord) {
            if (cord.valid()) {
                return new Move(HashSet.of(cord));
            }
            return new Move(HashSet.empty());
        }

        public Move add(Cord cord) {
            if (cord.valid()) {
                return new Move(cords.add(cord));
            }
            return new Move(cords);
        }

    }
}
