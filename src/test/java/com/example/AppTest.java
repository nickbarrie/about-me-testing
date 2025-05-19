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

// Test
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
    public void testPageTitle() {
        driver.get("https://nickbarrie.github.io/about-me/");

        String expectedDriverTitle = "Nick Barrie | Portfolio";
        System.out.println("Driver Title expected: " + expectedDriverTitle);
        System.out.println("Driver Title is: " + driver.getTitle());
        Assert.assertEquals(expectedDriverTitle, driver.getTitle());
    }

    @Test
    public void testProfileImageDisplayed() {
        driver.get("https://nickbarrie.github.io/about-me/");
        WebElement profileImage = driver.findElement(By.cssSelector(".profile-img"));

        System.out.println("Is the profile image available: " + profileImage.isDisplayed());
        Assert.assertTrue(profileImage.isDisplayed());
    }

    @Test
    public void testSocialLinks(){
        driver.get("https://nickbarrie.github.io/about-me/");

        WebElement emailLink = driver.findElement(By.cssSelector("a[href='mailto:nbarrie2@uwo.ca']"));
        Assert.assertTrue(emailLink.isDisplayed());

        WebElement linkedinLink = driver.findElement(By.cssSelector("a[href='https://www.linkedin.com/in/nick-w-barrie/']"));
        Assert.assertTrue(linkedinLink.isDisplayed());

        WebElement githubLink = driver.findElement(By.cssSelector("a[href='https://github.com/nickbarrie']"));
        Assert.assertTrue(githubLink.isDisplayed());
    }


    @AfterClass
    public static void tearDown() {
        driver.quit();
    }
}
