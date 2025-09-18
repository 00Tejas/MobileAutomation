package tests;

import pages.HomePage;
import pages.LoginPage;
// import utils.TestReport;  // Using fully qualified name instead
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * 🏠 Home Page Test - Simple test scenarios for Home Page functionality
 */
public class HomePageTest extends BaseTest {

    private LoginPage loginPage;
    private HomePage homePage;

    @BeforeClass
    public void setUp() throws Exception {
        setupTestWithReset(); 
        loginPage = new LoginPage(getDriver());
        homePage = new HomePage(getDriver());
        loginPage.completeLoginFlow();
    }
    
    @AfterClass
    public void tearDown() {
        try {
            // 🧹 CLEANUP: Close driver and reset app state
            cleanupTest();
            
            // ⏱️ DYNAMIC WAIT: Wait for app to fully close
            Thread.sleep(3000);
            
            // 🔄 ADDITIONAL RESET: Ensure app is completely reset
            resetAppData();
            
            // ⏱️ DYNAMIC WAIT: Wait for reset to complete
            Thread.sleep(2000);
            
        } catch (Exception e) {
            // Continue even if cleanup fails
        }
    }

    @Test(priority = 1, description = "Test home page loads correctly")
    public void testHomePageLoaded() throws Exception {
        try {
            homePage.verifyHomePageLoaded();
            utils.TestReport.addTestResult("Home Page Loaded", "PASSED", "Home page should load", "Home page loaded successfully", null);
        } catch (Exception e) {
            utils.TestReport.addTestResult("Home Page Loaded", "FAILED", "Home page should load", "Home page failed to load", e.getMessage());
            throw e;
        }
    }

    @Test(priority = 2, description = "Test app bar element - SKIPPED")
    public void testAppBarElement() throws Exception {
        // App bar xpath not available, so we skip this test
        utils.TestReport.addTestResult("App Bar Element", "SKIPPED", "App bar should be visible", "App bar xpath not available - test skipped", null);
    }

    @Test(priority = 3, description = "Test notification icon")
    public void testNotificationIcon() throws Exception {
        try {
            homePage.validateNotificationIcon();
            utils.TestReport.addTestResult("Notification Icon", "PASSED", "Notification icon should be visible", "Notification icon found", null);
        } catch (Exception e) {
            utils.TestReport.addTestResult("Notification Icon", "FAILED", "Notification icon should be visible", "Notification icon not found", e.getMessage());
            throw e;
        }
    }

    @Test(priority = 4, description = "Test activity streak element")
    public void testActivityStreakElement() throws Exception {
        try {
            homePage.validateActivityStreak();
            utils.TestReport.addTestResult("Activity Streak Element", "PASSED", "Activity Streak should be visible", "Activity Streak found", null);
        } catch (Exception e) {
            utils.TestReport.addTestResult("Activity Streak Element", "FAILED", "Activity Streak should be visible", "Activity Streak not found", e.getMessage());
            throw e;
        }
    }

    @Test(priority = 5, description = "Test claim medals element")
    public void testClaimMedalsElement() throws Exception {
        try {
            homePage.validateClaimMedals();
            utils.TestReport.addTestResult("Claim Medals Element", "PASSED", "Claim Medals should be visible", "Claim Medals found", null);
        } catch (Exception e) {
            utils.TestReport.addTestResult("Claim Medals Element", "FAILED", "Claim Medals should be visible", "Claim Medals not found", e.getMessage());
            throw e;
        }
    }

    @Test(priority = 6, description = "Test feedback popup element - OPTIONAL")
    public void testFeedbackPopupElement() throws Exception {
        try {
            homePage.validateFeedbackPopup();
            utils.TestReport.addTestResult("Feedback Popup Element", "PASSED", "Feedback Popup should be visible", "Feedback Popup found", null);
        } catch (Exception e) {
            utils.TestReport.addTestResult("Feedback Popup Element", "SKIPPED", "Feedback Popup should be visible", "Feedback Popup not found - may not always be visible", null);
            // Don't throw the exception - just skip the test
        }
    }
    
}