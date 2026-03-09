Feature: Game end

    Scenario: Game announces Player 2 win and highlights winning coins
        Given Player 2 has just completed 4-in-a-row
        When the game ends
        Then the final board is displayed with the winning coins marked with brackets like "🟥"
        And the message "Player 2 wins with 4 in a row!" is displayed