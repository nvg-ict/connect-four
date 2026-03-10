package connect.four

import io.mockk.mockk
import io.mockk.every
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class TurnControllerTest {
    private lateinit var game: Game
    private lateinit var validator: ColumnInputValidator
    private lateinit var turnController: TurnController

    @BeforeEach
    fun setUp() {
        game = mockk()
        validator = mockk()
        turnController = TurnController(game, validator)
    }

    @Test
    fun `handleInput returns Accepted for valid input and calls applyMove`() {
        every { validator.validate("3") } returns ColumnValidation.Valid(3)
        every { game.applyMove(3) } returns GameMoveResult.Success(Position(3, 1))

        val result = turnController.handleInput("3")

        assertTrue(result is MoveResult.Accepted)
        verify { game.applyMove(3) }
    }

    @Test
    fun `handleInput returns Rejected for invalid input`() {
        every { validator.validate("bad") } returns ColumnValidation.Invalid("error")

        val result = turnController.handleInput("bad")

        assertTrue(result is MoveResult.Rejected)
        assertEquals("error", result.errorMessage)
        verify(exactly = 0) { game.applyMove(any()) }
    }
}
