package utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 📝 Simple Test Logger using SLF4J
 * 
 * 📚 WHAT THIS CLASS DOES:
 * - Provides logging functionality for test execution
 * - Uses SLF4J (Simple Logging Facade for Java) framework
 * - Creates two types of logs: general logs and step-by-step logs
 * - Writes logs to both console and files for debugging
 * 
 * 🎯 FOR NEW TESTERS:
 * - This class helps you track what happens during test execution
 * - It creates log files that show exactly what each step did
 * - You can use these logs to debug when tests fail
 * - It's like a "flight recorder" for your tests
 * - You don't need to understand SLF4J, just know it works
 */
public class TestLogger {

    // 📝 LOGGER INSTANCES: Create two different loggers
    // logger: General test information (start, end, errors)
    // stepLogger: Step-by-step execution details (what each step did)
    private static final Logger logger = LoggerFactory.getLogger(TestLogger.class);
    private static final Logger stepLogger = LoggerFactory.getLogger("TEST_STEPS");

    /**
     * 🚀 Start New Test - Initialize logging for a new test
     * 
     * 📚 WHAT THIS METHOD DOES:
     * - Marks the beginning of a new test
     * - Logs the test name for identification
     * - Prepares the logging system for the test
     * 
     * 🎯 FOR NEW TESTERS:
     * - Call this method at the start of each test
     * - It creates a "header" in the log files
     * - Helps you identify which test the logs belong to
     * - Example: TestLogger.startTest("Successful Login");
     */
    public static void startTest(String testName) {
        stepLogger.info("=== STARTING TEST: {} ===", testName);
        logger.info("Starting test execution: {}", testName);
    }

    /**
     * 📝 Log Step - Record each step with its result
     * 
     * 📚 WHAT THIS METHOD DOES:
     * - Records each step of the test execution
     * - Shows whether the step succeeded or failed
     * - Includes details about what happened
     * - Creates step-by-step logs for debugging
     * 
     * 🎯 FOR NEW TESTERS:
     * - Call this method after each step in your test
     * - It shows exactly what each step did
     * - Helps you find where tests fail
     * - Example: TestLogger.logStep("1/6", "Click button", "SUCCESS", "Button found and clicked");
     * 
     * @param stepNumber: Which step (e.g., "1/6", "2/6")
     * @param action: What the step did (e.g., "Click button", "Enter email")
     * @param result: Whether it succeeded ("SUCCESS") or failed ("FAILED")
     * @param logs: Additional details about what happened
     */
    public static void logStep(String stepNumber, String action, String result, String logs) {
        if ("SUCCESS".equals(result)) {
            // ✅ SUCCESS: Log successful steps with green checkmark
            stepLogger.info("✅ STEP {}: {} - {}", stepNumber, action, result);
            if (logs != null && !logs.isEmpty()) {
                stepLogger.info("   📋 Details: {}", logs);
            }
        } else {
            // ❌ FAILURE: Log failed steps with red X
            stepLogger.error("❌ STEP {}: {} - {}", stepNumber, action, result);
            if (logs != null && !logs.isEmpty()) {
                stepLogger.error("   🔍 Error Details: {}", logs);
            }
        }
    }

    /**
     * 📝 Log Info - General information
     * 
     * 📚 WHAT THIS METHOD DOES:
     * - Logs general information about the test
     * - Used for important messages that aren't steps
     * - Shows with blue info icon
     * 
     * 🎯 FOR NEW TESTERS:
     * - Use this for important messages
     * - Example: TestLogger.logInfo("Login Flow Completed Successfully!");
     */
    public static void logInfo(String message) {
        logger.info("ℹ️ {}", message);
    }

    /**
     * 📝 Log Error - Error information
     * 
     * 📚 WHAT THIS METHOD DOES:
     * - Logs error messages
     * - Used when something goes wrong
     * - Shows with red error icon
     * 
     * 🎯 FOR NEW TESTERS:
     * - Use this when something fails
     * - Example: TestLogger.logError("Step 1 failed: " + e.getMessage());
     */
    public static void logError(String message) {
        logger.error("❌ {}", message);
    }

    /**
     * 📝 Log Warning - Warning information
     * 
     * 📚 WHAT THIS METHOD DOES:
     * - Logs warning messages
     * - Used for potential issues
     * - Shows with yellow warning icon
     * 
     * 🎯 FOR NEW TESTERS:
     * - Use this for warnings
     * - Example: TestLogger.logWarning("App reset failed, continuing anyway");
     */
    public static void logWarning(String message) {
        logger.warn("⚠️ {}", message);
    }

    /**
     * 📝 Log Debug - Debug information
     * 
     * 📚 WHAT THIS METHOD DOES:
     * - Logs debug messages
     * - Used for detailed debugging information
     * - Shows with magnifying glass icon
     * 
     * 🎯 FOR NEW TESTERS:
     * - Use this for detailed debugging
     * - Example: TestLogger.logDebug("Element found at coordinates: " + x + "," + y);
     */
    public static void logDebug(String message) {
        logger.debug("🔍 {}", message);
    }

    /**
     * 🏁 End Test - Finish logging for a test
     * 
     * 📚 WHAT THIS METHOD DOES:
     * - Marks the end of a test
     * - Shows the final result (PASSED/FAILED)
     * - Creates a "footer" in the log files
     * 
     * 🎯 FOR NEW TESTERS:
     * - Call this method at the end of each test
     * - It creates a "footer" in the log files
     * - Shows whether the test passed or failed
     * - Example: TestLogger.endTest("Successful Login", "PASSED");
     */
    public static void endTest(String testName, String result) {
        stepLogger.info("=== TEST COMPLETED: {} - {} ===", testName, result);
        logger.info("Test execution completed: {} - {}", testName, result);
    }
}