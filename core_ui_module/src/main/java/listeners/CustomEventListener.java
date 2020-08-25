package listeners;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import helpers.AllureUIUtil;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.events.WebDriverEventListener;

import java.util.logging.Level;
import java.util.logging.Logger;

import static browserConfiguration.help.AlertPopup.acceptAlertIfExist;

public class CustomEventListener implements WebDriverEventListener {

    @Override
    public void beforeAlertAccept(WebDriver driver) {

    }

    @Override
    public void afterAlertAccept(WebDriver driver) {

    }

    @Override
    public void afterAlertDismiss(WebDriver driver) {

    }

    @Override
    public void beforeAlertDismiss(WebDriver driver) {

    }

    @Override
    public void beforeNavigateTo(String url, WebDriver driver) {
        acceptAlertIfExist();
    }


    @Override
    public void afterNavigateTo(String url, WebDriver driver) {
        this.setFeatureCookie(driver);
        acceptAlertIfExist();
        AllureUIUtil.saveCurrentUrl();
    }

    @Override
    public void beforeNavigateBack(WebDriver driver) {
    }

    @Override
    public void afterNavigateBack(WebDriver driver) {
    }

    @Override
    public void beforeNavigateForward(WebDriver driver) {

    }

    @Override
    public void afterNavigateForward(WebDriver driver) {

    }

    @Override
    public void beforeNavigateRefresh(WebDriver driver) {

    }

    @Override
    public void afterNavigateRefresh(WebDriver driver) {

    }

    @Override
    public void beforeFindBy(By by, WebElement element, WebDriver driver) {
    }

    @Override
    public void afterFindBy(By by, WebElement element, WebDriver driver) {

    }

    @Override
    public void beforeClickOn(WebElement element, WebDriver driver) {

    }

    @Override
    public void afterClickOn(WebElement element, WebDriver driver) {

    }

    @Override
    public void beforeChangeValueOf(WebElement element, WebDriver driver, CharSequence[] keysToSend) {

    }

    @Override
    public void afterChangeValueOf(WebElement element, WebDriver driver, CharSequence[] keysToSend) {

    }

    @Override
    public void beforeScript(String script, WebDriver driver) {

    }

    @Override
    public void afterScript(String script, WebDriver driver) {
    }

    @Override
    public void beforeSwitchToWindow(String windowName, WebDriver driver) {

    }

    @Override
    public void afterSwitchToWindow(String windowName, WebDriver driver) {

    }

    @Override
    public void onException(Throwable throwable, WebDriver driver) {

    }

    @Override
    public <X> void beforeGetScreenshotAs(OutputType<X> target) {

    }

    @Override
    public <X> void afterGetScreenshotAs(OutputType<X> target, X screenshot) {

    }

    @Override
    public void beforeGetText(WebElement element, WebDriver driver) {

    }

    @Override
    public void afterGetText(WebElement element, WebDriver driver, String text) {

    }

    private void setFeatureCookie(WebDriver driver) {
        String featureN = System.getenv("featureNumber");
        if (StringUtils.isNotBlank(featureN)) {
//            Cookie featured = new Cookie.Builder("featured", featureN).build();//.domain(domain).isSecure(true).build();
//            Selenide.sleep(200);
//            driver.manage().addCookie(featured);
            System.out.println("New cookie was set!!!!!!!!!!!!!!!!!!!!");
            Logger.getGlobal().log(Level.INFO, "FEATURED COOKIE WAS SET: " + featureN);
            Selenide.sleep(20);
        } else {
            Logger.getGlobal().log(Level.INFO, "FEATURED COOKIE WASN’T SET: " + featureN);
        }
    }

}
