package com.example;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.AfterClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class App {

    static WebDriver driver;
    WebDriverWait wait;

    @BeforeClass
    public static void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\WebDrivers\\chromedriver.exe");
        driver = new ChromeDriver();
    }


    @Test
    public void testLogin() {
        driver.get("https://the-internet.herokuapp.com/login");
        // your test code here
        WebElement usernameInput = driver.findElement(By.id("username"));

        usernameInput.sendKeys("TOM");

        WebElement passwordInput = driver.findElement(By.id("password"));

        passwordInput.sendKeys("PASSWORD");

        WebElement submitButton = driver.findElement(By.xpath("//button[@type='submit']"));

        submitButton.click();

        WebElement errorMessage = driver.findElement(By.id("flash"));

        System.out.println("Actual text: '" + errorMessage.getText().trim() + "'");


        Assert.assertTrue(errorMessage.getText().contains("Your username is invalid!"));

    }

    @AfterClass
    public static void tearDown() {
        driver.quit();
    }
}
