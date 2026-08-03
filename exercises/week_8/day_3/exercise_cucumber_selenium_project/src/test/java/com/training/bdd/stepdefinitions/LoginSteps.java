package com.training.bdd.stepdefinitions;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
//import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.*;

public class LoginSteps {
    private WebDriver driver;
    private final String BASE_URL = "https://the-internet.herokuapp.com";

    @Given("the user is on the login page")
    public void theUserIsOnTheLoginPage() {
//        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get(BASE_URL + "/login");
    }

    @Given("the user enters username {string}")
    public void theUserEntersUsername(String username) {
        WebElement usernameField = driver.findElement(By.id("username"));
        usernameField.sendKeys(username);
    }

    @Given("the user enters password {string}")
    public void theUserEntersPassword(String password) {
        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.sendKeys(password);
    }

    @Given("the user clicks the login button")
    public void theUserEntersTheLoginButton() {
        WebElement loginButton = driver.findElement(By.cssSelector("button[type='submit']")); // check this
        loginButton.click();
    }

    @Then("the user should be redirected to the secure area")
    public void theUserShouldBeRedirectedToTheSecureArea() {
        String url = driver.getCurrentUrl();
        assertNotNull(url);
        assertTrue(url.contains("/secure"),
                "User was not redirected to a secure area");
    }

    @Then("the user should see a success message containing {string}")
    public void theUserShouldSeeASuccessMessageContaining(String expectedMessage) {
        String message = driver.findElement(By.id("flash")).getText();
        assertTrue(message.contains(expectedMessage));
    }


    @Then("the user should remain on the login page")
    public void theUserShouldRemainOnTheLoginPage() {
        String url = driver.getCurrentUrl();
        assertNotNull(url);
        assertTrue(url.contains("/login"),
                "User is not on login page");
    }

    @Then("the user should see an error message containing {string}")
    public void theUserShouldSeeAnErrorMessageContaining(String expectedMessage) {
        String message = driver.findElement(By.id("flash")).getText();
        assertTrue(message.contains(expectedMessage));
    }

}
