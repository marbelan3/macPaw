package helpers;

import com.automation.remarks.video.recorder.VideoRecorder;
import com.codeborne.selenide.Screenshots;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import com.google.common.io.Files;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.google.common.io.Files.*;
import static java.nio.file.Files.readAllBytes;

public class AllureUIUtil {

    @SuppressWarnings("UnusedReturnValue")
    @Step
    @Attachment(value = "Screenshot", type = "image/png")
    public static byte[] attachScreenshot() {
        try {
            return toByteArray(Screenshots.takeScreenShotAsFile());
        } catch (Exception e) {
            e.printStackTrace();
            return new byte[0];
        }
    }

//    @Attachment(value = "video", type = "video/mp4")
//    private byte[] attachment() {
//        File video = VideoRecorder.getLastRecording();
//        return readAllBytes(get(video.absolutePath()));
//    }

    @Attachment(value = "video",type="video/webm")
    public static byte[] attachVideo(String path) throws Exception {
        return getFile(path);

    }

    public static byte[] getFile(String fileName) throws Exception {
        File file = new File(fileName);
        return readAllBytes(Paths.get(file.getAbsolutePath()));
    }



    @SuppressWarnings("UnusedReturnValue")
    @Attachment(value = "Json", type = "text/plain")
    public static byte[] attachJson(File json) {
        try {
            return toByteArray(json);
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


    @SuppressWarnings("UnusedReturnValue")
    @Attachment(value = "AllureTextReport", type = "text/plain", fileExtension = ".txt")
    public static String attachText(String text) {
        return text;
    }

    @SuppressWarnings("UnusedReturnValue")
    @Attachment(value = "AllureCSVReport", type = "text/csv", fileExtension = ".csv")
    public static String attachCSV(String csv) {
        return csv;
    }

    @SuppressWarnings("UnusedReturnValue")
    @Attachment(value = "Html source", type = "text/html", fileExtension = ".html")
    public static byte[] getPageSource() {
        return getPageSourcetBytes();
    }

    @SuppressWarnings("UnusedReturnValue")
    @Attachment(value = "Скриншот", type = "image/png", fileExtension = ".png")
    public static byte[] takeScreenshot() {
        return getScreenshotBytes();
    }

    @SuppressWarnings("UnusedReturnValue")
    @Attachment(value = "{name}", type = "image/png", fileExtension = ".png")
    public static byte[] takeScreenshot(String name) {
        return getScreenshotBytes();
    }

    @SuppressWarnings("UnusedReturnValue")
    @Attachment(value = "Скриншот элемента", type = "image/png", fileExtension = ".png")
    public static byte[] takeScreenshot(SelenideElement elem) {
        return getScreenshotBytes(elem);
    }

    public static byte[] getPageSourcetBytes() {
        return WebDriverRunner.getWebDriver().getPageSource().getBytes(StandardCharsets.UTF_8);
    }

    public static byte[] getScreenshotBytes() {
        return ((TakesScreenshot) WebDriverRunner.getWebDriver()).getScreenshotAs(OutputType.BYTES);
    }

    public static byte[] getScreenshotBytes(SelenideElement elem) {
        return elem.getScreenshotAs(OutputType.BYTES);
    }
}