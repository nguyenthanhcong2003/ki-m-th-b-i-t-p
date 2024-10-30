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

public class Widthdraw {
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
        driver.get("https://www.demo.guru99.com/V4/manager/WithdrawalInput.php"); // Thay bằng URL của ứng dụng

    }

    @Test
    public void testAccountNoMustNotBeBlank() {
        WebElement accountNoInput = driver.findElement(By.name("accountno"));
        accountNoInput.clear();
        accountNoInput.sendKeys(""); // Để trống trường
        accountNoInput.sendKeys(Keys.TAB); // Nhấn Tab

        expectedOutput = "Account Number must not be blank";
        errorMessage = driver.findElement(By.id("message2")); // Cập nhật ID cho đúng
        String actualOutput = errorMessage.getText();
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void testSpecialCharactersNotAllowedInAccountNo() {
        WebElement accountNoInput = driver.findElement(By.name("accountno"));
        accountNoInput.clear();
        accountNoInput.sendKeys("@#$%^&*"); // Nhập ký tự đặc biệt
        accountNoInput.sendKeys(Keys.TAB); // Nhấn Tab

        expectedOutput = "Special characters are not allowed";
        errorMessage = driver.findElement(By.id("message2")); // Cập nhật ID cho đúng
        String actualOutput = errorMessage.getText();
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void testCharactersNotAllowedInAccountNo() {
        WebElement accountNoInput = driver.findElement(By.name("accountno"));
        accountNoInput.clear();
        accountNoInput.sendKeys("abcdefg"); // Nhập ký tự chữ cái
        accountNoInput.sendKeys(Keys.TAB); // Nhấn Tab

        expectedOutput = "Characters are not allowed";
        errorMessage = driver.findElement(By.id("message2")); // Cập nhật ID cho đúng
        String actualOutput = errorMessage.getText();
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void testAmountFieldMustNotBeBlank() {
        WebElement amountInput = driver.findElement(By.name("ammount")); // Thay bằng ID thực tế
        amountInput.clear();
        amountInput.sendKeys(""); // Để trống trường
        amountInput.sendKeys(Keys.TAB); // Nhấn Tab

        expectedOutput = "Amount field must not be blank";
        errorMessage = driver.findElement(By.id("message1")); // Cập nhật ID cho đúng
        String actualOutput = errorMessage.getText();
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void testCharactersNotAllowedInAmountField() {
        WebElement amountInput = driver.findElement(By.name("ammount")); // Thay bằng ID thực tế
        amountInput.clear();
        amountInput.sendKeys("abc123"); // Nhập ký tự chữ cái
        amountInput.sendKeys(Keys.TAB); // Nhấn Tab

        expectedOutput = "Characters are not allowed";
        errorMessage = driver.findElement(By.id("message1")); // Cập nhật ID cho đúng
        String actualOutput = errorMessage.getText();
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void testSpecialCharactersNotAllowedInAmountField() {
        WebElement amountInput = driver.findElement(By.name("ammount")); // Thay bằng ID thực tế
        amountInput.clear();
        amountInput.sendKeys("@#$%^&*"); // Nhập ký tự đặc biệt
        amountInput.sendKeys(Keys.TAB); // Nhấn Tab

        expectedOutput = "Special characters are not allowed";
        errorMessage = driver.findElement(By.id("message1")); // Cập nhật ID cho đúng
        String actualOutput = errorMessage.getText();
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void testDescriptionCannotBeBlank() {
        WebElement descriptionInput = driver.findElement(By.name("desc")); // Thay bằng ID thực tế
        descriptionInput.clear();
        descriptionInput.sendKeys(""); // Để trống trường
        descriptionInput.sendKeys(Keys.TAB); // Nhấn Tab

        expectedOutput = "Description can not be blank";
        errorMessage = driver.findElement(By.id("message17")); // Cập nhật ID cho đúng
        String actualOutput = errorMessage.getText();
        assertEquals(expectedOutput, actualOutput);
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
