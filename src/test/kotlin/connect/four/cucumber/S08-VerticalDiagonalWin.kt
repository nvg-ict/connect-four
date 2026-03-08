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
import kotlin.test.assertEquals

class WinDetectionSteps {
    private lateinit var game: Game
    private lateinit var gameMoveResult: GameMoveResult

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
        TODO("Implement step")
    }

    @Given("the board has {int} yellow coins \\(🟡\\) on an upward-right diagonal")
    fun boardHasYellowCoinsOnDiagonal(coins: Int) {
        TODO("Implement step")
    }

    @And("the coordinates are:")
    fun theCoordinatesAre(table: DataTable) {
        TODO("Implement step")
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