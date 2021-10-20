package pl.recursion.chess.game.boundary;

import io.vavr.collection.HashMap;
import io.vavr.collection.Map;
import lombok.EqualsAndHashCode;


import java.util.Objects;
import java.util.UUID;

@EqualsAndHashCode(of = {"uuid"})
public class Board {
    private final UUID uuid;
    private final int height;
    private final int width;
    private final Map<Cord, Figure> activeFigures;

    public Board(int height, int width) {
        this(UUID.randomUUID(), height, width, HashMap.empty());
    }

    public Board(UUID uuid, int height, int width, Map<Cord, Figure> activeFigures) {
        this.uuid = uuid;
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

    public boolean canBeat(Cord cord, Player player) {
        return activeFigures.get(cord).map(figure -> !figure.isPlayer(player)).getOrElse(false);
    }

    public Board add(Figure figure) {
        return new Board(uuid, height, width, activeFigures.put(figure.position(), figure));
    }
}
