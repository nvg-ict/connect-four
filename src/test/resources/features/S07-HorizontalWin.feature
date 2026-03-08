Feature: Horizontal Win

  Scenario: Player 1 wins with 4 consecutive coins horizontally in the middle of a row
    #Edited this story line to make more sense!
    Given the board has 3 consecutive coins "🟡" in row 1, columns 3-5
    When Player 1 drops a final coin completing the 4-in-a-row
    Then the game detects a horizontal win for Player 1 the game ends immediately