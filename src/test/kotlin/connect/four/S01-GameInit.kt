package connect.four

import io.cucumber.java.en.And
import io.cucumber.java.en.Given
import io.cucumber.java.en.Then
import io.cucumber.java.en.When
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.assertNotNull
import kotlin.test.assertEquals

class GameInit {
    private lateinit var rules: GameRules
    private lateinit var game: Game
    private lateinit var renderer: BoardRenderer
    private lateinit var output: String

    @Given("the game is started")
    fun theGameIsStarted() {
        rules = GameRules()
        game = Game(rules)
        renderer = BoardRenderer()
        assertNotNull(game)
    }

    @When("the board is initialized")
    fun theBoardIsInitialized() {
        output = renderer.render(game.board)
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
        for (c in 1 until 7) {
            assertTrue(column.contains(c.toString()))
        }
    }

    @Then("rows are labeled 1 through 6 \\(bottom to top)")
    fun rowsAreLabeled1Through6BottomToTop() {
        val rows = output.split('\n')
        for (i in 1..6) {
            val expectedRow = 7 - i
            assertTrue(rows[i].startsWith(expectedRow.toString()))
        }
    }
}
