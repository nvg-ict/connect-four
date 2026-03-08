package connect.four.cucumber

import connect.four.App
import io.cucumber.java.en.And
import io.cucumber.java.en.Given
import io.cucumber.java.en.Then
import io.cucumber.java.en.When

class RepeatGames {
    private lateinit var app: App

    @Given("a game has ended with a winner")
    fun aGameHasEndedWithAWinner() {
        app = App()
        app.startGame()

        val game = app.game!!
        game.applyMove(1)
        game.applyMove(7)
        game.applyMove(2)
        game.applyMove(7)
        game.applyMove(3)
        game.applyMove(7)
        game.applyMove(4)
    }

    @When("the game displays {string}")
    fun theGameDisplays(message: String) {
        TODO("Implement step")
    }

    @And("the player selects {string}")
    fun thePlayerSelects(choice: String) {
        TODO("Implement step")
    }

    @Then("the board is cleared to all empty positions \\({string}\\)")
    fun theBoardIsClearedToAllEmptyPositions(symbol: String) {
        TODO("Implement step")
    }

    @And("a new game begins with Player {int}'s turn")
    fun aNewGameBeginsWithPlayersTurn(player: Int) {
        TODO("Implement step")
    }
}
