import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import java.io.File;
import java.util.List;

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
        driver.get("https://todomvc.com");
    }

    // Your tests will go here!

    @Test
    void getTitle() throws InterruptedException {
        WebElement title = driver.findElement(By.cssSelector("body > div.container > header > div.col-md-8 > img"));
        String pageTitle = driver.getTitle();
        assertEquals("TodoMVC", driver.getTitle());
    }

    @Test
    void addItem() throws InterruptedException {

      WebElement reactButton = driver.findElement(By.xpath("/html/body/div/div[1]/div[2]/iron-pages/div[1]/ul/li[1]/a/span[1]"));
      reactButton.click();
      WebElement todoInput = driver.findElement(By.id("todo-input"));
      todoInput.click();
      todoInput.sendKeys("todo1");
      todoInput.sendKeys(Keys.ENTER);
      WebElement todoItems = driver.findElement(By.xpath("//*[@id=\"root\"]/main/ul/li/div/label"));
      String todoItemsText = todoItems.getText();
      assertEquals(todoItemsText,"todo1");
    }

    @Test
    void cannotAddEmptyItem() throws InterruptedException {
        WebElement reactButton = driver.findElement(By.xpath("/html/body/div/div[1]/div[2]/iron-pages/div[1]/ul/li[1]/a/span[1]"));
        reactButton.click();
        WebElement todoInput = driver.findElement(By.id("todo-input"));
        todoInput.click();
        todoInput.sendKeys(Keys.ENTER);
        assertTrue(driver.findElements(By.xpath("//*[@id=\"root\"]/main/ul/li/div/label")).isEmpty());
    }

    @Test
    void deleteItem() throws InterruptedException {

        WebElement reactButton = driver.findElement(By.xpath("/html/body/div/div[1]/div[2]/iron-pages/div[1]/ul/li[1]/a/span[1]"));
        reactButton.click();
        WebElement todoInput = driver.findElement(By.id("todo-input"));
        todoInput.click();
        todoInput.sendKeys("todo1");
        todoInput.sendKeys(Keys.ENTER);
        WebElement todoItems = driver.findElement(By.xpath("//*[@id=\"root\"]/main/ul/li/div/label"));
        driver.findElement(By.cssSelector("html")).click();
        driver.findElement(By.cssSelector(".destroy")).click();
        // We inserted three lines below expecting them to fail, to test the line that passes underneath
        // actually passes and isn't a false positive
        // List<WebElement> todos = driver.findElements(By.xpath("//*[@id=\"root\"]/main/ul/li/div/label"));
        // System.out.println("Size of elements list is " + todos.size() );
        // assertTrue(todos.size() == 2);
        assertTrue(driver.findElements(By.xpath("//*[@id=\"root\"]/main/ul/li/div/label")).isEmpty());

    }


    @AfterAll
    static void closeBrowser() {
        driver.quit();
    }
    public static void main(String[] args) throws Exception {

    }

    // Helper function for taking screenshots using WebDriver
    public static void takeScreenshot(WebDriver webdriver, String desiredPath) throws Exception {
        TakesScreenshot screenshot = ((TakesScreenshot) webdriver);
        File screenshotFile = screenshot.getScreenshotAs(OutputType.FILE);
        File targetFile = new File(desiredPath);
        FileUtils.copyFile(screenshotFile, targetFile);
    }
}
