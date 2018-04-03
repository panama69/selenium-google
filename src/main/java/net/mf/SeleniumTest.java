package net.mf;

import static org.junit.Assert.*;

import org.junit.*;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.pagefactory.ByChained;
import org.openqa.selenium.*;
import com.hpe.leanft.selenium.By;
import com.hpe.leanft.selenium.ByEach;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.util.HashMap;
import java.util.regex.Pattern;

public class SeleniumTest  {

    private static RemoteWebDriver driver;
    private static WebDriverWait waitfor;

    public SeleniumTest() {
    //Change this constructor to private if you supply your own public constructor
    }

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();

        capabilities.setCapability("SRF_CLIENT_SECRET", System.getenv("SRF_CLIENT_SECRET"));

        capabilities.setCapability("name","My Uploaded Selenium Example");

        URL FTAAS_URL = new URL( System.getenv("SELENIUM_ADDRESS"));
        driver = new RemoteWebDriver(FTAAS_URL, capabilities);

        waitfor = new WebDriverWait(driver,20);
        waitfor.ignoring(NoSuchElementException.class);
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
    }

    @Test
    public void srfSearch() throws Exception {
        driver.get("http://www.google.com");
        waitfor.until(ExpectedConditions.visibilityOfElementLocated(By.visibleText("Google Search")));

        //ByEach is an extention to the normal Selenium locators which is available through a UFT or LeanFT license or
        //it can be found on maven.org by searching for leanft-selenium-java-sdk
        //More information can be found https://github.com/MicroFocus/leanft-selenium-java-sdk or from the LeanFT help
        //https://admhelp.microfocus.com/leanft/en/14.03/HelpCenter/Content/HowTo/Sel_LeanFT4SelT.htm
        WebElement we = driver.findElement(new ByEach(
                By.name("q"),
                By.tagName("input")
        ));
        we.sendKeys("StormRunner Functional");
        we.submit();

        driver.findElement(By.visibleText("Google Search")).click();

        System.out.println("After By.visibleText");
        System.out.println("Title: "+driver.getTitle());
        Assert.assertTrue(driver.getTitle().matches("^StormRunner Functional.*"));

    }
}