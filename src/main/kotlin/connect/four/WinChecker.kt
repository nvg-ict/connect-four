package connect.four

class WinChecker {
    fun horizontalWin(board: Board, position: Position, player: Player): Boolean {
        val target = Cell.forPlayer(player)
        val row = position.row
        val col = position.column

        val left = generateSequence(col - 1) { it - 1 }
            .takeWhile { it >= 1 && board.getAt(Position(it, row)) == target }
            .count()

        val right = generateSequence(col + 1) { it + 1 }
            .takeWhile { it <= board.cols && board.getAt(Position(it, row)) == target }
            .count()

        return left + right + 1 >= 4
    }

    fun verticalWin(board: Board, position: Position, player: Player): Boolean {
        val target = Cell.forPlayer(player)
        val row = position.row
        val col = position.column

        val down = generateSequence(row - 1) { it - 1 }
            .takeWhile { it >= 1 && board.getAt(Position(col, it)) == target }
            .count()

        val up = generateSequence(row + 1) { it + 1 }
            .takeWhile { it <= board.rows && board.getAt(Position(col, it)) == target }
            .count()

        return down + up + 1 >= 4
    }

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
