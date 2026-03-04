package connect.four

class Game(val gameRules: GameRules) {

    val board = Board(rows = gameRules.rows, cols = gameRules.cols)
    var currentPlayer: Player = Player.P1

    fun turnIndicator(): String {
        return "${currentPlayer.label}'s turn"
    }

    @Suppress("UnusedParameter")
    fun applyMove(column: Int) {
        currentPlayer = currentPlayer.other()
    }
}

enum class Player(val label: String) {
    P1("Player 1"),
    P2( "Player 2");

    fun other(): Player = if (this == P1) P2 else P1
}

data class GameRules(val rows: Int = 6, val cols: Int = 7) {
    val validColumns: IntRange get() = 1..cols
}
