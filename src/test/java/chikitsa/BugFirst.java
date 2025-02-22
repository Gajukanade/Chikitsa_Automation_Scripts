package chikitsa;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BugFirst {
    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeClass
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://harmony.chikitsa.dev");

        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    @Test(priority = 1)
    public void testLoginWithCaptcha() {
        try {
            WebElement username = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
            username.sendKeys("rahulkhushalani@proton.me");

            WebElement password = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password")));
            password.sendKeys("123@Horizon");

            System.out.println("Please solve the CAPTCHA manually and press Enter in the console to continue...");
            System.in.read(); // Wait for user input before proceeding

            WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@type='submit']")));
            loginButton.click();

            WebElement patientsTab = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='Patients']")));
            patientsTab.click();

        } catch (Exception e) {
            System.err.println("Login test failed: " + e.getMessage());
        }
    }

    @Test(priority = 2, dependsOnMethods = "testLoginWithCaptcha")
    public void testValidatePatientCount() {
        try {
            WebElement weekRange = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div//p[text()='Week']")));
            weekRange.click();

            WebElement patientCountElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='MuiBox-root css-0']//h3")));
            String patientCount = patientCountElement.getText();

            System.out.println("Total Patients: " + patientCount);
            assert !patientCount.isEmpty() : "Patient count should not be empty";

        } catch (Exception e) {
            System.err.println("Patient count validation failed: " + e.getMessage());
        }
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
