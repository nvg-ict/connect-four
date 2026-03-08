package connect.four

import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class WinCheckerTest {

    private lateinit var board: Board
    private lateinit var winChecker: WinChecker

    @BeforeEach
    fun setUp() {
        board = Board(rows = 6, cols = 7)
        winChecker = WinChecker()
    }

    @Test
    fun `horizontalWin returns true for four consecutive coins`() {
        place(Player.P1, 3, 1)
        place(Player.P1, 4, 1)
        place(Player.P1, 5, 1)
        place(Player.P1, 6, 1)

        assertTrue(winChecker.isWin(board, Position(6, 1), Player.P1))
    }

    @Test
    fun `horizontalWin returns false when there are fewer than four consecutive coins`() {
        place(Player.P1, 3, 1)
        place(Player.P1, 4, 1)
        place(Player.P1, 5, 1)

        assertFalse(winChecker.isWin(board, Position(5, 1), Player.P1))
    }

    @Test
    fun `verticalWin returns true for four consecutive coins`() {
        place(Player.P1, 3, 1)
        place(Player.P1, 3, 2)
        place(Player.P1, 3, 3)
        place(Player.P1, 3, 4)

        assertTrue(winChecker.isWin(board, Position(3, 4), Player.P1))
    }

    @Test
    fun `verticalWin returns false when there are fewer than four consecutive coins`() {
        place(Player.P1, 3, 1)
        place(Player.P1, 3, 2)
        place(Player.P1, 3, 3)

        assertFalse(winChecker.isWin(board, Position(3, 3), Player.P1))
    }

    @Test
    fun `diagonalWin returns true for both diagonal directions`() {
        place(Player.P1, 1, 1)
        place(Player.P1, 2, 2)
        place(Player.P1, 3, 3)
        place(Player.P1, 4, 4)

        place(Player.P2, 1, 4)
        place(Player.P2, 2, 3)
        place(Player.P2, 3, 2)
        place(Player.P2, 4, 1)

        assertTrue(winChecker.isWin(board, Position(4, 4), Player.P1))
        assertTrue(winChecker.isWin(board, Position(4, 1), Player.P2))
    }

    @Test
    fun `diagonalWin returns false when there are fewer than four consecutive diagonal coins`() {
        place(Player.P1, 2, 2)
        place(Player.P1, 3, 3)
        place(Player.P1, 4, 4)

        assertFalse(winChecker.isWin(board, Position(4, 4), Player.P1))
    }

    private fun place(player: Player, column: Int, row: Int) {
        board.setAt(Position(column, row), Cell.forPlayer(player))
    }
}