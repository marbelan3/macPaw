package tempMailApi;

import com.codeborne.selenide.Selenide;
import helpers.AllureUIUtil;
import helpers.RandomData;
import helpers.UserForTest;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Assert;
import us.codecraft.xsoup.Xsoup;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.Thread.sleep;

public class TempMail extends BaseTempMailClass {
    public static final String API_KEY = "7532d28fc8mshb95973e80d71745p1e0de9jsn11a3a9851af1";
    List<ResponseTempMail> responseTempMailList;


    @Step
    public List<ResponseTempMail> getMails(UserForTest userForTest) {
        Selenide.sleep(3000);
        for (int i = 0; i < 15; i++) {
            ResponseTempMail[] response;

            response = RestAssured
                    .get(String.format("request/mail/id/%s/", stringToMD5Hash(userForTest.getEMAIL())))
                    .then()
                    .extract()
                    .response().as(ResponseTempMail[].class);

            List<ResponseTempMail> responseTempMails = new ArrayList<>(Arrays.asList(response));
            if (responseTempMails.size() >= 1) {
                responseTempMailList = responseTempMails;
                return responseTempMails;
            } else {
                try {
                    sleep(6000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Logger.getGlobal().log(Level.INFO, "Count of letters is: " + responseTempMails.size() + " ,expected count is 1");
                AllureUIUtil.paramNameValue("Count of letters is: " + responseTempMails.size() + " ,expected count is 1");
            }
        }
        throw new NoSuchElementException("No expected mail in folder");

    }

    @Step
    public static String stringToMD5Hash(String stringToMD5) {
        String plaintext = stringToMD5;
        MessageDigest m = null;
        try {
            m = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        m.reset();
        m.update(plaintext.getBytes());
        byte[] digest = m.digest();
        BigInteger bigInt = new BigInteger(1, digest);
        String hashtext = bigInt.toString(16);
        while (hashtext.length() < 32) {
            hashtext = "0" + hashtext;
        }
        Logger.getGlobal().log(Level.INFO, "Mail hash is: " + hashtext);
        AllureUIUtil.paramNameValue("Mail hash is: " + hashtext);
        System.out.println(hashtext);
        return hashtext;
    }

    public String getAPI_KEY() {
        return API_KEY;
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
            Logger.getGlobal().log(Level.WARNING, "Cannot retrieve href from string");
        }
        Assert.assertNotNull("Cannot retrieve href from string", url);
        return url;
    }

    @Step
    public String getLinkForApprove(String html) {
        Document document = Jsoup.parse(html);
        List<String> hrefs = Xsoup.compile("//*[@id=\"mailsub\"]/table/tbody/tr[3]/td/table/tbody/tr/td/table/tbody/tr[4]/td/span/a").evaluate(document).list();
        getHrefFromString(hrefs.get(0));

        if (hrefs.size() != 1) {
            Logger.getGlobal().log(Level.WARNING, "More than one link for recovered password, current size is: " + hrefs.size());
        }

        AllureUIUtil.paramNameValue("Link for approve: " + getHrefFromString(hrefs.get(0)));
        Logger.getGlobal().log(Level.INFO, "Link for approve: " + getHrefFromString(hrefs.get(0)));
        return getHrefFromString(hrefs.get(0));
    }

    @Step
    public void attachEmail(String htmlMail) {
        AllureUIUtil.attachMail(htmlMail);
    }

    @Step
    public void writeEmailToFile(String htmlMail) {
        String pathAndNameForMail = String.format("build/mail_%s.html", RandomData.getRandomNumber(5));
        try (OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(pathAndNameForMail))) {
            osw.write(htmlMail);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
