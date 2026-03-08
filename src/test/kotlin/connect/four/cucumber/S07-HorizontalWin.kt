package connect.four.cucumber

import connect.four.Cell
import connect.four.Game
import connect.four.GameMoveResult
import connect.four.GameRules
import connect.four.Player
import io.cucumber.java.en.Given
import io.cucumber.java.en.Then
import io.cucumber.java.en.When
import kotlin.test.assertEquals

class HorizontalWin {
    private lateinit var game: Game
    private lateinit var expectedCoin: String
    private lateinit var gameMoveResult: GameMoveResult

    @Given("the board has {int} consecutive coins {string} in row 1, columns {int}-{int}")
    fun aGameWithCoinsGetsInitialized(
        count: Int,
        coin: String,
        columnFrom: Int,
        columnTo: Int
    ) {
        require(columnTo - columnFrom + 1 == count)

        game = Game(GameRules())
        expectedCoin = coin
        val range = IntRange(columnFrom, columnTo)

        for (column in range) {
            game.board.dropInColumn(column, Cell.PLAYER1)
        }
    }

    @When("Player {int} drops a final coin completing the 4-in-a-row \\(column {int})")
    fun playerDropsWinningCoin(player: Int, column: Int) {
        val currentPlayer = if (player == 1) Player.P1 else Player.P2
        require(game.currentPlayer == currentPlayer)

        gameMoveResult = game.applyMove(column)
    }

    @Then("the game detects a horizontal win for Player {int} the game ends immediately")
    fun theGameIndicatesPlayerWins(player: Int) {
        val currentPlayer = if (player == 1) Player.P1 else Player.P2

        assertEquals(GameMoveResult.Win(currentPlayer), gameMoveResult)
    }
}
