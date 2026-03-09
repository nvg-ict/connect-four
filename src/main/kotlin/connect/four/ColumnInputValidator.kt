package connect.four

class ColumnInputValidator(private val validRange: IntRange) {

    fun validate(raw: String): ColumnValidation {
        val col = raw.trim().toIntOrNull()
            ?: return ColumnValidation.Invalid(
                "Invalid input. Choose a column ${validRange.display()}."
            )

        return if (col in validRange)
            ColumnValidation.Valid(col)
        else
            ColumnValidation.Invalid(
                "Invalid column. Choose a column ${validRange.display()}."
            )
    }

    private fun IntRange.display(): String =
        "(${this.first}-${this.last})"
}

sealed interface ColumnValidation {
    data class Valid(val column: Int) : ColumnValidation
    data class Invalid(val errorMessage: String) : ColumnValidation
}
