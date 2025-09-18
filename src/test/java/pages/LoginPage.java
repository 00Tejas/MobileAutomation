package pages;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.testng.Assert;

/**
 * 🔐 Login Page - Simple login actions with validation
 * 
 * 📚 WHAT THIS CLASS DOES:
 * - Contains all the login/onboarding steps for the mobile app
 * - Handles 3 different login scenarios: successful, invalid email, invalid password
 * - Uses Page Object Model pattern for better code organization
 * 
 * 🎯 FOR NEW TESTERS:
 * - This class contains the actual test steps (like clicking buttons, entering text)
 * - Each method represents a complete login flow
 * - Steps are numbered 1/6, 2/6, etc. to show progress
 * - All errors are caught and handled for easy debugging
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
     * 🚀 Complete Login Flow - Simple 6 steps with validation
     * 
     * 📚 WHAT THIS METHOD DOES:
     * - Performs a complete successful login flow
     * - Goes through all 6 onboarding steps
     * - Validates that user reaches the home page
     * 
     * 🎯 FOR NEW TESTERS:
     * - This is the "happy path" - when everything works correctly
     * - Each step is wrapped in try-catch to handle errors
     * - If any step fails, the test stops and shows the error
     * - The validation at the end confirms login was successful
     */
    public void completeLoginFlow() throws Exception {
        // 📱 CHECK APP STABILITY: Ensure app is running properly
        checkAppStability();

        // 📱 STEP 1: Click "Tap to Start" button on the first screen
        // This is the very first button users see when opening the app
        try {
            // Wait for element to be clickable before clicking
            By tapToStartLocator = By.xpath("//android.widget.ImageView[contains(@content-desc, 'Tap to Start')]");
            waitForElementToBeClickable(tapToStartLocator);
            driver.findElement(tapToStartLocator).click();
            // Wait for next screen button to appear
            waitForElementToBeClickable(By.xpath("//android.widget.Button"));
            checkAppStability(); // Check app stability after critical action
        } catch (Exception e) {
            // If this step fails, stop the test
            throw e; // Re-throw the exception to fail the test
        }

        // 📱 STEP 2: Click button on second screen
        // After clicking "Tap to Start", we see a second screen with another button
        try {
            // Wait for button to be clickable before clicking
            By buttonLocator = By.xpath("//android.widget.Button");
            waitForElementToBeClickable(buttonLocator);
            driver.findElement(buttonLocator).click();
            // Wait for advertisement option to appear
            waitForElementToBeClickable(By.xpath("//*[@content-desc=\"Saw an advertisement\"]"));
            checkAppStability(); // Check app stability after critical action
        } catch (Exception e) {
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
            // Wait for email field to appear
            waitForElementToBeClickable(By.xpath("//android.widget.EditText"));
        } catch (Exception e) {
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
            
            // Finally, click the "Sign in" button
            // XPath: //android.widget.Button[@content-desc="Sign in"]
            // This means: Find a Button that has exactly "Sign in" as content-desc
            waitForElementToBeClickable(By.xpath("//android.widget.Button[@content-desc=\"Sign in\"]"));
            driver.findElement(By.xpath("//android.widget.Button[@content-desc=\"Sign in\"]")).click();
            // Wait for Login with Password button to appear
            waitForElementToBeClickable(By.xpath("//android.widget.Button[@content-desc=\"Login with Password\"]"));
        } catch (Exception e) {
            throw e;
        }

        // 📱 STEP 5: Click "Login with Password"
        // After entering email, we see a screen asking to login with password
        try {
            // Find and click the "Login with Password" button
            // XPath: //android.widget.Button[@content-desc="Login with Password"]
            driver.findElement(By.xpath("//android.widget.Button[@content-desc=\"Login with Password\"]")).click();
            // Wait for password field to appear
            waitForElementToBeClickable(By.xpath("//android.widget.EditText"));
        } catch (Exception e) {
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
            
            // Click the final "Sign in" button
            waitForElementToBeClickable(By.xpath("//android.widget.Button[@content-desc=\"Sign in\"]"));
            driver.findElement(By.xpath("//android.widget.Button[@content-desc=\"Sign in\"]")).click();
            // Wait for home page to load
            waitForElementToBeVisible(By.xpath("//android.view.View[contains(@content-desc, 'Activity Streak')]"));
        } catch (Exception e) {
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
        } catch (Exception e) {
            throw e;
        }
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
        // 📱 CHECK APP STABILITY: Ensure app is running properly
        checkAppStability();
        
        // 📱 STEP 1: Click "Tap to Start" (same as successful login)
        try {
            waitForElementToBeClickable(By.xpath("//android.widget.ImageView[contains(@content-desc, 'Tap to Start')]"));
            driver.findElement(By.xpath("//android.widget.ImageView[contains(@content-desc, 'Tap to Start')]")).click();
            waitForElementToBeClickable(By.xpath("//android.widget.Button"));
        } catch (Exception e) {
            throw e;
        }

        // 📱 STEP 2: Click button on second screen (same as successful login)
        try {
            driver.findElement(By.xpath("//android.widget.Button")).click();
            waitForElementToBeClickable(By.xpath("//*[@content-desc=\"Saw an advertisement\"]"));
        } catch (Exception e) {
            throw e;
        }

        // 📱 STEP 3: Select "Saw an advertisement" and click "Continue" (same as successful login)
        try {
            driver.findElement(By.xpath("//*[@content-desc=\"Saw an advertisement\"]")).click();
            driver.findElement(By.xpath("//*[@content-desc=\"Continue\"]")).click();
            waitForElementToBeClickable(By.xpath("//android.widget.EditText"));
        } catch (Exception e) {
            throw e;
        }

        // 📱 STEP 4: Enter INVALID email and click "Sign in"
        // ⚠️ THIS IS THE KEY DIFFERENCE: We use an invalid email address
        try {
            waitForElementToBeClickable(By.xpath("//android.widget.EditText"));
            driver.findElement(By.xpath("//android.widget.EditText")).click();
            // 🚨 INVALID EMAIL: This email doesn't exist in the system
            driver.findElement(By.xpath("//android.widget.EditText")).sendKeys("invalid@email.com");
            Thread.sleep(2000); // Wait for app to process invalid email
            waitForElementToBeClickable(By.xpath("//android.widget.Button[@content-desc=\"Sign in\"]"));
            driver.findElement(By.xpath("//android.widget.Button[@content-desc=\"Sign in\"]")).click();
            Thread.sleep(3000); // Wait for app to process sign in attempt
        } catch (Exception e) {
            throw e;
        }

        // 📱 STEP 5: Click "Login with Password" (same as successful login)
        try {
            driver.findElement(By.xpath("//android.widget.Button[@content-desc=\"Login with Password\"]")).click();
            waitForElementToBeClickable(By.xpath("//android.widget.EditText"));
        } catch (Exception e) {
            throw e;
        }

        // 📱 STEP 6: Enter password and click "Sign in" (same as successful login)
        try {
            driver.findElement(By.xpath("//android.widget.EditText")).click();
            // Note: We still use the correct password, but email was invalid
            driver.findElement(By.xpath("//android.widget.EditText")).sendKeys("123456");
            waitForElementToBeClickable(By.xpath("//android.widget.Button[@content-desc=\"Sign in\"]"));
            driver.findElement(By.xpath("//android.widget.Button[@content-desc=\"Sign in\"]")).click();
            // Wait for error message to appear
            waitForElementToBeVisible(By.xpath("//android.view.View[@content-desc=\"The supplied auth credential is incorrect, malformed or has expired.\"]"));
        } catch (Exception e) {
            throw e;
        }

        // ✅ VALIDATION: Check for error message (NOT success message)
        // Since we used invalid email, we should see an error message
        try {
            // Wait for error message to appear
            waitForElementToBeVisible(By.xpath("//android.view.View[@content-desc=\"The supplied auth credential is incorrect, malformed or has expired.\"]"));
            // Look for the specific error message that appears for invalid credentials
            String errorMessage = driver.findElement(By.xpath("//android.view.View[@content-desc=\"The supplied auth credential is incorrect, malformed or has expired.\"]")).getAttribute("content-desc");
            
            // Verify that the error message contains the expected text
            if (errorMessage != null) {
                Assert.assertTrue(errorMessage.contains("The supplied auth credential is incorrect"), 
                    "Expected: Error message for invalid email. Actual: " + errorMessage);
            } else {
                Assert.fail("Expected: Error message for invalid email. Actual: No error message found");
            }
        } catch (Exception e) {
            throw e;
        }
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
        // 📱 CHECK APP STABILITY: Ensure app is running properly
        checkAppStability();
        
        // 📱 STEP 1: Click "Tap to Start" (same as successful login)
        try {
            waitForElementToBeClickable(By.xpath("//android.widget.ImageView[contains(@content-desc, 'Tap to Start')]"));
            driver.findElement(By.xpath("//android.widget.ImageView[contains(@content-desc, 'Tap to Start')]")).click();
            waitForElementToBeClickable(By.xpath("//android.widget.Button"));
        } catch (Exception e) {
            throw e;
        }

        // 📱 STEP 2: Click button on second screen (same as successful login)
        try {
            driver.findElement(By.xpath("//android.widget.Button")).click();
            waitForElementToBeClickable(By.xpath("//*[@content-desc=\"Saw an advertisement\"]"));
        } catch (Exception e) {
            throw e;
        }

        // 📱 STEP 3: Select "Saw an advertisement" and click "Continue" (same as successful login)
        try {
            driver.findElement(By.xpath("//*[@content-desc=\"Saw an advertisement\"]")).click();
            driver.findElement(By.xpath("//*[@content-desc=\"Continue\"]")).click();
            waitForElementToBeClickable(By.xpath("//android.widget.EditText"));
        } catch (Exception e) {
            throw e;
        }

        // 📱 STEP 4: Enter email and click "Sign in" (same as successful login)
        try {
            waitForElementToBeClickable(By.xpath("//android.widget.EditText"));
            driver.findElement(By.xpath("//android.widget.EditText")).click();
            // ✅ CORRECT EMAIL: We use the valid email address
            driver.findElement(By.xpath("//android.widget.EditText")).sendKeys("program1@prodigy.baby");
            waitForElementToBeClickable(By.xpath("//android.widget.Button[@content-desc=\"Sign in\"]"));
            driver.findElement(By.xpath("//android.widget.Button[@content-desc=\"Sign in\"]")).click();
            waitForElementToBeClickable(By.xpath("//android.widget.Button[@content-desc=\"Login with Password\"]"));
        } catch (Exception e) {
            throw e;
        }

        // 📱 STEP 5: Click "Login with Password" (same as successful login)
        try {
            driver.findElement(By.xpath("//android.widget.Button[@content-desc=\"Login with Password\"]")).click();
            waitForElementToBeClickable(By.xpath("//android.widget.EditText"));
        } catch (Exception e) {
            throw e;
        }

        // 📱 STEP 6: Enter INVALID password and click "Sign in"
        // ⚠️ THIS IS THE KEY DIFFERENCE: We use an invalid password
        try {
            driver.findElement(By.xpath("//android.widget.EditText")).click();
            // 🚨 INVALID PASSWORD: This password is wrong
            driver.findElement(By.xpath("//android.widget.EditText")).sendKeys("wrongpassword");
            Thread.sleep(2000); // Wait for app to process invalid password
            waitForElementToBeClickable(By.xpath("//android.widget.Button[@content-desc=\"Sign in\"]"));
            driver.findElement(By.xpath("//android.widget.Button[@content-desc=\"Sign in\"]")).click();
            Thread.sleep(3000); // Wait for app to process sign in attempt
        } catch (Exception e) {
            throw e;
        }

        // ✅ VALIDATION: Check for error message (NOT success message)
        // Since we used invalid password, we should see an error message
        try {
            // Wait for error message to appear
            waitForElementToBeVisible(By.xpath("//android.view.View[@content-desc=\"The supplied auth credential is incorrect, malformed or has expired.\"]"));
            // Look for the specific error message that appears for invalid credentials
            String errorMessage = driver.findElement(By.xpath("//android.view.View[@content-desc=\"The supplied auth credential is incorrect, malformed or has expired.\"]")).getAttribute("content-desc");
            
            // Verify that the error message contains the expected text
            if (errorMessage != null) {
                Assert.assertTrue(errorMessage.contains("The supplied auth credential is incorrect"), 
                    "Expected: Error message for invalid password. Actual: " + errorMessage);
            } else {
                Assert.fail("Expected: Error message for invalid password. Actual: No error message found");
            }
        } catch (Exception e) {
            throw e;
        }
    }
}