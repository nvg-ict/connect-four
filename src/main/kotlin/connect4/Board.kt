package nl.craftsmen.connect4

class Board(val rows: Int, val cols: Int) {
    private val grid = Array(rows) { Array(cols) { Cell.EMPTY } }

    fun setAt(position: Position, cell: Cell) {
        grid[position.rowIndex][position.columnIndex] = cell
    }

    fun getAt(position: Position): Cell {
        return grid[position.rowIndex][position.columnIndex]
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

class Position(private val row: Int, private val column: Int) {
    val rowIndex get() = row - 1
    val columnIndex get() = column - 1
}