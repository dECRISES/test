package org.dfm.test.cucumber;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "classpath:features", strict = true, plugin = {"json:target/cucumber/test.json", "json:target/cucumber/test.xml"}, tags = {
    "@Test"}, glue = "classpath:org.dfm.test.cucumber")
public class RunCucumberTestTest {

}
