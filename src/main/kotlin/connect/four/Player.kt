package connect.four

enum class Player(val label: String) {
    P1("Player 1"),
    P2( "Player 2");

    fun other(): Player = if (this == P1) P2 else P1
}

fun Int.toPlayer(): Player = when (this) {
    1 -> Player.P1
    2 -> Player.P2
    else -> throw IllegalArgumentException("Unknown player label: $this")
}
