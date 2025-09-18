package tests;

import pages.LoginPage;
// import utils.TestReport;  // Using fully qualified name instead
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.annotations.AfterSuite;

/**
 * 🔐 Login Test - All login scenarios in one class with priorities
 * 
 * 📚 WHAT THIS CLASS DOES:
 * - Contains 3 different test cases for login functionality
 * - Tests successful login, invalid email, and invalid password scenarios
 * - Uses TestNG annotations to control test execution order
 * - Generates reports and logs for each test case
 * 
 * 🎯 FOR NEW TESTERS:
 * - This is the main test class that runs the actual tests
 * - Each @Test method represents one test case
 * - @BeforeMethod runs before each test (setup)
 * - @AfterMethod runs after each test (cleanup)
 * - @AfterSuite runs after all tests (report generation)
 * - Priority numbers (1, 2, 3) control the order of test execution
 */
public class LoginTest extends BaseTest {

    // 📝 VARIABLE: Store the LoginPage object
    // This allows us to call methods from LoginPage class
    private LoginPage loginPage;

    /**
     * 🏗️ Setup - Initialize the login page
     * 
     * 📚 WHAT THIS METHOD DOES:
     * - Runs before each test method (@BeforeMethod)
     * - Resets the app to start fresh from onboarding
     * - Creates a new LoginPage object with the driver
     * - This ensures each test starts with a clean app state
     * 
     * 🎯 FOR NEW TESTERS:
     * - This method runs automatically before each test
     * - It's like "preparing the stage" before each test
     * - setupTestWithReset() ensures the app is in onboarding state
     * - new LoginPage(getDriver()) creates the page object we'll use
     */
    @BeforeMethod
    public void setUp() throws Exception {
        // 🔄 RESET APP: Start fresh from onboarding screen
        // This ensures each test starts from the beginning
        setupTestWithReset(); 
        
        // 📱 CREATE PAGE OBJECT: Pass the driver to LoginPage
        // This is how Page Object Model works - test creates page, page uses driver
        loginPage = new LoginPage(getDriver()); 
    }

    /**
     * 🧪 Test Case 1: Successful Login Flow
     * 
     * 📚 WHAT THIS TEST DOES:
     * - Tests the "happy path" - when everything works correctly
     * - Uses valid email and password
     * - Expects to reach the home page successfully
     * - Validates that "Activity Streak" text appears
     * 
     * 🎯 FOR NEW TESTERS:
     * - This is a "positive test case" - testing normal functionality
     * - Priority 1 means it runs first
     * - If this fails, there's a problem with the basic login flow
     * - The test calls loginPage.completeLoginFlow() which does all 6 steps
     */
    @Test(priority = 1, description = "Test successful login with valid credentials")
    public void testSuccessfulLogin() throws Exception {
        try {
            // 🚀 EXECUTE LOGIN: Call the complete login flow from LoginPage
            // This method contains all 6 steps: Tap to Start → Button → Advertisement → Email → Password → Validation
            loginPage.completeLoginFlow();
            
            // 📊 RECORD SUCCESS: Add this test result to the report
            utils.TestReport.addTestResult(
                "Successful Login",           // Test name
                "PASSED",                     // Status
                "User should reach home page with 'Activity Streak' text",  // Expected result
                "Home page displayed with Activity Streak",                // Actual result
                null                          // No error message
            );
            
        } catch (Exception e) {
            // 📊 RECORD FAILURE: Add this test result to the report
            utils.TestReport.addTestResult(
                "Successful Login",           // Test name
                "FAILED",                     // Status
                "User should reach home page with 'Activity Streak' text",  // Expected result
                "Login failed",               // Actual result
                e.getMessage()                // Error message
            );
            
            throw e; // Re-throw to mark test as failed
        }
    }

    /**
     * 🧪 Test Case 2: Login with Invalid Email
     * 
     * 📚 WHAT THIS TEST DOES:
     * - Tests error handling when user enters invalid email
     * - Uses wrong email but correct password
     * - Expects to see an error message
     * - Validates that the app handles invalid email gracefully
     * 
     * 🎯 FOR NEW TESTERS:
     * - This is a "negative test case" - testing what happens when things go wrong
     * - Priority 2 means it runs after the successful login test
     * - It ensures the app shows proper error messages
     * - This is important for user experience and security
     */
    @Test(priority = 2, description = "Test login with invalid email address")
    public void testLoginWithInvalidEmail() throws Exception {
        try {
            // 🚀 EXECUTE LOGIN WITH INVALID EMAIL: Call the invalid email flow
            // This method does the same 6 steps but uses "invalid@email.com"
            loginPage.completeLoginFlowWithInvalidEmail();
            
            // 📊 RECORD SUCCESS: Test passed - error message was shown correctly
            utils.TestReport.addTestResult(
                "Invalid Email Login",        // Test name
                "PASSED",                     // Status
                "App should show error message for invalid email",  // Expected result
                "Error message displayed correctly",              // Actual result
                null                          // No error message
            );
            
        } catch (Exception e) {
            // 📊 RECORD FAILURE: Test failed - error message was not shown
            utils.TestReport.addTestResult(
                "Invalid Email Login",        // Test name
                "FAILED",                     // Status
                "App should show error message for invalid email",  // Expected result
                "Error message not displayed",                     // Actual result
                e.getMessage()                // Error message
            );
            
            throw e; // Re-throw to mark test as failed
        }
    }

    /**
     * 🧪 Test Case 3: Login with Invalid Password
     * 
     * 📚 WHAT THIS TEST DOES:
     * - Tests error handling when user enters invalid password
     * - Uses correct email but wrong password
     * - Expects to see an error message
     * - Validates that the app's security works correctly
     * 
     * 🎯 FOR NEW TESTERS:
     * - Another "negative test case" - testing wrong password
     * - Priority 3 means it runs last
     * - It ensures the app doesn't allow login with wrong password
     * - This is crucial for app security
     */
    @Test(priority = 3, description = "Test login with invalid password")
    public void testLoginWithInvalidPassword() throws Exception {
        try {
            // 🚀 EXECUTE LOGIN WITH INVALID PASSWORD: Call the invalid password flow
            // This method does the same 6 steps but uses "wrongpassword"
            loginPage.completeLoginFlowWithInvalidPassword();
            
            // 📊 RECORD SUCCESS: Test passed - error message was shown correctly
            utils.TestReport.addTestResult(
                "Invalid Password Login",     // Test name
                "PASSED",                     // Status
                "App should show error message for invalid password",  // Expected result
                "Error message displayed correctly",                  // Actual result
                null                          // No error message
            );
            
        } catch (Exception e) {
            // 📊 RECORD FAILURE: Test failed - error message was not shown
            utils.TestReport.addTestResult(
                "Invalid Password Login",     // Test name
                "FAILED",                     // Status
                "App should show error message for invalid password",  // Expected result
                "Error message not displayed",                        // Actual result
                e.getMessage()                // Error message
            );
            
            throw e; // Re-throw to mark test as failed
        }
    }

    /**
     * 🧹 Cleanup - Close the driver
     * 
     * 📚 WHAT THIS METHOD DOES:
     * - Runs after each test method (@AfterMethod)
     * - Cleans up resources (closes driver, resets app state)
     * - Ensures the next test starts with a clean environment
     * 
     * 🎯 FOR NEW TESTERS:
     * - This method runs automatically after each test
     * - It's like "cleaning up the stage" after each test
     * - cleanupTest() closes the driver and resets the app
     * - This prevents tests from interfering with each other
     */
    @AfterMethod
    public void tearDown() {
        try {
            // 🧹 CLEANUP: Close driver and reset app state
            cleanupTest();
            
            // ⏱️ DYNAMIC WAIT: Wait for app to fully close before next test
            Thread.sleep(3000); // Wait 3 seconds for app to fully close
            
            // 🔄 ADDITIONAL RESET: Ensure app is completely reset
            resetAppData();
            
            // ⏱️ DYNAMIC WAIT: Wait for reset to complete
            Thread.sleep(2000); // Wait 2 seconds for reset to complete
            
        } catch (Exception e) {
            // Continue even if cleanup fails
        }
    }
    
    /**
     * 📊 Generate Report - After all tests are completed
     * 
     * 📚 WHAT THIS METHOD DOES:
     * - Runs after all test methods (@AfterSuite)
     * - Generates a comprehensive test report
     * - Creates Excel/CSV file with all test results
     * - Shows pass/fail statistics and error details
     * 
     * 🎯 FOR NEW TESTERS:
     * - This method runs only once, after all tests finish
     * - It creates a report file you can share with your team
     * - The report shows which tests passed/failed and why
     * - This is useful for tracking test results over time
     */
    @AfterSuite
    public void generateTestReport() {
        // 📊 GENERATE REPORT: Create Excel file with all test results
        utils.TestReport.generateReport();
    }
}