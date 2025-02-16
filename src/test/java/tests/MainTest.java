package tests;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/scenarios", glue = {"tests"}, monochrome = true, plugin = {"pretty"}, tags = "@simpleTest")

public class MainTest {}
