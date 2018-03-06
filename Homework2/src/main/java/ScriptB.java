import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

/**
 * Created by Natalya on 3/4/2018.
 */
public class ScriptB {
    public static void main (String[] args) {
        WebDriver driver = initChromedriver();

        driver.get("http://prestashop-automation.qatestlab.com.ua/admin147ajyvk0/");
        WebElement fieldSearch = driver.findElement(By.id("email"));
        fieldSearch.sendKeys("webinar.test@gmail.com");

        WebElement fieldSearch2 = driver.findElement(By.id("passwd"));
        fieldSearch2.sendKeys("Xcg7299bnSmMuRLp9ITw");

        WebElement button = driver.findElement(By.name("submitLogin"));
        button.click();

        String dashboardUrl = driver.getCurrentUrl();
        sleepSecond(2000);


        List<WebElement> maintabs = driver.findElements(By.className("maintab"));
        String[] tabIds = new String[maintabs.size()];

        Integer i = 0;
        for (WebElement tab : maintabs) {
            tabIds[i++]  = tab.getAttribute("id");
        }

        for (String tabId: tabIds) {
            WebElement fieldSearch3 = driver.findElement(By.id(tabId));
            fieldSearch3.click();

            String pageTitle = getPageTitle(driver);
            System.out.println(pageTitle);

            driver.navigate().refresh();

            String pageTitleAfterRefresh = getPageTitle(driver);

            System.out.println(pageTitle.equals(pageTitleAfterRefresh));

            goToMainPage(driver);
        }
        sleepSecond(1000);
        driver.quit();
    }

    private static void goToMainPage(WebDriver driver) {
        if (driver.findElements(By.id("header_logo")).size() != 0) {
            WebElement logo = driver.findElement(By.id("header_logo"));
            logo.click();
        } else {
            WebElement logo = driver.findElement(By.className("logo"));
            logo.click();
        }
    }


    public static WebDriver initChromedriver()
    {
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/drivers/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        return new ChromeDriver(options);
    }

    private static String getPageTitle(WebDriver driver)
    {
        WebElement pageTitle = driver.findElement(By.tagName("h2"));

        return pageTitle.getText();
    }

    private static void sleepSecond(int millisecond)
    {
        try {
            Thread.sleep(millisecond);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
