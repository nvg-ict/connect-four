package connect.four

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

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
    @ValueSource(ints = [1, 2])
    fun `int can be parsed to player`() {
        val player = 1.toPlayer()

        assertEquals(Player.P1, player)
    }
}
