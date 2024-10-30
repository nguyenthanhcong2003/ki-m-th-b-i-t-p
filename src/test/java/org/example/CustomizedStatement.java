package org.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;


import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

public class CustomizedStatement {
    private  WebDriver driver;
    static WebElement errorMessage;
    static String expectedOutput;

    @BeforeEach
    public void setUp() {
        System.setProperty("webdriver.edge.driver", "resources/msedgedriver.exe");
        EdgeOptions options = new EdgeOptions();
        options.addArguments("--headless");
        driver = new EdgeDriver(options);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://www.demo.guru99.com/V4/manager/CustomisedStatementInput.php"); // Thay bằng URL của ứng dụng

    }

    @Test
    public void testAccountNoInput() {
        WebElement accountNoInput = driver.findElement(By.name("accountno"));

        // Test case T33: Account Number must not be blank
        accountNoInput.clear(); // Để trống trường
        accountNoInput.sendKeys(""); // Để trống trường
        accountNoInput.sendKeys(Keys.TAB); // Nhấn Tab
        expectedOutput = "Account Number must not be blank";
        errorMessage = driver.findElement(By.id("message2"));
        String actualOutput = errorMessage.getText();
        assertEquals(expectedOutput, actualOutput);
//        checkAssertion(expectedOutput, actualOutput, "T33");
    }

    @Test
    public void testAccountCharacters() {
        WebElement accountNoInput = driver.findElement(By.name("accountno"));
        accountNoInput.clear();
        accountNoInput.sendKeys("ABC12345"); // Nhập ký tự chữ
        accountNoInput.sendKeys(Keys.TAB);
        errorMessage = driver.findElement(By.id("message2"));
        expectedOutput = "Characters are not allowed";
        String actualOutput = errorMessage.getText();
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void testSpecialCharactersInAccountNo() {
        WebElement accountNoInput = driver.findElement(By.name("accountno"));
        accountNoInput.clear();
        accountNoInput.sendKeys("@12345!"); // Nhập ký tự đặc biệt
        accountNoInput.sendKeys(Keys.TAB);

        String expectedOutputT35 = "Special characters are not allowed";
        WebElement errorMessage = driver.findElement(By.id("message2")); // Cập nhật ID cho đúng
        String actualOutputT35 = errorMessage.getText(); // Cập nhật actualOutput mới
        assertEquals(expectedOutputT35, actualOutputT35);
    }

    @Test
    public void testSpecialCharactersInTransactionValue() {
        WebElement transactionInput = driver.findElement(By.name("amountlowerlimit")); // Thay bằng ID thực tế
        transactionInput.clear();
        transactionInput.sendKeys("@1234!"); // Nhập ký tự đặc biệt
        transactionInput.sendKeys(Keys.TAB);

        String expectedOutputT39 = "Special characters are not allowed";
        WebElement errorMessage = driver.findElement(By.id("message12")); // Cập nhật ID cho đúng
        String actualOutputT39 = errorMessage.getText(); // Cập nhật actualOutput mới
        assertEquals(expectedOutputT39, actualOutputT39);
    }

    @Test
    public void testTransactionValueNotBlank() {
        WebElement transactionInput = driver.findElement(By.name("amountlowerlimit")); // Thay bằng ID thực tế
        transactionInput.clear();
        transactionInput.sendKeys(""); // Để trống
        transactionInput.sendKeys(Keys.TAB);

        String expectedOutputT40 = "Number of Transaction must not be blank";
        WebElement errorMessage = driver.findElement(By.id("message12")); // Cập nhật ID cho đúng
        String actualOutputT40 = errorMessage.getText(); // Cập nhật actualOutput mới
        assertEquals(expectedOutputT40, actualOutputT40);
    }

    @Test
    public void testTransactionValueCharactersNotAllowed() {
        WebElement transactionInput = driver.findElement(By.name("amountlowerlimit")); // Thay bằng ID thực tế
        transactionInput.clear();
        transactionInput.sendKeys("ABC123"); // Nhập ký tự chữ
        transactionInput.sendKeys(Keys.TAB);

        String expectedOutputT41 = "Characters are not allowed";
        WebElement errorMessage = driver.findElement(By.id("message12")); // Cập nhật ID cho đúng
        String actualOutputT41 = errorMessage.getText(); // Cập nhật actualOutput mới
        assertEquals(expectedOutputT41, actualOutputT41);
    }

    @Test
    public void testFromDateNotBlank() {
        WebElement fromDateInput = driver.findElement(By.name("fdate")); // Thay bằng ID thực tế
        fromDateInput.clear(); // Để trống trường
        fromDateInput.sendKeys(Keys.TAB); // Nhấn Tab

        String expectedFromDateError = "From Date Field must not be blank";
        WebElement fromDateError = driver.findElement(By.id("message26")); // Cập nhật ID cho đúng
        String actualFromDateError = fromDateError.getText();
        assertEquals(expectedFromDateError, actualFromDateError);
    }

    @Test
    public void testToDateNotBlank() {
        WebElement toDateInput = driver.findElement(By.name("tdate")); // Thay bằng ID thực tế
        toDateInput.clear(); // Để trống trường
        toDateInput.sendKeys(Keys.TAB); // Nhấn Tab

        String expectedToDateError = "To Date Field must not be blank";
        WebElement toDateError = driver.findElement(By.id("message27")); // Cập nhật ID cho đúng
        String actualToDateError = toDateError.getText();
        assertEquals(expectedToDateError, actualToDateError);
    }

    @Test
    public void testFromDateBeforeToDate() {
        WebElement fromDateInput = driver.findElement(By.name("fdate")); // Thay bằng ID thực tế
        fromDateInput.clear();
        fromDateInput.sendKeys("10/10/2024"); // Nhập một ngày hợp lệ

        WebElement toDateInput = driver.findElement(By.name("tdate")); // Thay bằng ID thực tế
        toDateInput.clear();
        toDateInput.sendKeys("01/10/2024"); // Nhập ngày sau ngày From Date
        toDateInput.sendKeys(Keys.TAB);

        String expectedDateError = "From Date must be earlier than To Date";
        WebElement toDateError = driver.findElement(By.id("message27")); // Cập nhật ID cho đúng
        String actualDateError = toDateError.getText(); // Giả sử thông báo lỗi sẽ hiển thị ở trường To Date
        assertEquals(expectedDateError, actualDateError);
    }

    @Test
    public void testValidFromAndToDate() {
        WebElement fromDateInput = driver.findElement(By.name("fdate")); // Thay bằng ID thực tế
        fromDateInput.clear();
        fromDateInput.sendKeys("01/10/2024"); // Nhập ngày hợp lệ

        WebElement toDateInput = driver.findElement(By.name("tdate")); // Thay bằng ID thực tế
        toDateInput.clear();
        toDateInput.sendKeys("10/10/2024"); // Nhập ngày hợp lệ
        toDateInput.sendKeys(Keys.TAB);

        String expectedValidOutput = ""; // Giả sử đây là thông báo thành công
        String actualValidOutput = driver.findElement(By.id("successMessage")).getText(); // Cập nhật ID cho đúng
        assertEquals(expectedValidOutput, actualValidOutput);
    }


    private static void checkAssertion(String expectedOutput, String actualOutput, String testCase) {
        try {
            assertEquals(expectedOutput, actualOutput);
            System.out.println("Expected: " + expectedOutput);
            System.out.println("Actual: " + actualOutput);
            System.out.println("Test case " + testCase + " passed");
        } catch (AssertionError e) {
            System.out.println("Test case " + testCase + " failed: " + e.getMessage());
            System.out.println("Expected: " + expectedOutput);
            System.out.println("Actual: " + actualOutput);
        }
    }

    @AfterEach
    public void tearDown() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.quit();
    }
}
