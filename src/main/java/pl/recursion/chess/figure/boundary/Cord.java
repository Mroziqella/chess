package pl.recursion.chess.figure.boundary;

public record Cord(X x, Y y, GameSize gameSize) {

    public Cord moveHorizontal(int horizontal) {
        return new Cord(x, Y.create(y.value() + horizontal), gameSize);
    }

    public Cord moveVertical(int vertical) {
        return new Cord(X.create(x.value() + vertical), y, gameSize);
    }

    public static Cord create(String x, int y, GameSize gameSize) {
        return new Cord(X.create(x), Y.create(y), gameSize);
    }

    public boolean valid() {
        return x.value > 0 && x.value <= gameSize.width() && y.value > 0 && y.value <= gameSize.height();
    }

    public boolean isPenultimateRowByY() {
        return y.value == gameSize.height() - 1 || y.value == 2;
    }

    public record Y(int value) {
        public static Y create(int value) {
            return new Y(value);
        }

    }

    public record X(String symbol, int value) {
        public static X create(String symbol) {
            return new X(symbol, symbol.codePointAt(0) - 64);
        }

        public static X create(int value) {
            return new X(String.valueOf((char) (value + 64)), value);
        }
    }
}
