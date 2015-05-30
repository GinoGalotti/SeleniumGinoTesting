package com.ginoprojects.seleniumginotesting.workinprogress.googlepacman;

/**
 * Created by GinoGalotti on 28/05/2015.
 * ginogalotti.com
 * ginogalotti@gmail.com
 */

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class PacmanSeleniumTest {

    private WebDriver webDriver;
    private WebDriverWait wait;

    public static final String MAXIMUM_WAIT_FOR_LOAD = "3000";

    @BeforeClass
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "chromedriver/chromedriver.exe");
        webDriver = new ChromeDriver();

        webDriver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
        webDriver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

    }

    @Test
    public void testMethod() {
        webDriver.get("http://www.google.com/pacman");

        PacmanPage pacmanPage = new PacmanPage(webDriver);

        pacmanPage.startGame();
        //TODO check javascript that the game has started

        //pacmanPage.moveRight();


    }


    @AfterClass
    public void restore() {
        wait.withTimeout(5l, TimeUnit.SECONDS);
        webDriver.quit();
    }


}
