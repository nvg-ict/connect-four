package connect.four

import io.cucumber.java.en.And
import io.cucumber.java.en.Given
import io.cucumber.java.en.Then
import io.cucumber.java.en.When
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class CoinDrop {
    private lateinit var game: Game
    private lateinit var gameMoveResult: GameMoveResult

    @Given("the board is empty")
    fun theBoardIsEmpty() {
        game = Game(GameRules())
    }

    @Suppress("UnusedParameter")
    @When("Player {int} drops a coin in column {int}")
    fun playerDropsACoinInColumn(player: Int, column: Int) {
         gameMoveResult = game.applyMove(column)
    }

    @Then("the coin lands in row {int} of column {int}")
    fun theCoinLandsInRowOfColumn(row: Int, column: Int) {
        assertEquals(GameMoveResult.Success(Position(column, row)),gameMoveResult)
    }

    @Then("the position records a yellow coin {string} at coordinates \\(row: {int}, column: {int})")
    fun thePositionRecordsAYellowCoinAtCoordinates(coin: String, row: Int, column: Int) {
        assertEquals(coin, Cell.PLAYER1.value)
        assertEquals(Cell.PLAYER1, game.board.getAt(Position(row = row, column = column)))
    }

    @Suppress("UnusedParameter")
    @Given("column {int} is completely full with {int} coins stacked from row {int} to row {int}")
    fun columnIsCompletelyFullWithCoinsStackedFromRowToRow(column: Int, coins: Int, rowFrom: Int, rowTo: Int) {
        game = Game(GameRules())
        repeat(coins) {
            game.applyMove(column)
        }
    }

    @And("column {int} has {int} coin")
    fun OtherColumnHasCoins(column: Int, coins: Int) {
        repeat(coins) {
            game.applyMove(column)
        }
    }

    @When("Player {int} attempts to drop a coin in column {int}")
    fun playerAttemptsToDropACoinInColumn(player: Int, column: Int) {
        assertEquals("Player ${player}'s turn",game.turnIndicator())
        gameMoveResult = game.applyMove(column)
    }

    @Then("the move is rejected")
    fun theMoveIsRejected() {
        assertTrue(gameMoveResult is GameMoveResult.Failure)
    }

    @Then("an error message states {string}")
    fun anErrorMessageStates(message: String) {
        assertEquals(GameMoveResult.Failure(message), gameMoveResult)
    }

    @And("Player {int} is re-prompted to select a different column")
    fun playerIsRepromptedToSelectADifferentColumn(player: Int) {
        assertEquals("Player ${player}'s turn",game.turnIndicator())
    }

    @And("it remains Player {int}'s turn \\(turn does not advance)")
    fun itRemainsPlayersTurn(player: Int) {
        assertEquals("Player ${player}'s turn",game.turnIndicator())
    }
}
