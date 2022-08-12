package com.nashtech.assetmanagement.pages;

import com.nashtech.assetmanagement.driver.Browser;
import com.nashtech.assetmanagement.pages.locators.CommonLocators;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.nashtech.assetmanagement.utils.Pair;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class BasePage {

    private static final int SELENIUM_TIMEOUT_SECONDS = Integer.parseInt(System.getProperty("TIMEOUT_IN_SECOND"));
    private static final Logger LOGGER = LogManager.getLogger();

    public static void navigate(String url) {
        LOGGER.info("Navigate to page {} ",  url);
        do try {
            Browser.getDriver().get(System.getProperty("BASE_URL") + url);
            break;
        } catch (WebDriverException ignored) {
            LOGGER.warn("WebDriverException");
        }
        while (true);
    }

    public static void goToUrl(String url) {
        LOGGER.info("Navigate to page {} ",  url);
        do try {
            Browser.getDriver().get(url);
            break;
        } catch (WebDriverException ignored) {
            LOGGER.warn("WebDriverException");
        }
        while (true);
    }

    public static void quit() {
        Browser.getDriver().quit();
        Browser.remove();
    }

    //  ------------------- Query Element -----------------------------
    public static WebElement findElement(By locator) {
        return Browser.getDriver().findElement(locator);
    }

    public static List<WebElement> findElements(By locator) {
        return Browser.getDriver().findElements(locator);
    }

    public static By convertToBy(String locatorType, String locatorValue) {
        switch (locatorType.toLowerCase()) {
            case "id":
                return By.id(locatorValue);
            case "name":
                return By.name(locatorValue);
            case "className":
                return By.className(locatorValue);
            case "tagName":
                return By.tagName(locatorValue);
            case "linkText":
                return By.linkText(locatorValue);
            case "partialLinkText":
                return By.partialLinkText(locatorValue);
            case "cssSelector":
                return By.cssSelector(locatorValue);
            default:
                return By.xpath(locatorValue);
        }
    }

    public static By getByLocator(Pair<String, String> elementPattern, String... elementLocatorValues) {
//
        String locatorValue = String.format(elementPattern.getValue(), elementLocatorValues);
        return convertToBy(elementPattern.getKey(),locatorValue);
    }
    //  ------------------- Wait Element -----------------------------

    public static WebElement waitForVisibilityOfElementLocated(By locator) {
        WebDriverWait wait = new WebDriverWait(Browser.getDriver(), Duration.ofSeconds(SELENIUM_TIMEOUT_SECONDS));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
    public static WebElement waitForPresentOfElementLocated(By locator) {
        WebDriverWait wait = new WebDriverWait(Browser.getDriver(), Duration.ofSeconds(SELENIUM_TIMEOUT_SECONDS));
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public static List<WebElement> waitForVisibilityOfAllElementsLocatedBy(By locator) {
        WebDriverWait wait = new WebDriverWait(Browser.getDriver(), Duration.ofSeconds(SELENIUM_TIMEOUT_SECONDS));
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
    }

    public static void waitForInvisibilityOfElementLocated(By locator) {
        WebDriverWait wait = new WebDriverWait(Browser.getDriver(), Duration.ofSeconds(SELENIUM_TIMEOUT_SECONDS));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }
    public static void waitForStalenessOfElementLocated(WebElement element) {
        WebDriverWait wait = new WebDriverWait(Browser.getDriver(), Duration.ofSeconds(SELENIUM_TIMEOUT_SECONDS));
        wait.until(ExpectedConditions.stalenessOf(element));
    }

    public static WebElement waitForElementToBeClickable(By locator) {
        WebDriverWait wait = new WebDriverWait(Browser.getDriver(), Duration.ofSeconds(SELENIUM_TIMEOUT_SECONDS));
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public static void waitForTextToBePresentInElementLocated(By locator, String text) {
        WebDriverWait wait = new WebDriverWait(Browser.getDriver(), Duration.ofSeconds(SELENIUM_TIMEOUT_SECONDS));
        wait.until(ExpectedConditions.textToBePresentInElementLocated(locator, text));
    }

    public static void waitForTextBePresentInElementValue(By locator, String text) {
        WebDriverWait wait = new WebDriverWait(Browser.getDriver(), Duration.ofSeconds(SELENIUM_TIMEOUT_SECONDS));
        wait.until(ExpectedConditions.textToBePresentInElementValue(locator, text));
    }


    public static boolean isElementDisplayed(By locator) {
        try {
            waitForVisibilityOfElementLocated(locator);
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    //  ------------------- Interact with  Element -----------------------------
    //  Write Text
    public static void inputText(By locator, String value) {
        WebElement element = waitForVisibilityOfElementLocated(locator);
        element.sendKeys(value);
    }

    public static void uploadFile(By locator, String value) {
        WebElement element = Browser.getDriver().findElement(locator);
        element.sendKeys(value);

    }

    public static void inputTextAndTab(By locator, String value) {
        WebElement element = waitForVisibilityOfElementLocated(locator);
        element.sendKeys(value);
        element.sendKeys(Keys.TAB);
    }

    public static void clickElement(By locator) {
        WebElement element = waitForElementToBeClickable(locator);
        element.click();
    }

    public static void clickElement(By locator, Boolean moveToElement) {
        WebElement element = waitForElementToBeClickable(locator);
        if (moveToElement == Boolean.TRUE) {
            Actions actionChains = new Actions(Browser.getDriver());
            actionChains.moveToElement(element).perform();
        }
        element.click();
    }

    public static void clickButtonByText(String text) {
        By locator = getByLocator(CommonLocators.BUTTON_BY_TEXT, text);
        clickElement(locator);
    }

    public static void SelectAllAndType(By locator, String value) {
        WebElement element = waitForElementToBeClickable(locator);
        element.sendKeys(Keys.chord(Keys.CONTROL,"a"), value);
        element.sendKeys(Keys.HOME);
    }


    public static void clearAndType(By locator, String value, Boolean moveToElement) {
        WebElement element = waitForVisibilityOfElementLocated(locator);
        if (moveToElement == Boolean.TRUE) {
            Actions actionChains = new Actions(Browser.getDriver());
            actionChains.moveToElement(element).perform();
        }
        element.click();
        element.clear();
        element.sendKeys(value);
    }

    public static String getText(By locator) {
        WebElement element = waitForVisibilityOfElementLocated(locator);
        return element.getText();
    }
    public static String getAttribute(By locator) {
        WebElement element = waitForVisibilityOfElementLocated(locator);
        return element.getAttribute("disabled");
    }
    public static String getAttribute(By locator, String text) {
        return findElement(locator).getAttribute(text);
    }
    public static ArrayList<String> getTextOfElements(By locator, Boolean moveToElement) {
        List<WebElement> elements = findElements(locator);
        ArrayList<String> result = new ArrayList<>();
        for (WebElement element : elements) {
            if (moveToElement == Boolean.TRUE) {
                Actions actionChains = new Actions(Browser.getDriver());
                actionChains.moveToElement(element).perform();
            }
            result.add(element.getText());
        }
        return result;
    }


//    public static By getLocator(String jsonObjLocator, String jsonPath) {
//
//        String locatorType = JsonPath.parse(jsonObjLocator).read(jsonPath.concat(".type"), String.class);
//        String locatorValue = JsonPath.parse(jsonObjLocator).read(jsonPath.concat(".value"), String.class);
//        return convertToBy(locatorType, locatorValue);
//
//    }

    public static void scrollToPageBottom(){
        JavascriptExecutor js = (JavascriptExecutor) Browser.getDriver();
        js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
    }

    public void selectElementValue(By locator, String value){
        Select elementSelect = new Select(waitForElementToBeClickable(locator));
        elementSelect.selectByVisibleText(value);
    }
    public void clickAtElementByLocation(By locator, int x, int y) {
        WebElement element = waitForElementToBeClickable(locator);
        Actions actionChains = new Actions(Browser.getDriver());
        actionChains.moveToElement(element, x, y);
        actionChains.click(element).build().perform();
    }

}



