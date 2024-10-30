package org.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestLogin {
    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://www.demo.guru99.com/V4/");
        driver.manage().window().maximize();
    }

    @Test
    public void testLogin() {
        WebElement userNameElement = driver.findElement(By.name("uid"));
        userNameElement.sendKeys("mngr596534");

        WebElement passwordElement = driver.findElement(By.name("password"));
        passwordElement.sendKeys("bytEqAt");

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        driver.findElement(By.name("btnLogin")).click();

        try {
            Alert alert = driver.switchTo().alert();
            String alertText = alert.getText();
            alert.accept();
            System.out.println("Alert detected: " + alertText);
            assertTrue(false, "Login failed with alert: " + alertText);
        } catch (NoAlertPresentException e) {
            // No alert found, continue with the test
        }

        // Kiểm tra xem có đăng nhập thành công hay không
        System.out.println("Page title is: " + driver.getTitle());
        assertTrue(driver.getTitle().contains("Guru99 Bank Manager HomePage"));
    }

    @AfterEach
    public void tearDown() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.quit();
    }
}