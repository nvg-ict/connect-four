package connect.four

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class BoardTest {
    private lateinit var board: Board

    @BeforeEach
    fun setUp() {
        board = Board(rows = 6, cols = 7)
    }

    @Test
    fun `setAt and getAt should set and retrieve cell correctly`() {
        val pos = Position(3, 2)
        val cell = Cell.Filled(Player.P1)

        board.setAt(pos, cell)

        assertEquals(cell, board.getAt(pos))
    }

    @Test
    fun `dropInColumn should drop cell in lowest empty row`() {
        val col = 4
        val cell = Cell.Filled(Player.P2)
        val result = board.dropInColumn(col, cell)

        assertTrue(result is DropResult.Success)

        val pos = (result as DropResult.Success).position
        assertEquals(Position(col, 1), pos)
        assertEquals(cell, board.getAt(pos))
    }

    @Test
    fun `dropInColumn should fail when column is full`() {
        val col = 1
        repeat(board.rows) {
            board.dropInColumn(col, Cell.Filled(Player.P1))
        }

        val result = board.dropInColumn(col, Cell.Filled(Player.P2))

        assertTrue(result is DropResult.Failure)
        assertEquals("Column $col is full", (result as DropResult.Failure).errorMessage)
    }

    @Test
    fun `isFull should return true when all columns are full`() {
        for (col in 1..board.cols) {
            repeat(board.rows) {
                board.dropInColumn(col, Cell.Filled(Player.P1))
            }
        }

        assertTrue(board.isFull())
    }

    @Test
    fun `isFull should return false when at least one cell is empty`() {
        for (col in 1..board.cols) {
            repeat(board.rows - 1) {
                board.dropInColumn(col, Cell.Filled(Player.P1))
            }
        }

        assertFalse(board.isFull())
    }

    @Test
    fun `markWinning should mark winning cells`() {
        val positions = listOf(
            Position(1, 1),
            Position(2, 1),
            Position(3, 1),
            Position(4, 1)
        )
        positions.forEach { board.setAt(it, Cell.Filled(Player.P1)) }

        board.markWinning(positions)

        positions.forEach {
            val cell = board.getAt(it)
            assertTrue(cell is Cell.Filled && cell.isWinning)
        }
    }
}
