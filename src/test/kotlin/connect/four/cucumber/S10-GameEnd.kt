@file:Suppress("UnusedParameter")

package connect.four.cucumber

import io.cucumber.java.en.Given
import io.cucumber.java.en.Then
import io.cucumber.java.en.When

class GameEnd {
    @Given("Player {int} has just completed 4-in-a-row")
    fun playerHasJustCompletedInARow(player: Int) {
        TODO("Implement step")
    }

    @Given("the board is completely full with no 4-in-a-row for either player")
    fun boardIsCompletelyFullWithNoInARow() {
        TODO("Implement step")
    }

    @When("the game ends")
    fun theGameEnds() {
        TODO("Implement step")
    }

    @Then("the final board is displayed")
    fun theFinalBoardIsDisplayed() {
        TODO("Implement step")
    }

    @Then("the final board is displayed with the winning coins marked with brackets like {string}")
    fun theFinalBoardHasWinningCoinsMarked(example: String) {
        TODO("Implement step")
    }

    @Then("the message {string} is displayed")
    fun theMessageIsDisplayed(expected: String) {
        TODO("Implement step")
    }
}
