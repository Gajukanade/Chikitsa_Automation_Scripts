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

	public class BugFirst {
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
	    public void validatePatientCount() throws InterruptedException {

	        
	        WebElement WeekRange = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div//p[text()='Week']")));
	        WeekRange.click();
	        
	        Thread.sleep(10000);
	        // Fetch "Total Patients" count
	        WebElement patientCount = driver.findElement(By.xpath("//div[@class='MuiBox-root css-0']//h3"));
	        String countText = patientCount.getText();
	        System.out.println("Total Patients: " + countText);
	        
	        
	        
//	        WebElement countElement = wait.until(ExpectedConditions.visibilityOfElementLocated(
//	        	    By.xpath("//text")
//	        	));
//	        	String countOpd = countElement.getText();
//	        	System.out.println("Extracted Count: " + countOpd);

	        
//	        int opdPatients = Integer.parseInt(driver.findElement(By.id("opdCount")).getText());
//	        int ipdPatients = Integer.parseInt(driver.findElement(By.id("ipdCount")).getText());
//	        int pathologyPatients = Integer.parseInt(driver.findElement(By.id("pathologyCount")).getText());
//	        int pharmacyPatients = Integer.parseInt(driver.findElement(By.id("pharmacyCount")).getText());
//	        int radiologyPatients = Integer.parseInt(driver.findElement(By.id("radiologyCount")).getText());
	        
	    }
	    
	    @AfterMethod
	    public void tearDown() {
//	        if (driver != null) {
//	            driver.quit();
//	        }
	    }
	}
	
