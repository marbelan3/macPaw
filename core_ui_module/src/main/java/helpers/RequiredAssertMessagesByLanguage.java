package helpers;

import io.qameta.allure.Step;

import java.util.logging.Level;
import java.util.logging.Logger;

import static com.codeborne.selenide.Selenide.$;

public class RequiredAssertMessagesByLanguage {

    private String activeLangOnPage = $("[class=\"lang-switcher-link active\"]").getText();

    private final String ruMessageAboutMailApprove = "Адрес электронной почты подтвержден, спасибо";
    private final String uaMessageAboutMailApprove = "Адресу електронної пошти підтверджено, дякуємо";

    @Step
    public String getMessageByLang() {
        if (activeLangOnPage.equals("ru")) {
            Logger.getGlobal().log(Level.INFO, "Message to assert : " + ruMessageAboutMailApprove);
            return ruMessageAboutMailApprove;
        } else {
            Logger.getGlobal().log(Level.INFO, "Message to assert : " + uaMessageAboutMailApprove);
        }
        return uaMessageAboutMailApprove;
    }


}
