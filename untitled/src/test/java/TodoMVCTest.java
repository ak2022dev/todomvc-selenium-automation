import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import java.io.File;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TodoMVCTest {
    private static ChromeDriver driver;
    @BeforeAll
    static void launchBrowser() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }
    @Test
    void getTitle() throws InterruptedException {
        driver.get("https://todomvc.com");
        WebElement title = driver.findElement(By.cssSelector("body > div.container > header > div.col-md-8 > img"));
        String pageTitle = driver.getTitle();
        assertEquals("TodoMVC", driver.getTitle());
    }


    // Your tests will go here!

    @AfterAll
    static void closeBrowser() {
        driver.quit();
    }
    public static void main(String[] args) throws Exception {
/*
        // Create a new instance of Selenium
        WebDriverManager.chromedriver().setup();

        // Use WebDriver to open a new instance of Chrome
        System.setProperty("webdriver.http.factory", "jdk-http-client");
        WebDriver driver = new ChromeDriver();

        // Instruct the driver to browse to the Makers website
        driver.get("https://todomvc.com");

        // Take a screenshot of what's currently on the page,
        // and store it in a file 'makers.png' in your project root
        takeScreenshot(driver, "todomvc.png");

        // Find the title of the webpage (the value inside the HTML
        // <title> element) and print it to the terminal
        System.out.println(driver.getTitle());

        // Close down Selenium and end the test
        driver.quit();

 */
    }

    // Helper function for taking screenshots using WebDriver
    public static void takeScreenshot(WebDriver webdriver, String desiredPath) throws Exception {
        TakesScreenshot screenshot = ((TakesScreenshot) webdriver);
        File screenshotFile = screenshot.getScreenshotAs(OutputType.FILE);
        File targetFile = new File(desiredPath);
        FileUtils.copyFile(screenshotFile, targetFile);
    }
}
