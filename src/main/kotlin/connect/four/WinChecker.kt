package connect.four

class WinChecker {
    @Suppress("MagicNumber")
    fun isWin(board: Board, origin: Position, player: Player): Boolean {
        return findWinningPositions(board, origin, player).isNotEmpty()
    }

    fun findWinningPositions(board: Board, origin: Position, player: Player): List<Position> {
        val directions = listOf(
            1 to 0,
            0 to 1,
            1 to 1,
            1 to -1
        )

        return directions.firstNotNullOfOrNull { (columnStep, rowStep) ->
            val winningLine = connectedPositions(board, origin, player, columnStep, rowStep)
            if (winningLine.size >= 4) winningLine else null
        } ?: emptyList()
    }

    private fun connectedPositions(
        board: Board,
        origin: Position,
        player: Player,
        columnStep: Int,
        rowStep: Int
    ): List<Position> {
        val backward = consecutivePositions(board, origin, player, -columnStep, -rowStep).reversed()
        val forward = consecutivePositions(board, origin, player, columnStep, rowStep)

        return backward + origin + forward
    }

    private fun consecutivePositions(
        board: Board,
        origin: Position,
        player: Player,
        columnStep: Int,
        rowStep: Int
    ): List<Position> {
        val target = Cell.forPlayer(player)

        return generateSequence(Position(origin.column + columnStep, origin.row + rowStep)) {
            Position(it.column + columnStep, it.row + rowStep)
        }
            .takeWhile {
                it.column in 1..board.cols &&
                        it.row in 1..board.rows &&
                        board.getAt(it) == target
            }
            .toList()
    }
}
