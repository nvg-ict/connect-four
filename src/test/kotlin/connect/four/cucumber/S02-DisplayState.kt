package connect.four.cucumber

import connect.four.BoardRenderer
import connect.four.Cell
import connect.four.Game
import connect.four.GameRules
import connect.four.Position
import io.cucumber.java.en.Given
import io.cucumber.java.en.Then
import io.cucumber.java.en.When
import org.junit.jupiter.api.assertNotNull
import kotlin.test.assertEquals

class DisplayState {
    private lateinit var rules: GameRules
    private lateinit var game: Game
    private val boardRenderer = BoardRenderer()

    @Given("the game has started and coins are placed on the board")
    fun theGameHasStartedAndCoinsArePlacedOnTheBoard() {
        rules = GameRules()
        game = Game(rules)
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

    @Then("empty positions show as {string}")
    fun emptyPositionsShowAs(symbol: String) {
        for (col in 1..7) {
            for (row in 1..6) {
                val cell = game.board.getAt(Position(col, row))
                if(cell == Cell.EMPTY) {
                    assertEquals(symbol, cell.value)
                }
            }
        }
    }
}
