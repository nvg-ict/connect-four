package connect.four

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertEquals

class PositionTest {
    @Test
    fun `position index are all -1`() {
        val position = Position(4, 4)

        assertEquals(3,  position.rowIndex)
        assertEquals(3,  position.columnIndex)
    }

    @Test
    fun `position throws exception for invalid column`() {
        val exception = assertThrows<IllegalArgumentException> {
            Position(0, 1)
        }
        assertEquals("Column index must be >= 1, got 0", exception.message)
    }

    @Test
    fun `position throws exception for invalid row`() {
        val exception = assertThrows<IllegalArgumentException> {
            Position(1, 0)
        }
        assertEquals("Row index must be >= 1, got 0", exception.message)
    }
}
