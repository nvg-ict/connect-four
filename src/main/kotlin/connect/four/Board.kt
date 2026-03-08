package connect.four

class Board(val rows: Int, val cols: Int) {
    private val grid = Array(rows) { Array(cols) { Cell.EMPTY } }

    fun setAt(position: Position, cell: Cell) {
        grid[position.rowIndex][position.columnIndex] = cell
    }

    fun getAt(position: Position): Cell {
        return grid[position.rowIndex][position.columnIndex]
    }

    fun dropInColumn(column: Int, cell: Cell): DropResult {
        for (row in 1..rows) {
            val position = Position(column, row)
            if (getAt(position) == Cell.EMPTY) {
                setAt(position, cell)
                return DropResult.Success(position)
            }
        }
        return DropResult.Failure("Column $column is full")
    }

    fun isFull(): Boolean =
        (1..cols).all { column ->
            getAt(Position(column, rows)) != Cell.EMPTY
        }

    fun markWinning(positions: List<Position>) {
        positions.forEach { position ->
            val current = getAt(position)
            setAt(position, current.copy(isWinning = true))
        }
    }
}

sealed interface DropResult {
    data class Success(val position: Position) : DropResult
    data class Failure(val errorMessage: String) : DropResult
}

data class Cell(
    val type: CellType,
    val isWinning: Boolean = false
) {
    val value get() = type.value

    companion object {
        val EMPTY = Cell(CellType.EMPTY)
        val PLAYER1 = Cell(CellType.PLAYER1)
        val PLAYER2 = Cell(CellType.PLAYER2)

        fun fromId(id: Int): Cell? = CellType.fromId(id)?.let(::Cell)
        fun forPlayer(p: Player): Cell = Cell(CellType.forPlayer(p))
    }
}

enum class CellType(
    val value: String,
    val id: Int? = null
) {
    EMPTY("⚪"),
    PLAYER1("🟡", 1),
    PLAYER2("🔴", 2);

    companion object {
        fun fromId(id: Int): CellType? = entries.find { it.id == id }
        fun forPlayer(p: Player): CellType =
            if (p == Player.P1) PLAYER1 else PLAYER2
    }
}

data class Position(val column: Int, val row: Int) {
    val rowIndex get() = row - 1
    val columnIndex get() = column - 1
}
