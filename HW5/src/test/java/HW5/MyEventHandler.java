package HW5;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverEventListener;

public class MyEventHandler implements WebDriverEventListener{
    public void beforeAlertAccept(WebDriver webDriver) {

    }

    public void afterAlertAccept(WebDriver webDriver) {

    }

    public void afterAlertDismiss(WebDriver webDriver) {

    }

    public void beforeAlertDismiss(WebDriver webDriver) {

    }

    public void beforeNavigateTo(String s, WebDriver webDriver) {


    }

    public void afterNavigateTo(String s, WebDriver webDriver) {
        System.out.println("Open url: " + s);

    }

    public void beforeNavigateBack(WebDriver webDriver) {

    }

    public void afterNavigateBack(WebDriver webDriver) {

    }

    public void beforeNavigateForward(WebDriver webDriver) {

    }

    public void afterNavigateForward(WebDriver webDriver) {

    }

    public void beforeNavigateRefresh(WebDriver webDriver) {

    }

    public void afterNavigateRefresh(WebDriver webDriver) {

    }

    public void beforeFindBy(By by, WebElement webElement, WebDriver webDriver) {
        System.out.println("Search for element: " + by.toString());

    }

    public void afterFindBy(By by, WebElement webElement, WebDriver webDriver) {
        System.out.println("   - Elemenet found successfully");

    }

    public void beforeClickOn(WebElement webElement, WebDriver webDriver) {
        System.out.println("Click on elemenet: " + webElement.getTagName() + " " + webElement.getAttribute("name") + webElement.getText());

    }

    public void afterClickOn(WebElement webElement, WebDriver webDriver) {
        System.out.println("   - Element clicked");

    }

    public void beforeChangeValueOf(WebElement webElement, WebDriver webDriver, CharSequence[] charSequences) {
        System.out.println("Fill input with valid " + webElement.getAttribute("id") + webElement.getText());

    }

    public void afterChangeValueOf(WebElement webElement, WebDriver webDriver, CharSequence[] charSequences) {
        System.out.println("  - Value successfully changed" + webElement.getAttribute("id") + webElement.getText());

    }

    public void beforeScript(String s, WebDriver webDriver) {

    }

    public void afterScript(String s, WebDriver webDriver) {

    }

    public void onException(Throwable throwable, WebDriver webDriver) {

    }
}
