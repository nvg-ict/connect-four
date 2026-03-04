package connect.four

class BoardRenderer {
    fun render(board: Board): String = buildString {
        // Column labels (align with row labels by indenting 2 spaces)
        append("  ")
        appendLine((1..board.cols).joinToString(" "))

        // Rows bottom -> top (row label + cells)
        for (row in board.rows downTo 1) {
            append(row)
            append(' ')
            val line = (1..board.cols)
                .joinToString("") { col -> board.getAt(Position(col, row)).value }
            appendLine(line)
        }
    }.removeSuffix("\n").trimEnd()
}
