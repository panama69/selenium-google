package net.mf;

import static org.junit.Assert.*;

import org.junit.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.firefox.*;
import org.openqa.selenium.ie.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.pagefactory.ByChained;
import org.openqa.selenium.*;
import com.hpe.leanft.selenium.By;
import com.hpe.leanft.selenium.ByEach;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

public class SeleniumTest  {
    private static WebDriver driver;
    //private static RemoteWebDriver driver;
    private static WebDriverWait waitfor;
    private static DesiredCapabilities capabilities;

    public SeleniumTest() {
    //Change this constructor to private if you supply your own public constructor
    }

    private static String  initBrowserCapabilities(String browser, Environment environment){

        String tmpBrowser = browser;

        switch (browser.toLowerCase()){
            case "chrome":
                capabilities = DesiredCapabilities.chrome();
                break;
            case "firefox":
                capabilities = DesiredCapabilities.firefox();
                break;
            case "ie":
                capabilities = DesiredCapabilities.internetExplorer();
                break;
            default:
                System.out.printf ("Browser %s was not found using default %s\n", System.getProperty("browser").toLowerCase(), environment.getBrowser() );
                browser = environment.getBrowser();
        }
        return tmpBrowser;
    }
    private static void populateEnvironment (Environment environment){
        if (System.getProperty("browser") != null){
            environment.setBrowser (initBrowserCapabilities(System.getProperty("browser").toLowerCase(), environment) );
        } else{
            initBrowserCapabilities(environment.getBrowser(),environment);
        }

        //private String version = "latest";
        //Browser version
        // latest is the default but check available versions in SRF
        if (System.getProperty("version") != null){
            environment.setVersion (System.getProperty("version") );
            System.out.printf("version  ###>>>>>>>>>> %s\t%s \n",environment.getVersion(), System.getProperty("version"));
        }
        System.out.printf("version >>>>>>>>>> %s\t%s \n",environment.getVersion(), System.getProperty("version"));

        //OS platform such as "Windows 10"
        if (System.getProperty("platform") != null){
            environment.setPlatform (System.getProperty("platform") );
            System.out.printf("platform###>>>>>>>>>> %s\t%s \n",environment.getPlatform(), System.getProperty("platform"));
        }
        System.out.printf("platform>>>>>>>>>> %s\t%s \n",environment.getPlatform(), System.getProperty("platform"));

        // name of the tes to show in SRF
        if (System.getProperty("testName") != null){
            environment.setPlatform (System.getProperty("testName"));
        }
        //srf client id to use
        if (System.getProperty("SRF_CLIENT_ID") != null){
            environment.setPlatform (System.getProperty("SRF_CLIENT_ID"));
        }

        //s
        if (System.getProperty("SRF_CLIENT_SECRET") != null){
            environment.setPlatform (System.getProperty("SRF_CLIENT_SECRET"));
        }

        //screen resolution to use
        //private String resolution = "800x600";
        if (System.getProperty("resolution") != null){
            environment.setPlatform (System.getProperty("resolution"));
        }

        environment.printEnvironment();
        System.out.println(System.getenv());
    }
    @BeforeClass
//    public static void setUpBeforeClass() throws Exception {
//        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
//
//        capabilities.setCapability("SRF_CLIENT_SECRET", System.getenv("SRF_CLIENT_SECRET"));
//
//        capabilities.setCapability("name","My Uploaded Selenium Example");
//
//        URL FTAAS_URL = new URL( System.getenv("SELENIUM_ADDRESS"));
//        driver = new RemoteWebDriver(FTAAS_URL, capabilities);
//
//        waitfor = new WebDriverWait(driver,20);
//        waitfor.ignoring(NoSuchElementException.class);
//    }

    public static void openBrowser() throws MalformedURLException {
        Environment environment = new Environment();

        populateEnvironment(environment);

//        capabilities.setVersion("latest");
//        capabilities.setCapability("platform", "Windows 8.1");
//        capabilities.setCapability("testName","Selenium Burst JAVA - Google");
//        capabilities.setCapability("SRF_CLIENT_ID", "t511780658_oauth2-8zlhcumOar9IdaurvPZ3@hpe.com");
//        capabilities.setCapability("SRF_CLIENT_SECRET", "Fbi8BY3XNjmjgAaek5gf");
//        capabilities.setCapability("resolution", "800x600");
        //capabilities.setVersion(environment.getVersion());

        capabilities.setVersion(environment.getVersion());
        capabilities.setCapability("platform", environment.getPlatform());
        capabilities.setCapability("resolution", environment.getResolution());
        capabilities.setCapability("testName",environment.getTestName());
        capabilities.setCapability("SRF_CLIENT_ID", environment.getSRF_CLIENT_ID());
        capabilities.setCapability("SRF_CLIENT_SECRET", environment.getSRF_CLIENT_SECRET());

        driver = new RemoteWebDriver(
                new URL(environment.getFTAAS_URL()), capabilities);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
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
        //waitfor.until(ExpectedConditions.visibilityOfElementLocated(By.visibleText("Google Search")));
        Thread.sleep(3500);

        //ByEach is an extention to the normal Selenium locators which is available through a UFT or LeanFT license or
        //it can be found on maven.org by searching for leanft-selenium-java-sdk
        //More information can be found https://github.com/MicroFocus/leanft-selenium-java-sdk or from the LeanFT help
        //https://admhelp.microfocus.com/leanft/en/14.03/HelpCenter/Content/HowTo/Sel_LeanFT4SelT.htm
        WebElement we = driver.findElement(new ByEach(
                By.name("q"),
                By.tagName("input")
        ));

//        WebElement we = driver.findElement(By.id("lst-ib"));
        we.sendKeys("StormRunner Functional");

        driver.findElement(By.visibleText("Google Search")).click();
//        driver.findElement(By.name("btnK"));

        System.out.println("After By.visibleText");
        System.out.println("Title: "+driver.getTitle());
       Assert.assertTrue(driver.getTitle().matches("^StormRunner Functional.*"));

    }
}