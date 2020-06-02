package helpers;

import com.codeborne.selenide.Screenshots;
import com.codeborne.selenide.WebDriverRunner;
import com.google.common.io.Files;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AllureUIUtil {

    @SuppressWarnings("UnusedReturnValue")
    @Step
    @Attachment(value = "Screenshot", type = "image/png")
    public static byte[] attachScreenshot() {
        try {
            return Files.toByteArray(Screenshots.takeScreenShotAsFile());
        } catch (Exception e) {
            e.printStackTrace();
            return new byte[0];
        }
    }



    @SuppressWarnings("UnusedReturnValue")
    @Attachment(value = "Json", type = "text/plain")
    public static byte[] attachJson(File json) {
        try {
            return Files.toByteArray(json);
        } catch (Exception e) {
            e.printStackTrace();
            return new byte[0];
        }
    }


    @SuppressWarnings("UnusedReturnValue")
    @Attachment(value = "Log for test", type = "text/html")
    public static String attachLog(String text) {
        return text;
    }

    @SuppressWarnings("UnusedReturnValue")
    @Attachment(value = "Result of parsing", type = "text/html")
    public static String attachParseResult(String text) {
        return text;
    }

    @SuppressWarnings("UnusedReturnValue")
    @Attachment(value = "Email", type = "text/html", fileExtension = ".html")
    public static String attachMail(String body) {
        return body;
    }


    @SuppressWarnings("UnusedReturnValue")
    @Attachment(value = "Page Sources", type = "text/html")
    static String attachPageSources() {
        return WebDriverRunner.getWebDriver().getPageSource();
    }

    @Step
    public static void executedScript(String script) {
    }

    @Step
    public static void paramNameValue(String argumentName, String argumentValue) {
    }

    @Step
    public static void paramNameValue(String argumentName) {
    }

    @SuppressWarnings("WeakerAccess")
    @Step
    public static void captureCurrentUrl(String url) throws URISyntaxException {
        List<NameValuePair> params = URLEncodedUtils.parse(new URI(url), "UTF-8");
        for (NameValuePair param : params) {
            paramNameValue(param.getName(), param.getValue());
        }
        Logger.getGlobal().log(Level.INFO, "Current url: " + url);
    }

    public static void saveCurrentUrl() {
        try {
            captureCurrentUrl(WebDriverRunner.url());
        } catch (URISyntaxException ex) {
            ex.printStackTrace();
        }
    }

    @SuppressWarnings("WeakerAccess")
    @Step
    public static void extendedUserToken(String token) {


    }




}