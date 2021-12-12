package com.beadando.demo;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = "src/test/java/resources/features",
		glue = "src/test/java/com/beadando/demo/stepdef"
)
public class TestRunnerIT {
}
