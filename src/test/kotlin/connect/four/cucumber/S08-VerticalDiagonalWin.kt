package connect.four.cucumber

import connect.four.Cell
import connect.four.Game
import connect.four.GameMoveResult
import connect.four.GameRules
import connect.four.Player
import connect.four.Position
import io.cucumber.datatable.DataTable
import io.cucumber.java.en.And
import io.cucumber.java.en.Given
import io.cucumber.java.en.Then
import io.cucumber.java.en.When
import org.junit.jupiter.api.Assertions.assertTrue
import kotlin.test.assertEquals

class WinDetectionSteps {
    private lateinit var game: Game
    private lateinit var gameMoveResult: GameMoveResult
    private lateinit var finalMove: Map<String, String>


    @Given("column {int} has {int} red coins \\(🔴\\) stacked consecutively from row {int} to row {int}")
    fun columnHasRedCoinsStacked(column: Int, coins: Int, rowFrom: Int, rowTo: Int) {
        require(rowTo - rowFrom + 1 == coins)

        game = Game(GameRules())
        val range = IntRange(rowFrom, rowTo)

        for (row in range) {
            game.board.setAt(Position(column, row), Cell.PLAYER2)
        }
    }

    @When("Player {int} drops a final coin in column {int}")
    fun playerDropsFinalCoinInColumn(player: Int, column: Int) {
        val currentPlayer = if (player == 1) Player.P1 else Player.P2

        // Logic to deal with player 2 winning
        if (game.currentPlayer != currentPlayer) {
            game.applyMove(column + 1)
        }

        gameMoveResult = game.applyMove(column)
    }

    @Then("the game detects a vertical win for Player {int}")
    fun theGameDetectsVerticalWinForPlayer(player: Int) {
        val currentPlayer = if (player == 1) Player.P1 else Player.P2
        assertEquals(GameMoveResult.Win(currentPlayer), gameMoveResult)
    }

    @And("the game ends immediately")
    fun theGameEndsImmediately() {
        assertTrue(gameMoveResult is GameMoveResult.Win)
    }

    @Given("the board has {int} yellow coins \\(🟡\\) on an upward-right diagonal")
    fun boardHasYellowCoinsOnDiagonal(coins: Int) {
        game = Game(GameRules())
    }

    @And("the coordinates are:")
    fun theCoordinatesAre(table: DataTable) {
        val coordinates = table.asMaps()

        val setupCoordinates = coordinates.dropLast(1)
        finalMove = coordinates.last()

        setupCoordinates.forEach {
            val row = it["row"]!!.toInt()
            val col = it["col"]!!.toInt()
            game.board.setAt(Position(col, row), Cell.PLAYER1)
        }
    }

    @When("Player {int} drops a final coin completing the diagonal")
    fun playerDropsFinalCoinCompletingDiagonal(player: Int) {
        TODO("Implement step")
    }

    @Then("the game detects a diagonal win for Player {int}")
    fun theGameDetectsDiagonalWinForPlayer(player: Int) {
        TODO("Implement step")
    }
}