package nl.craftsmen.connect4

class Board(val rows: Int, val cols: Int) {

    fun render()= buildString {
        // Column labels
        appendLine((1..cols).joinToString(" "))

        // Each board row: row label + cells
        val cells = "◯".repeat(cols)
        for (r in rows downTo 1) {
            appendLine("$r $cells")
        }
    }.trimEnd()
}