package tempMailApi;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;

public class BaseTempMailClass {
    public BaseTempMailClass() {
        RequestSpecBuilder baseRequestBuilder = new RequestSpecBuilder()
                .setBaseUri("https://privatix-temp-mail-v1.p.rapidapi.com/")
                .addParam("x-rapidapi-host", "privatix-temp-mail-v1.p.rapidapi.com")
                .addHeader("x-rapidapi-key", TempMail.API_KEY)
                .setRelaxedHTTPSValidation()
                .setAccept(ContentType.JSON)
                .setContentType(ContentType.JSON)
                .setUrlEncodingEnabled(false);
//                .log(LogDetail.ALL).addFilter(new AllureRestAssured());
        RestAssured.requestSpecification = baseRequestBuilder.build();
    }
}
