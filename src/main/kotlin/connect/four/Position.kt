package connect.four

data class Position(val column: Int, val row: Int) {
    init {
        require(column >= 1) { "Column index must be >= 1, got $column" }
        require(row >= 1) { "Row index must be >= 1, got $row" }
    }

    val rowIndex get() = row - 1
    val columnIndex get() = column - 1
}
