package connect.four

import connect.four.Player.P1
import connect.four.Player.P2

class Game(
    val gameRules: GameRules,
    private val winChecker: WinChecker = WinChecker()
) {
    val board = Board(rows = gameRules.rows, cols = gameRules.cols)
    var currentPlayer: Player = Player.P1

    private var lastMoveResult: GameMoveResult? = null

    fun status(): GameStatus =
        when (val result = lastMoveResult) {
            is GameMoveResult.Win -> GameStatus.Win(result.player)
            GameMoveResult.Draw -> GameStatus.Draw
            else -> GameStatus.Turn(currentPlayer)
        }

    fun applyMove(column: Int): GameMoveResult {
        val result = when (val drop = board.dropInColumn(column, Cell.forPlayer(currentPlayer))) {
            is DropResult.Failure ->
                GameMoveResult.Failure(drop.errorMessage)

            is DropResult.Success -> {
                val position = drop.position

                when {
                    winChecker.isWin(board, position, currentPlayer) -> {
                        val winningPositions =
                            winChecker.findWinningPositions(board, position, currentPlayer)
                        board.markWinning(winningPositions)
                        GameMoveResult.Win(currentPlayer)
                    }

                    board.isFull() ->
                        GameMoveResult.Draw

                    else -> {
                        currentPlayer = currentPlayer.other()
                        GameMoveResult.Success(position)
                    }
                }
            }
        }

        lastMoveResult = result
        return result
    }
}

sealed class GameMoveResult {
    data class Success(val position: Position) : GameMoveResult()
    data class Win(val player: Player) : GameMoveResult()
    object Draw : GameMoveResult()
    data class Failure(val reason: String) : GameMoveResult()
}

sealed interface GameStatus {
    data class Turn(val player: Player) : GameStatus
    data class Win(val player: Player) : GameStatus
    object Draw : GameStatus
}

enum class Player(val label: String) {
    P1("Player 1"),
    P2( "Player 2");

    fun other(): Player = if (this == P1) P2 else P1
}

fun Int.toPlayer(): Player = when (this) {
    1 -> P1
    2 -> P2
    else -> throw IllegalArgumentException("Unknown player label: $this")
}


data class GameRules(val rows: Int = 6, val cols: Int = 7) {
    val validColumns: IntRange get() = 1..cols
}
