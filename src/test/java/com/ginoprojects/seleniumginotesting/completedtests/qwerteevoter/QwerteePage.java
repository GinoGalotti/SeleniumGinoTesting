package com.ginoprojects.seleniumginotesting.completedtests.qwerteevoter;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by GinoGalotti on 28/05/2015.
 * ginogalotti.com
 * ginogalotti@gmail.com
 */
public class QwerteePage {

    //XPATH Patterns
    public static final String LOGIN_EMAIL_XPATH = "//input[@id='users-login-email']";
    public static final String LOGIN_PASSWORD_XPATH = "//input[@id='users-login-password']";
    public static final String LOGIN_BUTTO_XPATH =
        "//form[@id='login']/input[@class='submit raleway']";
    public static final String USER_NAME_LOGGED_IN_XPATH = "//span[@class='lora']";
    public static final String SUBMENU_VOTE_XPATH = "//div[@id='main-nav']/a[@data-submenu='vote']";
    public static final String SUBMENU_VOTE_MOST_POPULAR_XPATH =
        "//ul[@class='filtersMenu']/li[@class='filterItem ']/a[text()[contains(.,'up for voting')]]";
    public static final String SUBMENU_VOTE_SELECTED_XPATH =
        "//ul[@class='filtersMenu']/li[@class='filterItem ']/a[text()[contains(.,'selected')]]";
    public static final String ROW_DESIGN_TO_VOTE_XPATH = ".//div[@class='design-list-row-start']";
    public static final String VOTE_BUTTON_OF_THE_DESIGN_XPATH =
        ".//div[@class='design-image-wrap design-image-wrap-with-vote']/div[contains(@class,'vote-button')]/a[text()[contains(.,'VOTE')]]";
    public static final String VOTED_TEES_XPATH = "//div[@class='design-item']";
    public static final String SELECT_DESIGN_IN_A_ROW_XPATH =
        ".//div[contains(@class, 'design-item')]";

    //Web element texts
    public static final String ALREADY_VOTED_TEXT = "VOTED";
    public static final String MENU_TEXT_LOGIN = "Log in";
    public static final String MENU_TEXT_PROFILE = "Profile";

    WebDriver driver;

    public QwerteePage(WebDriver driver) {
        this.driver = driver;
    }

    public void logIn(String userEmail, String password) {
        WebElement emailLoginField = driver.findElement(By.xpath(LOGIN_EMAIL_XPATH));
        emailLoginField.sendKeys(userEmail);
        WebElement passwordLoginField = driver.findElement(By.xpath(LOGIN_PASSWORD_XPATH));
        passwordLoginField.sendKeys(password);
        driver.findElement(By.xpath(LOGIN_BUTTO_XPATH)).click();
    }

    public void goToLoginMenu() {
        driver.findElement(By.partialLinkText(MENU_TEXT_LOGIN)).click();

    }

    public void goToProfileMenu() {
        driver.findElement(By.partialLinkText(MENU_TEXT_PROFILE)).click();
    }

    public void goToVotePage() {
        driver.findElement(By.xpath(SUBMENU_VOTE_XPATH)).click();
    }

    public void goToMostPopularUpForVotePage() {
        goToVotePage();
        driver.findElement(By.xpath(SUBMENU_VOTE_MOST_POPULAR_XPATH)).click();
    }

    public void goToNewDesignsSelectedVotePage() {
        goToVotePage();
        driver.findElement(By.xpath(SUBMENU_VOTE_SELECTED_XPATH)).click();
    }

    public int voteTeeWithTextOnTitleWithinTheFirsts(int rowsToCheck, String... text) {
        int teesVoted = 0;

        List<WebElement> designsInTheRows = getEveryDesignInTheRows(rowsToCheck);
        for (WebElement currentDesign : designsInTheRows) {
            if (containsTextPattern(currentDesign, text)) {
                WebElement buttonToVote = getVoteButton(currentDesign);
                if (ifNotAlreadyVoted(buttonToVote)) {
                    buttonToVote.click();
                    teesVoted++;
                }
            }
        }

        return teesVoted;
    }

    private WebElement getVoteButton(WebElement currentDesign) {
        return currentDesign.findElement(By.xpath(VOTE_BUTTON_OF_THE_DESIGN_XPATH));
    }

    private boolean containsTextPattern(WebElement currentDesign, String... patternsToCheck) {
        boolean containsOnePatternText = false;

        for (String patternText : patternsToCheck) {
            if (currentDesign.getText().toLowerCase().contains(patternText.toLowerCase()))
                containsOnePatternText = true;
        }

        return containsOnePatternText;
    }

    private boolean ifNotAlreadyVoted(WebElement buttonToVote) {
        return !buttonToVote.getText().equals(ALREADY_VOTED_TEXT);
    }

    public int getAlreadyVotedTees() {
        goToProfileMenu();
        return driver.findElements(By.xpath(VOTED_TEES_XPATH)).size();
    }

    public int unVoteTeesWithText(String... text) {
        int teesUnvoted = 0;

        List<WebElement> designsInTheRows = getEveryDesign();
        for (WebElement currentDesign : designsInTheRows) {
            if (containsTextPattern(currentDesign, text)) {
                WebElement buttonToVote = getVoteButton(currentDesign);
                buttonToVote.click();
                teesUnvoted++;
            }
        }

        return teesUnvoted;
    }

    private List<WebElement> getEveryDesign() {
        return getEveryDesignInTheRows(
            driver.findElements(By.xpath(ROW_DESIGN_TO_VOTE_XPATH)).size());
    }

    private List<WebElement> getEveryDesignInTheRows(int rows) {
        List<WebElement> allDesignsInRows = new ArrayList<WebElement>();
        List<WebElement> designRows = driver.findElements(By.xpath(ROW_DESIGN_TO_VOTE_XPATH));

        if (designRows.size() < rows)
            rows = designRows.size();

        for (int index = 0; index < rows; index++) {
            WebElement currentRowOfDesign = designRows.get(index);
            List<WebElement> designsInTheRow =
                currentRowOfDesign.findElements(By.xpath(SELECT_DESIGN_IN_A_ROW_XPATH));
            allDesignsInRows.addAll(designsInTheRow);
        }

        return allDesignsInRows;
    }

    public boolean checkIfUserLoggedIn(String profileName) {
        boolean isPresent = false;

        if (isElementPresent(By.partialLinkText(MENU_TEXT_PROFILE))) {
            goToProfileMenu();
            WebElement userNameLoggedIn = driver.findElement(By.xpath(USER_NAME_LOGGED_IN_XPATH));
            isPresent = userNameLoggedIn.getText().contains(profileName);
        }

        return isPresent;
    }

    private boolean isElementPresent(By by) {
        return driver.findElements(by).size() != 0;
    }



}
