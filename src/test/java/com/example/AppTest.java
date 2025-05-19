package com.example;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.AfterClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;


public class AppTest {

    static WebDriver driver;


    @BeforeClass
    public static void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless"); // Run in headless mode
        options.addArguments("--disable-gpu");
        options.addArguments("--no-sandbox");

        driver = new ChromeDriver(options);
    }


    @Test
    public void testLogin() {
        driver.get("https://nickbarrie.github.io/about-me/");
        // your test code here
        WebElement nameElement = driver.findElement(By.className("profile-details"));

        // Verify the text inside that element contains your name
        String actualText = nameElement.getText();
        Assert.assertTrue("Name not found on the page!", actualText.contains("Nick Barrie"));

    }

    @AfterClass
    public static void tearDown() {
        driver.quit();
    }
}
