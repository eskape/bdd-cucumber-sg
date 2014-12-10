package monopoly.pageobjects;

import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;

public abstract class Page extends LoadableComponent<Page> {

    private final String relativePath;
    protected final WebDriver webDriver;

    public Page(WebDriver webDriver, String relativePath) {
        this.webDriver = webDriver;
        this.relativePath = relativePath;
        PageFactory.initElements(webDriver, this);
    }

    /**
     * Verifies that the current page is loaded.
     */
    protected void isLoaded() {
        Assert.assertTrue(
                "The page was not loaded correctly." +
                        "\nActual URL: " + webDriver.getCurrentUrl() +
                        "\nExpected URL: " + this.relativePath

                , webDriver.getCurrentUrl().contains(relativePath)
        );
    }

    protected void waitForAllAjaxRequestsToBeHandled() {
        if (webDriver instanceof JavascriptExecutor) {
            JavascriptExecutor javascriptExecutor = (JavascriptExecutor) webDriver;

            while (((Number) javascriptExecutor.executeScript("return $.active")).intValue() > 0) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException("Wait for Ajax query completion interrupted", e);
                }
            }
        }
    }


    /**
     * Loads the current page.
     */
    protected void load() {
        webDriver.get("http://localhost:8080/workshop-agile-testing" + this.relativePath);
    }

}