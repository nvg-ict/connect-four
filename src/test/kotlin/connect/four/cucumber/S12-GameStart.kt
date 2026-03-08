package connect.four.cucumber

import connect.four.App
import io.cucumber.java.en.And
import io.cucumber.java.en.Given
import io.cucumber.java.en.Then
import io.cucumber.java.en.When

class GameStart {
    private lateinit var app: App
    private var displayedText: String = ""

    @Given("the application is launched")
    fun theApplicationIsLaunched() {
        app = App()
    }

    @When("the game starts")
    fun theGameStarts() {
        displayedText = app.startupMessage()
    }

    @Then("a welcome message and basic rules are displayed")
    fun aWelcomeMessageAndBasicRulesAreDisplayed() {
        TODO("Implement step")
    }

    @And("the rules explain board is {int} rows by {int} columns")
    fun theRulesExplainBoardIsRowsByColumns(rows: Int, columns: Int) {
        TODO("Implement step")
    }

    @And("the rules explain coins are dropped and fall to the lowest row")
    fun theRulesExplainCoinsAreDroppedAndFallToTheLowestRow() {
        TODO("Implement step")
    }

    @And("the rules explain Player 1 goes first, then players alternate")
    fun theRulesExplainPlayer1GoesFirstThenPlayersAlternate() {
        TODO("Implement step")
    }

    @And("the rules explain {int}-in-a-row \\(horizontal, vertical, or diagonal\\) wins")
    fun theRulesExplainInARowHorizontalVerticalOrDiagonalWins(count: Int) {
        TODO("Implement step")
    }

    @And("the rules explain a draw occurs when board is full with no winner")
    fun theRulesExplainADrawOccursWhenBoardIsFullWithNoWinner() {
        TODO("Implement step")
    }

    @And("the player is prompted to press a key to start the game")
    fun thePlayerIsPromptedToPressAKeyToStartTheGame() {
        TODO("Implement step")
    }

    @And("upon pressing that key, the game begins with a fresh board")
    fun uponPressingThatKeyTheGameBeginsWithAFreshBoard() {
        TODO("Implement step")
    }
}