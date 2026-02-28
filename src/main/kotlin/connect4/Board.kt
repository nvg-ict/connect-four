package nl.craftsmen.connect4

class Board(val rows: Int, val cols: Int) {

    fun render(): String {
        val sb = StringBuilder()

        // Add row for column labels
        for (c in 1..cols) {
            sb.append("$c ")
        }
        sb.appendLine()

        sb.append( "◯".repeat(rows * cols))
        return sb.toString()
    }
}