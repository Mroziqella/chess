package pl.recursion.chess.game.boundary;

public record Cord(X x, Y y) {

    public Cord moveHorizontal(int horizontal) {
        return new Cord(x, Y.create(y.value() + horizontal));
    }

    public Cord moveVertical(int vertical) {
        return new Cord(X.create(x.value() + vertical), y);
    }

    public static Cord create(String x, int y) {
        return new Cord(X.create(x), Y.create(y));
    }

    public boolean valid(Board board) {
        return x.value > 0 && x.value <= board.width() && y.value > 0 && y.value <= board.height();
    }

    public boolean isPenultimateRowByY(Board board) {
        return y.value == board.height() - 1 || y.value == 2;
    }

    record Y(int value) {
        public static Y create(int value) {
            return new Y(value);
        }

    }

    record X(String symbol, int value) {
        public static X create(String symbol) {
            return new X(symbol, symbol.codePointAt(0) - 64);
        }

        public static X create(int value) {
            return new X(String.valueOf((char) (value + 64)), value);
        }
    }
}
