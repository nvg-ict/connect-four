Feature: Display state

  Scenario: Player enters valid column number
    Given it is Player 1's turn
    When Player 1 enters column 4
    Then the column number 4 is accepted
    And the game processes the move to drop a coin

  Scenario: Player enters invalid column number outside valid range
    Given it is Player 2's turn
    When Player 2 enters column 9
    Then the input is rejected with an error message
    And Player 2 is re-prompted to select a valid column (1-7)