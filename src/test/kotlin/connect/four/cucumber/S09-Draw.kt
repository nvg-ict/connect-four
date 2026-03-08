package connect.four.cucumber

import io.cucumber.java.en.And
import io.cucumber.java.en.Given
import io.cucumber.java.en.Then
import io.cucumber.java.en.When

class Draw {

    @Given("all {int} board positions are filled with alternating yellow and red coins")
    fun allBoardPositionsAreFilledWithAlternatingYellowAndRedCoins(positions: Int) {
        TODO("Implement step")
    }

    @And("no {int}-in-a-row exists for either player")
    fun noInARowExistsForEitherPlayer(count: Int) {
        TODO("Implement step")
    }

    @When("Player {int} attempts to make a move and finds all columns full")
    fun playerAttemptsToMakeAMoveAndFindsAllColumnsFull(player: Int) {
        TODO("Implement step")
    }

    @Then("the game declares {string}")
    fun theGameDeclares(message: String) {
        TODO("Implement step")
    }

    @And("the game ends without a winner")
    fun theGameEndsWithoutAWinner() {
        TODO("Implement step")
    }
}