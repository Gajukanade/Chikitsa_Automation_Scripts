package chikitsa;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;


public class BugFive {
	
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
        
        WebElement IpdClick = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='IPD']")));
        IpdClick.click();

        
        WebElement AdmitPatient = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Admit Patient']")));
        AdmitPatient.click();
        
        WebElement payerDetailClick = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Payer Details']")));
        payerDetailClick.click();
        
        WebElement InputName = wait.until(ExpectedConditions.elementToBeClickable(By.id("payerDetails.payerName")));
        InputName.sendKeys("LIC");
        
      Thread.sleep(3000);

      WebElement clearAllButton = wait.until(ExpectedConditions.presenceOfElementLocated(
          By.xpath("//button[text()='Clear All']")
      ));

      // Scroll into view
      ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center', behavior: 'smooth'});", clearAllButton);

      // Click the button after scrolling
      wait.until(ExpectedConditions.elementToBeClickable(clearAllButton)).click();
      
      System.out.println("Not clearing green tick.");
    


}
}
