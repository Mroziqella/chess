package pl.recursion.chess.figure.boundary;

import io.vavr.collection.HashMap;
import io.vavr.collection.List;
import io.vavr.collection.Map;
import pl.recursion.chess.figure.domain.Figure;


import java.util.Objects;

public class Board {
    private final int height;
    private final int width;
    private final Map<String,Figure> activeFigures;

    public Board(int height, int width) {
        this(height, width, HashMap.empty());
    }

    public Board(int height, int width, Map<String,Figure> activeFigures) {
        this.height = height;
        this.width = width;
        this.activeFigures = activeFigures;
    }

    public Board move(String id, Cord to){
        activeFigures.get(id).map(x -> x.move(to,this));
        return null;
    }

    public int height() {
        return height;
    }

    public int width() {
        return width;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Board board = (Board) o;
        return height == board.height && width == board.width;
    }

    @Override
    public int hashCode() {
        return Objects.hash(height, width);
    }
}
