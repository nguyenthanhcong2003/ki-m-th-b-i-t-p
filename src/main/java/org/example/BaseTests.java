package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class BaseTests {
    private WebDriver driver;

    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "resources/chromedriver.exe"); // Set path for ChromeDriver
        driver = new ChromeDriver(); // Instantiate ChromeDriver
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); // Set implicit wait
        driver.get("https://www.demo.guru99.com/V4/"); // Navigate to the URL
        driver.manage().window().maximize(); // Maximize the browser window
    }

    public void loginTest(String userId, String password) {
        WebElement userNameElement = driver.findElement(By.name("uid"));
        userNameElement.sendKeys(userId); // Enter UserID from parameter

        WebElement passwordElement = driver.findElement(By.name("password"));
        passwordElement.sendKeys(password); // Enter Password from parameter

        driver.findElement(By.name("btnLogin")).click(); // Click Login button
    }

    public void tearDown() {
        try {
            Thread.sleep(5000); // Wait for 5 seconds to observe the result
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.quit(); // Close the browser
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter UserID: ");
        String userId = scanner.nextLine();

        System.out.print("Enter Password: ");
        String password = scanner.nextLine();

        BaseTests test = new BaseTests();
        test.setUp(); // Set up the browser and navigate to the URL
        test.loginTest(userId, password); // Perform the login test
        test.tearDown(); // Close the browser
    }
}