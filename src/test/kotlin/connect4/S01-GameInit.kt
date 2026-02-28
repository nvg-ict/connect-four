package connect4

import io.cucumber.java.en.And
import io.cucumber.java.en.Given
import io.cucumber.java.en.Then
import io.cucumber.java.en.When
import nl.craftsmen.connect4.Game
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.assertNotNull
import kotlin.test.assertEquals

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
        assertEquals(6, game.board.rows)
        assertEquals(7, game.board.cols)
    }

    @Then("all 42 positions show empty spaces {string}")
    fun all42PositionsShowEmptySpaces(symbol: String) {
        val count = output.count { it.toString() == symbol }
        assertEquals(42, count)
    }

    @And("columns are labeled 1 through 7")
    fun columnsAreLabeled1Through7() {
        val column = output.split('\n')[0]
        for (c in 1..7) {
            assertTrue(column.contains(c.toString()))
        }
    }

    @Then("rows are labeled 1 through 6 (bottom to top)")
    fun rowsAreLabeled1Through6BottomToTop() {
        TODO("Implement step")
    }
}
