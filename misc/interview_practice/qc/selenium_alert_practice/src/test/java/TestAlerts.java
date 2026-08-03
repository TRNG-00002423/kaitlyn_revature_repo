import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestAlerts {
    private final String BASE_URL = "https://the-internet.herokuapp.com";
    private WebDriver driver;

    @BeforeEach
    void setUp() {
        driver = new ChromeDriver();
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }



    @Test
    @DisplayName("Alert displays appropriate message")
    public void alertDisplaysAppropriateMessage() {
        driver.get(BASE_URL + "/javascript_alerts");
        WebElement alertButton = driver.findElement(By.xpath("//ul/li[position()=1]/button"));
        System.out.println("Found button with text " + alertButton.getText());
        alertButton.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        assertEquals("I am a JS Alert", alert.getText());
    }
}
