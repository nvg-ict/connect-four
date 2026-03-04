package nl.craftsmen.connect4

class TurnController(
    private val validator: ColumnInputValidator,
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

class ColumnInputValidator(private val min: Int = 1, private val max: Int = 7) {
    fun validate(raw: String): MoveResult {
        val col = raw.trim().toIntOrNull()
            ?: return MoveResult.Rejected("Invalid input. Choose a column ($min-$max).")

        return if (col in min..max) MoveResult.Accepted(col)
        else MoveResult.Rejected("Invalid column. Choose a column ($min-$max).")
    }
}

sealed interface MoveResult {
    data class Accepted(val column: Int) : MoveResult
    data class Rejected(val errorMessage: String) : MoveResult
}