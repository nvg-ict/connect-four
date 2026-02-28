package nl.craftsmen.connect4

class Board(val rows: Int, val cols: Int) {

    fun render(): String {
        val sb = StringBuilder()

        // Add row for column labels
        for (c in 1..cols) {
            sb.append("$c ")
        }
        sb.appendLine()

        for (r in rows downTo 1) {
            sb.append("$r ")
            for (c in 1..cols) {
                sb.append("◯")
            }
            if (r != 1) sb.appendLine()
        }

        return sb.toString()
    }
}