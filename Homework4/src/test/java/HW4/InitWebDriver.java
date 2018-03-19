package HW4;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class InitWebDriver {

    public static WebDriver initChromeDriver(){
        System.setProperty("webdriver.chrome.driver", new File(InitWebDriver.class.getResource("/chromedriver.exe").getFile()).getPath());
        WebDriver chromeDriver = new ChromeDriver();
        chromeDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        chromeDriver.manage().window().maximize();
        return chromeDriver;
    }

    public static WebDriver initFirefoxDriver(){
        System.setProperty("webdriver.gecko.driver", new File(InitWebDriver.class.getResource("/geckodriver.exe").getFile()).getPath());
        WebDriver firefoxDriver = new FirefoxDriver();
        firefoxDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        firefoxDriver.manage().window().maximize();
        return firefoxDriver;
    }

    public static WebDriver initIEDriver(){
        System.setProperty("webdriver.ie.driver", new File(InitWebDriver.class.getResource("/IEDriverServer.exe").getFile()).getPath());

        InternetExplorerOptions options = new InternetExplorerOptions().destructivelyEnsureCleanSession();
        options.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
        options.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
        options.setCapability(InternetExplorerDriver.NATIVE_EVENTS, false);

        WebDriver ieDriver = new InternetExplorerDriver(options);
        ieDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        ieDriver.manage().window().maximize();
        return ieDriver;

    }
}
