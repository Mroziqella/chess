module game {
    requires transitive lombok;
    requires transitive io.vavr;
    exports pl.recursion.chess.game.boundary;
    exports pl.recursion.chess.game.domain;
}