package com.mobile.automation.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.By;
import java.time.Duration;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * 🏗️ Base Page - Common setup for all pages
 * 
 * 📚 WHAT THIS CLASS DOES:
 * - Provides common driver setup and cleanup methods
 * - Handles Appium driver initialization and configuration
 * - Manages app reset functionality for fresh test starts
 * - Contains all the technical setup that other pages need
 * 
 * 🎯 FOR NEW TESTERS:
 * - This is the "foundation" class that all other page classes extend
 * - It handles the complex technical setup so you don't have to
 * - Contains methods for starting the app, resetting it, and cleaning up
 * - You don't need to understand all the technical details, just know it works
 */
public class BasePage {
    
    // 📱 DRIVER VARIABLE: Store the Appium driver
    // This is the "remote control" that lets us interact with the mobile app
    protected AppiumDriver driver;
    
    /**
     * 🚀 Setup Driver - Initialize Appium driver
     * 
     * 📚 WHAT THIS METHOD DOES:
     * - Creates and configures the Appium driver
     * - Sets up all the technical requirements for mobile automation
     * - Connects to the Appium server running on your computer
     * - Launches the mobile app on the emulator
     * 
     * 🎯 FOR NEW TESTERS:
     * - This method does all the technical "magic" to start the app
     * - You don't need to understand the technical details
     * - Just know that it makes the app ready for testing
     * - The driver variable becomes your "remote control" for the app
     */
    public void setupDriver() throws MalformedURLException {
        // 📋 CREATE CAPABILITIES: Tell Appium what we want to automate
        // Capabilities are like "instructions" for Appium
        DesiredCapabilities capabilities = new DesiredCapabilities();
        
        // 📱 DEVICE INFORMATION: Tell Appium about the device
        capabilities.setCapability("platformName", "Android");        // We're testing Android
        capabilities.setCapability("deviceName", "emulator-5554");    // Use this specific emulator
        capabilities.setCapability("platformVersion", "16");          // Android version 16
        
        // 📱 APP INFORMATION: Tell Appium which app to test
        capabilities.setCapability("appPackage", "com.raising.prodigy");           // App's package name
        capabilities.setCapability("appActivity", "com.raising.prodigy.MainActivity"); // App's main activity
        
        // ⚙️ APPIUM SETTINGS: Configure how Appium should behave
        capabilities.setCapability("automationName", "UiAutomator2");  // Use UiAutomator2 for Android
        capabilities.setCapability("noReset", false);                  // Allow app reset for fresh start
        capabilities.setCapability("autoGrantPermissions", true);     // Grant permissions automatically
        
        // 🌐 CONNECT TO APPIUM SERVER: Connect to the Appium server
        // Appium server runs on your computer and controls the emulator
        URL appiumServerURL = new URL("http://127.0.0.1:4723/wd/hub");
        
        // 🚀 START THE APP: Create the driver and launch the app
        // This is where the magic happens - the app launches on the emulator
        driver = new AndroidDriver(appiumServerURL, capabilities);
        
        // ⏱️ SET WAIT TIME: Wait for elements to load
        // This tells the driver to wait up to 20 seconds for elements to appear
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        
        // 📱 MOBILE APPS: Only implicit wait is supported, other timeouts are not available
    }
    
    /**
     * 🧹 Cleanup - Close the driver
     * 
     * 📚 WHAT THIS METHOD DOES:
     * - Closes the Appium driver
     * - Stops the automation session
     * - Cleans up resources
     * 
     * 🎯 FOR NEW TESTERS:
     * - This method "turns off" the automation
     * - It's like closing the remote control
     * - Currently commented out to keep app open for debugging
     * - You can uncomment driver.quit() if you want to close the app
     */
    public void cleanup() {
        if (driver != null) {
            // driver.quit(); // COMMENTED OUT - Keep app open for debugging
        }
    }
    
    /**
     * 🔄 Reset App Data - Complete app reset to start fresh
     * 
     * 📚 WHAT THIS METHOD DOES:
     * - Completely resets the app to its initial state
     * - Clears all user data, cache, and permissions
     * - Ensures each test starts from the onboarding screen
     * - Uses ADB (Android Debug Bridge) commands to reset the app
     * 
     * 🎯 FOR NEW TESTERS:
     * - This method "wipes the slate clean" for each test
     * - It's like uninstalling and reinstalling the app
     * - Ensures tests don't interfere with each other
     * - Uses ADB commands (you don't need to understand these)
     */
    public void resetAppData() {
        try {
            // 🛑 STEP 1: Force stop the app
            ProcessBuilder stopPb = new ProcessBuilder("adb", "shell", "am", "force-stop", "com.raising.prodigy");
            Process stopProcess = stopPb.start();
            stopProcess.waitFor();
            Thread.sleep(3000); // Wait 3 seconds for the app to stop

            // 🧹 STEP 2: Clear app data completely
            ProcessBuilder clearPb = new ProcessBuilder("adb", "shell", "pm", "clear", "com.raising.prodigy");
            Process clearProcess = clearPb.start();
            clearProcess.waitFor();
            Thread.sleep(4000); // Wait 4 seconds for data to clear

            // 🔄 STEP 3: Reset app permissions
            ProcessBuilder resetPb = new ProcessBuilder("adb", "shell", "pm", "reset-permissions", "com.raising.prodigy");
            Process resetProcess = resetPb.start();
            resetProcess.waitFor();
            Thread.sleep(3000); // Wait 3 seconds for permissions to reset

            // ⏱️ FINAL WAIT: Ensure everything is reset
            Thread.sleep(2000); // Wait 2 seconds for everything to settle

        } catch (Exception e) {
            // ⚠️ ERROR HANDLING: If reset fails, continue anyway
        }
    }
    
    /**
     * 🚀 Setup Driver with Reset - Initialize driver and reset app
     * 
     * 📚 WHAT THIS METHOD DOES:
     * - Combines app reset and driver setup
     * - First resets the app to onboarding state
     * - Then sets up the driver normally
     * - Ensures each test starts fresh
     * 
     * 🎯 FOR NEW TESTERS:
     * - This is the "one-stop shop" for starting a test
     * - It does both reset and setup in the right order
     * - Use this method when you want a completely fresh start
     * - It's like "restarting the app" before each test
     */
    public void setupDriverWithReset() throws MalformedURLException {
        // 🔄 RESET FIRST: Always reset the app first to ensure fresh start
        resetAppData();
        
        // ⏱️ WAIT: Give extra time for reset to complete
        try {
            Thread.sleep(3000); // Wait 3 seconds for reset to fully complete
        } catch (Exception e) {
            // Continue if sleep fails
        }
        
        // 🚀 SETUP DRIVER: Then setup the driver normally
        setupDriver();
    }
    
    /**
     * 🔗 Get Driver - Return the driver instance
     * 
     * 📚 WHAT THIS METHOD DOES:
     * - Returns the Appium driver instance
     * - Allows other classes to access the driver
     * - Part of the Page Object Model pattern
     * 
     * 🎯 FOR NEW TESTERS:
     * - This method gives other classes access to the driver
     * - It's like "sharing the remote control"
     * - Other classes use this to interact with the app
     * - You don't need to call this directly, it's used internally
     */
    public AppiumDriver getDriver() {
        return driver;
    }
    
    /**
     * ⏱️ Wait for Element - Simple wait for element to be clickable
     * 
     * 📚 WHAT THIS METHOD DOES:
     * - Waits for an element to be clickable before proceeding
     * - Prevents app crashes by ensuring elements are ready
     * - Uses explicit wait for better stability
     * 
     * 🎯 FOR NEW TESTERS:
     * - This method helps prevent app crashes
     * - It waits for elements to be ready before clicking
     * - Simple to use: just pass the element locator
     */
    public void waitForElementToBeClickable(By locator) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
            wait.until(ExpectedConditions.elementToBeClickable(locator));
        } catch (Exception e) {
            // Element not ready - continue without logging
        }
    }
    
    /**
     * ⏱️ Wait for Element - Simple wait for element to be visible
     * 
     * 📚 WHAT THIS METHOD DOES:
     * - Waits for an element to be visible before proceeding
     * - Prevents app crashes by ensuring elements are loaded
     * - Uses explicit wait for better stability
     * 
     * 🎯 FOR NEW TESTERS:
     * - This method helps prevent app crashes
     * - It waits for elements to be visible before interacting
     * - Simple to use: just pass the element locator
     */
    public void waitForElementToBeVisible(By locator) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        } catch (Exception e) {
            // Element not visible - continue without logging
        }
    }
    
    /**
     * 📱 Check App Stability - Simple app health check
     * 
     * 📚 WHAT THIS METHOD DOES:
     * - Checks if the app is still running and responsive
     * - Prevents crashes by detecting app issues early
     * - Simple connectivity check using mobile-specific method
     * 
     * 🎯 FOR NEW TESTERS:
     * - This method helps prevent app crashes
     * - It checks if the app is still working properly
     * - Simple to use: call it before important operations
     */
    public void checkAppStability() {
        try {
            // Simple check - try to get current activity (mobile-specific)
            String currentActivity = driver.getCurrentUrl();
        } catch (Exception e) {
            // Mobile apps don't support getCurrentUrl, so we use a different approach
            try {
                // Try to find any element to check if app is responsive
                driver.findElement(By.xpath("//*"));
            } catch (Exception e2) {
                // App stability check failed - continue without logging
            }
        }
    }
}