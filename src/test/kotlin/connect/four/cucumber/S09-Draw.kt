package connect.four.cucumber

import connect.four.Cell
import connect.four.Game
import connect.four.GameMoveResult
import connect.four.GameRules
import connect.four.Position
import connect.four.WinChecker
import io.cucumber.java.en.And
import io.cucumber.java.en.Given
import io.cucumber.java.en.Then
import io.cucumber.java.en.When
import org.junit.jupiter.api.Assertions.assertFalse
import kotlin.properties.Delegates
import kotlin.test.assertEquals

class Draw {
    private lateinit var game: Game
    private var lastMove: Int? = null
    private lateinit var gameMoveResult: GameMoveResult

    @Given("all {int} board positions are filled with alternating yellow and red coins except for top of column {int}")
    fun allBoardPositionsAreFilledWithAlternatingYellowAndRedCoins(positions: Int, column: Int) {
        game = Game(GameRules())

        require(game.gameRules.rows * game.gameRules.cols == positions)

        lastMove = column

        val pattern = listOf(
            "YYRRYYR",
            "RRYYRRY",
            "YYRRYYR",
            "RRYYRRY",
            "YYRRYYR",
            "RRYYRRY"
        )

        for (row in 1..game.gameRules.rows) {
            for (col in 1..game.gameRules.cols) {
                // Skip the top of the specified column to allow for the next move
                if (row ==game.gameRules.rows && col == column) continue
                val token = pattern[row - 1][col - 1]
                val cell = when (token) {
                    'Y' -> Cell.PLAYER1
                    'R' -> Cell.PLAYER2
                    else -> Cell.EMPTY
                }

                game.board.setAt(Position(col, row), cell)
            }
        }
    }

    @And("no 4-in-a-row exists for either player")
    fun noInARowExistsForEitherPlayer() {
        val winChecker = WinChecker()
        for (row in 1..game.gameRules.rows) {
            for (col in 1..game.gameRules.cols) {
                val position = Position(col, row)
                val cell = game.board.getAt(position)

                if (cell == Cell.PLAYER1) {
                    assertFalse(winChecker.isWin(game.board, position, connect.four.Player.P1))
                } else if (cell == Cell.PLAYER2) {
                    assertFalse(winChecker.isWin(game.board, position, connect.four.Player.P2))
                }
            }
        }
    }

    @Suppress("UnusedParameter")
    @When("Player {int} attempts to make a move and finds all columns full")
    fun playerAttemptsToMakeAMoveAndFindsAllColumnsFull(player: Int) {
        gameMoveResult = game.applyMove(lastMove!!)
    }

    @Then("the game declares {string}")
    fun theGameDeclares(message: String) {
        assertEquals(message, game.turnIndicator())
    }

    @And("the game ends without a winner")
    fun theGameEndsWithoutAWinner() {
        assertEquals(GameMoveResult.Draw, gameMoveResult)
    }
}
