package connect4.connect4

import io.cucumber.java.en.And
import io.cucumber.java.en.Given
import io.cucumber.java.en.Then
import io.cucumber.java.en.When
import nl.craftsmen.connect4.Board
import nl.craftsmen.connect4.Cell
import nl.craftsmen.connect4.Player
import nl.craftsmen.connect4.Position
import kotlin.test.assertEquals

class CoinDrop {
    private lateinit var board: Board
    private lateinit var processedPosition: Position

    @Given("the board is empty")
    fun theBoardIsEmpty() {
        board = Board(rows = 6, cols = 7)
    }

    @When("Player {int} drops a coin in column {int}")
    fun playerDropsACoinInColumn(player: Int, column: Int) {
        val currentPlayer = if(player == 1) Player.P1 else Player.P2
        val cell = Cell.forPlayer(currentPlayer)

        processedPosition = board.dropInColumn(column, cell)
    }

    @Then("the coin lands in row {int} of column {int}")
    fun theCoinLandsInRowOfColumn(row: Int, column: Int) {
        assertEquals(column,processedPosition.column)
        assertEquals(row,processedPosition.row)
    }

    @Then("the position records a yellow coin {string} at coordinates \\(row: {int}, column: {int})")
    fun thePositionRecordsAYellowCoinAtCoordinates(coin: String, row: Int, column: Int) {
        assertEquals(coin, Cell.PLAYER1.value)
        assertEquals(Cell.PLAYER1, board.getAt(Position(row = row, column = column)))
    }

    @Given("column {int} is completely full with {int} coins stacked from row {int} to row {int}")
    fun columnIsCompletelyFullWithCoinsStackedFromRowToRow(column: Int, coins: Int, rowFrom: Int, rowTo: Int) {
        board = Board(rows = 6, cols = 7)
        repeat(coins) {
            board.dropInColumn(column, Cell.PLAYER1)
        }
    }

    @When("Player {int} attempts to drop a coin in column {int}")
    fun playerAttemptsToDropACoinInColumn(player: Int, column: Int) {
        TODO("Implement step")
    }

    @Then("the move is rejected")
    fun theMoveIsRejected() {
        TODO("Implement step")
    }

    @Then("an error message states {string}")
    fun anErrorMessageStates(message: String) {
        TODO("Implement step")
    }

    @And("Player {int} is re-prompted to select a different column")
    fun playerIsRepromptedToSelectADifferentColumn(player: Int) {
        TODO("Implement step")
    }
}