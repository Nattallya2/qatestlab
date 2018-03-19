package HW5;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.not;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

public class ShopMainPage {
    private WebDriver driver;
    private String BaseUrl = "http://prestashop-automation.qatestlab.com.ua/";
    private By mobileMenu = By.cssSelector(".material-icons.d-inline");
    private By allProducts = By.xpath("//*[@id=\"content\"]/section/a");
    private By productTitle = By.xpath("//*[@id=\"js-product-list\"]/div[1]/article//h1");

    public ShopMainPage(WebDriver driver){
        this.driver = driver;
    }

    public void mainPage(){
        driver.get(BaseUrl);
    }

    public boolean isDesktopBrowser(){
        return new WebDriverWait(driver,5).until(not(visibilityOf(driver.findElement(mobileMenu)))).booleanValue();
    }

    public boolean isMobileBrowser(){
        return new WebDriverWait(driver,5).until(visibilityOf(driver.findElement(mobileMenu))).isDisplayed();
    }

    public boolean isCorrectBrowser(InitWebDriver.BrowserVersion browserVersion){
        if (browserVersion.equals(InitWebDriver.BrowserVersion.DESKTOP_VERSION)){
            try {
                return isDesktopBrowser();
            } catch (TimeoutException ex){
                return false;
            }
        } else {
            try {
                return isMobileBrowser();
            } catch (TimeoutException ex){
                return false;
            }
        }
    }

    public void goToAllProductsList(){
        driver.findElement(allProducts).click();
    }

    public void randomProduct(){
        List<WebElement> products = driver.findElements(productTitle);
        products.get((int) (products.size()*Math.random())).click();
    }



}
