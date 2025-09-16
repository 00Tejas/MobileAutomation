package pages;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

/**
 * 🏠 Home Page - Page Object for Home Screen functionality
 * 
 * 📚 WHAT THIS CLASS DOES:
 * - Contains methods to interact with the home page elements
 * - Handles home page specific actions and validations
 * - Extends BasePage to inherit driver setup and common functionality
 * - Provides reusable methods for home page test scenarios
 * 
 * 🎯 FOR NEW TESTERS:
 * - This class represents the home page of the mobile app
 * - Each method represents a specific action on the home page
 * - You can add new methods here as you discover new home page functionality
 * - This follows the Page Object Model pattern for maintainable tests
 */
public class HomePage extends BasePage {
    
    // 📱 CONSTRUCTOR: Initialize with driver
    public HomePage(AppiumDriver driver) {
        this.driver = driver;
    }
    
    /**
     * ✅ Verify Home Page Elements - Check if home page is loaded
     * 
     * 📚 WHAT THIS METHOD DOES:
     * - Verifies that the home page has loaded successfully
     * - Checks for key home page elements like Activity Streak
     * - Returns true if home page is properly displayed
     * 
     * 🎯 FOR NEW TESTERS:
     * - This method validates that login was successful
     * - It looks for the "Activity Streak" text which indicates home page
     * - You can add more element checks here as needed
     */
    public boolean verifyHomePageLoaded() {
        try {
            System.out.println("🔍 Verifying home page is loaded...");
            
            // Wait for home page elements to load
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            
            // Check for Activity Streak text (indicates successful login)
            WebElement activityStreak = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//android.view.View[@content-desc=\"Activity Streak\n"
                		+ "0 Week Streak\n"
                		+ "Mon\n"
                		+ "Tue\n"
                		+ "Wed\n"
                		+ "Thu\n"
                		+ "Fri\n"
                		+ "Sat\n"
                		+ "Sun\"]")
            ));
            
            System.out.println("✅ Home page loaded successfully!");
            return true;
            
        } catch (Exception e) {
            System.out.println("❌ Home page verification failed: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * 📊 Get Activity Streak Text - Extract streak information
     * 
     * 📚 WHAT THIS METHOD DOES:
     * - Gets the current activity streak text from home page
     * - Returns the streak information for validation
     * 
     * 🎯 FOR NEW TESTERS:
     * - This method extracts text from the Activity Streak section
     * - You can use this to validate streak information in tests
     * - Returns empty string if element not found
     */
    public String getActivityStreakText() {
        try {
            WebElement activityStreak = driver.findElement(By.xpath("//android.view.View[@content-desc=\"Activity Streak\n"
            		+ "0 Week Streak\n"
            		+ "Mon\n"
            		+ "Tue\n"
            		+ "Wed\n"
            		+ "Thu\n"
            		+ "Fri\n"
            		+ "Sat\n"
            		+ "Sun\"]"));
            String streakText = activityStreak.getText();
            System.out.println("📊 Activity Streak Text: " + streakText);
            return streakText;
        } catch (Exception e) {
            System.out.println("⚠️ Could not get Activity Streak text: " + e.getMessage());
            return "";
        }
    }
    
    /**
     * 📱 Validate App Bar - Check if app bar is visible
     */
    public void validateAppBar() {
        // TODO: Add xpath for app bar
        driver.findElement(By.xpath("//*[@content-desc='App Bar']"));
    }
    
    /**
     * 🔔 Validate Notification Icon - Check if notification icon is visible
     */
    public void validateNotificationIcon() {
        // TODO: Add xpath for notification icon
        driver.findElement(By.xpath("//android.view.View[@content-desc=\"1\"]/android.view.View[2]"));
    }
    
    /**
     * 📊 Validate Activity Streak - Check if activity streak section is visible
     */
    public void validateActivityStreak() {
        // TODO: Add xpath for activity streak
        driver.findElement(By.xpath("//*[contains(@text, 'Activity Streak')]"));
    }
    
    /**
     * 🏆 Validate Claim Medals - Check if claim medals section is visible
     */
    public void validateClaimMedals() {
        // TODO: Add xpath for claim medals
        driver.findElement(By.xpath("//android.view.View[@content-desc=\"Claim medals\"]"));
    }
    
    /**
     * 💬 Validate Feedback Pop-up - Check if feedback pop-up is visible
     */
    public void validateFeedbackPopup() {
        // TODO: Add xpath for feedback pop-up
        driver.findElement(By.xpath("//android.view.View[@content-desc=\"Enjoying Prodigy Baby?\"]"));
    }
    
    /**
     * 🔍 Check Element Exists - Generic method to check if element exists
     * 
     * 📚 WHAT THIS METHOD DOES:
     * - Generic method to check if any element exists on the page
     * - Takes a locator and returns true if element is found
     * 
     * 🎯 FOR NEW TESTERS:
     * - This is a utility method for checking element presence
     * - You can use this to validate any element on the home page
     * - Pass any By locator to check if element exists
     */
    public boolean checkElementExists(By locator) {
        try {
            driver.findElement(locator);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
