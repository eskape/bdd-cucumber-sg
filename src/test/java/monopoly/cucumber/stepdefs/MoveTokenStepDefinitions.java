package monopoly.cucumber.stepdefs;


import static monopoly.helpers.HelperFunctions.convertBooleanToYesOrNo;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import monopoly.helpers.GameHelper;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class MoveTokenStepDefinitions {

    private GameHelper gameHelper = new GameHelper();

    @Before
    public void setupGame() {
        gameHelper.setupGame();
    }

    @Given("^players token is at starting position (.+)$")
    public void playersTokenIsAtStartingPosition(int position){
        gameHelper.getPlayer().setCurrentPosition(position);
    }

    @When("^player throws die1 (.+) and die2 (.+)$")
    public void playerThrowsDie1AndDie2(int die1, int die2) throws Throwable {
        gameHelper.setDice(die1, die2);
    }

    @Then("^players token moves to a new position (.+)$")
    public void playersTokenMovesToANewPosition(int newPosition) throws Throwable {
        gameHelper.doPlayAction();
        assertThat(gameHelper.getNewPosition(), is(newPosition));
    }

    @Then("^player gets another turn (.+)$")
    public void playerGetsAnotherTurn(String anotherTurn) throws Throwable {
        gameHelper.doPlayAction();
        assertEquals("Another roll allowed", anotherTurn, convertBooleanToYesOrNo(gameHelper.getPlayer().isRollAllowed()));
    }
    
}
