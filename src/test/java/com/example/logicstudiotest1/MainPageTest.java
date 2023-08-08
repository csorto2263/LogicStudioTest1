package com.example.logicstudiotest1;

import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import static org.testng.Assert.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class MainPageTest {
    private WebDriver driver;
    private WebDriverWait wait;
    private MainPage mainPage;

    @BeforeMethod
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        // Fix the issue https://github.com/SeleniumHQ/selenium/issues/11750
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://demo.testim.io/");
        driver.manage().timeouts().implicitlyWait(300, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, Duration.ofSeconds(3));

        mainPage = new MainPage(driver);
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    @Test(priority = 1)
    public void VerificarTitulo() {
        String expectedTitle = "Space & Beyond | Testim.io demo";
        String actualTitle = driver.getTitle();
        Assert.assertEquals(actualTitle, expectedTitle);
    }

    @Test(priority = 2)
    public void VerificarDestinoMadan() {
        WebElement destination = driver.findElement(By.xpath("//*[@id='app']/div/section[2]/div[4]/div/div/div[1]/div[2]/div/h5"));
        Assert.assertEquals(destination.getText(), "Madan");
    }

    @Test(priority = 3)
    public void testSelectDepartureDate() throws InterruptedException {

        // Open the datepicker
        WebElement datepickerToggle = driver.findElement(By.cssSelector(".Hero__date-picker-box___RaqVV:nth-child(1) .theme__inputElement___27dyY"));
        Actions builder = new Actions(driver);
        builder.moveToElement(datepickerToggle).clickAndHold().perform();

        driver.findElement(By.cssSelector("body")).click();
        driver.findElement(By.cssSelector(".theme__day___3cb3g:nth-child(28) > span")).click();

        {
            WebElement element = driver.findElement(By.cssSelector(".theme__button___3HGWm:nth-child(2)"));
            builder = new Actions(driver);
            builder.moveToElement(element).perform();
        }
        driver.findElement(By.cssSelector(".theme__button___3HGWm:nth-child(2)")).click();
        {
            WebElement element = driver.findElement(By.tagName("body"));
            builder = new Actions(driver);
            builder.moveToElement(element, 0, 0).perform();
        }
    }

    @Test(priority = 4)
    public void testSelectPassengers() {
        driver.findElement(By.xpath("//*[@id=\"app\"]/div/section[1]/div[3]/div/div[3]")).click();
        driver.findElement(By.cssSelector(".theme__active___31xyK li:nth-child(3)")).click();
        driver.findElement(By.xpath("//*[@id=\"app\"]/div/section[1]/div[3]/div/div[4]")).click();
        driver.findElement(By.cssSelector(".theme__active___31xyK li:nth-child(2)")).click();
    }

    @Test(priority = 5)
    public void filtrarPlanetaAzul() {
        driver.findElement(By.cssSelector(".Hero__cta-button___9VskW")).click();

        driver.findElement(By.xpath("//*[@id=\"app\"]/div/section[2]/div[4]/div/div/div[2]")).click();
        driver.findElement(By.cssSelector(".theme__active___31xyK li:nth-child(4)")).click();
    }

    @Test(priority = 6)
    public void BookTayabamba(){

        filtrarPlanetaAzul();
        // Locate the "Tayabamba" destination and check if it's marked as "booked"
        WebElement tayabambaDestination = driver.findElement(By.xpath("//*[@id=\"app\"]/div/section[2]/div[5]/div/div/div[2]/div[4]/button"));
        tayabambaDestination.click();


        Assert.assertEquals(tayabambaDestination.getText(), "BOOKED");
    }

    @Test(priority = 7)
    public void CheckOut(){

        filtrarPlanetaAzul();
        BookTayabamba();

        // Enter other form data, such as name, email, etc.
        driver.findElement(By.xpath("//*[@id=\"app\"]/div/div[2]/section[1]/div[3]/div[1]/form/div[1]/input")).sendKeys("John Doe");
        driver.findElement(By.xpath("//*[@id=\"app\"]/div/div[2]/section[1]/div[3]/div[1]/form/div[2]/input")).sendKeys("john@example.com");

        // Fill in security number and phone number
        driver.findElement(By.xpath("//*[@id=\"app\"]/div/div[2]/section[1]/div[3]/div[1]/form/div[3]/input")).sendKeys("111-11-1111");
        driver.findElement(By.xpath("//*[@id=\"app\"]/div/div[2]/section[1]/div[3]/div[1]/form/div[4]/input")).sendKeys("2124567890");

        //Applying Promotion Code
        driver.findElement(By.xpath("//*[@id=\"app\"]/div/div[2]/section[1]/div[3]/div[2]/div[4]/div[1]/div/input")).sendKeys("30076");
        driver.findElement(By.xpath("//*[@id=\"app\"]/div/div[2]/section[1]/div[3]/div[2]/div[4]/div[2]/button")).click();

        // Upload a vaccination image (replace with the actual file path)
        File imageFile = new File("C:\\Users\\LATITUDE\\Downloads\\20831-8-ryu-hd.png");
        String absolutePath = imageFile.getAbsolutePath();
        WebElement uploadInput = driver.findElement(By.cssSelector(".CustomerInfo__dropzone-icon___1knAL"));
        uploadInput.sendKeys(absolutePath);

        // Click the "Next" button to proceed to payment
        driver.findElement(By.id("next-button")).click();

    }

}
