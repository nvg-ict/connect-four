package nl.craftsmen.connect4


class Game(val gameRules: GameRules) {
    val board = Board(rows = gameRules.rows, cols = gameRules.cols)
    var currentPlayer: Player = Player.P1
}

enum class Player {
    P1, P2
}

data class GameRules(val rows: Int = 6, val cols: Int = 7) {
    val validColumns: IntRange get() = 1..cols
}