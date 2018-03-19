package HW5;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class InitWebDriver {

    public static WebDriver initChromeDriver() {
        System.setProperty("webdriver.chrome.driver", new File(InitWebDriver.class.getResource("/chromedriver.exe").getFile()).getPath());
        WebDriver chromeDriver = new ChromeDriver();
        chromeDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        chromeDriver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        chromeDriver.manage().window().maximize();
        return chromeDriver;
    }

    public static WebDriver initFirefoxDriver() {
        System.setProperty(
            "webdriver.gecko.driver",
            new File(InitWebDriver.class.getResource("/geckodriver.exe").getFile()).getPath()
        );
        FirefoxOptions options = new FirefoxOptions();
        options.setCapability("marionette", true);
        WebDriver firefoxDriver = new FirefoxDriver(options);
        firefoxDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        firefoxDriver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        firefoxDriver.manage().window().maximize();
        return firefoxDriver;
    }

    public static WebDriver initIEDriver() {
        System.setProperty("webdriver.ie.driver",
                new File(InitWebDriver.class.getResource("/IEDriverServer.exe").getFile()).getPath());

        InternetExplorerOptions options = new InternetExplorerOptions().requireWindowFocus().destructivelyEnsureCleanSession();
        options.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
        options.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
        options.setCapability(InternetExplorerDriver.NATIVE_EVENTS, false);

        WebDriver ieDriver = new InternetExplorerDriver(options);
        ieDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        ieDriver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        ieDriver.manage().window().maximize();
        return ieDriver;
    }

    public static WebDriver initRemoteChrome() {
        System.setProperty("webdriver.chrome.driver",
                new File(InitWebDriver.class.getResource("/chromedriver.exe").getFile()).getPath());
        WebDriver chromeDriver = null;
        try{
            ChromeOptions options = new ChromeOptions();
            chromeDriver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), options);
        } catch (MalformedURLException e){
            e.printStackTrace();
        }
        chromeDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        chromeDriver.manage().window().maximize();
        return chromeDriver;
    }

    public static WebDriver initMobileAndroid(){
        System.setProperty("webdriver.chrome.driver",
                new File(InitWebDriver.class.getResource("/chromedriver.exe").getFile()).getPath());
        Map<String, String> mobileEmulation = new HashMap<>();
        mobileEmulation.put("deviceName", "Nexus 5");
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setExperimentalOption("mobileEmulation", mobileEmulation);
        return new ChromeDriver(chromeOptions);
    }

    public enum BrowserVersion {DESKTOP_VERSION, MOBILE_VERSION}

}