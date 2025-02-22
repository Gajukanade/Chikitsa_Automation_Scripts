package chikitsa;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;

public class ChikitsaAutomation {
    WebDriver driver;
    WebDriverWait wait;

    @BeforeClass
    public void setup() {
        // Use WebDriverManager to setup ChromeDriver
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://harmony.chikitsa.dev");

        // Initialize WebDriverWait after WebDriver setup
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    @Test(priority = 1)
    public void loginTestWithCaptcha() {
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
    }

    @Test(priority = 2)
    public void testMandatoryAddressField() {
        // Select "Receptionist" role
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

        // Locate Address field and clear it
        WebElement addressField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("address")));
        addressField.clear();

        // Click "Save" button
        WebElement saveButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Save']")));
        saveButton.click();

        // Check if validation error appears
        try {
            WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Address is required')]"))); // Adjust this locator based on actual UI
            Assert.assertTrue(errorMessage.isDisplayed(), "Validation error message is displayed correctly.");
            System.out.println("Test Passed: Address field validation is working.");
        } catch (Exception e) {
            Assert.fail("Test Failed: Address field allows empty submission!");
        }
    }
    
   
    @Test(priority = 3)
    public void dcotorModule() throws InterruptedException {
    	 WebElement roleDropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@aria-labelledby='roles roles']")));
         roleDropdown.click();
         
         Thread.sleep(3000);
         WebElement receptionistOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[text()='Doctor']")));
         receptionistOption.click();
    	
         WebElement actionBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[text()='---']")));
         actionBtn.click();
         
         WebElement OpenBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Open']")));
         OpenBtn.click();
         
         WebElement AbdmBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='ABDM Linking']")));
         AbdmBtn.click();
         
//         Thread.sleep(3000);
         
         WebElement enableBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[text()='Enable ABDM Linking']")));
//         enableBtn.click();
         Actions actions = new Actions(driver);
         actions.moveToElement(enableBtn).click().perform();
    	
    }
    
    
    
    
    

    @AfterMethod
    public void tearDown() {
//        if (driver != null) {
//            driver.quit();
//        }
    }
}
