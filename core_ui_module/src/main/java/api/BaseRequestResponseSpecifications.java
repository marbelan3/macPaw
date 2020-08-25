package api;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.parsing.Parser;
import mailSac.BaseMailSacData;

public class BaseRequestResponseSpecifications {
    static String baseBeUrl = String.format("https://%s/api/", BaseMailSacData.DOMAIN);

    static {

        RestAssured.responseSpecification = new ResponseSpecBuilder()
                .setDefaultParser(Parser.JSON)
                .build();

        RequestSpecBuilder baseRequestBuilder = new RequestSpecBuilder();
        RestAssured.requestSpecification = baseRequestBuilder.build();
        baseRequestBuilder.setBaseUri(baseBeUrl)
                .addParam("_mailsacKey", BaseMailSacData.KEY)
                .setUrlEncodingEnabled(true)
                .setRelaxedHTTPSValidation()
                .log(LogDetail.ALL)
//                .addFilter(new AllureRestAssured())
        ;
    }

    protected RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();

    public BaseRequestResponseSpecifications() {
        requestSpecBuilder.setBaseUri(baseBeUrl)
                .setUrlEncodingEnabled(true)
                .setRelaxedHTTPSValidation()
                .log(LogDetail.ALL);
    }

}
