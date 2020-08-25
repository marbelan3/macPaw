package browserConfiguration;

import com.codeborne.selenide.Configuration;
import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.SneakyThrows;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

public class WebDriverFactory {

    private String URL = "https://alexanderbasarab2:pzzJubixfcUY3837EmsK@hub-cloud.browserstack.com/wd/hub";
    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());


    @SneakyThrows
    private WebDriver getAndroidDeviceDriver(String device, String browserType) {
        Configuration.remote = URL;
        Configuration.browserCapabilities.setCapability("device", device);
        Configuration.browserCapabilities.setCapability("os_version", "10.0");
        Configuration.browserCapabilities.setCapability("realMobile", "true");
        Configuration.browserCapabilities.setCapability("browserName", browserType);
        Configuration.browserCapabilities.setCapability("name", "Smoke tests on" + device);
        try {
            return new RemoteWebDriver(new URL(URL), Configuration.browserCapabilities);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    @SneakyThrows
    private WebDriver getIOSDeviceDriver(String device, String browserType) {
        Configuration.remote = URL;
        Configuration.browserCapabilities.setCapability("device", device);
        Configuration.browserCapabilities.setCapability("os_version", 11);
        Configuration.browserCapabilities.setCapability("realMobile", "true");
        Configuration.browserCapabilities.setCapability("browserName", browserType);
        Configuration.browserCapabilities.setCapability("name", "Smoke tests on" + device);
        try {
            return new RemoteWebDriver(new URL(URL), Configuration.browserCapabilities);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }


}
