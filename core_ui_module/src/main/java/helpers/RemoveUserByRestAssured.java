package helpers;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import org.junit.Assert;
import java.util.logging.Level;
import java.util.logging.Logger;

class BaseRozetkaAction {
    static {
        RestAssured.responseSpecification = new ResponseSpecBuilder()
                .setDefaultParser(Parser.JSON)
                .build();
    }

    protected RequestSpecBuilder baseRequestBuilder = new RequestSpecBuilder();

    /**
     * Base constructor for building predefined request specification with baseUri and base param "key" with needed value
     */
    protected BaseRozetkaAction() {
        baseRequestBuilder
                .setBaseUri("https://my.rozetka.com.ua/")
                .setUrlEncodingEnabled(true)
                .setRelaxedHTTPSValidation()
                .setContentType(ContentType.URLENC.withCharset("UTF-8"))
                .addHeader("Accept", "text/javascript, text/html, application/xml, text/xml, */*")
                .addHeader("Referer", "https://my.rozetka.com.ua/ua/?utm_source=dm&utm_medium=email&utm_campaign=welcome-letter&utm_content=confirm&utm_term=general-info")
                .addHeader("ajaxAction", "https://my.rozetka.com.ua/profile/personal-information/edit/#removeAccount")
                .addHeader("X-Requested-With", "XMLHttpRequest")
                .addHeader("Content-Type", "application/x-www-form-urlencoded")
                .addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.61 Safari/537.36")
                .log(LogDetail.ALL)
                .setContentType("application/x-www-form-urlencoded");

    }
}
class RemoveUserAction extends BaseRozetkaAction {
    public RemoveUserAction() {
        RestAssured.requestSpecification = super.baseRequestBuilder
                .build();
    }
}
public class RemoveUserByRestAssured extends RemoveUserAction {


    public void removeRozetkaAccount(CookieHelper cookie) {

        Response response = RestAssured
                .given()
                .param("removeAccount", "1")
                .param("remove_reason", "Test")
                .param("request_token", cookie.getUid())
                .cookie("ho_sid", cookie.getHo_sid())
                .cookie("slang", cookie.getSlang())
                .cookie("uid", cookie.getUid())
                .cookie("ab_xl", cookie.getAb_xl())
                .cookie("ab_xl_uid", cookie.getAb_xl_uid())
                .cookie("sections_test", "monolith")
                .cookie("login", cookie.getLogin())
                .cookie("sid", cookie.getSid())
                .cookie("gauid", cookie.getGauid())
                .urlEncodingEnabled(true)
                .when()
                .post("profile/personal-information/edit/&action=removeAccount")
                .then().extract().response();//.as(ResponseRemoveAccount.class);

        System.out.println(response.statusCode());
        Assert.assertTrue("Response action should be equals - 4", response.body().jsonPath().get("action").equals(4));
        AllureUIUtil.paramNameValue("User account has been deleted");
        Logger.getGlobal().log(Level.INFO, "User account has been deleted");
    }


}
