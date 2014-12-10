@MoveToken
Feature: Moving the token over the board

  As a player
  I want to move to new positions on the board
  So that I can get rich and win the game

  Scenario Outline: Moving a token after a single throw
    Given players token is at starting position <startingPosition>
    When player throws die1 <die1> and die2 <die2>
    Then players token moves to a new position <newPosition>

  Examples:
    | startingPosition | die1 | die2 | newPosition |
    | 1                | 1    | 2    | 4           |
    | 2                | 3    | 4    | 9           |
    | 39               | 2    | 2    | 3           |


  Scenario Outline: Roll again
    When player throws die1 <die1> and die2 <die2>
    Then player gets another turn <anotherTurn>

  Examples:
    | die1 | die2 | anotherTurn |
    | 1    | 1    | Yes         |
    | 5    | 3    | No          |


