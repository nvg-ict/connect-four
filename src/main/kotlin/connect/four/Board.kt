package connect.four

class Board(val rows: Int, val cols: Int) {
    private val grid: Array<Array<Cell>>  = Array(rows) { Array(cols) { Cell.Empty } }

    fun setAt(position: Position, cell: Cell) {
        grid[position.rowIndex][position.columnIndex] = cell
    }

    fun getAt(position: Position): Cell {
        return grid[position.rowIndex][position.columnIndex]
    }

    fun dropInColumn(column: Int, cell: Cell): DropResult {
        for (row in 1..rows) {
            val position = Position(column, row)
            if (getAt(position) is Cell.Empty) {
                setAt(position, cell)
                return DropResult.Success(position)
            }
        }
        return DropResult.Failure("Column $column is full")
    }

    fun isFull(): Boolean =
        (1..cols).all { column ->
            getAt(Position(column, rows)) !is Cell.Empty
        }

    fun markWinning(positions: List<Position>) {
        positions.forEach { position ->
            val current = getAt(position)
            if (current is Cell.Filled) {
                setAt(position, current.copy(isWinning = true))
            }
        }
    }
}

sealed interface DropResult {
    data class Success(val position: Position) : DropResult
    data class Failure(val errorMessage: String) : DropResult
}

sealed interface Cell {
    object Empty : Cell
    data class Filled(val player: Player, val isWinning: Boolean = false) : Cell

    companion object {
        fun forPlayer(p: Player, isWinning: Boolean = false): Cell = Filled(p, isWinning)
    }
}
