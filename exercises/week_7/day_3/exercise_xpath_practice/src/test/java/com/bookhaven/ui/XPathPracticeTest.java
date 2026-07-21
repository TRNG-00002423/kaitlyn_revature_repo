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

    @Test
    @DisplayName("XPath contains() function")
    void testContainsFunction() {
        WebElement link = driver.findElement(By.xpath("//a[contains(@href, 'login')]"));
        assertEquals("Form Authentication", link.getText());

        WebElement heading = driver.findElement(By.xpath("//*[contains(text(), 'Welcome')]"));
        assertNotNull(heading);

        driver.get("https://the-internet.herokuapp.com/login");
        WebElement button = driver.findElement(By.xpath("//button[contains(@class, 'radius')]"));
        assertTrue(button.isDisplayed());
    }

    @Test
    @DisplayName("Test starts-with() function")
    void testStartsWithFunction() {
        List<WebElement> internalLinks = driver.findElements(
                By.xpath("//a[starts-with(@href, '/')]"));
        assertFalse(internalLinks.isEmpty());
        driver.get("https://the-internet.herokuapp.com/challenging_dom");
        List<WebElement> elements = driver.findElements(
                By.xpath("//*[starts-with(@id, 'row')]"));
    }

    @Test
    @DisplayName("XPath text() function")
    void testTextFunction() {
        WebElement link = driver.findElement(By.xpath("//a[text()='Checkboxes']"));
        assertNotNull(link);
        link.click();
        assertTrue(driver.getCurrentUrl().contains("checkboxes"));
    }

    @Test
    @DisplayName("XPath normalize-space() function")
    void testNormalizeSpaceFunction() {
        driver.get("https://the-internet.herokuapp.com/login");
        WebElement label = driver.findElement(
                By.xpath("//label[normalize-space()='Username']"));
        assertNotNull(label);
    }

    @Test
    @DisplayName("Get links with \"Add\" in their text")
    void testGetLinksWithAdd() {
        List<WebElement> links = driver.findElements(
                By.xpath("//a[starts-with(text(), 'Add')]"));
        assertEquals(1, links.size());
        assertEquals(links.get(0).getText(), "Add/Remove Elements");
    }

    @Test
    @DisplayName("Get elements with suffix")
    void testGetElementsWithSuffix() {
        driver.get("https://the-internet.herokuapp.com/challenging_dom");
        List<WebElement> elements = driver.findElements(
                By.xpath("//*[contains(@id, '7aa9d374440e')]"));
        assertEquals(3, elements.size());
    }

    @Test
    @DisplayName("XPath Parent Axis")
    void testParentAxis() {
        driver.get("https://the-internet.herokuapp.com/login");
        WebElement input = driver.findElement(By.id("username"));
        WebElement parent = driver.findElement(
                By.xpath("//input[@id='username']/parent::div"));
        WebElement parentAlt = driver.findElement(By.xpath("//input[@id='username']/.."));
        assertNotNull(parent);
        assertNotNull(parentAlt);
    }

    @Test
    @DisplayName("XPath Child Axis")
    void testChildAxis() {
        driver.get("https://the-internet.herokuapp.com/");
        List<WebElement> listItems = driver.findElements(
                By.xpath("//ul/child::li"));
        assertTrue(listItems.size() > 0);
        // alternative: direct path
        // child is kinda implied?
        List<WebElement> listItemsAlt = driver.findElements(
                By.xpath("//ul/li"));
        assertTrue(listItemsAlt.size() > 0);
    }

    @Test
    @DisplayName("XPath descendant axis")
    void testDescendantAxis() {
        List<WebElement> allDescendants = driver.findElements(
                By.xpath("//div[@id='content']//a"));
        assertTrue(allDescendants.size() > 0);
    }

    @Test
    @DisplayName("XPath following-sibling axis")
    void testFollowingSiblingAxis() {
        driver.get("https://the-internet.herokuapp.com/login");
        WebElement inputAfterLabel = driver.findElement(
                By.xpath("//label[@for='username']/following-sibling::input"));
        assertNotNull(inputAfterLabel);
    }

    @Test
    @DisplayName("Test preceding-sibling axis")
    void testPrecedingSiblingAxis() {
        driver.get("https://the-internet.herokuapp.com/tables");
        WebElement cell = driver.findElement(
                By.xpath("//table[@id='table1']//td[text()='http://www.jdoe.com']/preceding-sibling::td[1]"));
        assertNotNull(cell);
    }

    @Test
    @DisplayName("XPath Ancestor Axis")
    void testAncestorAxis() {
        driver.get("https://the-internet.herokuapp.com/tables");
        WebElement table = driver.findElement(
                By.xpath("//td[text()='jsmith@gmail.com']/ancestor::table"));
        assertEquals("table1", table.getAttribute("id"));
    }

    @Test
    @DisplayName("Get the element containing username input")
    void testUsernameParent() {
        driver.get("https://the-internet.herokuapp.com/login");
        WebElement container = driver.findElement(
                By.xpath("//input[@id='username']/parent::div"));
        assertNotNull(container);
        assertEquals("large-6 small-12 columns", container.getAttribute("class"));
    }

    @Test
    @DisplayName("Get siblings of first list element")
    void testListSiblings() {
        WebElement abTesting = driver.findElement(By.xpath("//a[text()='A/B Testing']/.."));
        assertEquals("li", abTesting.getTagName());
        List<WebElement> siblings = driver.findElements(
                By.xpath("//a[text()='A/B Testing']/../following-sibling::*"));
        assertEquals(43, siblings.size());
    }

    @Test
    @DisplayName("More table navigation")
    void testNavigateFromCells() {
        driver.get("https://the-internet.herokuapp.com/tables");
        WebElement table = driver.findElement(
                By.xpath("//td[text()='Smith']/../../.."));
        assertEquals("table", table.getTagName());
    }

    @Test
    @DisplayName("XPath with multiple conditions (and/or)")
    void testMultipleConditions() {
        driver.get("https://the-internet.herokuapp.com/login");

        // AND condition
        WebElement input = driver.findElement(
                By.xpath("//input[@type='text' and @id='username']"));
        assertNotNull(input);

        // OR condition
        List<WebElement> inputs = driver.findElements(
                By.xpath("//input[@type='text' or @type='password']"));
        assertEquals(2, inputs.size());
    }

    @Test
    @DisplayName("XPath with position")
    void testPositionalXPath() {
        WebElement firstLink = driver.findElement(
                By.xpath("(//a)[1]"));
        assertNotNull(firstLink);
        System.out.println("firstLink text: " + firstLink.getAttribute("href"));
    }
}
