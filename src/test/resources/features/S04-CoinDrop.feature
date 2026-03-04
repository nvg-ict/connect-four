Feature: Coin drop

  Scenario: Coin drops to lowest empty row in selected column
    Given the board is empty
    When Player 1 drops a coin in column 3
    Then the coin lands in row 1 of column 3
    And the position records a yellow coin "🟡" at coordinates (row: 1, column: 3)

  Scenario: Player attempts to drop coin in full column
    Given column 2 is completely full with 6 coins stacked from row 1 to row 6
    When Player 1 attempts to drop a coin in column 2
    Then the move is rejected
    And an error message states "Column 2 is full"
    And Player 1 is re-prompted to select a different column