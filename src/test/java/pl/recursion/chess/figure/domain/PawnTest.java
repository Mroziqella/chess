package pl.recursion.chess.figure.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pl.recursion.chess.figure.boundary.Cord;
import pl.recursion.chess.figure.boundary.Board;

import java.util.stream.Stream;

import static pl.recursion.chess.figure.boundary.Cord.create;

class PawnTest {

    private static final Board GAME_SIZE = new Board(8, 8);

    private static Stream<Arguments> method() {
        return Stream.of(
                Arguments.of(new Pawn(create("C", 3), new White()),
                        AvailableMoves.Move.create(create("C", 4), GAME_SIZE),
                        AvailableMoves.Beating.create(GAME_SIZE)
                                .add(create("B", 4))
                                .add(create("D", 4)), GAME_SIZE),

                Arguments.of(new Pawn(
                                createCord("D6"), new White()),
                        AvailableMoves.Move.create(createCord("D7"), GAME_SIZE),
                        AvailableMoves.Beating.create(GAME_SIZE)
                                .add(createCord("C7"))
                                .add(createCord("E7")), GAME_SIZE),

                Arguments.of(new Pawn(create("A", 3), new White()),
                        AvailableMoves.Move.create(create("A", 4), GAME_SIZE),
                        AvailableMoves.Beating.create(GAME_SIZE)
                                .add(create("B", 4)), GAME_SIZE),

                Arguments.of(new Pawn(createCord("H3"), new White()),
                        AvailableMoves.Move.create(createCord("H4"), GAME_SIZE),
                        AvailableMoves.Beating.create(GAME_SIZE)
                                .add(createCord("G4")), GAME_SIZE),

                Arguments.of(new Pawn(createCord("H3"), new Black()),
                        AvailableMoves.Move.create(createCord("H2"), GAME_SIZE),
                        AvailableMoves.Beating.create(GAME_SIZE)
                                .add(createCord("G2")), GAME_SIZE),

                Arguments.of(new Pawn(create("C", 3), new Black()),
                        AvailableMoves.Move.create(create("C", 2), GAME_SIZE),
                        AvailableMoves.Beating.create(GAME_SIZE)
                                .add(create("B", 2))
                                .add(create("D", 2)), GAME_SIZE),

                Arguments.of(new Pawn(create("C", 2), new White()),
                        AvailableMoves.Move.create(create("C", 3), GAME_SIZE)
                                .add(create("C", 4)),
                        AvailableMoves.Beating.create(GAME_SIZE)
                                .add(create("B", 3))
                                .add(create("D", 3)), GAME_SIZE),

                Arguments.of(new Pawn(create("C", 7), new Black()),
                        AvailableMoves.Move.create(create("C", 6), GAME_SIZE)
                                .add(create("C", 5)),
                        AvailableMoves.Beating.create(GAME_SIZE)
                                .add(create("B", 6))
                                .add(create("D", 6)), GAME_SIZE),

                Arguments.of(new Pawn(create("C", 5), new Black()),
                        AvailableMoves.Move.create(create("C", 4), new Board(6, 6))
                                .add(create("C", 3)),
                        AvailableMoves.Beating.create(new Board(6, 6))
                                .add(create("B", 4))
                                .add(create("D", 4)), new Board(6, 6)));
    }

    private static Cord createCord(String cords) {
        return create(String.valueOf((char) cords.codePointAt(0)), cords.codePointAt(1) - 48);
    }

    @ParameterizedTest(name = "Movement for {0}")
    @MethodSource("method")
    void obtainAvailableMove(Pawn pawn, AvailableMoves.Move move, AvailableMoves.Beating beating, Board board) {
        AvailableMoves availableMoves = pawn.obtainAvailableMove(board);
        Assertions.assertThat(availableMoves.getMove()).isEqualTo(move);
        Assertions.assertThat(availableMoves.getBeating()).isEqualTo(beating);
    }
}