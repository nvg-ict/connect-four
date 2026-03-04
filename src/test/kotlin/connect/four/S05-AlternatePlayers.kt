package connect.four

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

    @Then("the game indicates {string} \\(🟡)")
    fun theGameIndicatesPlayer1Turn(message: String) {
        assertEquals(message, game.turnIndicator())
        assertEquals(Player.P1, game.currentPlayer)
    }

    @And("Player {int} drops a coin in {int}")
    fun playerDropsACoin(player: Int, column: Int) {
        val currentPlayer = if (player == 1) Player.P1 else Player.P2

        assertEquals(currentPlayer, game.currentPlayer)

        game.applyMove(column)
    }

    @And("the game indicates {string} \\(🔴)")
    fun theGameIndicatesPlayer2Turn(message: String) {
        assertEquals(message, game.turnIndicator())
        assertEquals(Player.P2, game.currentPlayer)
    }

    @Then("the game again indicates {string} \\(🟡)")
    fun theGameAgainIndicatesPlayer1Turn(message: String) {
        TODO("Implement step")
    }
}
