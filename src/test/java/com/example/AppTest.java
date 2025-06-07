package com.example;

import org.junit.*;
import org.junit.rules.TestName;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;

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


    @Rule
    public TestName testName = new TestName();

    @Before
    public void beforeEachTest() {
        System.out.println("\n==============================");
        System.out.println("Starting test: " + testName.getMethodName());
        System.out.println("==============================");
    }

    @Test
    public void testPageTitle() {
        String url = "https://nickbarrie.github.io/about-me/";
        String expectedDriverTitle = "Nick Barrie | Portfolio";
        System.out.println("[TEST] Starting test: testPageTitle");

        try {
            driver.get(url);
            System.out.println("[INFO] Navigated to " + url);

            String actualTitle = driver.getTitle();
            System.out.println("[INFO] Page title is: \"" + actualTitle + "\"");
            System.out.println("[INFO] Expected title: \"" + expectedDriverTitle + "\"");

            Assert.assertEquals("Page title did not match expected.", expectedDriverTitle, actualTitle);
            System.out.println("[PASS] Page title matched expected value.");
        } catch (AssertionError | Exception e) {
            System.err.println("[FAIL] testPageTitle failed: " + e.getMessage());
            throw e;
        }
    }

    @Test
    public void testProfileTitle() {
        System.out.println("[TEST] Starting test: testProfileTitle");
        driver.get("https://nickbarrie.github.io/about-me/");
        System.out.println("[INFO] Navigated to https://nickbarrie.github.io/about-me/");

        ArrayList<String> titles = new ArrayList<>(Arrays.asList("QA Analyst", "Software Developer", "Certified Tester"));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        boolean matchFound = false;

        try {
            for (int i = 0; i < 5; i++) {
                WebElement profileTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("rotating-title")));
                String text = profileTitle.getText();
                System.out.println("[CHECK] Attempt " + (i + 1) + ": Current title: \"" + text + "\"");

                if (titles.contains(text)) {
                    System.out.println("[PASS] Found matching title: " + text);
                    matchFound = true;
                    break;
                }

                Thread.sleep(1000); // wait to let the title rotate
            }

            Assert.assertTrue("Expected title not found in rotating titles.", matchFound);
        } catch (Exception e) {
            System.err.println("[FAIL] Exception in testProfileTitle: " + e.getMessage());
            throw new AssertionError("Title not found or exception occurred.", e);
        }
    }



    @Test
    public void testProfileImageDisplayed() {
        System.out.println("[TEST] Starting test: testProfileImageDisplayed");
        driver.get("https://nickbarrie.github.io/about-me/");
        System.out.println("[INFO] Navigated to https://nickbarrie.github.io/about-me/");

        try {
            WebElement profileImage = driver.findElement(By.cssSelector(".profile-img"));
            System.out.println("[CHECK] Profile image is displayed: " + profileImage.isDisplayed());
            Assert.assertTrue("Profile image is not visible", profileImage.isDisplayed());
            System.out.println("[PASS] Profile image is present and visible.");
        } catch (Exception e) {
            System.err.println("[FAIL] Exception encountered during profile image test: " + e.getMessage());
            throw e;
        }
    }


    @Test
    public void testSocialLinks() {
        System.out.println("[TEST] Starting test: testSocialLinks");
        driver.get("https://nickbarrie.github.io/about-me/");
        System.out.println("[INFO] Navigated to https://nickbarrie.github.io/about-me/");

        try {
            WebElement emailLink = driver.findElement(By.cssSelector("a[href='mailto:nbarrie2@uwo.ca']"));
            System.out.println("[CHECK] Email link displayed: " + emailLink.isDisplayed());
            Assert.assertTrue("Email link is not visible", emailLink.isDisplayed());

            WebElement linkedinLink = driver.findElement(By.cssSelector("a[href='https://www.linkedin.com/in/nick-w-barrie/']"));
            System.out.println("[CHECK] LinkedIn link displayed: " + linkedinLink.isDisplayed());
            Assert.assertTrue("LinkedIn link is not visible", linkedinLink.isDisplayed());

            WebElement githubLink = driver.findElement(By.cssSelector("a[href='https://github.com/nickbarrie']"));
            System.out.println("[CHECK] GitHub link displayed: " + githubLink.isDisplayed());
            Assert.assertTrue("GitHub link is not visible", githubLink.isDisplayed());

            System.out.println("[PASS] All social links are present and visible.");
        } catch (Exception e) {
            System.err.println("[FAIL] Exception encountered during social link test: " + e.getMessage());
            throw e;
        }
    }



    @AfterClass
    public static void tearDown() {
        driver.quit();
    }
}
