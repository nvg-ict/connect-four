package connect.four

import io.cucumber.java.en.And
import io.cucumber.java.en.Given
import io.cucumber.java.en.Then
import io.cucumber.java.en.When

class AlternatePlayers {

    @Given("a new game has been initialized")
    fun aNewGameHasBeenInitialized() {
        TODO("Implement step")
    }

    @When("the game is ready for play")
    fun theGameIsReadyForPlay() {
        TODO("Implement step")
    }

    @Then("the game indicates {string} \\(🟡\\)")
    fun theGameIndicatesPlayer1Turn(message: String) {
        TODO("Implement step")
    }

    @And("Player {int} drops a coin")
    fun playerDropsACoin(player: Int) {
        TODO("Implement step")
    }

    @And("the game indicates {string} \\(🔴\\)")
    fun theGameIndicatesPlayer2Turn(message: String) {
        TODO("Implement step")
    }

    @Then("the game again indicates {string} \\(🟡\\)")
    fun theGameAgainIndicatesPlayer1Turn(message: String) {
        TODO("Implement step")
    }
}
