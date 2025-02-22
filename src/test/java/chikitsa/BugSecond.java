package chikitsa;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BugSecond {
	
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

        // Select Role as Receptionist
        WebElement roleDropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@aria-labelledby='roles roles']")));
        roleDropdown.click();
        WebElement receptionistOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[@data-value='receptionist']")));
        receptionistOption.click();

        System.out.println("Receptionist role selected successfully!");

        // Navigate to Patient List
        WebElement patientList = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='Patients']")));
        patientList.click();

        // Click "Edit" button for the first patient
        WebElement editButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Edit']")));
        editButton.click();

        // Clear Address field
        WebElement addressField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("address")));
        addressField.clear();
        Thread.sleep(5000);

        // Click "Save" button
        WebElement saveButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Save']")));
        saveButton.click();

    
        // check for success message
        try {
            WebElement successMessage = wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath("//div[contains(@class, 'MuiSnackbarContent-message')]")
                ));
//            WebElement successMessage = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(),'Update Successfully')]")));
            if (successMessage.isDisplayed()) {
                System.out.println("Test Failed: System allowed saving with an empty address!");
                Assert.fail("Bug Detected: The address field should be mandatory, but the update was successful.");
            }
        } catch (Exception ignored) {
            System.out.println("Test Passed: Address validation is working correctly.");
        }
    }

    @AfterMethod
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
