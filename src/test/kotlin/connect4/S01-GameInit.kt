package connect4

import io.cucumber.java.en.And
import io.cucumber.java.en.Given
import io.cucumber.java.en.Then
import io.cucumber.java.en.When
import nl.craftsmen.connect4.Game
import org.junit.jupiter.api.assertNotNull

class GameInit {
    private lateinit var game: Game
    private lateinit var output: String

    @Given("the game is started")
    fun theGameIsStarted() {
        game = Game()
        assertNotNull(game)
    }

    @When("the board is initialized")
    fun theBoardIsInitialized() {
        output = game.board.render()
        assertNotNull(output)
    }

    @Then("the board displays 6 rows and 7 columns")
    fun theBoardDisplays6RowsAnd7Columns() {
        TODO("Implement step")
    }

    @Then("all 42 positions show empty spaces {string}")
    fun all42PositionsShowEmptySpaces() {
        TODO("Implement step")
    }

    @And("columns are labeled 1 through 7")
    fun columnsAreLabeled1Through7() {
        TODO("Implement step")
    }

    @Then("rows are labeled 1 through 6 (bottom to top)")
    fun rowsAreLabeled1Through6BottomToTop() {
        TODO("Implement step")
    }
}
