package connect.four

class App(
    private val console: Console,
    private val gameRules: GameRules = GameRules(),
    private val renderer: BoardRenderer = BoardRenderer()
) {
    var game: Game? = null
        private set

    fun run() {
        showStartup()
        waitForPlayerToStart()

        do {
            startGame()
            playCurrentGame()
        } while (askToPlayAgain())
    }

    private fun startupMessage(): String = """
        Welcome to Connect Four!

        Rules:
        - The board is 6 rows by 7 columns.
        - Coins are dropped into a column and fall to the lowest available row.
        - Player 1 goes first, then players alternate turns.
        - Get 4 in a row horizontally, vertically, or diagonally to win.
        - The game is a draw when the board is full and nobody has won.

        Press any key to start the game.
    """.trimIndent()

    val playAgainMessage: String
        get() = "Play again? (yes/no)"

    fun startGame() {
        game = Game(gameRules)
    }

    fun handlePlayAgainSelection(selection: String) {
        if (selection.equals("yes", ignoreCase = true)) {
            startGame()
        }
    }

    private fun showStartup() {
        console.println(startupMessage())
    }

    private fun waitForPlayerToStart() {
        console.readLine()
    }

    private fun playCurrentGame() {
        val game = requireNotNull(game)
        val turnController = TurnController(game)

        while (game.status() is GameStatus.Turn) {
            console.println(renderer.render(game.board))
            console.println(statusMessage(game))
            console.print("Enter column: ")

            val input = console.readLine().orEmpty()

            when (val move = turnController.handleInput(input)) {
                is MoveResult.Rejected -> console.println(move.errorMessage)
                is MoveResult.Accepted -> handleGameResult(move.gameMoveResult)
            }
        }

        console.println(renderer.render(game.board))
        console.println(statusMessage(game))
    }

    private fun handleGameResult(result: GameMoveResult) {
        if (result is GameMoveResult.Failure) {
            console.println(result.reason)
        }
    }

    private fun statusMessage(game: Game): String =
        when (val status = game.status()) {
            is GameStatus.Turn -> "${status.player.label}'s turn"
            is GameStatus.Win -> "${status.player.label} wins!"
            GameStatus.Draw -> "Game is a draw!"
        }

    private fun askToPlayAgain(): Boolean {
        while (true) {
            console.println(playAgainMessage)
            val input = console.readLine().orEmpty()

            when {
                input.equals("yes", ignoreCase = true) -> {
                    handlePlayAgainSelection(input)
                    return true
                }
                input.equals("no", ignoreCase = true) -> return false
                else -> console.println("Please type 'yes' or 'no'.")
            }
        }
    }
}
