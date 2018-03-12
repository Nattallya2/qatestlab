import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pages.Category;
import pages.LoggingPage;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main (String[] args) {
        WebDriver driver = initChromedriver();
        LoggingPage loggingPage = new LoggingPage(driver);

        loggingPage.open();
        loggingPage.fillEmailInput();
        loggingPage.fillPassInput();
        loggingPage.clickLoginBtn();


        Category category = new Category(driver);
        category.selectCat();
        category.newCatAdd();
        category.isCreated();
        category.selectCat();
        category.sortCat();
    }

    public static WebDriver initChromedriver() {
        System.setProperty("webdriver.chrome.driver", new File(Main.class.getResource("/chromedriver.exe").getFile()).getPath());
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        return driver;
    }
}