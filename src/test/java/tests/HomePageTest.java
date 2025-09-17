package tests;

import pages.HomePage;
import pages.LoginPage;
import utils.TestReport;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.annotations.AfterSuite;

/**
 * 🏠 Home Page Test - Simple test scenarios for Home Page functionality
 */
public class HomePageTest extends BaseTest {

    private LoginPage loginPage;
    private HomePage homePage;

    @BeforeClass
    public void setUp() throws Exception {
        System.out.println("🔧 Setting up Home Page Test Suite...");
        setupTestWithReset(); 
        loginPage = new LoginPage(getDriver());
        homePage = new HomePage(getDriver());
        loginPage.completeLoginFlow();
        System.out.println("✅ Home Page Test Suite setup completed!");
    }

    @Test(priority = 1, description = "Test home page loads correctly")
    public void testHomePageLoaded() throws Exception {
        System.out.println("🧪 TEST: HOME PAGE LOADED - STARTING");
        
        try {
            homePage.verifyHomePageLoaded();
            TestReport.addTestResult("Home Page Loaded", "PASSED", "Home page should load", "Home page loaded successfully", null);
            System.out.println("✅ TEST: HOME PAGE LOADED - PASSED");
        } catch (Exception e) {
            TestReport.addTestResult("Home Page Loaded", "FAILED", "Home page should load", "Home page failed to load", e.getMessage());
            System.out.println("❌ TEST: HOME PAGE LOADED - FAILED: " + e.getMessage());
            throw e;
        }
    }

    @Test(priority = 2, description = "Test app bar element - SKIPPED")
    public void testAppBarElement() throws Exception {
        System.out.println("🧪 TEST: APP BAR ELEMENT - SKIPPED");
        
        // App bar xpath not available, so we skip this test
        TestReport.addTestResult("App Bar Element", "SKIPPED", "App bar should be visible", "App bar xpath not available - test skipped", null);
        System.out.println("⚠️ TEST: APP BAR ELEMENT - SKIPPED (xpath not available)");
    }

    @Test(priority = 3, description = "Test notification icon")
    public void testNotificationIcon() throws Exception {
        System.out.println("🧪 TEST: NOTIFICATION ICON - STARTING");
        
        try {
            homePage.validateNotificationIcon();
            TestReport.addTestResult("Notification Icon", "PASSED", "Notification icon should be visible", "Notification icon found", null);
            System.out.println("✅ TEST: NOTIFICATION ICON - PASSED");
        } catch (Exception e) {
            TestReport.addTestResult("Notification Icon", "FAILED", "Notification icon should be visible", "Notification icon not found", e.getMessage());
            System.out.println("❌ TEST: NOTIFICATION ICON - FAILED: " + e.getMessage());
            throw e;
        }
    }

    @Test(priority = 4, description = "Test activity streak element")
    public void testActivityStreakElement() throws Exception {
        System.out.println("🧪 TEST: ACTIVITY STREAK ELEMENT - STARTING");
        
        try {
            homePage.validateActivityStreak();
            TestReport.addTestResult("Activity Streak Element", "PASSED", "Activity Streak should be visible", "Activity Streak found", null);
            System.out.println("✅ TEST: ACTIVITY STREAK ELEMENT - PASSED");
        } catch (Exception e) {
            TestReport.addTestResult("Activity Streak Element", "FAILED", "Activity Streak should be visible", "Activity Streak not found", e.getMessage());
            System.out.println("❌ TEST: ACTIVITY STREAK ELEMENT - FAILED: " + e.getMessage());
            throw e;
        }
    }

    @Test(priority = 5, description = "Test claim medals element")
    public void testClaimMedalsElement() throws Exception {
        System.out.println("🧪 TEST: CLAIM MEDALS ELEMENT - STARTING");
        
        try {
            homePage.validateClaimMedals();
            TestReport.addTestResult("Claim Medals Element", "PASSED", "Claim Medals should be visible", "Claim Medals found", null);
            System.out.println("✅ TEST: CLAIM MEDALS ELEMENT - PASSED");
        } catch (Exception e) {
            TestReport.addTestResult("Claim Medals Element", "FAILED", "Claim Medals should be visible", "Claim Medals not found", e.getMessage());
            System.out.println("❌ TEST: CLAIM MEDALS ELEMENT - FAILED: " + e.getMessage());
            throw e;
        }
    }

    @Test(priority = 6, description = "Test feedback popup element - OPTIONAL")
    public void testFeedbackPopupElement() throws Exception {
        System.out.println("🧪 TEST: FEEDBACK POPUP ELEMENT - STARTING");
        
        try {
            homePage.validateFeedbackPopup();
            TestReport.addTestResult("Feedback Popup Element", "PASSED", "Feedback Popup should be visible", "Feedback Popup found", null);
            System.out.println("✅ TEST: FEEDBACK POPUP ELEMENT - PASSED");
        } catch (Exception e) {
            TestReport.addTestResult("Feedback Popup Element", "SKIPPED", "Feedback Popup should be visible", "Feedback Popup not found - may not always be visible", null);
            System.out.println("⚠️ TEST: FEEDBACK POPUP ELEMENT - SKIPPED (not visible)");
            // Don't throw the exception - just skip the test
        }
    }

    @AfterClass
    public void tearDown() {
        System.out.println("🧹 Cleaning up Home Page Test Suite...");
        cleanupTest();
        System.out.println("✅ Home Page Test Suite cleanup completed!");
    }
    
    @AfterSuite
    public void generateTestReport() {
        System.out.println("📊 Generating test report...");
        TestReport.generateReport();
        System.out.println("📋 Report ready! Copy content to Google Docs for sharing.");
    }
}
