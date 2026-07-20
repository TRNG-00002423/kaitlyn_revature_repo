package com.bookhaven.ui;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import static org.junit.jupiter.api.Assertions.*;

public class FirstSeleniumTest extends BaseTest {
    @Test
    @DisplayName("Navigate to Google and verify title")
    void testNavigateToGoogle() {
        driver.get("https://www.google.com");
        String title = driver.getTitle();
        assertTrue(title.contains("Google"), "title should contain Google");
    }

    @Test
    @DisplayName("Navigate to example.com and verify content")
    void testNavigateToExample() {
        driver.get("https://example.com");
        String title = driver.getTitle();
        String currentURL = driver.getCurrentUrl();

        assertEquals("Example Domain", title);
        assertTrue(currentURL.contains("example.com"));

        WebElement heading = driver.findElement(By.tagName("h1"));
        assertEquals("Example Domain", heading.getText());
    }

    @Test
    @DisplayName("Navigate to practice site and find elements")
    void testFindElements() {
        driver.get("https://the-internet.herokuapp.com");
        WebElement heading = driver.findElement(By.tagName("h1"));
        assertEquals("Welcome to the-internet", heading.getText());

        WebElement formAuthLink = driver.findElement(By.linkText("Form Authentication"));
        assertTrue(formAuthLink.isDisplayed());
        String pageSource = driver.getPageSource();
        assertTrue(pageSource.contains("Available Examples"));
    }

    @Test
    @DisplayName("Tests with custom website")
    void testCustomSite() {
        driver.get("https://compcon.app");
        String currentURL = driver.getCurrentUrl();
        assertTrue(currentURL.contains("compcon"));
        WebElement heading = driver.findElement(By.className("heading"));
        System.out.println("Found heading: " + heading.getText());
        assertEquals("COMPENDIUM", heading.getText());
        WebElement title = driver.findElement(By.id("title"));
        System.out.println("Found title: " + title.getText());
        assertEquals("COMP/CON", title.getText());
    }

    @Test
    @DisplayName("Fill and submit login form")
    void testLoginForm() {
        driver.get("https://the-internet.herokuapp.com/login");
        WebElement usernameField = driver.findElement(By.id("username"));
        usernameField.sendKeys("tomsmith");
        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.sendKeys("SuperSecretPassword!");
        WebElement loginButton = driver.findElement(By.cssSelector("button[type='submit']"));
        loginButton.click();
        WebElement flashMessage = driver.findElement(By.id("flash"));
        assertTrue(flashMessage.getText().contains("You logged into a secure area!"));
    }

    @Test
    @DisplayName("Test invalid login")
    void testInvalidLogin() {
        driver.get("https://the-internet.herokuapp.com/login");
        driver.findElement(By.id("username")).sendKeys("invalid");
        driver.findElement(By.id("password")).sendKeys("invalid");
        driver.findElement(By.cssSelector("button[type='submit']")).click();

        WebElement flashMessage = driver.findElement(By.id("flash"));
        assertTrue(flashMessage.getText().contains("Your username is invalid!"));
    }

    @Test
    @DisplayName("Test form clearing")
    void testFormClearing() {
        driver.get("https://the-internet.herokuapp.com/login");
        WebElement usernameField = driver.findElement(By.id("username"));
        usernameField.sendKeys("some text");
        assertEquals("some text", usernameField.getAttribute("value"));
        usernameField.clear();
        assertEquals("", usernameField.getAttribute("value"));
        usernameField.sendKeys("new text");
        assertEquals("new text", usernameField.getAttribute("value"));
    }

    @Test
    @DisplayName("Test logout")
    void testLogOut() {
        driver.get("https://the-internet.herokuapp.com/login");
        driver.findElement(By.id("username")).sendKeys("tomsmith");
        driver.findElement(By.id("password")).sendKeys("SuperSecretPassword!");
        driver.findElement(By.cssSelector("button[type='submit']")).click();
        WebElement logoutButton = driver.findElement(By.className("button"));
        System.out.println("Found logout button: " + logoutButton.getText());
        logoutButton.click();
        WebElement flashMessage = driver.findElement(By.id("flash"));
        assertTrue(flashMessage.getText().contains("You logged out of the secure area!"));
    }

    @Test
    @DisplayName("Verify placeholder text")
    void testPlaceholder() {
        driver.get("https://github.com");
        WebElement placeholder = driver.findElement(By.className("placeholder"));
        System.out.println("Found placeholder: " + placeholder.getText());
        assertTrue(placeholder.getText().contains("Search or jump to..."));
    }

    @Test
    @DisplayName("Login with enter button")
    void testLoginEnter() {
        driver.get("https://the-internet.herokuapp.com/login");
        WebElement username = driver.findElement(By.id("username"));
        WebElement password = driver.findElement(By.id("password"));
        username.sendKeys("tomsmith", Keys.TAB);
        password.sendKeys("SuperSecretPassword!");
        password.sendKeys(Keys.ENTER);
        WebElement flashMessage = driver.findElement(By.id("flash"));
        assertTrue(flashMessage.getText().contains("You logged into a secure area!"));

    }

    @Test
    @DisplayName("Test link clicking and navigation")
    void testLinkClicking() {
        driver.get("https://the-internet.herokuapp.com");
        driver.findElement(By.linkText("Checkboxes")).click();
        assertTrue(driver.getCurrentUrl().contains("checkboxes"));
        driver.navigate().back();
        assertTrue(driver.getCurrentUrl().equals("https://the-internet.herokuapp.com/"));
    }

    @Test
    @DisplayName("Test checkbox interactions")
    void testCheckboxes() {
        driver.get("https://the-internet.herokuapp.com/checkboxes");
        java.util.List<WebElement> checkboxes = driver.findElements(By.cssSelector("input[type='checkbox']"));
        assertEquals(2, checkboxes.size(), "Should find 2 checkboxes");
        WebElement checkbox1 = checkboxes.get(0);
        WebElement checkbox2 = checkboxes.get(1);
        assertFalse(checkbox1.isSelected());
        checkbox1.click();
        assertTrue(checkbox1.isSelected());
        checkbox1.click();
        assertFalse(checkbox1.isSelected());
        assertTrue(checkbox2.isSelected());
    }

    @Test
    @DisplayName("Test getting element attributes")
    void testGetAttributes() {
        driver.get("https://the-internet.herokuapp.com/login");

        WebElement usernameField = driver.findElement(By.id("username"));

        // Get various attributes
        String id = usernameField.getAttribute("id");
        String type = usernameField.getAttribute("type");
        String name = usernameField.getAttribute("name");

        assertEquals("username", id);
        assertEquals("text", type);
        assertEquals("username", name);

        // Check if element is enabled and displayed
        assertTrue(usernameField.isEnabled());
        assertTrue(usernameField.isDisplayed());
    }
}
