package monopoly.pageobjects;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static org.junit.Assert.assertTrue;

public class PlayGamePage extends Page {

    @FindBy(name="d1")
    private WebElement die1;
    @FindBy(name="d2")
    private WebElement die2;
    @FindBy(name="roll")
    private WebElement roll;
    @FindBy(name="buy")
    private WebElement buy;
    @FindBy(name="finish")
    private WebElement finish;
    @FindBy(css=".square")
    private List<WebElement> squares;

    public PlayGamePage(WebDriver webDriver) {
        super(webDriver, "/");
    }

    public PlayGamePage rollDice(int eyes1, int eyes2) {
        die1.clear();
        die1.sendKeys(String.valueOf(eyes1));
        die2.clear();
        die2.sendKeys(String.valueOf(eyes2));
        roll.click();

        waitForAllAjaxRequestsToBeHandled();

        return this;
    }

    public PlayGamePage buy() {
        buy.click();

        waitForAllAjaxRequestsToBeHandled();

        return this;
    }

    public PlayGamePage finish() {
        finish.click();

        waitForAllAjaxRequestsToBeHandled();

        return this;
    }

    public boolean isRollAllowed() {
        return roll.isEnabled();
    }

    public boolean isJailed() {
        try {
            return webDriver.findElement(By.cssSelector(".player.active.jailed")) != null;
        } catch (NoSuchElementException e) {
            // No jailed active player found.
            return false;
        }
    }

    public int getCurrentPlayerPosition() {
        WebElement square = getSquareForActivePlayer();
        String id = square.getAttribute("id");
        assertTrue("Not a square: " + id, id.startsWith("square"));
        return Integer.parseInt(id.substring("square".length()));
    }

    private WebElement getSquareForActivePlayer() {
        for (WebElement square : squares) {
            if (square.findElement(By.cssSelector(".player.active")) != null)
                return square;
        }
        throw new IllegalStateException("No square found for active player");
    }
}
