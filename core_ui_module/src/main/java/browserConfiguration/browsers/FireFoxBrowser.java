package browserConfiguration.browsers;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverProvider;
import io.qameta.allure.Step;
import org.junit.After;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

public class FireFoxBrowser implements WebDriverProvider {

    @Override
    public WebDriver createDriver(DesiredCapabilities capabilities) {
        Configuration.browser = DesiredCapabilities.firefox().getBrowserName();
        Configuration.browserCapabilities.setCapability("build", "BuildForFireFox");
        Configuration.browserCapabilities.setCapability("project", "GlobeIn");
        Configuration.browserCapabilities.setCapability("os", "Windows");
        Configuration.browserCapabilities.setCapability("os_version", "10");
        Configuration.browserCapabilities.setCapability("resolution", "1920x1080");
        Configuration.browserCapabilities.setBrowserName(DesiredCapabilities.firefox().getBrowserName());
        Configuration.browserVersion = "78";
        Configuration.browserCapabilities.setCapability("name", "Smoke tests");
        Configuration.browserCapabilities.setCapability("browserstack.debug", "true");
        Configuration.browserCapabilities.setCapability(FirefoxOptions.FIREFOX_OPTIONS, getFirefoxOptions());
        return new FirefoxDriver(capabilities);
    }

    @Step
    public static FirefoxOptions getFirefoxOptions() {
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.addArguments("--disable-popup-blocking");
        firefoxOptions.addArguments("--allow-failed-policy-fetch-for-test");
        firefoxOptions.addArguments("--disable-browser-side-navigation");
        firefoxOptions.addArguments("--incognito");
        firefoxOptions.addArguments("--disable-notifications");
        firefoxOptions.addArguments("--width=1920");
        firefoxOptions.addArguments("--height=1080");
        firefoxOptions.addArguments("--headless");
        return firefoxOptions;
    }


    @After
    public void afterEachTest() {
    }
}
