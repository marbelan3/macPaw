package browserConfiguration.help;

import browserConfiguration.browsers.ChromeBrowser;
import browserConfiguration.browsers.FireFoxBrowser;
import browserConfiguration.browsers.SelenoidRemote;
import com.codeborne.selenide.Configuration;
import io.qameta.allure.Step;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

public class ConfigurationBrowser {

    private static java.util.logging.Logger logger = Logger.getLogger(ConfigurationBrowser.class.getName());

    @Step
    public String getDriver(String browser, String runType) {
        Thread.currentThread().setName(browser + " : " + Thread.currentThread().getName());
        logger.info("--------------------------- " + Thread.currentThread().getName() + " : " + Thread.currentThread().getId() + " -------------------------------------");
        switch (browser) {
            case "CHROME":
                return Configuration.browser = getChrome(runType);
            case "FIREFOX":
                return Configuration.browser = getFireFox(runType);
            default:
                logger.info("Use default driver config");
                return Configuration.browser = getChrome(runType);
        }
    }

    @Step
    public String getChrome(String runType) {
        if (runType.equalsIgnoreCase("LOCAL")) {
            logger.info("Chosen browser : Chrome, chosen run type : " + runType);
            return ChromeBrowser.class.getName();
        } else if (runType.equalsIgnoreCase("SELENOID")) {
            return SelenoidRemote.class.getName();
        } else {
            return "BROWSERSTACK";
        }
    }


    @Step
    public String getFireFox(String runType) {
        if (runType.equalsIgnoreCase("LOCAL")) {
            logger.info("Chosen browser : FireFox, chosen run type : " + runType);
            return Configuration.browser = FireFoxBrowser.class.getName();
        } else if (runType.equalsIgnoreCase("SELENOID")) {
            return SelenoidRemote.class.getName();
        } else {
            return "BROWSERSTACK";
        }
    }
}
