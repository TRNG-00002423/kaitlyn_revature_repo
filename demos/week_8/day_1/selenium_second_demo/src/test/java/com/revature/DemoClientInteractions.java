package com.revature;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * 
 * DemoClientInteractions
 * 
 * Demo Element interactions in Selenium.
 * 1. WebElement represents any HTML element.
 * 2. Basic interactions: click, sendKeys, clear
 * 3. Information getters: getText, getAttribute, getCssValue
 * 4. State checks: isDisplayed, isEnabled, isSelected
 */

@DisplayName("Element Interactions Demo")
public class DemoClientInteractions {
    private WebDriver driver;
    private static final String BASE_URL = "https://the-internet.herokuapp.com";

    @BeforeEach
    void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    // 1. Basic click operations
    @Test
    @DisplayName("click() - basic button click")
    void click_basicButton() {
        /*
         * click() simulates a mouse click on the element.
         * works on buttons, links, checkboxes, etc.
         */
        driver.get(BASE_URL + "/add_remove_elements/");
        // find and click the "Add Element" button.
        WebElement addButton = driver.findElement(
                By.xpath("//button[text()='Add Element']"));
        // Before clicking
        int elementsBefore = driver.findElements(By.className("added-manually")).size();
        System.out.println("Elements before click: " + elementsBefore);

        addButton.click();

        // After clicking
        int elementsAfter = driver.findElements(By.className("added-manually")).size();
        System.out.println("Elements after click: " + elementsAfter);

        assertEquals(elementsBefore + 1, elementsAfter);
    }

    @Test
    @DisplayName("click() - link navigation")
    void click_linkNavigation() {
        driver.get(BASE_URL);
        WebElement link = driver.findElement(
                By.linkText("Form Authentication"));
        link.click();
        assertTrue(driver.getCurrentUrl().contains("login"));
    }

    // 2. Input operations

    @Test
    @DisplayName("sendKeys() - Type text into input")
    void sendKeys_typeText() {
        /*
         * sendKeys() types text into input fields
         * works with fields, text areas, etc.
         */
        driver.get(BASE_URL + "/login");

        WebElement usernameInput = driver.findElement(By.id("username"));
        WebElement passwordInput = driver.findElement(By.id("password"));

        usernameInput.sendKeys("tomsmith");
        passwordInput.sendKeys("SuperSecretPassword!");

        assertEquals("tomsmith", usernameInput.getAttribute("value"));
        assertEquals("SuperSecretPassword!", passwordInput.getAttribute("value"));

        System.out.println("Entered successfully!");
    }

    @Test
    @DisplayName("sendKeys() - special keys")
    void sendKeys_specialKeys() {
        // Keys enum provides special keys like ENTER, TAB, etc.

        driver.get(BASE_URL + "/login");

        WebElement usernameInput = driver.findElement(By.id("username"));
        WebElement passwordInput = driver.findElement(By.id("password"));

        // type and press TAB to move to next field
        usernameInput.sendKeys("tomsmith");
        usernameInput.sendKeys(Keys.TAB);

        // Type password and press ENTER to submit
        passwordInput.sendKeys("SuperSecretPassword!");
        passwordInput.sendKeys(Keys.ENTER);

        // Verify the login occurred
        System.out.println("Current URL after login: " + driver.getCurrentUrl());
    }

    @Test
    @DisplayName("getText() - get visible text")
    void getText_getVisibleText() {
        driver.get(BASE_URL + "/login");
        WebElement heading = driver.findElement(By.tagName("h2"));
        String headingText = heading.getText();
        System.out.println("Heading text: " + headingText);
        assertEquals("Login Page", headingText);

        WebElement subHeading = driver.findElement(By.tagName("h4"));
        System.out.println("Subheading: " + subHeading.getText());
    }

    @Test
    @DisplayName("getAttribute() - Get attribute values")
    void getAttribute_getAttributeValues() {
        // getAttribute gets HTML attribute values
        // Common attributes: id, class, name, href, value, placeholder
        driver.get(BASE_URL + "/login");
        WebElement usernameInput = driver.findElement(By.id("username"));

        String id = usernameInput.getAttribute("id");
        String type = usernameInput.getAttribute("type");
        String name = usernameInput.getAttribute("name");

        assertEquals("username", id);
        assertEquals("text", type);

        WebElement link = driver.findElement(By.xpath("//a[contains(text(), 'Elemental')]"));
        String href = link.getAttribute("href");
        System.out.println("Link href: " + href);
    }

}
