package connect.four.cucumber

import connect.four.App
import io.cucumber.java.en.And
import io.cucumber.java.en.Given
import io.cucumber.java.en.Then
import io.cucumber.java.en.When
import org.junit.jupiter.api.Assertions.assertTrue

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
        assertTrue(displayedText.contains("Welcome"))
        assertTrue(displayedText.contains("Rules"))
    }

    @And("the rules explain board is 6 rows by 7 columns")
    fun theRulesExplainBoardIs6RowsBy7Columns() {
        assertTrue(displayedText.contains("6 rows by 7 columns"))
    }

    @And("the rules explain coins are dropped and fall to the lowest row")
    fun theRulesExplainCoinsAreDroppedAndFallToTheLowestRow() {
        assertTrue(displayedText.contains("fall to the lowest"))
    }

    @And("the rules explain Player 1 goes first, then players alternate")
    fun theRulesExplainPlayer1GoesFirstThenPlayersAlternate() {
        assertTrue(displayedText.contains("Player 1 goes first"))
        assertTrue(displayedText.contains("players alternate"))
    }

    @And("the rules explain 4-in-a-row \\(horizontal, vertical, or diagonal) wins")
    fun theRulesExplain4InARowWins() {
        assertTrue(displayedText.contains("4 in a row"))
        assertTrue(displayedText.contains("horizontally"))
        assertTrue(displayedText.contains("vertically"))
        assertTrue(displayedText.contains("diagonally"))
    }

    @And("the rules explain a draw occurs when board is full with no winner")
    fun theRulesExplainADrawOccursWhenBoardIsFullWithNoWinner() {
        assertTrue(displayedText.contains("draw"))
        assertTrue(displayedText.contains("board is full"))
    }

    @And("the player is prompted to press a key to start the game")
    fun thePlayerIsPromptedToPressAKeyToStartTheGame() {
        assertTrue(displayedText.contains("Press any key"))
    }

    @And("upon pressing that key, the game begins with a fresh board")
    fun uponPressingThatKeyTheGameBeginsWithAFreshBoard() {
        TODO("Implement step")
    }
}
