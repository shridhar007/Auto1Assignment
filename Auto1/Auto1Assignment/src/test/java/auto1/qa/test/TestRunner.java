package auto1.qa.test;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)

@CucumberOptions(
plugin = {"json:build/cucumber.json"},
features = {"C:\\Auto1\\Auto1Assignment\\features\\SearchResults.feature"},
glue= {"auto1.qa.testautomation"},
tags = {"@Scenario001"}
)

public class TestRunner {
	
}	