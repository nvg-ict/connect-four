package connect.four

class Game(
    val gameRules: GameRules,
    private val winChecker: WinChecker = WinChecker()
) {
    val board = Board(rows = gameRules.rows, cols = gameRules.cols)
    var currentPlayer: Player = Player.P1

    fun turnIndicator(): String {
        return "${currentPlayer.label}'s turn"
    }

    fun applyMove(column: Int): GameMoveResult {
        val result = when (val drop = board.dropInColumn(column, Cell.forPlayer(currentPlayer))) {
            is DropResult.Failure ->
                GameMoveResult.Failure(drop.errorMessage)

            is DropResult.Success -> {
                val position = drop.position

                if (winChecker.horizontalWin(board, position, currentPlayer) ||
                    winChecker.verticalWin(board, position, currentPlayer)
                    ) {
                    GameMoveResult.Win(currentPlayer)
                } else {
                    currentPlayer = currentPlayer.other()
                    GameMoveResult.Success(position)
                }
            }
        }

        return result
    }
}

sealed class GameMoveResult {
    data class Success(val position: Position) : GameMoveResult()
    data class Win(val player: Player) : GameMoveResult()
    data class Failure(val reason: String) : GameMoveResult()
}


enum class Player(val label: String) {
    P1("Player 1"),
    P2( "Player 2");

    fun other(): Player = if (this == P1) P2 else P1
}

data class GameRules(val rows: Int = 6, val cols: Int = 7) {
    val validColumns: IntRange get() = 1..cols
}
