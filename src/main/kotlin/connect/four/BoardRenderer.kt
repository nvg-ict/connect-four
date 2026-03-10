package connect.four

class BoardRenderer {
    fun render(board: Board): String = buildString {
        append("   ")
        appendLine((1..board.cols).joinToString("  "))

        for (row in board.rows downTo 1) {
            append(row)
            append(" |")

            for (col in 1..board.cols) {
                val cell = board.getAt(Position(col, row))
                append(renderCell(cell))
                append('|')
            }

            appendLine()
        }
    }.trimEnd()

    fun renderCell(cell: Cell): String {
        return when (cell) {
            is Cell.Empty -> "O "
            is Cell.Filled -> when (cell.player) {
                Player.P1 -> if (cell.isWinning) "🟨" else "🟡"
                Player.P2 -> if (cell.isWinning) "🟥" else "🔴"
            }
        }
    }
}
