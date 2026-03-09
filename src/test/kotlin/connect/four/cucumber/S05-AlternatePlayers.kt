package connect.four.cucumber

import connect.four.Game
import connect.four.GameRules
import connect.four.toPlayer
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

    @Suppress("UnusedParameter")
    @Then("the game indicates {string} for Player {int}")
    fun theGameIndicatesPlayer1Turn(message: String, player: Int) {
        assertEquals(player.toPlayer(), game.currentPlayer)
    }

    @And("Player {int} drops a coin in {int}")
    fun playerDropsACoin(player: Int, column: Int) {
        assertEquals(player.toPlayer(), game.currentPlayer)

        game.applyMove(column)
    }
}
