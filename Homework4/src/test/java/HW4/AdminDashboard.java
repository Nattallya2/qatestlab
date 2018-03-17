package HW4;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AdminDashboard {
    private EventFiringWebDriver logDriver;
    private By catalog = By.id("subtab-AdminCatalog");
    private By adminCategories = By.id("subtab-AdminCategories");
    private By adminProducts = By.id("subtab-AdminProducts");

    public AdminDashboard(EventFiringWebDriver logDriver){
        this.logDriver = logDriver;
    }

    public void selectProductsTab(){
        Actions actions = new Actions(logDriver);
        WebElement catalogTabElement = logDriver.findElement(catalog);
        actions.moveToElement(catalogTabElement).build().perform();
        WebDriverWait wait = new WebDriverWait(logDriver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(adminProducts));
        catalogTabElement.findElement(adminProducts).click();
    }
}
