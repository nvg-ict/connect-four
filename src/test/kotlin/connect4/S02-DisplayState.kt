package connect4

import io.cucumber.java.en.Given
import io.cucumber.java.en.Then
import io.cucumber.java.en.When
import nl.craftsmen.connect4.BoardRenderer
import nl.craftsmen.connect4.Cell
import nl.craftsmen.connect4.Game
import nl.craftsmen.connect4.Position
import org.junit.jupiter.api.assertNotNull

class DisplayState {
    private lateinit var game: Game
    private val boardRenderer = BoardRenderer()

    @Given("the game has started and coins are placed on the board")
    fun theGameHasStartedAndCoinsArePlacedOnTheBoard() {
        game = Game()
        assertNotNull(game)
    }

    @Given("column {int} row {int} contains a Player {int} coin")
    fun columnRowContainsAPlayerCoin(column: Int, row: Int, player: Int) {
        game.board.setAt(Position(column, row), Cell.fromId(player) ?: Cell.EMPTY)
    }

    @When("the board state is displayed")
    fun theBoardStateIsDisplayed() {
        val output = boardRenderer.render(game.board)
        assertNotNull(output)
    }

    @Then("Player 1's coin shows as {string} in column {int} row {int}")
    fun player1CoinShowsAsInPosition(symbol: String, column: Int, row: Int) {
        val cell = game.board.getAt(Position(column, row))
        assert(cell.value == symbol)
    }

    @Then("Player 2's coin shows as {string} in column {int} row {int}")
    fun player2CoinShowsAsInPosition(symbol: String, column: Int, row: Int) {
        val cell = game.board.getAt(Position(column, row))
        assert(cell.value == symbol)
    }

    @Then("empty positions show as ◯")
    fun emptyPositionsShowAsEmptyCircle() {
        TODO("Implement step")
    }
}