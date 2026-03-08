package connect.four

class WinChecker {
    fun isWin(board: Board, origin: Position, player: Player): Boolean {
        val directions = listOf(
            1 to 0,
            0 to 1,
            1 to 1,
            1 to -1
        )

        return directions.any { (columnStep, rowStep) ->
            connectedCoinCount(board, origin, player, columnStep, rowStep) >= 4
        }
    }

    private fun connectedCoinCount(
        board: Board,
        origin: Position,
        player: Player,
        columnStep: Int,
        rowStep: Int
    ): Int =
        countConsecutiveCoins(board, origin, player, columnStep, rowStep) +
                countConsecutiveCoins(board, origin, player, -columnStep, -rowStep) + 1

    private fun countConsecutiveCoins(
        board: Board,
        origin: Position,
        player: Player,
        columnStep: Int,
        rowStep: Int
    ): Int {
        val target = Cell.forPlayer(player)

        return generateSequence(Position(origin.column + columnStep, origin.row + rowStep)) {
            Position(it.column + columnStep, it.row + rowStep)
        }
            .takeWhile {
                it.column in 1..board.cols &&
                        it.row in 1..board.rows &&
                        board.getAt(it) == target
            }
            .count()
    }
}
