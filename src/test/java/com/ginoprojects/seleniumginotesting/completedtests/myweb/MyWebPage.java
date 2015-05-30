package com.ginoprojects.seleniumginotesting.completedtests.myweb;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Created by GinoGalotti on 29/05/2015.
 * ginogalotti.com
 * ginogalotti@gmail.com
 */
public class MyWebPage {

    public static final String SHOOTER_PLAYABLE_XPATH =
        "/html/body[@class='body blockbody']/div[@class='component  stretch template']/div[@class='self']/div[@class='row'][7]/div[@class='col'][1]/div[@class='row'][2]/div[@class='component ']/div[@class='self mobile-leaf text textnormal mobile-undersized-upper']/div/p/span/a[@class='textlink link']";
    public static final String SHOOTER_PROJECT_XPATH =
        "/html/body[@class='body blockbody']/div[@class='component  stretch template']/div[@class='self']/div[@class='row'][7]/div[@class='col'][1]/div[@class='row'][1]";
    public static final String SHOOTER_PROJECT_LINK_TEXT = "Survival Shooter";
    public static final String PROJECT_PLAYABLE_LINK_TEXT = "playable version";
    WebDriver driver;


    public MyWebPage(WebDriver driver) {
        this.driver = driver;
    }

    public void goToShooterProjectByXpath() {
        WebElement shooterProjectLink = driver.findElement(By.xpath(SHOOTER_PROJECT_XPATH));
        shooterProjectLink.click();
    }

    public void goToPlayableVersionByXpath() {
        WebElement shooterPlayableLink = driver.findElement(By.xpath(SHOOTER_PLAYABLE_XPATH));
        shooterPlayableLink.click();
    }

    public void goToShooterProjectByLinkText() {
        driver.findElement(By.partialLinkText(SHOOTER_PROJECT_LINK_TEXT)).click();
    }

    public void goToRogueLikeProjectByLinkText() {
        driver.findElement(By.partialLinkText("2D Roguelike")).click();
    }

    public void goToPlayableVersionByLinkText() {
        driver.findElement(By.partialLinkText(PROJECT_PLAYABLE_LINK_TEXT)).click();
    }

}
