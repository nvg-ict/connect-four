package connect.four.cucumber

import connect.four.Game
import connect.four.GameRules
import connect.four.MoveResult
import connect.four.Player
import connect.four.TurnController
import io.cucumber.java.en.And
import io.cucumber.java.en.Given
import io.cucumber.java.en.Then
import io.cucumber.java.en.When
import io.mockk.spyk
import io.mockk.verify
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class PlayerInput {
    private lateinit var rules: GameRules
    private lateinit var game: Game
    private lateinit var result: MoveResult
    private lateinit var controller: TurnController
    private var processedColumn: Int? = null

    @Given("it is Player {int}'s turn")
    fun itIsPlayersTurn(player: Int) {
        rules = GameRules()
        game = spyk(Game(rules))
        controller = TurnController(game)
        game.currentPlayer = if(player == 1) Player.P1 else Player.P2
    }

    @When("Player {int} enters column {int}")
    fun playerEntersColumn(player: Int, column: Int) {
        val expectedPlayer = if (player == 1) Player.P1 else Player.P2

        assertEquals(expectedPlayer, game.currentPlayer)

        result = controller.handleInput(column.toString())
    }

    @Then("the column number {int} is accepted")
    fun theColumnNumberIsAccepted(column: Int) {
        processedColumn = column
        assertTrue(result is MoveResult.Accepted)
    }

    @Then("the input is rejected with an error message")
    fun theInputIsRejectedWithAnErrorMessage() {
        assertEquals(MoveResult.Rejected("Invalid column. Choose a column (1-7)."), result);
    }

    @And("the game processes the move to drop a coin")
    fun theGameProcessesTheMoveToDropACoin() {
        verify { game.applyMove(processedColumn!!) }
    }

    @And("Player {int} is re-prompted to select a valid column \\(1-7)")
    fun playerIsRepromptedToSelectAValidColumn(player: Int) {
        val expectedPlayer = if (player == 1) Player.P1 else Player.P2

        assertEquals(expectedPlayer, game.currentPlayer)
    }
}
