package chikitsa;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.testng.Assert;
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

        // Initialize WebDriverWait
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    @Test(priority = 1)
    public void loginTestWithCaptcha() throws InterruptedException {
        // Enter username
        WebElement username = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
        username.sendKeys("rahulkhushalani@proton.me");

        // Enter password
        WebElement password = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password")));
        password.sendKeys("123@Horizon");

        // Pause for CAPTCHA
        System.out.println("Please solve the CAPTCHA manually and press Enter in the console to continue...");
        try {
            System.in.read();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Click Login button
        WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@type='submit']")));
        loginButton.click();

        // Navigate to "Patients"
        WebElement patientsClick = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='Patients']")));
        patientsClick.click();

        WebElement roleDropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@aria-labelledby='roles roles']")));
        roleDropdown.click();

        // Wait for and click the "Doctor" option
        WebElement doctorOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[@data-value='doctor']")));
        doctorOption.click();

        // Wait for and click the "---" element
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='MuiBox-root css-h67psh']/p")));
        element.click();

        WebElement openButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(), 'Open')]")));
        openButton.click();

        WebElement abdmLinkingButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(), 'ABDM Linking')]")));
        abdmLinkingButton.click();

        Thread.sleep(3000);

        WebElement dateField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@type='date' and @name='dateOfBirth']")));

        // Clear existing value and enter a future date
        String futureDate = "19-08-2028";  // YYYY-MM-DD format as expected by most date pickers
        dateField.clear();
        dateField.sendKeys(futureDate);

        Thread.sleep(3000);

        // Verify if the entered date is accepted
        String enteredDate = dateField.getAttribute("value");  // Get the entered value
        System.out.println("Entered Date: " + enteredDate);

        // Convert to LocalDate for comparison
        LocalDate today = LocalDate.now();
        LocalDate enteredLocalDate = LocalDate.parse(enteredDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        // If the entered date is in the future, throw an exception
        if (enteredLocalDate.isAfter(today)) {
            throw new AssertionError("BUG FOUND: The Date of Birth field accepts a future date! This is incorrect behavior.");
        }
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
