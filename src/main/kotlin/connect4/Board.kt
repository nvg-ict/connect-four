package nl.craftsmen.connect4

class Board(val rows: Int, val cols: Int) {

    fun setAt(position: Position, cell: Cell) {

    }

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

enum class Cell(
    val value: String,
    val id: Int? = null
) {
    EMPTY("◯"),
    PLAYER1("🟡", 1),
    PLAYER2("🔴", 2);

    companion object {
        fun fromId(id: Int): Cell? = entries.find { it.id == id }
    }
}

class Position(val column: Int, val row: Int) {
    val columnIndex get() = column - 1
    val rowIndex get() = row - 1
}