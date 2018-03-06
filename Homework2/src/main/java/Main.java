import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Created by Natalya on 3/4/2018.
 */
public class Main {
    public static void main (String[] args) {
    WebDriver driver = initChromedriver();

    driver.get("http://prestashop-automation.qatestlab.com.ua/admin147ajyvk0/");
        WebElement fieldSearch = driver.findElement(By.id("email"));
        fieldSearch.sendKeys("webinar.test@gmail.com");

        WebElement fieldSearch2 = driver.findElement(By.id("passwd"));
        fieldSearch2.sendKeys("Xcg7299bnSmMuRLp9ITw");

        WebElement button = driver.findElement(By.name("submitLogin"));
        button.click();

        sleepSecond(1000);

        WebElement icon = driver.findElement(By.id("employee_infos"));
        icon.click();

        WebElement logout = driver.findElement(By.id("header_logout"));
        logout.click();

        sleepSecond(1000);

        driver.quit();

    }

    public static WebDriver initChromedriver() {
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/drivers/chromedriver.exe");
        return new ChromeDriver();
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
