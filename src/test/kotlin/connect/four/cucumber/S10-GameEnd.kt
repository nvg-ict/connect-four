package connect.four.cucumber

import connect.four.Game
import connect.four.GameRules
import io.cucumber.java.en.Given
import io.cucumber.java.en.Then
import io.cucumber.java.en.When

class GameEnd {
    private lateinit var game: Game

    @Given("Player {int} has just completed 4-in-a-row")
    fun playerHasJustCompletedInARow(player: Int) {
        game = Game(GameRules())

        val moves = when (player) {
            1 -> listOf(1, 6, 2, 6, 3, 6, 4)
            2 -> listOf(6, 1, 6, 2, 6, 3, 5, 4)
            else -> error("Unsupported player: $player")
        }

        moves.forEach { column ->
            game.applyMove(column)
        }
    }

    @When("the game ends")
    fun theGameEnds() {
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
