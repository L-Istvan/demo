package com.beadando.demo.stepdef;

import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginStepDefs {
    private final WebDriver driver = new ChromeDriver();

    @Given("user navigates to the login page by opening Chrome")
    public void navigateToLoginPage(){
        driver.get("http://localhost:8080/index");
    }

    @When("^user enters correct username and password values$")
    public void enterUsernameAndPassword(){
        driver.findElement(By.xpath("username")).sendKeys("Pok");
        driver.findElement(By.name("password")).sendKeys("admin");
    }

    @Then("^user gets directed to homepage$")
    public void navigateToHomePage(){
        driver.get("http://localhost:8080/");
    }

    @After()
    public void closeBrowser(){
        driver.quit();
    }
}
