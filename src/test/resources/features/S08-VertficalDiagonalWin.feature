Feature: Vertical and Diagonal Win

  Scenario: Player 2 wins with 4 consecutive coins stacked vertically
    Given column 4 has 3 red coins (🔴) stacked consecutively from row 1 to row 3
    When Player 2 drops a final coin in column 4
    Then the game detects a vertical win for Player 2
    And the game ends immediately

  Scenario: Player 1 wins with 4 coins in upward-right diagonal (↗)
    Given the board has 4 yellow coins (🟡) on an upward-right diagonal
    And the coordinates are:
      | row | col |
      | 1   | 2   |
      | 2   | 3   |
      | 3   | 4   |
      | 4   | 5   |
    When Player 1 drops a final coin completing the diagonal
    Then the game detects a diagonal win for Player 1
    And the game ends immediately