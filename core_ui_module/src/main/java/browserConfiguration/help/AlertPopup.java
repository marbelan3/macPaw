package browserConfiguration.help;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Step;
import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.logging.Level;
import java.util.logging.Logger;

public class AlertPopup {

    @Step
    public static void openUrlWithHandleAlert(String testUrl) {
        try {
            Selenide.open(testUrl);

        } catch (UnhandledAlertException f) {
            try {
                Alert alert = WebDriverRunner.getWebDriver().switchTo().alert();
                String alertText = alert.getText();
                System.out.println("Alert data: " + alertText);
                alert.accept();

                Selenide.open(testUrl);
            } catch (NoAlertPresentException e) {
                e.printStackTrace();
            }
        }
    }


    @Step
    public static void acceptAlertIfExist() {
        boolean foundAlert = false;
        WebDriverWait wait = new WebDriverWait(WebDriverRunner.getWebDriver(), 0 /*timeout in seconds*/);
        try {
            wait.until(ExpectedConditions.alertIsPresent());
            foundAlert = true;
            Logger.getGlobal().log(Level.INFO, "Is Alert Present : " + foundAlert);
        } catch (TimeoutException eTO) {
            foundAlert = false;
            Logger.getGlobal().log(Level.INFO, "Is Alert Present : " + foundAlert);
        }
        if (foundAlert) {
            Alert alert = WebDriverRunner.getWebDriver().switchTo().alert();
            alert.accept();
            Logger.getGlobal().log(Level.INFO, "ALERT IS ACCEPTED!!!!!!!!!!!");
        }
    }


}
