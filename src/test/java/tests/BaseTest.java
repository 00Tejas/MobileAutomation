package tests;

import pages.BasePage;

/**
 * 🏗️ Base Test - Simple test setup
 * 
 * 📚 WHAT THIS CLASS DOES:
 * - Provides common test setup and cleanup methods
 * - Extends BasePage to inherit driver setup functionality
 * - Contains methods that all test classes can use
 * - Handles the "before" and "after" parts of testing
 * 
 * 🎯 FOR NEW TESTERS:
 * - This is the "foundation" class that all test classes extend
 * - It provides the setup and cleanup methods you need
 * - You don't need to understand the technical details
 * - Just know that it prepares the app for testing and cleans up afterward
 */
public class BaseTest extends BasePage {
    
    /**
     * 🚀 Setup Test - Initialize driver before each test
     * 
     * 📚 WHAT THIS METHOD DOES:
     * - Sets up the Appium driver for testing
     * - Launches the app on the emulator
     * - Prepares everything needed for test execution
     * - Does NOT reset the app (keeps existing state)
     * 
     * 🎯 FOR NEW TESTERS:
     * - This method "prepares the stage" for your test
     * - It starts the app and makes it ready for testing
     * - Use this when you want to continue from where the app left off
     * - It's like "opening the app" before testing
     */
    public void setupTest() throws Exception {
        // 🚀 SETUP DRIVER: Start the app and prepare for testing
        setupDriver();
    }
    
    /**
     * 🚀 Setup Test with Reset - Initialize driver and reset app before each test
     * 
     * 📚 WHAT THIS METHOD DOES:
     * - Sets up the Appium driver for testing
     * - Completely resets the app to onboarding state
     * - Ensures each test starts fresh from the beginning
     * - Clears all user data, cache, and permissions
     * 
     * 🎯 FOR NEW TESTERS:
     * - This method "restarts the app" before your test
     * - It's like uninstalling and reinstalling the app
     * - Use this when you want a completely fresh start
     * - It ensures tests don't interfere with each other
     * - This is the method you'll use most often
     */
    public void setupTestWithReset() throws Exception {
        // 🔄 SETUP WITH RESET: Start fresh from onboarding screen
        setupDriverWithReset();
    }
    
    /**
     * 🧹 Cleanup Test - Close driver after each test
     * 
     * 📚 WHAT THIS METHOD DOES:
     * - Closes the Appium driver
     * - Stops the automation session
     * - Cleans up resources
     * - Prepares for the next test
     * 
     * 🎯 FOR NEW TESTERS:
     * - This method "cleans up the stage" after your test
     * - It closes the app and frees up resources
     * - It's like "turning off the remote control"
     * - Currently keeps the app open for debugging
     * - You can modify it to close the app if needed
     */
    public void cleanupTest() {
        // 🧹 CLEANUP: Close driver and clean up resources
        cleanup();
    }
}