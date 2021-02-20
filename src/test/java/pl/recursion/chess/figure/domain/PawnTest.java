package pl.recursion.chess.figure.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pl.recursion.chess.figure.boundary.Cord;
import pl.recursion.chess.figure.boundary.GameSize;

import java.util.stream.Stream;

class PawnTest {

    private static final GameSize GAME_SIZE = new GameSize(8, 8);

    private static Stream<Arguments> method() {
        return Stream.of(
                Arguments.of(new Pawn(Cord.create("C", 3, GAME_SIZE), new White()),
                        AvailableMoves.Move.create(Cord.create("C", 4, GAME_SIZE)),
                        AvailableMoves.Beating.create()
                                .add(Cord.create("B", 4, GAME_SIZE))
                                .add(Cord.create("D", 4, GAME_SIZE))),

                Arguments.of(new Pawn(
                                createCord("D6"), new White()),
                        AvailableMoves.Move.create(createCord("D7")),
                        AvailableMoves.Beating.create()
                                .add(createCord("C7"))
                                .add(createCord("E7"))),

                Arguments.of(new Pawn(Cord.create("A", 3, GAME_SIZE), new White()),
                        AvailableMoves.Move.create(Cord.create("A", 4, GAME_SIZE)),
                        AvailableMoves.Beating.create()
                                .add(Cord.create("B", 4, GAME_SIZE))),

                Arguments.of(new Pawn(createCord("H3"), new White()),
                        AvailableMoves.Move.create(createCord("H4")),
                        AvailableMoves.Beating.create()
                                .add(createCord("G4"))),

                Arguments.of(new Pawn(createCord("H3"), new Black()),
                        AvailableMoves.Move.create(createCord("H2")),
                        AvailableMoves.Beating.create()
                                .add(createCord("G2"))),

                Arguments.of(new Pawn(Cord.create("C", 3, GAME_SIZE), new Black()),
                        AvailableMoves.Move.create(Cord.create("C", 2, GAME_SIZE)),
                        AvailableMoves.Beating.create()
                                .add(Cord.create("B", 2, GAME_SIZE))
                                .add(Cord.create("D", 2, GAME_SIZE))),

                Arguments.of(new Pawn(Cord.create("C", 2, GAME_SIZE), new White()),
                        AvailableMoves.Move.create(Cord.create("C", 3, GAME_SIZE))
                                .add(Cord.create("C", 4, GAME_SIZE)),
                        AvailableMoves.Beating.create()
                                .add(Cord.create("B", 3, GAME_SIZE))
                                .add(Cord.create("D", 3, GAME_SIZE))),

                Arguments.of(new Pawn(Cord.create("C", 7, GAME_SIZE), new Black()),
                        AvailableMoves.Move.create(Cord.create("C", 6, GAME_SIZE))
                                .add(Cord.create("C", 5, GAME_SIZE)),
                        AvailableMoves.Beating.create()
                                .add(Cord.create("B", 6, GAME_SIZE))
                                .add(Cord.create("D", 6, GAME_SIZE))),

                Arguments.of(new Pawn(Cord.create("C", 5, new GameSize(6, 6)), new Black()),
                        AvailableMoves.Move.create(Cord.create("C", 4, new GameSize(6, 6)))
                                .add(Cord.create("C", 3, new GameSize(6, 6))),
                        AvailableMoves.Beating.create()
                                .add(Cord.create("B", 4, new GameSize(6, 6)))
                                .add(Cord.create("D", 4, new GameSize(6, 6))))
        );
    }

    private static Cord createCord(String cords) {
        return Cord.create(String.valueOf((char) cords.codePointAt(0)), cords.codePointAt(1) - 48, GAME_SIZE);
    }

    @ParameterizedTest(name = "Movement for {0}")
    @MethodSource("method")
    void obtainAvailableMove(Pawn pawn, AvailableMoves.Move move, AvailableMoves.Beating beating) {
        AvailableMoves availableMoves = pawn.obtainAvailableMove();
        Assertions.assertThat(availableMoves.getMove()).isEqualTo(move);
        Assertions.assertThat(availableMoves.getBeating()).isEqualTo(beating);
    }
}