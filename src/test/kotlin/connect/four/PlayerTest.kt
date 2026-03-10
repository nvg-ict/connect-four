package connect.four

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class PlayerTest {
    @Test
    fun `other gives the other player`() {
        var player = Player.P1

        player = player.other()

        assertEquals(Player.P2, player)

        player = player.other()

        assertEquals(Player.P1, player)
    }

    @ParameterizedTest
    @CsvSource("1,P1", "2,P2")
    fun `int can be parsed to player`(input: Int, expected: String) {
        val player = input.toPlayer()
        val expectedPlayer = if (expected == "P1") Player.P1 else Player.P2

        assertEquals(expectedPlayer, player)
    }

    @Test
    fun `invalid int throws when parsed to player`() {
        val exception = assertThrows<IllegalArgumentException> {
            3.toPlayer()
        }

        assertEquals("Unknown player label: 3", exception.message)
    }
}
