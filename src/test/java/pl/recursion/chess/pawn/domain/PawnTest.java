package pl.recursion.chess.pawn.domain;

import io.vavr.control.Option;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pl.recursion.chess.figure.boundary.Black;
import pl.recursion.chess.figure.boundary.Cord;
import pl.recursion.chess.figure.boundary.Board;
import pl.recursion.chess.figure.boundary.White;
import pl.recursion.chess.figure.domain.Figure;


import java.util.stream.Stream;

import static pl.recursion.chess.figure.boundary.Cord.create;

class PawnTest {

    private static final Board GAME_SIZE = new Board(8, 8);
    public static final White PLAYER = new White();
    public static final Black PLAYER_BLACK = new Black();

    private static Stream<Arguments> method() {
        return Stream.of(
                Arguments.of(new Pawn(create("C", 3), PLAYER), GAME_SIZE, create("C", 4), Option.of(new Pawn(create("C", 4), PLAYER))),
                Arguments.of(new Pawn(create("C", 2), PLAYER), GAME_SIZE, create("C", 4), Option.of(new Pawn(create("C", 4), PLAYER))),
                Arguments.of(new Pawn(create("C", 2), PLAYER), GAME_SIZE, create("C", 1), Option.none()),
                Arguments.of(new Pawn(create("C", 2), PLAYER), GAME_SIZE, create("C", 5), Option.none()),
                Arguments.of(new Pawn(create("A", 2), PLAYER), GAME_SIZE, create("B", 3), Option.none()),
                Arguments.of(new Pawn(create("H", 2), PLAYER), GAME_SIZE, create("I", 3), Option.none()),
                Arguments.of(new Pawn(create("H", 2), PLAYER), GAME_SIZE, create("G", 3), Option.none()),

                Arguments.of(new Pawn(create("C", 3), PLAYER_BLACK), GAME_SIZE, create("C", 2), Option.of(new Pawn(create("C", 2), PLAYER_BLACK))),
                Arguments.of(new Pawn(create("C", 7), PLAYER_BLACK), GAME_SIZE, create("C", 5), Option.of(new Pawn(create("C", 5), PLAYER_BLACK))),
                Arguments.of(new Pawn(create("C", 2), PLAYER_BLACK), GAME_SIZE, create("C", 3), Option.none()),
                Arguments.of(new Pawn(create("C", 2), PLAYER_BLACK), GAME_SIZE, create("C", 5), Option.none()),
                Arguments.of(new Pawn(create("A", 2), PLAYER_BLACK), GAME_SIZE, create("B", 3), Option.none()),
                Arguments.of(new Pawn(create("H", 2), PLAYER_BLACK), GAME_SIZE, create("I", 3), Option.none()),
                Arguments.of(new Pawn(create("H", 2), PLAYER_BLACK), GAME_SIZE, create("G", 3), Option.none()),

                Arguments.of(new Pawn(create("C", 2), PLAYER), GAME_SIZE.add(new Pawn(create("A", 3), PLAYER_BLACK)), create("A", 3), Option.none()),
                Arguments.of(new Pawn(create("B", 2), PLAYER), GAME_SIZE.add(new Pawn(create("A", 3), PLAYER_BLACK)), create("A", 3), Option.of(new Pawn(create("A", 3), PLAYER))),
                Arguments.of(new Pawn(create("B", 2), PLAYER), GAME_SIZE.add(new Pawn(create("C", 3), PLAYER_BLACK)), create("C", 3), Option.of(new Pawn(create("C", 3), PLAYER))),
                Arguments.of(new Pawn(create("B", 2), PLAYER), GAME_SIZE.add(new Pawn(create("C", 3), PLAYER)), create("C", 3), Option.none()),
                Arguments.of(new Pawn(create("B", 2), PLAYER), GAME_SIZE.add(new Pawn(create("A", 3), PLAYER_BLACK)), create("D", 3), Option.none()),

                Arguments.of(new Pawn(create("C", 7), PLAYER_BLACK), GAME_SIZE.add(new Pawn(create("A", 6), PLAYER)), create("A", 6), Option.none()),
                Arguments.of(new Pawn(create("B", 7), PLAYER_BLACK), GAME_SIZE.add(new Pawn(create("A", 6), PLAYER)), create("A", 6), Option.of(new Pawn(create("A", 6), PLAYER_BLACK))),
                Arguments.of(new Pawn(create("B", 7), PLAYER_BLACK), GAME_SIZE.add(new Pawn(create("C", 6), PLAYER)), create("C", 6), Option.of(new Pawn(create("C", 6), PLAYER_BLACK))),
                Arguments.of(new Pawn(create("B", 7), PLAYER_BLACK), GAME_SIZE.add(new Pawn(create("C", 6), PLAYER_BLACK)), create("C", 6), Option.none()),
                Arguments.of(new Pawn(create("B", 7), PLAYER_BLACK), GAME_SIZE.add(new Pawn(create("A", 6), PLAYER)), create("D", 6), Option.none())

        );
    }

    private static Cord createCord(String cords) {
        return create(String.valueOf((char) cords.codePointAt(0)), cords.codePointAt(1) - 48);
    }

    @ParameterizedTest(name = "Movement for {0}")
    @MethodSource("method")
    void obtainAvailableMove(Pawn pawn, Board board, Cord cord, Option<Figure> figure) {
        Option<Figure> move = pawn.move(cord, board);

        Assertions.assertThat(figure).isEqualTo(move);
    }
}