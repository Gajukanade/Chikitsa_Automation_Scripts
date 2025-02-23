package chikitsa;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import org.testng.Assert;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BugSix {

	
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
	        WebElement receptionistOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[@data-value='receptionist']")));
	        receptionistOption.click();

	        System.out.println("Receptionist role selected successfully!");
	        
	        WebElement IpdClick = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='IPD']")));
	        IpdClick.click();

	        
	        WebElement AdmitPatient = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Admit Patient']")));
	        AdmitPatient.click();
	        
	        WebElement input = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='basicDetails.patientName']")));
	        input.sendKeys("gajanan");
	        
	        WebElement payerDetailClick = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Payer Details']")));
	        payerDetailClick.click();
	        
	        WebElement kinDetailsButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Kin Details']")));
	        kinDetailsButton.click();
	        
	        WebElement assignStaffBedButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Assign Staff & Bed']")));
	        assignStaffBedButton.click();
	        	
	        	
	        WebElement departmentDropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='department0' and @role='combobox']")));
	        	departmentDropdown.click();
	        	
	        WebElement neurosurgeryOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[@role='option' and text()='Neurosurgery']")));
	        		neurosurgeryOption.click();
	        		
	        WebElement doctorDropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@role='combobox' and contains(@id, 'doctor')]") ));  
	        			doctorDropdown.click();

	        WebElement doctorOption = wait.until(ExpectedConditions.elementToBeClickable( By.xpath("//li[@role='option' and text()='Dr.Sumit']")));
	        			doctorOption.click();
	        			
	        			// Click the Nursing Station dropdown
	        			WebElement nursingStationDropdown = wait.until(ExpectedConditions.elementToBeClickable(
	        			    By.xpath("//div[@role='combobox' and contains(@id, 'nursingStation')]")));
	        			nursingStationDropdown.click();

	        			WebElement abhishekOption = wait.until(ExpectedConditions.elementToBeClickable(
	        			    By.xpath("//li[@role='option' and text()='Abhishek']")));
	        			abhishekOption.click();
	        			// Wait for the button to be clickable
	        			// Wait until the button is clickable
	        			WebElement reassignBedButton = wait.until(ExpectedConditions.elementToBeClickable(
	        			    By.xpath("//button[contains(., 'Assign Bed')]")));

	        			// Click the button
	        			reassignBedButton.click();
	        			
	        			// Wait for the dropdown to be clickable and open it
	        			WebElement wingDropdown = wait.until(ExpectedConditions.elementToBeClickable(
	        			    By.xpath("//div[@role='combobox' and contains(text(), 'Wing')]")));
	        			wingDropdown.click();

	        			// Select "Wing B" from the dropdown list
	        			WebElement wingBOption = wait.until(ExpectedConditions.elementToBeClickable(
	        			    By.xpath("//li[@role='option' and text()='Wing B']")));
	        			wingBOption.click();
	        			
	        			// Wait for the "10 Vacant Beds" span element to be clickable
	        			WebElement vacantBeds = wait.until(ExpectedConditions.elementToBeClickable(
	        			    By.xpath("//span[contains(text(), '10 Vacant Beds')]")));

	        			// Click the element
	        			vacantBeds.click();

	        			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	        			WebElement vacantButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@aria-label='Vacant']")));
	        			vacantButton.click();

	        			System.out.println(" clicking delux ");
	        			
	        			
	        			
	        			JavascriptExecutor js = (JavascriptExecutor) driver;
	        			long initialPosition = (Long) js.executeScript("return window.scrollY;");

	        			// Locate and scroll to the "Clear All" button
	        			WebElement clearAllButton = driver.findElement(By.xpath("//button[text()='Clear All']"));
	        			js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", clearAllButton);

	        			// Click the button
//	        			clearAllButton.click();
	        			
	        			Thread.sleep(3000);
	        			// Scroll back to the previous position
	        			js.executeScript("window.scrollTo(0, arguments[0]);", initialPosition);
	        			
	        			
	        			System.out.println("clear button click and return back to old postion");

//	        			wingDropdown.click();
//
//	        			wingBOption.click();
//	        		
//	        			vacantBeds.click();
	        			
	        		     // Wait for the 'Vacant' bed to be visible & clickable
	        	        WebElement vacantBed = wait.until(ExpectedConditions.elementToBeClickable(
	        	            By.xpath("//div[@aria-label='Vacant']//p[text()='001']")
	        	        ));

	        	        // Click on the element
	        	        vacantBed.click();
	        	     
	        	     // Locate the "Save Details" button
	        	        WebElement saveButton = driver.findElement(By.xpath("//button[contains(text(),'Save Details')]"));

	        	        // Scroll to the button using JavaScriptExecutor
//	        	        JavascriptExecutor js = (JavascriptExecutor) driver;
	        	        js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", saveButton);

	        	        // Wait for a second to ensure smooth scrolling (optional)
	        	        try {
	        	            Thread.sleep(1000);
	        	        } catch (InterruptedException e) {
	        	            e.printStackTrace();
	        	        }

	        	        // Check if the button is disabled
	        	        if (!saveButton.isEnabled()) {
	        	            System.out.println("Test Failed: Save Details button is disabled for a normal type of room.");
	        	            Assert.fail("Save Details button should be enabled for a normal type of room, but it's disabled.");
	        	        } else {
	        	            // Click the button
	        	            saveButton.click();
	        	            System.out.println("Test Passed: Save Details button is enabled and clicked successfully.");
	        	        }

	        	        


	    } 
}
