package connect.four

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotSame
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertNotNull
import org.junit.jupiter.api.assertNull
import org.junit.jupiter.api.BeforeEach

class AppTest {
    private lateinit var app: App
    private lateinit var console: FakeConsole

    @BeforeEach
    fun setUp() {
        console = FakeConsole()
        app = App(console = console)
    }

    @Test
    fun `startupMessage returns welcome text and rules`() {
        val message = app.startupMessage()

        assertTrue(message.contains("Welcome to Connect Four!"))
        assertTrue(message.contains("The board is 6 rows by 7 columns."))
        assertTrue(message.contains("Player 1 goes first"))
        assertTrue(message.contains("Get 4 in a row"))
        assertTrue(message.contains("Press any key to start the game."))
    }

    @Test
    fun `playAgainMessage returns expected text`() {
        assertEquals("Play again? (yes/no)", app.playAgainMessage)
    }

    @Test
    fun `game is null before startGame`() {
        assertNull(app.game)
    }

    @Test
    fun `startGame creates a game`() {
        app.startGame()

        assertNotNull(app.game)
    }

    @Test
    fun `startGame replaces previous game with a new one`() {
        app.startGame()
        val firstGame = app.game

        app.startGame()
        val secondGame = app.game

        assertNotNull(firstGame)
        assertNotNull(secondGame)
        assertNotSame(firstGame, secondGame)
    }

    @Test
    fun `handlePlayAgainSelection starts a new game when input is yes`() {
        assertNull(app.game)

        app.handlePlayAgainSelection("yes")

        assertNotNull(app.game)
    }

    @Test
    fun `handlePlayAgainSelection starts a new game when input is YES ignoring case`() {
        assertNull(app.game)

        app.handlePlayAgainSelection("YES")

        assertNotNull(app.game)
    }

    @Test
    fun `handlePlayAgainSelection does nothing when input is no`() {
        app.handlePlayAgainSelection("no")

        assertNull(app.game)
    }

    @Test
    fun `handlePlayAgainSelection does nothing for unrecognized input`() {
        app.handlePlayAgainSelection("maybe")

        assertNull(app.game)
    }
}
