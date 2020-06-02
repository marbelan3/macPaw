package browser;


import com.codeborne.selenide.WebDriverProvider;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.HashMap;
import java.util.Map;

public class ChromeClass implements WebDriverProvider {
    private DesiredCapabilities capabilities;

    @Override
    public WebDriver createDriver(DesiredCapabilities capabilities) {
        WebDriverManager.chromedriver().setup();
        capabilities.setCapability(ChromeOptions.CAPABILITY, getChromeOptions());
        capabilities.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.ACCEPT);
        return new ChromeDriver(capabilities);
    }

    public static ChromeOptions getChromeOptions() {
        ChromeOptions chromeOptions = new ChromeOptions();
        //chromeOptions.addArguments("--start-maximized");
        //chromeOptions.addArguments("--window-size=1024,768");

        /**HEADLESS MODE IF NEED*/
        /**--------------------------------------------------------------------------------------*/
        /*chromeOptions.setBinary("C:/Program Files (x86)/Google/Chrome/Application/chrome.exe");
        chromeOptions.addArguments("--headless");*/
        /**--------------------------------------------------------------------------------------*/

        chromeOptions.addArguments("--disable-gpu");
        chromeOptions.addArguments("--disable-extensions");
        chromeOptions.addArguments("--no-sandbox");
        chromeOptions.addArguments("--disable-dev-shm-usage");
        chromeOptions.addArguments("disable-popup-blocking", "true");
        chromeOptions.addArguments("disable-infobars");
        chromeOptions.addArguments("--disable-notifications");


        Map<String, Object> prefs = new HashMap<String, Object>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        prefs.put("profile.default_content_setting_values.notifications", 2);

        chromeOptions.setExperimentalOption("prefs", prefs);

        return chromeOptions;
    }}
