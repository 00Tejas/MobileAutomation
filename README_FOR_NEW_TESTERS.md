# 📱 Mobile Automation Framework - Complete Guide for New Testers

## 🎯 What This Framework Does

This framework automates testing of a mobile app using **Appium** and **TestNG**. It tests the login/onboarding flow with 3 different scenarios:
- ✅ **Successful Login** - Valid email and password
- ❌ **Invalid Email** - Wrong email, correct password  
- ❌ **Invalid Password** - Correct email, wrong password

## 📁 Project Structure

```
MobileAutomationFramework/
├── src/test/java/
│   ├── pages/           # Page Object Model classes
│   │   ├── BasePage.java      # Driver setup and app reset
│   │   └── LoginPage.java     # Login steps and validations
│   ├── tests/          # Test classes
│   │   ├── BaseTest.java      # Test setup and cleanup
│   │   └── LoginTest.java     # Main test cases
│   └── utils/          # Utility classes
│       ├── TestLogger.java    # SLF4J logging
│       └── TestReport.java    # Excel report generation
├── src/test/resources/
│   ├── testng.xml      # TestNG configuration
│   └── logback.xml     # Logging configuration
└── pom.xml             # Maven dependencies
```

## 🚀 How to Run Tests

### Prerequisites
1. **Android Emulator** running (Pixel_7)
2. **Appium Server** running on port 4723
3. **App installed** on emulator (com.raising.prodigy)

### Running Tests
```bash
# Run all tests
mvn test

# Run specific test
mvn test -Dtest=LoginTest#testSuccessfulLogin
```

## 📚 Understanding the Code

### 1. **BasePage.java** - The Foundation
- **What it does**: Sets up the Appium driver and handles app reset
- **Key methods**:
  - `setupDriver()` - Starts the app
  - `resetAppData()` - Resets app to onboarding state
  - `setupDriverWithReset()` - Combines both operations

### 2. **LoginPage.java** - The Test Steps
- **What it does**: Contains all the login steps and validations
- **Key methods**:
  - `completeLoginFlow()` - Successful login (6 steps)
  - `completeLoginFlowWithInvalidEmail()` - Invalid email test
  - `completeLoginFlowWithInvalidPassword()` - Invalid password test

### 3. **BaseTest.java** - Test Setup
- **What it does**: Provides setup and cleanup methods for tests
- **Key methods**:
  - `setupTestWithReset()` - Prepares fresh app state
  - `cleanupTest()` - Cleans up after test

### 4. **LoginTest.java** - The Test Cases
- **What it does**: Contains the actual test methods
- **Key methods**:
  - `testSuccessfulLogin()` - Tests successful login
  - `testLoginWithInvalidEmail()` - Tests invalid email handling
  - `testLoginWithInvalidPassword()` - Tests invalid password handling

### 5. **TestLogger.java** - Logging
- **What it does**: Creates detailed logs of test execution
- **Key methods**:
  - `startTest()` - Begin logging for a test
  - `logStep()` - Log each step with result
  - `endTest()` - Finish logging for a test

### 6. **TestReport.java** - Reporting
- **What it does**: Generates Excel reports with test results
- **Key methods**:
  - `addTestResult()` - Record test result
  - `generateReport()` - Create Excel file

## 🔍 Understanding the 6 Steps

Each login test follows these 6 steps:

1. **Step 1/6**: Click "Tap to Start" button
2. **Step 2/6**: Click button on second screen
3. **Step 3/6**: Select "Saw an advertisement" and click "Continue"
4. **Step 4/6**: Enter email and click "Sign in"
5. **Step 5/6**: Click "Login with Password"
6. **Step 6/6**: Enter password and click "Sign in"

## 📊 Understanding the Logs

### Console Output
```
🧪 TEST CASE 1: SUCCESSFUL LOGIN - STARTING
16:25:01.404 [main] INFO  TEST_STEPS - === STARTING TEST: Successful Login ===
16:25:06.924 [main] INFO  TEST_STEPS - ✅ STEP 1/6: Click 'Tap to Start' - SUCCESS
16:25:10.399 [main] INFO  TEST_STEPS - ✅ STEP 2/6: Click button on second screen - SUCCESS
...
✅ TEST CASE 1: SUCCESSFUL LOGIN - PASSED
```

### Log Files Generated
- **`step-by-step.log`** - Clean step-by-step execution logs
- **`test-execution.log`** - Comprehensive test execution logs
- **`TestReport_*.csv`** - Excel-compatible test results

## 🎯 Key Concepts for New Testers

### Page Object Model (POM)
- **Pages** contain the test steps (what to do)
- **Tests** contain the test cases (what to test)
- **Base classes** provide common functionality

### TestNG Annotations
- `@BeforeMethod` - Runs before each test
- `@AfterMethod` - Runs after each test
- `@Test` - Marks a test method
- `@AfterSuite` - Runs after all tests

### XPath Locators
- `//android.widget.Button` - Find any button
- `//*[@content-desc="Sign in"]` - Find element with specific text
- `//android.widget.EditText` - Find text input field

### Try-Catch Blocks
- Each step is wrapped in try-catch
- If step fails, error is logged and test stops
- This helps identify exactly where tests fail

## 🔧 Troubleshooting

### Common Issues
1. **App not found**: Make sure app is installed on emulator
2. **Element not found**: Check if app is in correct state
3. **Session creation failed**: Restart Appium server and emulator

### Debug Tips
1. Check the log files for detailed error information
2. Use `step-by-step.log` to see exactly which step failed
3. Check the Excel report for test results summary

## 📈 Adding New Tests

### To add a new test case:
1. Add a new method in `LoginPage.java`
2. Add a new test method in `LoginTest.java`
3. Follow the same pattern as existing tests
4. Use `TestLogger.logStep()` for each step
5. Use `TestReport.addTestResult()` to record results

### Example:
```java
@Test(priority = 4, description = "Test new scenario")
public void testNewScenario() throws Exception {
    try {
        loginPage.completeNewFlow();
        TestReport.addTestResult("New Test", "PASSED", "Expected", "Actual", null);
    } catch (Exception e) {
        TestReport.addTestResult("New Test", "FAILED", "Expected", "Actual", e.getMessage());
        throw e;
    }
}
```

## 🎉 Success Indicators

### When everything works correctly:
- ✅ All 3 tests pass
- 📊 Excel report shows 100% success rate
- 📝 Log files show all steps completed successfully
- 🏠 App reaches home page with "Activity Streak" text

### When tests fail:
- ❌ Test stops at specific step
- 📝 Log files show which step failed and why
- 📊 Excel report shows failure details
- 🔍 Error messages help identify the problem

## 💡 Tips for New Testers

1. **Start Simple**: Run one test at a time to understand the flow
2. **Check Logs**: Always check log files when tests fail
3. **Read Comments**: The code has extensive comments explaining each step
4. **Practice**: Try modifying test data to see how it affects results
5. **Ask Questions**: Don't hesitate to ask for clarification

## 🚀 Next Steps

Once you understand this framework:
1. **Add More Tests**: Create tests for other app features
2. **Improve Locators**: Make XPath locators more robust
3. **Add More Validations**: Check more elements on each screen
4. **Create More Pages**: Add pages for other app screens
5. **Enhance Reporting**: Customize the report format

---

**Remember**: This framework is designed to be simple and educational. Every line of code has comments explaining what it does. Take your time to understand each part, and don't hesitate to experiment!
