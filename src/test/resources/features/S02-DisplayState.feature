Feature: Display state

  Scenario: Board displays coins with correct colors for each player
    Given the game has started and coins are placed on the board
    And column 1 row 1 contains a Player 1 coin
    And column 2 row 1 contains a Player 2 coin
    When the board state is displayed
    Then Player 1's coin shows as "🟡" in column 1 row 1
    And Player 2's coin shows as "🔴" in column 2 row 1
    And empty positions show as ◯