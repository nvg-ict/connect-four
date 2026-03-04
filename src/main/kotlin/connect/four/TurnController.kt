package connect.four

class TurnController(
    private val game: Game,
    private val validator: ColumnInputValidator = ColumnInputValidator(game.gameRules.validColumns),
    private val onAcceptedMove: (column: Int) -> Unit
) {
    fun handleInput(raw: String): MoveResult =
        when (val res = validator.validate(raw)) {
            is MoveResult.Accepted -> {
                onAcceptedMove(res.column)
                res
            }
            is MoveResult.Rejected -> res
        }
}

class ColumnInputValidator(private val validRange: IntRange) {
    fun validate(raw: String): MoveResult {
        val col = raw.trim().toIntOrNull()
            ?: return MoveResult.Rejected("Invalid input. Choose a column ${validRange.display()}.")

        return if (col in validRange) MoveResult.Accepted(col)
         else MoveResult.Rejected("Invalid column. Choose a column ${validRange.display()}.")
    }

    private fun IntRange.display(): String =
        "(${this.first}-${this.last})"
}

sealed interface MoveResult {
    data class Accepted(val column: Int) : MoveResult
    data class Rejected(val errorMessage: String) : MoveResult
}
