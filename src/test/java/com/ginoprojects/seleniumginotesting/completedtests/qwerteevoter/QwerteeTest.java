package com.ginoprojects.seleniumginotesting.completedtests.qwerteevoter;

/**
 * Created by GinoGalotti on 28/05/2015.
 * ginogalotti.com
 * ginogalotti@gmail.com
 */

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class QwerteeTest {

    //TODO User information... remember that you need to fill it!!
    public static final String USER_EMAIL = "surthlover@gmail.com";
    public static final String USER_PASSWORD = "dadapi1091";
    public static final String USER_NAME = "Surth";

    public static final String QWERTEE_URL = "https://www.qwertee.com/";
    public static final String QWERTEE_LOGOUT_URL = QWERTEE_URL + "/logout";
    public static final String PROFILE_URL_PARAMETER = "profile";
    public static final String LOGIN_URL_PARAMETER = "login";

    private QwerteePage qwerteePage;
    private WebDriver webDriver;

    @BeforeClass public void setUp() {
        initAndConfigureChromeDriver();
    }

    private void initAndConfigureChromeDriver() {
        System.setProperty("webdriver.chrome.driver", "chromedriver/chromedriver.exe");
        webDriver = new ChromeDriver();

        webDriver.manage().timeouts().pageLoadTimeout(4, TimeUnit.SECONDS);
        webDriver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
    }

    @Test() public void loginTest() {
        goHomePage();
        qwerteePage = new QwerteePage(webDriver);

        qwerteePage.goToLoginMenu();
        Assert.assertTrue(webDriver.getCurrentUrl().contains(LOGIN_URL_PARAMETER));
        qwerteePage.logIn(USER_EMAIL, USER_PASSWORD);
        waitJavaScriptsToProcess();
        qwerteePage.goToProfileMenu();
        Assert.assertTrue(webDriver.getCurrentUrl().contains(PROFILE_URL_PARAMETER));
        Assert.assertTrue(qwerteePage.checkIfUserLoggedIn(USER_NAME));
    }

    @Test public void voteForPopularTeesUnexistingPattern() {
        goHomePage();
        qwerteePage = new QwerteePage(webDriver);
        logInIfNeeded(qwerteePage);

        int previousVotedTees = qwerteePage.getAlreadyVotedTees();
        qwerteePage.goToMostPopularUpForVotePage();
        waitJavaScriptsToProcess();
        int teesVoted = qwerteePage.voteTeeWithTextOnTitleWithinTheFirsts(5, "Zelda");
        waitJavaScriptsToProcess();

        int currentVotedTees = qwerteePage.getAlreadyVotedTees();
        Assert.assertTrue(currentVotedTees - previousVotedTees == teesVoted);
    }

    @Test public void voteForPopularTeesMultipleExistingTextPatterns() {
        goHomePage();
        qwerteePage = new QwerteePage(webDriver);
        logInIfNeeded(qwerteePage);

        int previousVotedTees = qwerteePage.getAlreadyVotedTees();
        qwerteePage.goToMostPopularUpForVotePage();
        waitJavaScriptsToProcess();
        int teesVoted = qwerteePage.voteTeeWithTextOnTitleWithinTheFirsts(5, "Poke", "Pokemon");
        waitJavaScriptsToProcess();

        int currentVotedTees = qwerteePage.getAlreadyVotedTees();
        Assert.assertTrue((currentVotedTees - previousVotedTees) == teesVoted);
    }

    @Test public void unvoteTeesWithTextPattern() {
        goHomePage();
        qwerteePage = new QwerteePage(webDriver);
        logInIfNeeded(qwerteePage);

        int previousVotedTees = qwerteePage.getAlreadyVotedTees();
        qwerteePage.goToProfileMenu();
        int teesUnVoted = qwerteePage.unVoteTeesWithText("Poke", "Pokemon");
        waitJavaScriptsToProcess();

        int currentVotedTees = qwerteePage.getAlreadyVotedTees();
        Assert.assertTrue((previousVotedTees - teesUnVoted) == currentVotedTees);
    }

    @Test public void voteAndUnvoteWithTextPattern() {
        goHomePage();
        qwerteePage = new QwerteePage(webDriver);
        logInIfNeeded(qwerteePage);

        qwerteePage.goToMostPopularUpForVotePage();
        waitJavaScriptsToProcess();
        int teesVoted = qwerteePage.voteTeeWithTextOnTitleWithinTheFirsts(5, "Goku", "Ape");

        qwerteePage.goToProfileMenu();
        int teesUnVoted = qwerteePage.unVoteTeesWithText("Goku", "Ape");
        waitJavaScriptsToProcess();

        Assert.assertTrue(teesVoted == teesUnVoted);
    }

    @AfterClass public void restore() {
        doLogOut();
        webDriver.quit();
    }

    private void goHomePage() {
        webDriver.get(QWERTEE_URL);
    }

    private void logInIfNeeded(QwerteePage qwerteePage) {
        if (!qwerteePage.checkIfUserLoggedIn(USER_NAME)) {
            doLogIn(qwerteePage);
        }
    }

    private void doLogOut() {
        webDriver.get(QWERTEE_LOGOUT_URL);
    }

    private void doLogIn(QwerteePage qwerteePage) {
        qwerteePage.goToLoginMenu();
        qwerteePage.logIn(USER_EMAIL, USER_PASSWORD);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void waitJavaScriptsToProcess() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
