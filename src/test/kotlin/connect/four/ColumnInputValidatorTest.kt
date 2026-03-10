package connect.four

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class ColumnInputValidatorTest {
    private lateinit var validator: ColumnInputValidator

    @BeforeEach
    fun setUp() {
        validator = ColumnInputValidator(1..7)
    }

    @Test
    fun `validate returns Valid for numeric input in range`() {
        val result = validator.validate("3")
        assertTrue(result is ColumnValidation.Valid)
        assertEquals(3, result.column)
    }

    @Test
    fun `validate returns Invalid for decimal input in range`() {
        val result = validator.validate("3.5")
        assertTrue(result is ColumnValidation.Invalid)
        assertEquals("Invalid input. Choose a column (1-7).", result.errorMessage)
    }

    @Test
    fun `validate returns Invalid for non-numeric input`() {
        val result = validator.validate("abc")
        assertTrue(result is ColumnValidation.Invalid)
        assertEquals("Invalid input. Choose a column (1-7).", result.errorMessage)
    }

    @Test
    fun `validate returns Invalid for out-of-range input`() {
        val result = validator.validate("10")
        assertTrue(result is ColumnValidation.Invalid)
        assertEquals("Invalid column. Choose a column (1-7).", result.errorMessage)
    }
}
