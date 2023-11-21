package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

import static java.time.Duration.ofSeconds;

public class SeleniumWebsiteTests {

    private WebDriver chromeDriver;
    private static final String baseUrl = "https://www.selenium.dev/";

    @BeforeClass(alwaysRun = true)
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--start-fullscreen");
        chromeOptions.setImplicitWaitTimeout(ofSeconds(15));
        this.chromeDriver = new ChromeDriver(chromeOptions);
    }

    @BeforeMethod
    public void preconditions() {
        chromeDriver.get(baseUrl);
    }

    @Test
    public void clickOnElementTestAndFindXpath() {
        WebElement downloadTab = chromeDriver.findElement(By.linkText("Downloads"));
        downloadTab.click();
        WebElement versionHeader = chromeDriver.findElement(By.xpath("/html/body/div/main/div[1]/section/div/div/div/h1"));
        Assert.assertTrue(versionHeader.isDisplayed(), "Downloads page is not displayed");
    }

    @Test
    public void clickElementTest() {
        WebElement aboutLink = chromeDriver.findElement(By.linkText("Documentation"));
        aboutLink.click();
        String currentUrl = chromeDriver.getCurrentUrl();
        Assert.assertEquals(currentUrl, baseUrl+"documentation/", "URL after clicking 'Documentation' link is incorrect");
    }

    @Test
    public void clickAndInputTextTest() {
        WebElement searchButton = chromeDriver.findElement(By.xpath("//*[@id='docsearch']/button/span[1]/span"));
        searchButton.click();

        WebDriverWait wait = new WebDriverWait(chromeDriver, Duration.ofSeconds(10));
        WebElement searchInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("docsearch-input")));

        String searchText = "Selenium";
        searchInput.sendKeys(searchText);
        Assert.assertEquals(searchInput.getAttribute("value"), searchText, "Text input verification failed");
    }


    @Test
    public void verifyLogoDisplayedTest() {
        WebElement logoElement = chromeDriver.findElement(By.xpath("//*[@id='Layer_1']"));
        Assert.assertTrue(logoElement.isDisplayed(), "Logo is not displayed");
    }


}
