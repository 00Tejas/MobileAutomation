package pages;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.testng.Assert;
import utils.TestLogger;

/**
 * 🔐 Login Page - Simple login actions with validation and SLF4J logging
 * 
 * 📚 WHAT THIS CLASS DOES:
 * - Contains all the login/onboarding steps for the mobile app
 * - Handles 3 different login scenarios: successful, invalid email, invalid password
 * - Each step is logged using SLF4J for debugging purposes
 * - Uses Page Object Model pattern for better code organization
 * 
 * 🎯 FOR NEW TESTERS:
 * - This class contains the actual test steps (like clicking buttons, entering text)
 * - Each method represents a complete login flow
 * - Steps are numbered 1/6, 2/6, etc. to show progress
 * - All errors are caught and logged for easy debugging
 */
public class LoginPage extends BasePage {
    
    /**
     * 🏗️ Constructor with driver - Initialize with existing driver
     * 
     * 📝 WHAT THIS DOES:
     * - Takes the Appium driver from BaseTest
     * - Makes the driver available to all methods in this class
     * - This is how Page Object Model works - driver is passed from test to page
     */
    public LoginPage(AppiumDriver driver) {
        this.driver = driver;
    }
    
    /**
     * 🚀 Complete Login Flow - Simple 6 steps with validation and SLF4J logging
     * 
     * 📚 WHAT THIS METHOD DOES:
     * - Performs a complete successful login flow
     * - Goes through all 6 onboarding steps
     * - Validates that user reaches the home page
     * - Logs each step for debugging
     * 
     * 🎯 FOR NEW TESTERS:
     * - This is the "happy path" - when everything works correctly
     * - Each step is wrapped in try-catch to handle errors
     * - If any step fails, the test stops and shows the error
     * - The validation at the end confirms login was successful
     */
    public void completeLoginFlow() throws Exception {
        // 📝 START LOGGING: Begin tracking this test
        TestLogger.startTest("Successful Login");
        TestLogger.logInfo("Starting Login Flow...");

        // 📱 STEP 1: Click "Tap to Start" button on the first screen
        // This is the very first button users see when opening the app
        try {
            // Find the "Tap to Start" button using XPath locator
            // XPath: //android.widget.ImageView[contains(@content-desc, 'Tap to Start')]
            // This means: Find an ImageView element that contains "Tap to Start" in its content-desc
            driver.findElement(By.xpath("//android.widget.ImageView[contains(@content-desc, 'Tap to Start')]")).click();
            Thread.sleep(2000); // Wait 2 seconds for the screen to load
            TestLogger.logStep("1/6", "Click 'Tap to Start'", "SUCCESS", "Element found and clicked successfully");
        } catch (Exception e) {
            // If this step fails, log the error and stop the test
            TestLogger.logStep("1/6", "Click 'Tap to Start'", "FAILED", "Error: " + e.getMessage());
            TestLogger.logError("Step 1 failed: " + e.getMessage());
            throw e; // Re-throw the exception to fail the test
        }

        // 📱 STEP 2: Click button on second screen
        // After clicking "Tap to Start", we see a second screen with another button
        try {
            // Find any button on the current screen
            // XPath: //android.widget.Button means "find any Button element"
            driver.findElement(By.xpath("//android.widget.Button")).click();
            Thread.sleep(2000); // Wait 2 seconds for the screen to load
            TestLogger.logStep("2/6", "Click button on second screen", "SUCCESS", "Button found and clicked successfully");
        } catch (Exception e) {
            TestLogger.logStep("2/6", "Click button on second screen", "FAILED", "Error: " + e.getMessage());
            TestLogger.logError("Step 2 failed: " + e.getMessage());
            throw e;
        }

        // 📱 STEP 3: Select "Saw an advertisement" and click "Continue"
        // This is where users choose how they heard about the app
        try {
            // First, click on "Saw an advertisement" option
            // XPath: //*[@content-desc="Saw an advertisement"]
            // This means: Find any element (*) that has exactly "Saw an advertisement" as content-desc
            driver.findElement(By.xpath("//*[@content-desc=\"Saw an advertisement\"]")).click();
            
            // Then, click the "Continue" button
            // XPath: //*[@content-desc="Continue"]
            driver.findElement(By.xpath("//*[@content-desc=\"Continue\"]")).click();
            Thread.sleep(2000); // Wait 2 seconds for the screen to load
            TestLogger.logStep("3/6", "Select 'Saw an advertisement' and click 'Continue'", "SUCCESS", "Both elements found and clicked successfully");
        } catch (Exception e) {
            TestLogger.logStep("3/6", "Select 'Saw an advertisement' and click 'Continue'", "FAILED", "Error: " + e.getMessage());
            TestLogger.logError("Step 3 failed: " + e.getMessage());
            throw e;
        }

        // 📱 STEP 4: Enter email and click "Sign in"
        // This is the email input screen
        try {
            // First, click on the email input field to focus it
            // XPath: //android.widget.EditText means "find any text input field"
            driver.findElement(By.xpath("//android.widget.EditText")).click();
            
            // Then, type the email address
            // sendKeys() is used to type text into input fields
            driver.findElement(By.xpath("//android.widget.EditText")).sendKeys("program1@prodigy.baby");
            Thread.sleep(1000); // Wait 1 second for typing to complete
            
            // Finally, click the "Sign in" button
            // XPath: //android.widget.Button[@content-desc="Sign in"]
            // This means: Find a Button that has exactly "Sign in" as content-desc
            driver.findElement(By.xpath("//android.widget.Button[@content-desc=\"Sign in\"]")).click();
            Thread.sleep(2000); // Wait 2 seconds for the screen to load
            TestLogger.logStep("4/6", "Enter email and click 'Sign in'", "SUCCESS", "Email entered and sign in button clicked successfully");
        } catch (Exception e) {
            TestLogger.logStep("4/6", "Enter email and click 'Sign in'", "FAILED", "Error: " + e.getMessage());
            TestLogger.logError("Step 4 failed: " + e.getMessage());
            throw e;
        }

        // 📱 STEP 5: Click "Login with Password"
        // After entering email, we see a screen asking to login with password
        try {
            // Find and click the "Login with Password" button
            // XPath: //android.widget.Button[@content-desc="Login with Password"]
            driver.findElement(By.xpath("//android.widget.Button[@content-desc=\"Login with Password\"]")).click();
            Thread.sleep(2000); // Wait 2 seconds for the screen to load
            TestLogger.logStep("5/6", "Click 'Login with Password'", "SUCCESS", "Login with Password button found and clicked successfully");
        } catch (Exception e) {
            TestLogger.logStep("5/6", "Click 'Login with Password'", "FAILED", "Error: " + e.getMessage());
            TestLogger.logError("Step 5 failed: " + e.getMessage());
            throw e;
        }

        // 📱 STEP 6: Enter password and click "Sign in"
        // This is the final step - entering the password
        try {
            // Click on the password input field
            driver.findElement(By.xpath("//android.widget.EditText")).click();
            
            // Type the password
            // Note: This is the correct password for successful login
            driver.findElement(By.xpath("//android.widget.EditText")).sendKeys("123456");
            Thread.sleep(1000); // Wait 1 second for typing to complete
            
            // Click the final "Sign in" button
            driver.findElement(By.xpath("//android.widget.Button[@content-desc=\"Sign in\"]")).click();
            Thread.sleep(2000); // Wait 2 seconds for the screen to load
            TestLogger.logStep("6/6", "Enter password and click 'Sign in'", "SUCCESS", "Password entered and sign in button clicked successfully");
        } catch (Exception e) {
            TestLogger.logStep("6/6", "Enter password and click 'Sign in'", "FAILED", "Error: " + e.getMessage());
            TestLogger.logError("Step 6 failed: " + e.getMessage());
            throw e;
        }

        // ✅ VALIDATION: Check if login was successful
        // After all steps, we need to verify that we reached the home page
        try {
            // Look for "Activity Streak" text on the home page
            // This text only appears when login is successful
            String homePageText = driver.findElement(By.xpath("//android.view.View[contains(@content-desc, 'Activity Streak')]")).getAttribute("content-desc");
            
            // Use Assert.assertTrue() to verify the text contains "Activity Streak"
            // If this assertion fails, the test will fail
            Assert.assertTrue(homePageText.contains("Activity Streak"), 
                "Expected: Home page with Activity Streak after successful login. Actual: " + homePageText);
            TestLogger.logStep("VALIDATION", "Check home page", "SUCCESS", "Home page displayed with Activity Streak: " + homePageText);
        } catch (Exception e) {
            TestLogger.logStep("VALIDATION", "Check home page", "FAILED", "Error: " + e.getMessage());
            TestLogger.logError("Validation failed: " + e.getMessage());
            throw e;
        }
        
        // 📝 END LOGGING: Mark test as completed successfully
        TestLogger.logInfo("Login Flow Completed Successfully!");
        TestLogger.endTest("Successful Login", "PASSED");
    }
    
    /**
     * 🚀 Complete Login Flow with Invalid Email
     * 
     * 📚 WHAT THIS METHOD DOES:
     * - Performs the same 6 steps but with an invalid email
     * - Tests the app's error handling for invalid email addresses
     * - Validates that the app shows an appropriate error message
     * 
     * 🎯 FOR NEW TESTERS:
     * - This is a "negative test case" - testing what happens when things go wrong
     * - The steps are identical to successful login, but we use wrong email
     * - The validation checks for an error message instead of success
     * - This helps ensure the app handles errors gracefully
     */
    public void completeLoginFlowWithInvalidEmail() throws Exception {
        TestLogger.startTest("Invalid Email Login");
        TestLogger.logInfo("Starting Login Flow with Invalid Email...");

        // 📱 STEP 1: Click "Tap to Start" (same as successful login)
        try {
            driver.findElement(By.xpath("//android.widget.ImageView[contains(@content-desc, 'Tap to Start')]")).click();
            Thread.sleep(2000);
            TestLogger.logStep("1/6", "Click 'Tap to Start'", "SUCCESS", "Element found and clicked successfully");
        } catch (Exception e) {
            TestLogger.logStep("1/6", "Click 'Tap to Start'", "FAILED", "Error: " + e.getMessage());
            TestLogger.logError("Step 1 failed: " + e.getMessage());
            throw e;
        }

        // 📱 STEP 2: Click button on second screen (same as successful login)
        try {
            driver.findElement(By.xpath("//android.widget.Button")).click();
            Thread.sleep(2000);
            TestLogger.logStep("2/6", "Click button on second screen", "SUCCESS", "Button found and clicked successfully");
        } catch (Exception e) {
            TestLogger.logStep("2/6", "Click button on second screen", "FAILED", "Error: " + e.getMessage());
            TestLogger.logError("Step 2 failed: " + e.getMessage());
            throw e;
        }

        // 📱 STEP 3: Select "Saw an advertisement" and click "Continue" (same as successful login)
        try {
            driver.findElement(By.xpath("//*[@content-desc=\"Saw an advertisement\"]")).click();
            driver.findElement(By.xpath("//*[@content-desc=\"Continue\"]")).click();
            Thread.sleep(2000);
            TestLogger.logStep("3/6", "Select 'Saw an advertisement' and click 'Continue'", "SUCCESS", "Both elements found and clicked successfully");
        } catch (Exception e) {
            TestLogger.logStep("3/6", "Select 'Saw an advertisement' and click 'Continue'", "FAILED", "Error: " + e.getMessage());
            TestLogger.logError("Step 3 failed: " + e.getMessage());
            throw e;
        }

        // 📱 STEP 4: Enter INVALID email and click "Sign in"
        // ⚠️ THIS IS THE KEY DIFFERENCE: We use an invalid email address
        try {
            driver.findElement(By.xpath("//android.widget.EditText")).click();
            // 🚨 INVALID EMAIL: This email doesn't exist in the system
            driver.findElement(By.xpath("//android.widget.EditText")).sendKeys("invalid@email.com");
            Thread.sleep(1000);
            driver.findElement(By.xpath("//android.widget.Button[@content-desc=\"Sign in\"]")).click();
            Thread.sleep(2000);
            TestLogger.logStep("4/6", "Enter INVALID email and click 'Sign in'", "SUCCESS", "Invalid email entered and sign in button clicked successfully");
        } catch (Exception e) {
            TestLogger.logStep("4/6", "Enter INVALID email and click 'Sign in'", "FAILED", "Error: " + e.getMessage());
            TestLogger.logError("Step 4 failed: " + e.getMessage());
            throw e;
        }

        // 📱 STEP 5: Click "Login with Password" (same as successful login)
        try {
            driver.findElement(By.xpath("//android.widget.Button[@content-desc=\"Login with Password\"]")).click();
            Thread.sleep(2000);
            TestLogger.logStep("5/6", "Click 'Login with Password'", "SUCCESS", "Login with Password button found and clicked successfully");
        } catch (Exception e) {
            TestLogger.logStep("5/6", "Click 'Login with Password'", "FAILED", "Error: " + e.getMessage());
            TestLogger.logError("Step 5 failed: " + e.getMessage());
            throw e;
        }

        // 📱 STEP 6: Enter password and click "Sign in" (same as successful login)
        try {
            driver.findElement(By.xpath("//android.widget.EditText")).click();
            // Note: We still use the correct password, but email was invalid
            driver.findElement(By.xpath("//android.widget.EditText")).sendKeys("123456");
            Thread.sleep(1000);
            driver.findElement(By.xpath("//android.widget.Button[@content-desc=\"Sign in\"]")).click();
            Thread.sleep(2000);
            TestLogger.logStep("6/6", "Enter password and click 'Sign in'", "SUCCESS", "Password entered and sign in button clicked successfully");
        } catch (Exception e) {
            TestLogger.logStep("6/6", "Enter password and click 'Sign in'", "FAILED", "Error: " + e.getMessage());
            TestLogger.logError("Step 6 failed: " + e.getMessage());
            throw e;
        }

        // ✅ VALIDATION: Check for error message (NOT success message)
        // Since we used invalid email, we should see an error message
        try {
            // Look for the specific error message that appears for invalid credentials
            String errorMessage = driver.findElement(By.xpath("//android.view.View[@content-desc=\"The supplied auth credential is incorrect, malformed or has expired.\"]")).getAttribute("content-desc");
            
            // Verify that the error message contains the expected text
            Assert.assertTrue(errorMessage.contains("The supplied auth credential is incorrect"), 
                "Expected: Error message for invalid email. Actual: " + errorMessage);
            TestLogger.logStep("VALIDATION", "Check error message", "SUCCESS", "Error message displayed correctly: " + errorMessage);
            TestLogger.logInfo("Found error message: " + errorMessage);
        } catch (Exception e) {
            TestLogger.logStep("VALIDATION", "Check error message", "FAILED", "Error: " + e.getMessage());
            TestLogger.logError("Validation failed: " + e.getMessage());
            throw e;
        }
        
        TestLogger.logInfo("Invalid Email Login Flow Completed!");
        TestLogger.endTest("Invalid Email Login", "PASSED");
    }
    
    /**
     * 🚀 Complete Login Flow with Invalid Password
     * 
     * 📚 WHAT THIS METHOD DOES:
     * - Performs the same 6 steps but with an invalid password
     * - Tests the app's error handling for invalid passwords
     * - Validates that the app shows an appropriate error message
     * 
     * 🎯 FOR NEW TESTERS:
     * - Another "negative test case" - testing wrong password
     * - Uses correct email but wrong password
     * - Validates that app shows error message for wrong password
     * - This ensures the app's security works correctly
     */
    public void completeLoginFlowWithInvalidPassword() throws Exception {
        TestLogger.startTest("Invalid Password Login");
        TestLogger.logInfo("Starting Login Flow with Invalid Password...");

        // 📱 STEP 1: Click "Tap to Start" (same as successful login)
        try {
            driver.findElement(By.xpath("//android.widget.ImageView[contains(@content-desc, 'Tap to Start')]")).click();
            Thread.sleep(2000);
            TestLogger.logStep("1/6", "Click 'Tap to Start'", "SUCCESS", "Element found and clicked successfully");
        } catch (Exception e) {
            TestLogger.logStep("1/6", "Click 'Tap to Start'", "FAILED", "Error: " + e.getMessage());
            TestLogger.logError("Step 1 failed: " + e.getMessage());
            throw e;
        }

        // 📱 STEP 2: Click button on second screen (same as successful login)
        try {
            driver.findElement(By.xpath("//android.widget.Button")).click();
            Thread.sleep(2000);
            TestLogger.logStep("2/6", "Click button on second screen", "SUCCESS", "Button found and clicked successfully");
        } catch (Exception e) {
            TestLogger.logStep("2/6", "Click button on second screen", "FAILED", "Error: " + e.getMessage());
            TestLogger.logError("Step 2 failed: " + e.getMessage());
            throw e;
        }

        // 📱 STEP 3: Select "Saw an advertisement" and click "Continue" (same as successful login)
        try {
            driver.findElement(By.xpath("//*[@content-desc=\"Saw an advertisement\"]")).click();
            driver.findElement(By.xpath("//*[@content-desc=\"Continue\"]")).click();
            Thread.sleep(2000);
            TestLogger.logStep("3/6", "Select 'Saw an advertisement' and click 'Continue'", "SUCCESS", "Both elements found and clicked successfully");
        } catch (Exception e) {
            TestLogger.logStep("3/6", "Select 'Saw an advertisement' and click 'Continue'", "FAILED", "Error: " + e.getMessage());
            TestLogger.logError("Step 3 failed: " + e.getMessage());
            throw e;
        }

        // 📱 STEP 4: Enter email and click "Sign in" (same as successful login)
        try {
            driver.findElement(By.xpath("//android.widget.EditText")).click();
            // ✅ CORRECT EMAIL: We use the valid email address
            driver.findElement(By.xpath("//android.widget.EditText")).sendKeys("program1@prodigy.baby");
            Thread.sleep(1000);
            driver.findElement(By.xpath("//android.widget.Button[@content-desc=\"Sign in\"]")).click();
            Thread.sleep(2000);
            TestLogger.logStep("4/6", "Enter email and click 'Sign in'", "SUCCESS", "Email entered and sign in button clicked successfully");
        } catch (Exception e) {
            TestLogger.logStep("4/6", "Enter email and click 'Sign in'", "FAILED", "Error: " + e.getMessage());
            TestLogger.logError("Step 4 failed: " + e.getMessage());
            throw e;
        }

        // 📱 STEP 5: Click "Login with Password" (same as successful login)
        try {
            driver.findElement(By.xpath("//android.widget.Button[@content-desc=\"Login with Password\"]")).click();
            Thread.sleep(2000);
            TestLogger.logStep("5/6", "Click 'Login with Password'", "SUCCESS", "Login with Password button found and clicked successfully");
        } catch (Exception e) {
            TestLogger.logStep("5/6", "Click 'Login with Password'", "FAILED", "Error: " + e.getMessage());
            TestLogger.logError("Step 5 failed: " + e.getMessage());
            throw e;
        }

        // 📱 STEP 6: Enter INVALID password and click "Sign in"
        // ⚠️ THIS IS THE KEY DIFFERENCE: We use an invalid password
        try {
            driver.findElement(By.xpath("//android.widget.EditText")).click();
            // 🚨 INVALID PASSWORD: This password is wrong
            driver.findElement(By.xpath("//android.widget.EditText")).sendKeys("wrongpassword");
            Thread.sleep(1000);
            driver.findElement(By.xpath("//android.widget.Button[@content-desc=\"Sign in\"]")).click();
            Thread.sleep(2000);
            TestLogger.logStep("6/6", "Enter INVALID password and click 'Sign in'", "SUCCESS", "Invalid password entered and sign in button clicked successfully");
        } catch (Exception e) {
            TestLogger.logStep("6/6", "Enter INVALID password and click 'Sign in'", "FAILED", "Error: " + e.getMessage());
            TestLogger.logError("Step 6 failed: " + e.getMessage());
            throw e;
        }

        // ✅ VALIDATION: Check for error message (NOT success message)
        // Since we used invalid password, we should see an error message
        try {
            // Look for the specific error message that appears for invalid credentials
            String errorMessage = driver.findElement(By.xpath("//android.view.View[@content-desc=\"The supplied auth credential is incorrect, malformed or has expired.\"]")).getAttribute("content-desc");
            
            // Verify that the error message contains the expected text
            Assert.assertTrue(errorMessage.contains("The supplied auth credential is incorrect"), 
                "Expected: Error message for invalid password. Actual: " + errorMessage);
            TestLogger.logStep("VALIDATION", "Check error message", "SUCCESS", "Error message displayed correctly: " + errorMessage);
            TestLogger.logInfo("Found error message: " + errorMessage);
        } catch (Exception e) {
            TestLogger.logStep("VALIDATION", "Check error message", "FAILED", "Error: " + e.getMessage());
            TestLogger.logError("Validation failed: " + e.getMessage());
            throw e;
        }
        
        TestLogger.logInfo("Invalid Password Login Flow Completed!");
        TestLogger.endTest("Invalid Password Login", "PASSED");
    }
}