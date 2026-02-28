package connect4

import io.cucumber.java.en.And
import io.cucumber.java.en.Given
import io.cucumber.java.en.Then
import io.cucumber.java.en.When

class PlayerInput {

    @Given("it is Player {int}'s turn")
    fun itIsPlayersTurn(player: Int) {
        TODO("Implement step")
    }

    @When("Player {int} enters column {int}")
    fun playerEntersColumn(player: Int, column: Int) {
        TODO("Implement step")
    }

    @Then("the column number {int} is accepted")
    fun theColumnNumberIsAccepted(column: Int) {
        TODO("Implement step")
    }

    @Then("the input is rejected with an error message")
    fun theInputIsRejectedWithAnErrorMessage() {
        TODO("Implement step")
    }

    @And("the game processes the move to drop a coin")
    fun theGameProcessesTheMoveToDropACoin() {
        TODO("Implement step")
    }

    @And("Player {int} is re-prompted to select a valid column \\(1-7\\)")
    fun playerIsRepromptedToSelectAValidColumn(player: Int) {
        TODO("Implement step")
    }
}