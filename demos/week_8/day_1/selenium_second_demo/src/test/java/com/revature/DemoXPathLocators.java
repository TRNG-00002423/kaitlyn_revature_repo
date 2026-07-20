package com.revature;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * 
 * DemoXPathLocators
 * 
 * 1. XPath is the most flexible locator strategy
 * 
 */
public class DemoXPathLocators {

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

    @Test
    @DisplayName("Absolute XPath - start from root")
    void absoluteXPath_startsFromRoot() {

        /*
         * Absolute XPath starts with a single slash (/)
         * starts from document root
         * 
         * problems:
         * - very brittle: breaks if id DOM structure changes
         * - hard to read and maintain
         * - avoid this in production code
         */
        driver.get(BASE_URL);
        try {
            WebElement heading = driver.findElement(By.xpath("/html/body/div[2]/div/h1"));
            System.out.println("Found heading: " + heading.getText());
        } catch (NoSuchElementException e) {
            System.out.println("Absolute xpath is brittle: no element found.");
        }
    }

    @Test
    @DisplayName("Relative XPath - starts with //")
    void relativeXPath_startsAnywhere() {
        /*
         * Relative xpath starts with //
         * Searches the entire document for matching elements
         * Much better and more maintainable
         * Survives DOM restructuring
         * Preferred in production
         */
        driver.get(BASE_URL);
        // relative XPath
        WebElement heading = driver.findElement(By.xpath("//h1[@class='heading']"));
        System.out.println("Heading text: " + heading.getText());
        assertNotNull(heading.getText());
    }

    @Test
    @DisplayName("XPath by ID attribute")
    void xpathById_findElement() {
        driver.get(BASE_URL + "/login");
        WebElement usernameInput = driver.findElement(By.xpath("//input[@id='username']"));
        assertTrue(usernameInput.isDisplayed());
        System.out.println("Found username input by id");
    }

    @Test
    @DisplayName("XPath by name attribute")
    void xpathByName_findElement() {
        driver.get(BASE_URL + "/login");
        WebElement passwordInput = driver.findElement(By.xpath("//input[@name='password']"));
        assertTrue(passwordInput.isDisplayed());
        System.out.println("Found password input by name");
    }

    @Test
    @DisplayName("XPath by multiple attributes")
    void xpathByMultipleAttributes_moreSpecific() {
        driver.get(BASE_URL + "/login");
        WebElement loginButton = driver.findElement(
                By.xpath("//button[@type='submit' and @class = 'radius']"));
        assertTrue(loginButton.isDisplayed());
        System.out.println("Found login button: " + loginButton.getText());
    }

    @Test
    @DisplayName("contains() - partial match")
    void xpathContains_partialMatch() {
        /*
         * contains() matches if attributes/text CONTAIN the value
         * great for dynamic IDs or partial class names
         */
        driver.get(BASE_URL);
        WebElement formLink = driver.findElement(
                By.xpath("//a[contains(text(), 'Form')]"));

        System.out.println("Found link: " + formLink.getText());
        assertTrue(formLink.getText().contains("Form"));

        WebElement heading = driver.findElement(
                By.xpath("//*[contains(@class, 'heading')]"));

        System.out.println("Heading: " + heading.getText());

    }

    @Test
    @DisplayName("parent axis - navigate up")
    void xpathParent_navigateUp() {
        /*
         * parent:: moves up one level in the DOM
         * useful when you find the child but need the parent
         */
        driver.get(BASE_URL + "/tables");
        WebElement cell = driver.findElement(By.xpath(
                "//td[text() = 'jsmith@gmail.com']"));
        WebElement parentRow = cell.findElement(By.xpath(
                "./parent::tr")); // <tr> is a table row
        System.out.println("Parent row text: " + parentRow.getText());
        assertTrue(parentRow.getText().contains("Smith"));
    }

    @Test
    @DisplayName("child axis - navigate down")
    void xpathChild_navigateDown() {
        /*
         * ::child selects direct children
         * default axis, so child::div is same as just div
         */
        driver.get(BASE_URL);
        List<WebElement> links = driver.findElements(By.xpath("//div[@id='content']//a"));
        System.out.println("Found " + links.size() + " links in content area");
        assertTrue(links.size() > 0);
    }
}
