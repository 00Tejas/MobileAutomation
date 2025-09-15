package utils;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

/**
 * 📊 Dynamic Test Report Generator
 * 
 * 📚 WHAT THIS CLASS DOES:
 * - Collects test results from all test cases
 * - Generates Excel-compatible CSV reports
 * - Shows pass/fail statistics and success rates
 * - Creates professional reports for sharing with teams
 * 
 * 🎯 FOR NEW TESTERS:
 * - This class creates reports that show test results
 * - It's like a "report card" for your tests
 * - You can share these reports with your team
 * - The reports show which tests passed/failed and why
 * - Uses simple arrays and loops (no complex concepts)
 */
public class TestReport {

    // 📊 STORAGE ARRAYS: Store test results in simple arrays
    // These arrays can handle up to 20 tests (you can increase if needed)
    private static String[] testNames = new String[20];      // Names of tests
    private static String[] testStatuses = new String[20];  // PASSED or FAILED
    private static String[] testExpecteds = new String[20]; // What we expected
    private static String[] testActuals = new String[20];   // What actually happened
    private static String[] testErrors = new String[20];    // Error messages (if any)

    // 📊 COUNTER: Keep track of how many tests we've recorded
    private static int testCount = 0;

    /**
     * ➕ Add Test Result - Simple method
     * 
     * 📚 WHAT THIS METHOD DOES:
     * - Records the result of a test case
     * - Stores all the information in arrays
     * - Increments the test counter
     * - Prints confirmation message
     * 
     * 🎯 FOR NEW TESTERS:
     * - Call this method after each test completes
     * - It records whether the test passed or failed
     * - It stores what you expected vs what actually happened
     * - Example: TestReport.addTestResult("Login Test", "PASSED", "Should login", "Login successful", null);
     * 
     * @param testName: Name of the test (e.g., "Successful Login")
     * @param status: Whether it passed ("PASSED") or failed ("FAILED")
     * @param expected: What you expected to happen
     * @param actual: What actually happened
     * @param errorMessage: Error message if the test failed (null if passed)
     */
    public static void addTestResult(String testName, String status, String expected, String actual, String errorMessage) {
        // 📝 STORE IN ARRAYS: Save all the test information
        testNames[testCount] = testName;
        testStatuses[testCount] = status;
        testExpecteds[testCount] = expected;
        testActuals[testCount] = actual;
        testErrors[testCount] = errorMessage;

        // 📊 INCREMENT COUNTER: Move to next position in arrays
        testCount++;

        // 📝 CONFIRMATION: Print that we recorded the result
        System.out.println("📊 Test Result Added: " + testName + " - " + status);
    }

    /**
     * 📄 Generate Excel Report (CSV format)
     * 
     * 📚 WHAT THIS METHOD DOES:
     * - Creates a CSV file with all test results
     * - CSV files can be opened directly in Excel
     * - Includes headers, test data, and summary statistics
     * - Shows pass/fail counts and success rate
     * 
     * 🎯 FOR NEW TESTERS:
     * - This method creates the final report file
     * - The report shows all your test results in a table
     * - You can open the CSV file in Excel to see the results
     * - It includes a summary showing how many tests passed/failed
     * - Perfect for sharing with your team or manager
     */
    public static void generateReport() {
        try {
            // 📁 CREATE FILE: Generate filename with current date
            String fileName = "TestReport_" + getCurrentDate() + ".csv";
            FileWriter writer = new FileWriter(fileName);

            // 📋 WRITE HEADERS: Create column headers for the report
            writer.write("Test Name,Status,Expected Result,Actual Result,Error Message,Timestamp\n");

            // 📊 COUNT VARIABLES: Track how many tests passed/failed
            int passed = 0;
            int failed = 0;

            // 📝 WRITE TEST DATA: Loop through all recorded tests
            for (int i = 0; i < testCount; i++) {
                // Write each test result as a row in the CSV
                writer.write("\"" + testNames[i] + "\",");           // Test name
                writer.write("\"" + testStatuses[i] + "\",");        // Status (PASSED/FAILED)
                writer.write("\"" + testExpecteds[i] + "\",");       // Expected result
                writer.write("\"" + testActuals[i] + "\",");         // Actual result
                
                // Handle error message (might be null)
                if (testErrors[i] != null && !testErrors[i].isEmpty()) {
                    writer.write("\"" + testErrors[i] + "\",");      // Error message
                } else {
                    writer.write("\"\",");                          // Empty if no error
                }
                
                writer.write("\"" + getCurrentDate() + "\"\n");      // Timestamp

                // 📊 COUNT RESULTS: Track pass/fail counts
                if (testStatuses[i].equals("PASSED")) {
                    passed++;
                } else {
                    failed++;
                }
            }

            // 📊 WRITE SUMMARY: Add summary statistics at the end
            writer.write("\n");  // Empty line for separation
            writer.write("\"SUMMARY\",\"\",\"\",\"\",\"\",\"\"\n");
            writer.write("\"Total Tests\",\"" + testCount + "\",\"\",\"\",\"\",\"\"\n");
            writer.write("\"Passed\",\"" + passed + "\",\"\",\"\",\"\",\"\"\n");
            writer.write("\"Failed\",\"" + failed + "\",\"\",\"\",\"\",\"\"\n");
            
            // 📊 CALCULATE SUCCESS RATE: Show percentage of tests that passed
            if (testCount > 0) {
                double successRate = (passed * 100.0) / testCount;
                writer.write("\"Success Rate\",\"" + successRate + "%\",\"\",\"\",\"\",\"\"\n");
            }

            // 📁 CLOSE FILE: Finish writing and close the file
            writer.close();

            // 📝 SUCCESS MESSAGE: Tell user the report was created
            System.out.println("📊 Excel Report generated: " + fileName);
            System.out.println("📋 Double-click the CSV file to open in Excel!");

        } catch (IOException e) {
            // ❌ ERROR HANDLING: If file creation fails
            System.out.println("❌ Error generating Excel report: " + e.getMessage());
        }
    }

    /**
     * 🕒 Get Current Date - Simple method
     * 
     * 📚 WHAT THIS METHOD DOES:
     * - Gets the current date and time
     * - Formats it for use in filenames
     * - Replaces spaces and colons with safe characters
     * 
     * 🎯 FOR NEW TESTERS:
     * - This method creates timestamps for reports
     * - It makes sure filenames are valid (no special characters)
     * - You don't need to call this directly, it's used internally
     */
    private static String getCurrentDate() {
        Date now = new Date();
        return now.toString().replace(" ", "_").replace(":", "-");
    }

    /**
     * 🧹 Clear Results - Reset everything
     * 
     * 📚 WHAT THIS METHOD DOES:
     * - Clears all stored test results
     * - Resets the test counter to 0
     * - Prepares for a new set of tests
     * 
     * 🎯 FOR NEW TESTERS:
     * - Use this if you want to start fresh
     * - It clears all previous test results
     * - You might use this between different test suites
     * - Example: TestReport.clearResults();
     */
    public static void clearResults() {
        // 📊 RESET COUNTER: Start counting from 0 again
        testCount = 0;
        
        // 🧹 CLEAR ARRAYS: Set all array elements to null using simple loop
        for (int i = 0; i < 20; i++) {
            testNames[i] = null;
            testStatuses[i] = null;
            testExpecteds[i] = null;
            testActuals[i] = null;
            testErrors[i] = null;
        }
        
        // 📝 CONFIRMATION: Tell user that results were cleared
        System.out.println("🧹 Test results cleared!");
    }
}