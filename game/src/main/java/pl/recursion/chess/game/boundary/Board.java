package pl.recursion.chess.game.boundary;

import io.vavr.collection.HashMap;
import io.vavr.collection.Map;
import lombok.EqualsAndHashCode;


import java.util.Objects;

@EqualsAndHashCode
public class Board {
    private final int height;
    private final int width;
    private final Map<Cord, Figure> activeFigures;

    public Board(int height, int width) {
        this(height, width, HashMap.empty());
    }

    public Board(int height, int width, Map<Cord, Figure> activeFigures) {
        this.height = height;
        this.width = width;
        this.activeFigures = activeFigures;
    }

    public int height() {
        return height;
    }

    public int width() {
        return width;
    }


    @Override
    public int hashCode() {
        return Objects.hash(height, width);
    }

    public boolean canBeat(Cord cord, Player player) {
        return activeFigures.get(cord).map(figure-> !figure.isPlayer(player)).getOrElse(false);
    }

    public Board add(Figure figure) {
        return new Board(height,width,activeFigures.put(figure.position(), figure));
    }
}
