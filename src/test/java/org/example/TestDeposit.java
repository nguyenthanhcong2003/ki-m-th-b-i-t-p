package org.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestDeposit {
    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://www.demo.guru99.com/V4/"); // URL trang đăng nhập
        driver.manage().window().maximize();

        // Đăng nhập tự động
        driver.findElement(By.name("uid")).sendKeys("mngr596385"); // Điền username
        driver.findElement(By.name("password")).sendKeys("YsEgumu"); // Điền password
        driver.findElement(By.name("btnLogin")).click(); // Nhấn nút Login

        // Điều hướng đến trang Deposit sau khi đăng nhập thành công
        driver.get("https://www.demo.guru99.com/V4/manager/DepositInput.php");
    }

    @Test
    public void testAccountNoMustNotBeBlank() {
        WebElement accountNoInput = driver.findElement(By.name("accountno"));
        accountNoInput.clear();
        accountNoInput.sendKeys(""); // Để trống trường
        accountNoInput.sendKeys(Keys.TAB); // Nhấn Tab

        String expectedOutput = "Account Number must not be blank";
        WebElement errorMessage = driver.findElement(By.id("message2")); // Cập nhật ID cho đúng
        String actualOutput = errorMessage.getText();

        System.out.println("Expected: " + expectedOutput);
        System.out.println("Actual: " + actualOutput);

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void testOnlyNumbersAllowedInAccountNo() {
        WebElement accountNoInput = driver.findElement(By.name("accountno"));
        accountNoInput.clear();
        accountNoInput.sendKeys("abc123"); // Nhập ký tự chữ cái vào trường
        accountNoInput.sendKeys(Keys.TAB); // Nhấn Tab

        String expectedOutput = "Only numbers are allowed";
        WebElement errorMessage = driver.findElement(By.id("message2")); // Cập nhật ID cho đúng
        String actualOutput = errorMessage.getText();

        System.out.println("Expected: " + expectedOutput);
        System.out.println("Actual: " + actualOutput);

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void testAmountMustNotBeBlank() {
        WebElement amountInput = driver.findElement(By.name("amount"));
        amountInput.clear();
        amountInput.sendKeys(""); // Để trống trường
        amountInput.sendKeys(Keys.TAB); // Nhấn Tab

        String expectedOutput = "Amount must not be blank";
        WebElement errorMessage = driver.findElement(By.id("message2")); // Cập nhật ID cho đúng
        String actualOutput = errorMessage.getText();

        System.out.println("Expected: " + expectedOutput);
        System.out.println("Actual: " + actualOutput);

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void testOnlyNumbersAllowedInAmount() {
        WebElement amountInput = driver.findElement(By.name("amount"));
        amountInput.clear();
        amountInput.sendKeys("abc123"); // Nhập ký tự chữ cái vào trường
        amountInput.sendKeys(Keys.TAB); // Nhấn Tab

        String expectedOutput = "Only numbers are allowed";
        WebElement errorMessage = driver.findElement(By.id("message2")); // Cập nhật ID cho đúng
        String actualOutput = errorMessage.getText();

        System.out.println("Expected: " + expectedOutput);
        System.out.println("Actual: " + actualOutput);

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void testMinimumDepositAmountIs10() {
        WebElement amountInput = driver.findElement(By.name("amount"));
        amountInput.clear();
        amountInput.sendKeys("5"); // Nhập số tiền nhỏ hơn 10
        amountInput.sendKeys(Keys.TAB); // Nhấn Tab

        String expectedOutput = "Minimum deposit amount is 10";
        WebElement errorMessage = driver.findElement(By.id("message2")); // Cập nhật ID cho đúng
        String actualOutput = errorMessage.getText();

        System.out.println("Expected: " + expectedOutput);
        System.out.println("Actual: " + actualOutput);

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void testDescriptionMustNotBeBlank() {
        WebElement descriptionInput = driver.findElement(By.name("description"));
        descriptionInput.clear();
        descriptionInput.sendKeys(""); // Để trống trường
        descriptionInput.sendKeys(Keys.TAB); // Nhấn Tab

        String expectedOutput = "Description must not be blank";
        WebElement errorMessage = driver.findElement(By.id("message2")); // Cập nhật ID cho đúng
        String actualOutput = errorMessage.getText();

        System.out.println("Expected: " + expectedOutput);
        System.out.println("Actual: " + actualOutput);

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void testSubmitButtonTriggersValidation() {
        WebElement accountNoInput = driver.findElement(By.name("accountno"));
        accountNoInput.clear();
        accountNoInput.sendKeys("abc123"); // Nhập ký tự chữ cái vào trường

        WebElement amountInput = driver.findElement(By.name("amount"));
        amountInput.clear();
        amountInput.sendKeys("@#$%"); // Nhập ký tự không hợp lệ

        WebElement submitButton = driver.findElement(By.name("submit"));
        submitButton.click(); // Nhấn nút Submit

        String expectedAccountNoError = "Only numbers are allowed";
        WebElement accountNoErrorMessage = driver.findElement(By.id("message2")); // Cập nhật ID cho đúng
        String actualAccountNoError = accountNoErrorMessage.getText();

        System.out.println("Expected Account No Error: " + expectedAccountNoError);
        System.out.println("Actual Account No Error: " + actualAccountNoError);

        assertEquals(expectedAccountNoError, actualAccountNoError);

        String expectedAmountError = "Only numbers are allowed";
        WebElement amountErrorMessage = driver.findElement(By.id("message2")); // Cập nhật ID cho đúng
        String actualAmountError = amountErrorMessage.getText();

        System.out.println("Expected Amount Error: " + expectedAmountError);
        System.out.println("Actual Amount Error: " + actualAmountError);

        assertEquals(expectedAmountError, actualAmountError);
    }

    @Test
    public void testResetButtonClearsFields() {
        WebElement accountNoInput = driver.findElement(By.name("accountno"));
        accountNoInput.clear();
        accountNoInput.sendKeys("12345");

        WebElement amountInput = driver.findElement(By.name("amount"));
        amountInput.clear();
        amountInput.sendKeys("100");

        WebElement descriptionInput = driver.findElement(By.name("description"));
        descriptionInput.clear();
        descriptionInput.sendKeys("Test");

        WebElement resetButton = driver.findElement(By.name("reset"));
        resetButton.click(); // Nhấn nút Reset

        System.out.println("Account No after reset: " + accountNoInput.getAttribute("value"));
        System.out.println("Amount after reset: " + amountInput.getAttribute("value"));
        System.out.println("Description after reset: " + descriptionInput.getAttribute("value"));

        assertEquals("", accountNoInput.getAttribute("value"));
        assertEquals("", amountInput.getAttribute("value"));
        assertEquals("", descriptionInput.getAttribute("value"));
    }

    @Test
    public void testSubmitValidData() {
        WebElement accountNoInput = driver.findElement(By.name("accountno"));
        accountNoInput.clear();
        accountNoInput.sendKeys("12345");

        WebElement amountInput = driver.findElement(By.name("amount"));
        amountInput.clear();
        amountInput.sendKeys("100");

        WebElement descriptionInput = driver.findElement(By.name("description"));
        descriptionInput.clear();
        descriptionInput.sendKeys("Test");

        WebElement submitButton = driver.findElement(By.name("submit"));
        submitButton.click();

        String expectedConfirmationMessage = "Deposit transaction is processed and confirmed";
        WebElement confirmationMessage = driver.findElement(By.id("confirmationMessage")); // Cập nhật ID cho đúng
        String actualConfirmationMessage = confirmationMessage.getText();

        System.out.println("Expected Confirmation Message: " + expectedConfirmationMessage);
        System.out.println("Actual Confirmation Message: " + actualConfirmationMessage);

        assertTrue(actualConfirmationMessage.contains(expectedConfirmationMessage));
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}
