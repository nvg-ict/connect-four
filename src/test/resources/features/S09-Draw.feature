Feature: Draw

  Scenario: Game ends in draw when board is full with no winner
    Given all 42 board positions are filled with alternating yellow and red coins
    And no 4-in-a-row exists for either player
    When Player 1 attempts to make a move and finds all columns full
    Then the game declares "Game is a Draw"
    And the game ends without a winner