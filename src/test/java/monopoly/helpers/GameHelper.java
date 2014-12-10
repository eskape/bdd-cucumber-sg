package monopoly.helpers;

import nl.javadude.monopoly.domain.Dice;
import nl.javadude.monopoly.domain.Game;
import nl.javadude.monopoly.domain.ISquare;
import nl.javadude.monopoly.domain.Player;

/**
 * GameFixture class: class
 */
public class GameHelper {

    private Game game = new Game();

    private Player player;

    /**
     * method to setplayer and start using it immediately per this turn
     * @param name of the player
     */
    public void setPlayer(String name) {
        if (game.getCurrentPlayer() == null || !name.equals(game.getCurrentPlayer().getName())) {
            addPlayerIfNotYetInGame(name);
            player = game.findPlayer(name);
            game.setCurrentPlayer(player);
            player.startTurn();
        }
    }

    protected void addPlayerIfNotYetInGame(String name) {
        if (!game.playerInGame(name)) {
            player = game.addPlayer(name);
            player.startTurn();
        }
    }

    public Player getPlayer() {
        return player;
    }


    /**
     * setting up a game directly
     */
    public void setupGame() {
        String[] playerNames = {"Kishen", "Arjan"};
        for (String name : playerNames) {
            game.addPlayer(name);
        }
        game.startPlay();
        player = game.getCurrentPlayer();
    }

    /**
     * make a test-player ("Anton") start from a certain position
     * @param currentPosition
     */
    public void addPlayerAndSetPosition(int currentPosition) {
        addPlayerIfNotYetInGame("Anton");
        player = game.findPlayer("Anton");
        player.setCurrentPosition(currentPosition);
    }

    /**
     * "roll the dice": set the dice for a turn
     * @param die1
     * @param die2
     */
    public void setDice(int die1, int die2) {
        Dice.getInstance().setDiceValues(die1, die2);
    }


    /**
     * make the actual move with the player
     */
    public void doPlayAction() {
        player.move(Dice.getInstance());
    }

    /**
     * get the new position by name
     * @return
     */
    public String getNewPositionName() {
        return player.getCurrentPosition().getName();
    }

    /**
     * get the new position as an int
     * @return
     */
    public int getNewPosition() {
        return player.getCurrentPosition().getPosition();
    }


    /**
     * set the new location
     * @param locationName as a String
     */
    public void setLocation(String locationName) {
        ISquare newLocation = game.getBoard().findLocation(locationName);
        player.setCurrentPosition(newLocation);
    }

    public void jumpToTurn(int turn) {
        for(int i=1; i<turn;i++){
            setDice(1, 1);
            doPlayAction();
        }
    }
}
