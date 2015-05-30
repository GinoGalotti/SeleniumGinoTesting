package com.ginoprojects.seleniumginotesting.completedtests.myweb;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

/**
 * Created by GinoGalotti on 29/05/2015.
 * ginogalotti.com
 * ginogalotti@gmail.com
 */
public class MyWebTest {

    private WebDriver webDriver;
    private WebDriverWait wait;

    public static final String MAXIMUM_WAIT_FOR_LOAD = "3000";
    public static final String GINOGALOTTI_URL = "http://ginogalotti.com";

    @BeforeClass public void setUp() {
        System.setProperty("webdriver.chrome.driver", "chromedriver/chromedriver.exe");
        webDriver = new ChromeDriver();

        webDriver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        webDriver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);


    }

    @Test public void testIfShootingPlayableScriptThroughXPath() throws InterruptedException {
        webDriver.get(GINOGALOTTI_URL);
        MyWebPage myPage = new MyWebPage(webDriver);

        myPage.goToShooterProjectByXpath();

        myPage.goToPlayableVersionByXpath();

        assertShooterPlayableVersion();
    }

    @Test public void testIfShootingPlayableScriptThroughLinkText() {
        webDriver.get(GINOGALOTTI_URL);
        MyWebPage myPage = new MyWebPage(webDriver);

        myPage.goToShooterProjectByLinkText();
        myPage.goToPlayableVersionByLinkText();

        assertShooterPlayableVersion();
    }

    @Test public void testIfRoguelikePlayableScriptThroughLinkText() {
        webDriver.get(GINOGALOTTI_URL);
        MyWebPage myPage = new MyWebPage(webDriver);

        myPage.goToRogueLikeProjectByLinkText();
        myPage.goToPlayableVersionByLinkText();

        assertRoguelikePlayableVersion();
    }


    private void assertShooterPlayableVersion() {
        //Contains the header
        Assert.assertTrue(webDriver.getPageSource().contains("Gino´s Shooting Enhancement"));

        assertPlayableVersion();
    }

    private void assertRoguelikePlayableVersion()
    {
        //Contains the header
        Assert.assertTrue(webDriver.getPageSource().contains("2DRoguelikeEnhanced"));

        assertPlayableVersion();
    }


    private void assertPlayableVersion()
    {
        //Contains the unity player
        Assert.assertTrue(webDriver.findElement(By.id("unityPlayer")) != null);
        Assert.assertTrue(webDriver.getPageSource().contains("Unity Web Player. Install now!"));
    }


    @AfterClass public void restore() {
        webDriver.quit();
    }

}
