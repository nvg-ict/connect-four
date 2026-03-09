Feature: Repeat Games

  Scenario: Player chooses to play again after game ends
    Given a game has ended with a winner
    When the game displays "Play again? (yes/no)"
    And the player selects "yes"
    Then the board is cleared to all empty positions ("O")
    And a new game begins with Player 1's turn