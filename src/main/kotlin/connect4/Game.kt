package nl.craftsmen.connect4


class Game(
    val rows: Int = 6,
    val cols: Int = 7
) {
    val board = Board(rows, cols)
}