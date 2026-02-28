package connect4

import io.cucumber.java.en.Given
import io.cucumber.java.en.Then
import io.cucumber.java.en.When

class DisplayState {

    @Given("the game has started and coins are placed on the board")
    fun theGameHasStartedAndCoinsArePlacedOnTheBoard() {
        TODO("Implement step")
    }

    @Given("column {int} row {int} contains a Player {int} coin")
    fun columnRowContainsAPlayerCoin(column: Int, row: Int, player: Int) {
        TODO("Implement step")
    }

    @When("the board state is displayed")
    fun theBoardStateIsDisplayed() {
        TODO("Implement step")
    }

    @Then("Player 1's coin shows as {string} in column {int} row {int}")
    fun player1CoinShowsAsInPosition(symbol: String, column: Int, row: Int) {
        TODO("Implement step")
    }

    @Then("Player 2's coin shows as {string} in column {int} row {int}")
    fun player2CoinShowsAsInPosition(symbol: String, column: Int, row: Int) {
        TODO("Implement step")
    }

    @Then("empty positions show as ◯")
    fun emptyPositionsShowAsEmptyCircle() {
        TODO("Implement step")
    }
}