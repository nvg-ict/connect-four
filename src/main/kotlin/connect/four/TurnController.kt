package connect.four

class TurnController(
    private val game: Game,
    private val validator: ColumnInputValidator = ColumnInputValidator(game.gameRules.validColumns),
) {
    fun handleInput(raw: String): MoveResult =
        when (val validation = validator.validate(raw)) {
            is ColumnValidation.Valid -> {
                val gameResult = game.applyMove(validation.column)
                MoveResult.Accepted(gameResult)
            }

            is ColumnValidation.Invalid ->
                MoveResult.Rejected(validation.errorMessage)
        }
}

sealed interface MoveResult {
    data class Accepted(val gameMoveResult: GameMoveResult) : MoveResult
    data class Rejected(val errorMessage: String) : MoveResult
}
