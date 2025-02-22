package chikitsa;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BugNine {

    WebDriver driver;
    WebDriverWait wait;

    @BeforeClass
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://harmony.chikitsa.dev");
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    @Test(priority = 1)
    public void loginTestWithCaptcha() throws InterruptedException {
        WebElement username = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
        username.sendKeys("rahulkhushalani@proton.me");

        WebElement password = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password")));
        password.sendKeys("123@Horizon");

        System.out.println("Please solve the CAPTCHA manually and press Enter in the console to continue...");
        try {
            System.in.read();
        } catch (Exception e) {
            e.printStackTrace();
        }

        WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@type='submit']")));
        loginButton.click();

        WebElement patientsClick = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='Patients']")));
        patientsClick.click();

        WebElement roleDropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@aria-labelledby='roles roles']")));
        roleDropdown.click();

        WebElement doctorOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[@data-value='doctor']")));
        doctorOption.click();

        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='MuiBox-root css-h67psh']/p")));
        element.click();

        WebElement openButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(), 'Open')]")));
        openButton.click();

        WebElement abdmLinkingButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(), 'ABDM Linking')]")));
        abdmLinkingButton.click();

        Thread.sleep(3000);

        WebElement dateField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@type='date' and @name='dateOfBirth']")));
        String futureDate = "19-08-2028";
        dateField.clear();
        dateField.sendKeys(futureDate);

        Thread.sleep(3000);

        String enteredDate = dateField.getAttribute("value");
        System.out.println("Entered Date: " + enteredDate);

        LocalDate today = LocalDate.now();
        LocalDate enteredLocalDate = LocalDate.parse(enteredDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        if (enteredLocalDate.isAfter(today)) {
            throw new AssertionError("BUG FOUND: The Date of Birth field accepts a future date! This is incorrect behavior.");
        }
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
