package browserConfiguration;

import browserConfiguration.browsers.ChromeBrowser;
import browserConfiguration.help.ConfigurationBrowser;
import org.openqa.selenium.WebDriver;

public enum Browsers {

    PROPERTIES(System.getProperty("browser")),

    CHROME_TEST("CHROME") {
        public String create(String type) {
            return ChromeBrowser.class.getName();
        }
    },

    CHROME("CHROME") {
        public String create(String type) {
            return new ConfigurationBrowser().getChrome(type);
        }
    },
    FIREFOX("FIREFOX") {
        public String create(String type) {
            return new ConfigurationBrowser().getFireFox(type);
        }
    };


    private String browser;

    Browsers(String domainValue) {
        this.browser = domainValue;
    }

    @Override
    public String toString() {
        return this.browser;
    }

    public static void main(String[] args) {
        System.out.println(EnvBrowser.browser.name());
    }

}
