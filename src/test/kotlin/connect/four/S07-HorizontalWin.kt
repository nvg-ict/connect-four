package connect.four

import io.cucumber.java.en.Given
import io.cucumber.java.en.Then
import io.cucumber.java.en.When

class HorizontalWin {
    private lateinit var game: Game
    private lateinit var expectedCoin: String

    @Given("the board has {int} consecutive coins {string} in row 1, columns {int}-{int}")
    fun aGameWithCoinsGetsInitialized(
        count: Int,
        coin: String,
        columnFrom: Int,
        columnTo: Int
    ) {
        game = Game(GameRules())
        expectedCoin = coin
        val range = IntRange(columnFrom, columnTo)

        for (column in range) {
            game.board.dropInColumn(column, Cell.PLAYER1)
        }
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
