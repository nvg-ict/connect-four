package connect.four.cucumber

import connect.four.Game
import connect.four.GameRules
import connect.four.Player
import io.cucumber.java.en.And
import io.cucumber.java.en.Given
import io.cucumber.java.en.Then
import io.cucumber.java.en.When
import kotlin.test.assertEquals

class AlternatePlayers {
    private lateinit var game: Game

    @Given("a new game has been initialized")
    fun aNewGameHasBeenInitialized() {
        game = Game(GameRules())
    }

    @When("the game is ready for play")
    fun theGameIsReadyForPlay() {
        // Nothing for now
    }

    @Then("the game indicates {string} for Player {int}")
    fun theGameIndicatesPlayer1Turn(message: String, player: Int) {
        val currentPlayer = if (player == 1) Player.P1 else Player.P2

        assertEquals(message, game.turnIndicator())
        assertEquals(currentPlayer, game.currentPlayer)
    }

    @And("Player {int} drops a coin in {int}")
    fun playerDropsACoin(player: Int, column: Int) {
        val currentPlayer = if (player == 1) Player.P1 else Player.P2

        assertEquals(currentPlayer, game.currentPlayer)

        game.applyMove(column)
    }
}
