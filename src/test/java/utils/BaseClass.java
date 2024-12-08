package utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import page_object_manager.PageObjectManager;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

public class BaseClass {

    public static WebDriver driver = null;
    public static WebDriverWait webDriverWait;
    public static PageObjectManager poManger;
    public static JavascriptExecutor javascriptExecutor;

    public static WebDriver launchBrowser(String browser, String headless) {

        try {
            switch (browser) {

                case "Chrome": {
                    driver = new ChromeDriver(chromeOptions(headless));
                    break;
                }

                case "Edge":{
                    driver = new EdgeDriver(edgeOptions(headless));
                    break;
                }
            }
            if (driver != null){

                driver.manage().window().maximize();
                navigateToApplication("https://www.spicejet.com/");
                webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(10));
                javascriptExecutor = (JavascriptExecutor) driver;
                poManger = new PageObjectManager(driver);
            }else{

                throw new RuntimeException(" ====== The driver = null : please check the browser name ====== ");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return driver;
    }

    static ChromeOptions chromeOptions;
    static Map<String, Integer> contentSettings = new HashMap<>();
    static Map<String, Object> profile = new HashMap<>();
    static Map<String, Object> prefs = new HashMap<>();

    private static ChromeOptions chromeOptions(String headLess) {

        chromeOptions = new ChromeOptions();

        if (headLess.equalsIgnoreCase("headless")) {

            chromeOptions.addArguments("--headless=new"); // Run Chrome in headless mode
        }

        contentSettings.put("media_stream", 1);
        contentSettings.put("geolocation", 1);
        contentSettings.put("notifications", 2);
        profile.put("managed_default_content_settings", contentSettings);
        prefs.put("profile", profile);
        chromeOptions.setExperimentalOption("prefs", prefs);

//        chromeOptions.addArguments("user-data-dir=" + System.getProperty("user.dir") + "/ChromeCache");

        return chromeOptions;
    }

    static EdgeOptions edgeOptions;
    static Map<String, Integer> edgeContentSettings = new HashMap<>();
    static Map<String, Object> edgeProfile = new HashMap<>();
    static Map<String, Object> edgePrefs = new HashMap<>();
    private static EdgeOptions edgeOptions(String headLess) {

        edgeOptions = new EdgeOptions();

        if (headLess.equalsIgnoreCase("headless")) {

            edgeOptions.addArguments("--headless=new"); // Run Chrome in headless mode
        }

        edgeContentSettings.put("media_stream", 1);
        edgeContentSettings.put("geolocation", 1);
        edgeContentSettings.put("notifications", 2);
        edgeProfile.put("managed_default_content_settings", edgeContentSettings);
        edgePrefs.put("profile", edgeProfile);
        edgeOptions.setExperimentalOption("prefs", edgePrefs);

        return edgeOptions;
    }

    public static void navigateToApplication(String url){

        try {
            driver.get(url);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void sendKeys(WebElement element, String value) {
        try {
            webDriverWait.until(ExpectedConditions.elementToBeClickable(element)).sendKeys(value);
        } catch (Exception exception) {
            System.out.println(exception.getMessage());

        }
    }

    public static void clickElement(WebElement element) {

        try {
            webDriverWait.until(ExpectedConditions.elementToBeClickable(element)).click();
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }

    public static String getCurrentPageUrl(){
        try {
            return driver.getCurrentUrl();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static void browserClose(){

        try{
            driver.quit();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public static void assertString(WebElement actual, String expected){

        if (!getText(actual).trim().equals(expected)){
            System.out.println("Expected => "+ expected + " : Actual => " + actual);
        }

    }

    static FluentWait<WebDriver> fluentWait;

    public static FluentWait<WebDriver> getFluentWait(){

        fluentWait = fluentWait == null ? new FluentWait<>(BaseClass.driver)
                .withTimeout(Duration.ofSeconds(30))   // Maximum wait time of 30 seconds
                .pollingEvery(Duration.ofSeconds(2))   // Check every 2 seconds
                .ignoring(NoSuchElementException.class) // Ignore this exception
                .ignoring(StaleElementReferenceException.class) // Ignore list of exceptions
                .ignoring(Exception.class)
                .withMessage(() -> "Element was not found in the time limit")
                : fluentWait;

        return fluentWait;
    }

    public static WebElement fluentWaitForElement(WebElement element){

        return getFluentWait().until(driver1 -> {

            try{

                if (element.isDisplayed()) return element;

            }catch (Exception exception){

                System.out.println(exception.getMessage());

            }

            return null;
        });
    }

    public static String getText(WebElement element){

        return fluentWaitForElement(element).getText();
    }

    public void assertUrl(String url){

        if (!getCurrentPageUrl().equals(url)){

            System.out.println("user present in wrong page");
        }
    }

    public static String getInputValue(WebElement element, String value) {

        try {
            return webDriverWait.until(ExpectedConditions.elementToBeClickable(element)).getAttribute(value);
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        return null;
    }

    public static void threadSleep(long sec){

        try{ Thread.sleep(sec); }catch (Exception ex){

            System.out.println(ex.getMessage());

        }
    }

    public static void clickEleByJs(WebElement element){

        try {
            javascriptExecutor.executeScript("arguments[0].click();", element);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static <anyType> String getAsString(anyType value){

        return String.valueOf(value);
    }

}
