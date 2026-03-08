package connect.four

class WinChecker {
    fun isWin(board: Board, position: Position, player: Player): Boolean {
        val directions = listOf(
            1 to 0,
            0 to 1,
            1 to 1,
            1 to -1
        )

        return directions.any { (columnStep, rowStep) ->
            count(board, position, player, columnStep, rowStep) +
                    count(board, position, player, -columnStep, -rowStep) + 1 >= 4
        }
    }

    private fun count(
        board: Board,
        start: Position,
        player: Player,
        columnStep: Int,
        rowStep: Int
    ): Int {
        val target = Cell.forPlayer(player)

        return generateSequence(Position(start.column + columnStep, start.row + rowStep)) {
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
