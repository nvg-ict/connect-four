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
}
