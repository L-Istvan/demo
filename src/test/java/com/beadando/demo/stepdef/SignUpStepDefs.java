package com.beadando.demo.stepdef;

import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class SignUpStepDefs{
    private final WebDriver driver = new ChromeDriver();

    @Given("user navigates to sign up page by opening Chrome")
    public void navigateToSignUpPage(){
        driver.get("http://localhost:8080/regist");
    }

    @When("user enters all the required values")
    public void userEntersRequiredValues(){
        driver.findElement(By.name("lastname")).sendKeys("Tóbiás");
        driver.findElement(By.name("username")).sendKeys("TestTobias");
        driver.findElement(By.name("password")).sendKeys("Tobias001");
        driver.findElement(By.name("confirmpassword")).sendKeys("Tobias001");
        driver.findElement(By.name("faculty")).sendKeys("1");
        driver.findElement(By.className("buttonRegister")).click();
    }

    @Then("user is directed to the login page")
    public void navigateToLoginPage(){
        driver.get("http://localhost:8080/index");
    }

    @After()
    public void closeBrowser(){
        driver.quit();
    }
}
