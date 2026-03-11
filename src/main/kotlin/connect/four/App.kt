package connect.four

class App(
    private val console: Console,
    private val gameRules: GameRules = GameRules(),
    private val renderer: BoardRenderer = BoardRenderer()
) {
    var game: Game? = null
        private set

    fun startGame() {
        game = Game(gameRules)
    }

    fun handlePlayAgainSelection(selection: String) {
        if (selection.equals("yes", ignoreCase = true)) {
            startGame()
        }
    }

    fun showStartup() {
        console.println(startupMessage())
    }

    fun waitForPlayerToStart() {
        console.readLine()
    }

    fun playCurrentGame() {
        val game = requireNotNull(game)
        val turnController = TurnController(game)

        while (game.status() is GameStatus.Turn) {
            clearConsole()
            console.println(renderer.render(game.board))
            console.println(statusMessage(game))
            console.print("Enter column: ")

            val input = console.readLine().orEmpty()

            when (val move = turnController.handleInput(input)) {
                is MoveResult.Rejected -> console.println(move.errorMessage)
                is MoveResult.Accepted -> handleGameResult(move.gameMoveResult)
            }
        }

        clearConsole()
        console.println(renderer.render(game.board))
        console.println(statusMessage(game))
    }

    fun handleGameResult(result: GameMoveResult) {
        if (result is GameMoveResult.Failure) {
            console.println(result.reason)
        }
    }

    fun statusMessage(game: Game): String =
        when (val status = game.status()) {
            is GameStatus.Turn -> "${status.player.label}'s turn"
            is GameStatus.Win -> "${status.player.label} wins!"
            GameStatus.Draw -> "Game is a draw!"
        }

    tailrec fun askToPlayAgain(): Boolean {
        console.println(playAgainMessage)
        val input = console.readLine().orEmpty()
        return when {
            input.equals("yes", ignoreCase = true) -> {
                handlePlayAgainSelection(input)
                true
            }
            input.equals("no", ignoreCase = true) -> false
            else -> {
                console.println("Please type 'yes' or 'no'.")
                askToPlayAgain()
            }
        }
    }

    val playAgainMessage: String
        get() = "Play again? (yes/no)"

    private  fun startupMessage(): String = """
        Welcome to Connect Four!

        Rules:
        - The board is 6 rows by 7 columns.
        - Coins are dropped into a column and fall to the lowest available row.
        - Player 1 goes first, then players alternate turns.
        - Get 4 in a row horizontally, vertically, or diagonally to win.
        - The game is a draw when the board is full and nobody has won.

        Press any key to start the game.
    """.trimIndent()

    private fun clearConsole() {
        console.print("\u001b[H\u001b[2J")
    }
}
