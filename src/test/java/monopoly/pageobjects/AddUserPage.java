package monopoly.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class AddUserPage extends Page {

    @FindBy(name="name")
    private WebElement name;
    @FindBy(name="addplayer")
    private WebElement addPlayer;
    @FindBy(name="startgame")
    private WebElement startGame;

    @FindBy(css = "#players > ul")
    private WebElement players;

    public AddUserPage(WebDriver webDriver) {
        super(webDriver, "/");
    }

    public AddUserPage addUser(String name) {
        this.name.sendKeys(name);
        this.addPlayer.click();

        waitForAllAjaxRequestsToBeHandled();

        assertTrue(players.getText().contains(name + ": money: 1500; possessions:"));

        return this;
    }

    public PlayGamePage startGame() {
        startGame.click();

        waitForAllAjaxRequestsToBeHandled();

        assertFalse("start game button is still visible", startGame.isDisplayed());

        return new PlayGamePage(webDriver);
    }
}
