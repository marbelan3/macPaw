package browser;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.junit.TextReport;
import com.codeborne.selenide.logevents.SelenideLogger;
import envirnoment.TestEnvironment;
import helpers.CustomEventListener;
import helpers.ReportWatcher;
import io.qameta.allure.selenide.AllureSelenide;
import org.apache.commons.lang3.SystemUtils;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;

import java.util.logging.Level;
import java.util.logging.Logger;

public class BaseBrowserInit {


    static {
        Logger.getGlobal().log(Level.INFO, "Initialization BaseBrowserInit class");
        Configuration.baseUrl = TestEnvironment.globalEnvValue.toString();
        Configuration.timeout = 10000;
        Configuration.screenshots = true;
        Configuration.savePageSource = true;
        Configuration.fastSetValue = true;
        Configuration.pageLoadStrategy = "normal";
        Configuration.pollingInterval = 1000;

    }

    @Rule
    public ReportWatcher customWatcher = new ReportWatcher()
            .onFailedTest(true)
            .onSucceededTest(true);

    @Rule
    public TextReport textReport = new TextReport().onSucceededTest(false);


    @BeforeClass
    public static void beforeBaseUiClass() {
        Logger.getGlobal().log(Level.INFO, SystemUtils.OS_NAME);
        Configuration.browser = ChromeClass.class.getName();
        Configuration.startMaximized = true;
        Configuration.pageLoadStrategy = "none";
        WebDriverRunner.addListener(new CustomEventListener());
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide().screenshots(false).savePageSource(false));

    }

    @AfterClass
    public static void afterBaseUIClass() {

    }


    @After
    public void afterEachTest() {
        while (WebDriverRunner.getWebDriver().getWindowHandles().size() != 1) {
            Selenide.switchTo().window(0);
            try {
                Selenide.clearBrowserCookies();
                Selenide.clearBrowserLocalStorage();
                Selenide.switchTo().window(0).close();
            } catch (Exception e) {
                Logger.getGlobal().log(Level.WARNING, "CLEARING BROWSER COOKIES AND STORAGE WAS FAILED");
            }
        }
    }

}
