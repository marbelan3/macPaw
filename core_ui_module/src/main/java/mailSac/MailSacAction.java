package mailSac;

import api.BaseRequestResponseSpecifications;
import com.codeborne.selenide.Selenide;
import helpers.AllureUIUtil;
import helpers.RandomData;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import mailSac.mailResp.MailSacResponse;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Assert;
import us.codecraft.xsoup.Xsoup;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import static io.restassured.RestAssured.given;
import static java.lang.Thread.sleep;

public class MailSacAction extends BaseRequestResponseSpecifications {

    public static void main(String[] args) {
        new MailSacAction().getSingleHtmlEmailMessage("jamesjones_49558@mailsac.com","v6kwni7gAe8T4awUiepS_Ymco-0");
    }

    private final File mailDirectory = new File("./build/tmp");
    private static final Semaphore SEMAPHORE = new Semaphore(1, true);
    private static final Logger logger = Logger.getLogger(MailSacAction.class);

    @Step
    public List<MailSacResponse> findMailSacListByEmail(String email) {
        Selenide.sleep(3000);
        for (int i = 0; i < 10; i++) {
            MailSacResponse[] response;
            response =
                    given()
                            .get("/addresses/" + email + "/messages")
                            .then()
                            .statusCode(200)
                            .extract().response().as(MailSacResponse[].class);
            logger.info(Arrays.asList(response));
            List<MailSacResponse> responseTempMails = new ArrayList<>(Arrays.asList(response));
            if (responseTempMails.size() >= 1) {
                return Arrays.asList(response);
            } else {
                try {
                    sleep(6000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                java.util.logging.Logger.getGlobal().log(Level.INFO, "Count of letters is: " + responseTempMails.size() + " ,expected count is 1");
                AllureUIUtil.paramNameValue("Count of letters is: " + responseTempMails.size() + " ,expected count is 1");
            }
        }
        throw new NoSuchElementException("No expected mail in folder");
    }


    @Step
    public String getSingleHtmlEmailMessage(String email, String messageId) {
        Response response;
        response =
                given()
                        .get("/dirty/" + email + "/" + messageId)
                        .then()
                        .statusCode(200)
                        .extract().response().andReturn();
        logger.info("HTML response data is available ");
        return response.getBody().asString();
    }


    @Step
    public String getSingleEmailMessage(String email, String messageId) {
        Response response;
        response =
                given()
                        .get("/text/" + email + "/" + messageId)
                        .then()
                        .statusCode(200)
                        .extract().response().andReturn();
        logger.info(response);
        return response.prettyPrint();
    }


    @Step
    public String getActivateUrl(String email) {
        Assert.assertTrue("PROM.UA link should be present, but null",
                findMailSacListByEmail(email).stream().anyMatch(mailSacResponse -> mailSacResponse.getLinks()
                        .stream().anyMatch(link -> link.contains("account/activate_email"))));
        String linkkkk = findMailSacListByEmail(email).stream()
                .filter(mailSacResponse -> mailSacResponse.getLinks().stream()
                        .anyMatch(link -> link.contains("account/activate_email")))
                .findFirst().get().getLinks()
                .stream().filter(s -> s.contains("account/activate_email")).findFirst().get();
        logger.info(linkkkk);
        return linkkkk;
    }


    @Step
    private String getHrefFromString(String str) {
        Pattern p = Pattern.compile("href=\"(.*?)\"");
        Matcher m = p.matcher(str);
        String url = null;
        if (m.find()) {
            url = m.group(1); // this variable should contain the link URL
        }
        if (StringUtils.isEmpty(url)) {
            logger.warn("Cannot retrieve href from string");
        }
        Assert.assertNotNull("Cannot retrieve href from string", url);
        return url;
    }


    @Step
    public String getPassFromMail(String html) {
        Document document = Jsoup.parse(html);
        String lineWithPass = Xsoup.compile("//*[@id=\"mailsub\"]/table/tbody/tr[3]/td/table/tbody/tr/td/table/tbody/tr[4]/td/span").evaluate(document).get();
        String passwordFromMail = Arrays.asList(lineWithPass.split(" ")).get(7);
        if (passwordFromMail == null) {
            logger.warn("Could not get the password from HTML, the line contains  : " + lineWithPass);
        }
        AllureUIUtil.paramNameValue("Password in mail : " + passwordFromMail);
        logger.info("Password in mail : " + passwordFromMail);
        return passwordFromMail;
    }


    @Step
    public void attachEmail(String htmlMail) {
        AllureUIUtil.attachMail(htmlMail);
    }

    @Step
    public void writeEmailToFile(String htmlMail) {
        String pathAndNameForMail = String.format("./build/tmp/mail_%s.html", RandomData.getRandomNumber(5));
        try (OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(pathAndNameForMail))) {
            osw.write(htmlMail);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void cleanMailFolder() {
        try {
            SEMAPHORE.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (mailDirectory.exists()) {
            try {
                FileUtils.cleanDirectory(mailDirectory);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        SEMAPHORE.release();
        logger.info("Mail directory was cleaned ");
    }

}
