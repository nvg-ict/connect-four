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

    # This is from story 6, but added here due to all the ducplication
  Scenario: Player cannot drop coin into full column and remains on their turn
    Given column 5 is completely full with 6 coins stacked from row 1 to row 6
    # Added so we force player 2's turn
    And column 4 has 1 coin
    And it is Player 2's turn
    When Player 2 attempts to drop a coin in column 5
    Then an error message states "Column 5 is full"
    And Player 2 is re-prompted to select a different column
    And it remains Player 2's turn (turn does not advance)