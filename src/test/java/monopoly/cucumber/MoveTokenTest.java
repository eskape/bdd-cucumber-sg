package monopoly.cucumber;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		format = {"pretty", "json:target/cucumber/movetoken/cucumber.json", "html:target/cucumber/movetoken"},
		tags = {"@MoveToken"}
)

public class MoveTokenTest {

}
