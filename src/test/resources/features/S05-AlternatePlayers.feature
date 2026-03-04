Feature: Alternate Players

  Scenario: Player 1 goes first, then turns alternate with each move
    Given a new game has been initialized
    When the game is ready for play
    Then the game indicates "Player 1's turn" (🟡)
    And Player 1 drops a coin in 1
    And the game indicates "Player 2's turn" (🔴)
    And Player 2 drops a coin in 2
    Then the game again indicates "Player 1's turn" (🟡)