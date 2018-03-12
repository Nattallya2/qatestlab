package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class Category {
    private WebDriver driver;

    private By userIcon = By.id("employee_infos");
    private By catalog = By.id("subtab-AdminCatalog");
    private By category = By.id("subtab-AdminCategories");
    private By addCat = By.className("process-icon-new");
    private By newCatName = By.id("name_1");
    private String actualNewName = "New Category Name";
    private By savenewCat = By.className("process-icon-save");
    private String catUrl;
    private String newCatUrl;

    public Category (WebDriver driver){
        this.driver = driver;
    }

    public void selectCat(){
        WebDriverWait wait = new WebDriverWait(driver,15);
        wait.until(ExpectedConditions.elementToBeClickable(catalog));

        WebElement categoryElement = driver.findElement(catalog);
        Actions actions = new Actions(driver);
        actions.moveToElement(categoryElement).build().perform();
        wait.until(ExpectedConditions.visibilityOfElementLocated(category));
        categoryElement.findElements(By.cssSelector("li")).get(1).click();
        wait.until(ExpectedConditions.elementToBeClickable(userIcon));
        String catUrl = driver.getCurrentUrl();
    }

    public void newCatAdd(){
        WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.elementToBeClickable(userIcon));

        driver.findElement(addCat).click();

        WebDriverWait wait2 = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.elementToBeClickable(userIcon));

        driver.findElement(newCatName).sendKeys(actualNewName);
        driver.findElement(savenewCat).submit();
    }

    public void isCreated(){
        WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.elementToBeClickable(userIcon));
        WebElement successAlert = driver.findElement(By.className("alert-success"));
        System.out.println(successAlert.getText().toLowerCase().contains("создано"));
        String newCatUrl = driver.getCurrentUrl();
        System.out.println(newCatUrl);
    }

    public void sortCat() {
        WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.elementToBeClickable(userIcon));
        driver.findElement(By.xpath("//*[@id=\"table-category\"]/thead/tr[1]/th[3]/span/a[1]/i")).click();
        //wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body//td[3]")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body//td[contains(text(),actualNewName)]")));

        }
    }

