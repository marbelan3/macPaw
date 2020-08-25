package browserConfiguration.browsers;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverProvider;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Step;
import org.junit.After;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.HashMap;
import java.util.Map;

public class ChromeBrowser implements WebDriverProvider {
    private DesiredCapabilities capabilities;

    @Override
    public WebDriver createDriver(DesiredCapabilities capabilities) {
        WebDriverManager.chromedriver().version("2.44").setup();
        Configuration.browserCapabilities.setCapability("build", "BuildForChrome");
        Configuration.browserCapabilities.setCapability("project", "GlobeIn");
        Configuration.browserCapabilities.setCapability("os", "Windows");
        Configuration.browserCapabilities.setCapability("os_version", "10");
        Configuration.browserCapabilities.setBrowserName(DesiredCapabilities.chrome().getBrowserName());
        Configuration.browserCapabilities.setCapability("resolution", "1920x1080");
        Configuration.browserVersion = "81.0";
        Configuration.browserCapabilities.setCapability("name", "Smoke tests");
        Configuration.browserCapabilities.setCapability(ChromeOptions.CAPABILITY, getChromeOptions());
        capabilities.setCapability(ChromeOptions.CAPABILITY, getChromeOptions());
        capabilities.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.ACCEPT);
        return new ChromeDriver(capabilities);
    }

    @Step
    public static ChromeOptions getChromeOptions() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-gpu");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--allow-failed-policy-fetch-for-test");
        options.addArguments("--disable-browser-side-navigation");
        options.addArguments("--disable-extensions");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--incognito");
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-popup-blocking", "true");
        options.addArguments("--disable-infobars");
//        options.addArguments("--headless");
        Map<String, Object> prefs = new HashMap<String, Object>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        prefs.put("profile.default_content_setting_values.notifications", 2);
        options.setExperimentalOption("prefs", prefs);
        return options;
    }


    @After
    public void afterEachTest() {
    }
}
