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
        val currentGame = requireNotNull(game) { "Game must be started before playCurrentGame()" }
        val turnController = TurnController(currentGame)

        var gameEnded = false
        while (!gameEnded) {
            console.println(renderer.render(currentGame.board))
            console.println(currentGame.turnIndicator())
            console.print("Enter column: ")

            val input = console.readLine().orEmpty()
            when (val moveResult = turnController.handleInput(input)) {
                is MoveResult.Rejected -> console.println(moveResult.errorMessage)
                is MoveResult.Accepted -> {
                    when (val result = moveResult.gameMoveResult) {
                        is GameMoveResult.Failure -> console.println(result.reason)
                        is GameMoveResult.Win -> {
                            console.println(renderer.render(currentGame.board))
                            console.println("${result.player.label} wins!")
                            gameEnded = true
                        }
                        GameMoveResult.Draw -> {
                            console.println(renderer.render(currentGame.board))
                            console.println("Game is a draw!")
                            gameEnded = true
                        }
                        is GameMoveResult.Success -> Unit
                    }
                }
            }
        }
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
