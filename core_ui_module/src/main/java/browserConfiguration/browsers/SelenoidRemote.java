package browserConfiguration.browsers;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverProvider;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URI;

public class SelenoidRemote implements WebDriverProvider {


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
        return options;
    }

    @Override
    public WebDriver createDriver(DesiredCapabilities desiredCapabilities) {
        Configuration.browserCapabilities.setCapability("enableVNC", true);
        Configuration.browserCapabilities.setCapability("enableVideo", true);
        Configuration.browserCapabilities.setCapability("videoFrameRate", 24);

        Configuration.browserCapabilities.setCapability("build", "BuildForChrome");
        Configuration.browserCapabilities.setCapability("project", "TEST");
        Configuration.browserCapabilities.setCapability("os", "Windows");
        Configuration.browserCapabilities.setCapability("os_version", "10");
        Configuration.browserCapabilities.setBrowserName(DesiredCapabilities.chrome().getBrowserName());
        Configuration.browserCapabilities.setCapability("resolution", "1920x1080");
        Configuration.browserVersion = "81.0";
        Configuration.browserCapabilities.setCapability("name", "Smoke tests");
        Configuration.browserCapabilities.setCapability(ChromeOptions.CAPABILITY, getChromeOptions());
        Configuration.browser = DesiredCapabilities.chrome().getBrowserName();
        try {
            RemoteWebDriver driver = new RemoteWebDriver(URI.create("http://194.44.160.2:4444/wd/hub/").toURL(), Configuration.browserCapabilities);
            return driver;
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}
