package connect.four

class WinChecker {
    fun isWin(board: Board, position: Position, player: Player): Boolean =
        horizontalWin(board, position, player) ||
                verticalWin(board, position, player) ||
                diagonalWin(board, position, player)

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

    fun horizontalWin(board: Board, position: Position, player: Player): Boolean =
        count(board, position, player, -1, 0) +
                count(board, position, player, 1, 0) + 1 >= 4

    fun verticalWin(board: Board, position: Position, player: Player): Boolean =
        count(board, position, player, 0, -1) +
                count(board, position, player, 0, 1) + 1 >= 4

    fun diagonalWin(board: Board, position: Position, player: Player): Boolean {
        return diagonalDownUpWin(board, position, player) ||
                diagonalUpDownWin(board, position, player)
    }

    private fun diagonalDownUpWin(board: Board, position: Position, player: Player): Boolean {
        val target = Cell.forPlayer(player)
        val row = position.row
        val col = position.column

        val downLeft = generateSequence(Position(col - 1, row - 1)) {
            Position(it.column - 1, it.row - 1)
        }
            .takeWhile {
                it.column >= 1 &&
                        it.row >= 1 &&
                        board.getAt(it) == target
            }
            .count()

        val upRight = generateSequence(Position(col + 1, row + 1)) {
            Position(it.column + 1, it.row + 1)
        }
            .takeWhile {
                it.column <= board.cols &&
                        it.row <= board.rows &&
                        board.getAt(it) == target
            }
            .count()

        return downLeft + upRight + 1 >= 4
    }

    private fun diagonalUpDownWin(board: Board, position: Position, player: Player): Boolean {
        val target = Cell.forPlayer(player)
        val row = position.row
        val col = position.column

        val upLeft = generateSequence(Position(col - 1, row + 1)) {
            Position(it.column - 1, it.row + 1)
        }
            .takeWhile {
                it.column >= 1 &&
                        it.row <= board.rows &&
                        board.getAt(it) == target
            }
            .count()

        val downRight = generateSequence(Position(col + 1, row - 1)) {
            Position(it.column + 1, it.row - 1)
        }
            .takeWhile {
                it.column <= board.cols &&
                        it.row >= 1 &&
                        board.getAt(it) == target
            }
            .count()

        return upLeft + downRight + 1 >= 4
    }
}
