package monopoly.pageobjects;


import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

@Ignore
public class PageObjectsTest {

    WebDriver webDriver;

    @Before
    public void makeWebDriver() {
        webDriver = new FirefoxDriver();
    }

    @After
    public void cleanUp() {
        webDriver.close();
    }

    @Test
    public void verifyPageObjectFlow() {
        AddUserPage addUserPage = new AddUserPage(webDriver);
        addUserPage.get();
        addUserPage
                .addUser("Arjan")
                .addUser("Kishen")
                .startGame()
                .rollDice(2, 3);


    }
}
