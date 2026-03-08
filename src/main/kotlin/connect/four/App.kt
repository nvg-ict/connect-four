package connect.four

class App(
    private val gameRules: GameRules = GameRules()
) {
    var game: Game? = null
        private set

    fun startupMessage(): String = """
        Welcome to Connect Four!

        Rules:
        - The board is 6 rows by 7 columns.
        - Coins are dropped into a column and fall to the lowest available row.
        - Player 1 goes first, then players alternate turns.
        - Get 4 in a row horizontally, vertically, or diagonally to win.
        - The game is a draw when the board is full and nobody has won.
        
        Press any key to start the game.
    """.trimIndent()

    fun startGame() {
        game = Game(gameRules)
    }
}
