package connect.four

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class WinCheckerTest {
    private lateinit var board: Board
    private lateinit var checker: WinChecker
    private val player = Player.P1
    private val cell = Cell.forPlayer(player)

    @BeforeEach
    fun setup() {
        board = Board(rows = 6, cols = 7)
        checker = WinChecker()
    }

    @Test
    fun `detects horizontal win of four coins`() {
        board.setAt(Position(3, 1), cell)
        board.setAt(Position(4, 1), cell)
        board.setAt(Position(5, 1), cell)
        board.setAt(Position(6, 1), cell)

        val result = checker.horizontalWin(board, Position(6, 1), player)

        assertTrue(result)
    }

    @Test
    fun `does not detect win with only three coins`() {
        board.setAt(Position(3, 1), cell)
        board.setAt(Position(4, 1), cell)
        board.setAt(Position(5, 1), cell)

        val result = checker.horizontalWin(board, Position(5, 1), player)

        assertFalse(result)
    }

    @Test
    fun `detects win when coin is placed in the middle of a sequence`() {
        board.setAt(Position(3, 1), cell)
        board.setAt(Position(4, 1), cell)
        board.setAt(Position(6, 1), cell)
        board.setAt(Position(7, 1), cell)

        val result = checker.horizontalWin(board, Position(5, 1), player)

        assertTrue(result)
    }

    @Test
    fun `does not count opponent coins`() {
        val opponent = Cell.forPlayer(Player.P2)

        board.setAt(Position(3, 1), cell)
        board.setAt(Position(4, 1), cell)
        board.setAt(Position(5, 1), opponent)
        board.setAt(Position(6, 1), cell)

        val result = checker.horizontalWin(board, Position(6, 1), player)

        assertFalse(result)
    }

    @Test
    fun `detects win when sequence extends to the left`() {
        board.setAt(Position(1, 1), cell)
        board.setAt(Position(2, 1), cell)
        board.setAt(Position(3, 1), cell)
        board.setAt(Position(4, 1), cell)

        val result = checker.horizontalWin(board, Position(4, 1), player)

        assertTrue(result)
    }
}