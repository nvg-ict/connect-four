package nl.craftsmen.connect4

class Board(val rows: Int, val cols: Int) {
    private val grid = Array(rows) { Array(cols) { Cell.EMPTY } }

    fun setAt(position: Position, cell: Cell) {
        grid[position.rowIndex][position.columnIndex] = cell
    }

    fun getAt(position: Position): Cell {
        return grid[position.rowIndex][position.columnIndex]
    }

    fun dropInColumn(column: Int, cell: Cell): Position {
        for (row in 1..rows) {
            val position = Position(column, row)
            if (getAt(position) == Cell.EMPTY) {
                setAt(position, cell)
                return position
            }
        }
        error("Column $column is full")
    }
}

enum class Cell(
    val value: String,
    val id: Int? = null
) {
    EMPTY("⚪"),
    PLAYER1("🟡", 1),
    PLAYER2("🔴", 2);

    companion object {
        fun fromId(id: Int): Cell? = entries.find { it.id == id }
        fun forPlayer(p: Player): Cell = if (p == Player.P1) PLAYER1 else PLAYER2
    }
}

class Position(val column: Int, val row: Int) {
    val rowIndex get() = row - 1
    val columnIndex get() = column - 1
}