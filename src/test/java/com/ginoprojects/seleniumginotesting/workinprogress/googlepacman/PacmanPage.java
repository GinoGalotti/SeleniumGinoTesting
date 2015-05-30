package com.ginoprojects.seleniumginotesting.workinprogress.googlepacman;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Created by GinoGalotti on 28/05/2015.
 * ginogalotti.com
 * ginogalotti@gmail.com
 */
public class PacmanPage {

    WebDriver driver;

    private static final String INSERT_COIN_ID = "btnI";

    public PacmanPage(WebDriver driver) {
        this.driver = driver;
    }

    public void startGame() {
        WebElement insertCoinButton = driver.findElement(By.tagName(INSERT_COIN_ID));
//        WebElement insertCoinButton = driver.findElement(By.tagName(INSERT_COIN_ID));
        insertCoinButton.click();
    }

    public void moveRight(Keys... directions) {
        WebElement movementControls = driver.findElement(By.id("pcm-c"));
        movementControls.sendKeys(directions);
    }

}
