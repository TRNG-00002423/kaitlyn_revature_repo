package com.bookhaven.ui;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class XPathPracticeTest extends BaseTest {
    @BeforeEach
    void navigateToPage() {
        driver.get("https://the-internet.herokuapp.com/");
    }

    @Test
    @DisplayName("XPath by tag name")
    void testXPathByTagName() {
        WebElement heading = driver.findElement(By.xpath("//h1"));
        assertEquals("Welcome to the-internet", heading.getText());

        List<WebElement> links = driver.findElements(By.xpath("//a"));
        assertTrue(links.size() > 10, "should find many links");
    }

    @Test
    @DisplayName("XPath by attribute")
    void testXPathByAttribute() {
        driver.get("https://the-internet.herokuapp.com/login");

        WebElement usernameById = driver.findElement(By.xpath("//*[@id='username']"));
        assertNotNull(usernameById);
        WebElement usernameByName = driver.findElement(By.xpath("//*[@name='username']"));
        assertNotNull(usernameByName);
        WebElement passwordByType = driver.findElement(By.xpath("//*[@type='password']"));
        assertNotNull(passwordByType);
        WebElement button = driver.findElement(By.xpath("//button[@type='submit'][@class='radius']"));
        assertTrue(button.isDisplayed());
    }

    @Test
    @DisplayName("XPath with specific value")
    void testXPathSpecificValue() {
        driver.get("https://the-internet.herokuapp.com/dropdown");
        WebElement option = driver.findElement(By.xpath("//option[@value='1']"));
        assertEquals("Option 1", option.getText());
    }

    @Test
    @DisplayName("XPath with class")
    void testXPathByClass() {
        driver.get("https://the-internet.herokuapp.com/upload");
        WebElement button = driver.findElement(By.xpath("//*[@class='button']"));
        assertEquals("Upload", button.getAttribute("value"));
    }

    @Test
    @DisplayName("XPath with href containing")
    void testXPathHrefContains() {
        WebElement loginLink = driver.findElement(By.xpath("//a[contains(@href, 'login')]"));
        assertEquals("Form Authentication", loginLink.getText());
    }

    @Test
    @DisplayName("Find all input elements")
    void testFindAllInputElements() {
        driver.get("https://the-internet.herokuapp.com/login");
        List<WebElement> inputElements = driver.findElements(By.xpath("//input"));
        assertEquals(2, inputElements.size());
        WebElement usernameInput = inputElements.get(0);
        WebElement passwordInput = inputElements.get(1);
        assertEquals("username", usernameInput.getAttribute("name"));
        assertEquals("password", passwordInput.getAttribute("name"));
    }
}
