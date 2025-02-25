package runners;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"steps", "support"},
        plugin = {"pretty", "html:target/cucumber","json:target/cucumber.json"},
        tags = "@Booking",
        monochrome = true
)
public class TestRunner{



}
