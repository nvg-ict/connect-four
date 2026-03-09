package connect.four.cucumber

import connect.four.App
import connect.four.BoardRenderer
import connect.four.FakeConsole
import connect.four.GameStatus
import connect.four.toPlayer
import io.cucumber.java.en.And
import io.cucumber.java.en.Given
import io.cucumber.java.en.Then
import io.cucumber.java.en.When
import kotlin.test.assertEquals

class RepeatGames {
    private lateinit var console: FakeConsole
    private lateinit var app: App
    private var displayedMessage: String = ""

    @Given("a game has ended with a winner")
    fun aGameHasEndedWithAWinner() {
        console = FakeConsole()
        app = App(console)

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
        displayedMessage = app.playAgainMessage
        assertEquals(message, displayedMessage)
    }

    @And("the player selects {string}")
    fun thePlayerSelects(choice: String) {
        app.handlePlayAgainSelection(choice)
    }

    @Then("the board is cleared to all empty positions \\({string}\\)")
    fun theBoardIsClearedToAllEmptyPositions(symbol: String) {
        val output = BoardRenderer().render(app.game!!.board)

        val count = output.count { it.toString() == symbol }
        assertEquals(42, count)
    }

    @And("a new game begins with Player {int}'s turn")
    fun aNewGameBeginsWithPlayersTurn(player: Int) {
        assertEquals(GameStatus.Turn(player.toPlayer()), app.game!!.status())
    }
}
