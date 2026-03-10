package connect.four

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class BoardRendererTest {
    private lateinit var renderer: BoardRenderer

    @BeforeEach
    fun setUp() {
        renderer = BoardRenderer()
    }

    @Test
    fun `renderCell returns correct symbol for empty cell`() {
        val cell = Cell(null)
        assertEquals("O ", renderer.renderCell(cell))
    }

    @Test
    fun `renderCell returns correct symbol for Player 1`() {
        val cell = Cell(Player.P1)
        assertEquals("🟡", renderer.renderCell(cell))
    }

    @Test
    fun `renderCell returns correct symbol for Player 2`() {
        val cell = Cell(Player.P2)
        assertEquals("🔴", renderer.renderCell(cell))
    }

    @Test
    fun `renderCell returns correct symbol for winning Player 1`() {
        val cell = Cell(Player.P1, isWinning = true)
        assertEquals("🟨", renderer.renderCell(cell))
    }

    @Test
    fun `renderCell returns correct symbol for winning Player 2`() {
        val cell = Cell(Player.P2, isWinning = true)
        assertEquals("🟥", renderer.renderCell(cell))
    }

    @Test
    fun `render returns correct output for empty board`() {
        val board = Board(6, 7)
        val output = renderer.render(board)

        val emptyCount = output.count { it.toString() == "O" }
        assertEquals(42, emptyCount)
    }
}


