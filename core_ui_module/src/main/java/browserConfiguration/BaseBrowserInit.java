package browserConfiguration;

import browserConfiguration.help.ConfigurationBrowser;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.junit.TextReport;
import com.codeborne.selenide.logevents.SelenideLogger;
import envirnoment.TestURL;
import helpers.ReportWatcher;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import io.qameta.allure.selenide.AllureSelenide;
import io.restassured.RestAssured;
import listeners.CustomEventListener;
import mailSac.MailSacAction;
import org.apache.commons.compress.utils.IOUtils;
import org.apache.commons.lang3.SystemUtils;
import org.junit.*;
import org.junit.rules.TestName;
import org.openqa.selenium.WrapsDriver;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.SessionId;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.*;


public class BaseBrowserInit extends WebDriverFactory {
    private static Logger logger = Logger.getLogger(BaseBrowserInit.class.getName());

    static {
        logger.info("Initialization BaseBrowserInit class");
        Configuration.baseUrl = TestURL.url.toString();
        Configuration.timeout = 10000;
        Configuration.screenshots = true;
        Configuration.savePageSource = true;
        Configuration.fastSetValue = true;
        Configuration.pageLoadStrategy = "none";
        Configuration.pollingInterval = 1000;
    }

    @Rule
    @ClassRule
    public static TestName name = new TestName();

    @Rule
    public ReportWatcher customWatcher = new ReportWatcher()
            .onFailedTest(true)
            .onSucceededTest(true);

    @Rule
    public TextReport textReport = new TextReport().onSucceededTest(false);


    @Before
    public void before() throws IOException {
        deleteAllSelenoidVideo();
        open(Configuration.baseUrl);
        LoggingPreferences logPrefs = new LoggingPreferences();
        logPrefs.enable(LogType.BROWSER, Level.ALL);
        new MailSacAction().cleanMailFolder();
    }

    @BeforeClass
    public static void beforeBaseUiClass() {
        logger.info("OPERATION SYSTEM : " + SystemUtils.OS_NAME);
        new ConfigurationBrowser().getDriver(EnvBrowser.browser.toString(), EnvBrowser.runType.toString());
        Configuration.startMaximized = true;
        WebDriverRunner.addListener(new CustomEventListener());
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide().screenshots(false).savePageSource(false));
    }

    private static boolean isStartInSelenoid() {
        return System.getProperty("runType") != null && System.getProperty("runType").equalsIgnoreCase("selenoid");
    }


    @AfterClass
    public static void afterBaseUIClass() {
        if (isStartInSelenoid()) {
            logger.info("DOCKER CONTAINER IS TEAR DOWN");
        }
    }


    @After
    public void afterEachTest() throws Exception {
        String sessionId = getSessionId();
        logger.info("Session ID : " + sessionId);
        if ("true".equals(System.getProperty("video.enabled"))) {
            attachAllureVideo(sessionId);
        }

        while (getWebDriver().getWindowHandles().size() != 1) {
            Selenide.switchTo().window(0);
            try {
                Selenide.clearBrowserCookies();
                Selenide.clearBrowserLocalStorage();
                Selenide.switchTo().window(0).close();
            } catch (Exception e) {
                Logger.getGlobal().log(Level.WARNING, "CLEARING BROWSER COOKIES AND STORAGE WAS FAILED");
            }
        }
        Selenide.switchTo().window(0);
        try {

            Selenide.clearBrowserCookies();
            Selenide.clearBrowserLocalStorage();
        } catch (Exception ignore) {
            Logger.getGlobal().log(Level.WARNING, "CLEARING BROWSER COOKIES AND STORAGE WAS FAILED");
        }

    }

    public static String getSessionId() {
        SessionId sessionId = ((RemoteWebDriver) (((WrapsDriver) WebDriverRunner.getWebDriver()).getWrappedDriver())).getSessionId();
        return sessionId.toString();
    }

    private static String selenoidUrl = "http://194.44.160.2:4888";

    @Step
    public static void attachAllureVideo(String sessionId) {
        try {
            URL videoUrl = new URL(selenoidUrl + "/video/" + sessionId + ".mp4");
            InputStream is = getSelenoidVideo(videoUrl);
            Allure.addAttachment("Video : " + name.getMethodName(), "video/mp4", is, "mp4");
        } catch (Exception e) {
            logger.info("attachAllureVideo");
            e.printStackTrace();
        }
    }

    @Step
    public static InputStream getSelenoidVideo(URL url) {
        int lastSize = 0;
        int exit = 1;
        for (int i = 0; i < 20; i++) {
            try {
                int size = Integer.parseInt(url.openConnection().getHeaderField("Content-Length"));
                logger.info("Content-Length: " + size);
                System.out.println(i);
                if (size > lastSize) {
                    lastSize = size;
                    Thread.sleep(500);
                } else if (size == lastSize) {
                    exit--;
                    Thread.sleep(200);
                }
                if (exit < 0) {
                    return url.openStream();
                }
            } catch (Exception e) {
                logger.info("checkSelenoidVideo");
                e.printStackTrace();
            }
        }

        return null;
    }


    @Step
    public static void deleteAllSelenoidVideo() throws IOException {
        List<String> listOfSelenoidVideoSessions = getListOfSelenoidVideoSessions();
        for (String session : listOfSelenoidVideoSessions) {
            URL videoUrl = new URL(selenoidUrl + "/video/" + session + ".mp4");
            RestAssured.given().when().delete(videoUrl);
            logger.info(videoUrl + " has been removed from server");
        }

    }


    @Step
    public static List<String> getListOfSelenoidVideoSessions() throws IOException {
        List<String> listOfVideo = new ArrayList<>();
        List<String> list = new ArrayList<>();
        URL oracle = new URL("http://194.44.160.2:4888/video/");
        BufferedReader in = new BufferedReader(
                new InputStreamReader(oracle.openStream()));
        String inputLine;
        while ((inputLine = in.readLine()) != null)
            if (inputLine.length() > 10) {
                list.add(inputLine);
            }
        in.close();
        for (String s : list) {
            listOfVideo.add(s.substring(9, s.indexOf(".")));
            logger.info(s.substring(9, s.indexOf(".")));
        }
        logger.info("List of videos : " + listOfVideo);
        return listOfVideo;

    }

    public static void save(InputStream in, String fname) throws IOException {
        final String RECORD_DIRECTORY = "video";
        File dir = new File(RECORD_DIRECTORY);
        try (FileOutputStream out = new FileOutputStream(new File(fname))) {
            IOUtils.copy(in, out);
        }
    }

}
