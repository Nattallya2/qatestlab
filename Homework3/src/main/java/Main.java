import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import pages.Category;
import pages.LoggingPage;

import java.io.File;

public class Main {
    public static void main (String[] args) {
        EventFiringWebDriver driver = initChromedriver();
        LoggingPage loggingPage = new LoggingPage(driver);

        loggingPage.open();
        loggingPage.fillEmailInput();
        loggingPage.fillPassInput();
        loggingPage.clickLoginBtn();

        Category category = new Category(driver);
        category.selectCategory();
        category.newCatAdd();
        category.isCreated();
        category.selectCategory();
        category.sortCategory();
        category.checkCategoryExists();
        driver.quit();
    }

    public static EventFiringWebDriver initChromedriver() {
        System.setProperty("webdriver.chrome.driver", new File(Main.class.getResource("/chromedriver.exe").getFile()).getPath());
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        EventFiringWebDriver webDriver = new EventFiringWebDriver(driver);
        webDriver.register(new EventHandler());
        return webDriver;
    }
}
