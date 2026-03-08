package connect.four

import io.cucumber.java.en.Given
import io.cucumber.java.en.Then
import io.cucumber.java.en.When

class HorizontalWin {
    @Given("the board has {int} consecutive yellow coins {string} in row {int}, columns {int}-{int}")
    fun aGameWithCoinsGetsInitialized(
        count: Int,
        coin: String,
        row: Int,
        columnFrom: Int,
        columnTo: Int
    ) {
        TODO("Implement step to initialize game")
    }

    @When("Player {int} drops a final coin completing the 4-in-a-row")
    fun playerDropsWinningCoin(player: Int) {
        TODO("Implement step to drop winning coin")
    }

    @Then("the game detects a horizontal win for Player {int} the game ends immediately")
    fun theGameIndicatesPlayerWins(player: Int) {
        TODO("Implement step to check for horizontal win and game end")
    }
}
