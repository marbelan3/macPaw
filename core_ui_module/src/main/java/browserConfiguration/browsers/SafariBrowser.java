package browserConfiguration.browsers;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverProvider;
import lombok.SneakyThrows;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class SafariBrowser implements WebDriverProvider {


    @SneakyThrows
    private WebDriver getSafariDriver() {
        Configuration.browserCapabilities.setCapability("browserConfiguration", "Safari");
        Configuration.browserCapabilities.setCapability("browser_version", "12.0");
        Configuration.browserCapabilities.setCapability("os", "OS X");
        Configuration.browserCapabilities.setCapability("os_version", "Mojave");
        Configuration.browserCapabilities.setCapability("resolution", "1920x1080");
        Configuration.browserCapabilities.setCapability("build", "BuildForSafari");
        Configuration.browserCapabilities.setCapability("project", "GlobeIn");
        Configuration.browserCapabilities.setCapability("name", "Smoke tests");
        try {
            return new RemoteWebDriver(new URL("URL"), Configuration.browserCapabilities);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public WebDriver createDriver(DesiredCapabilities desiredCapabilities) {
        return null;
    }
}
