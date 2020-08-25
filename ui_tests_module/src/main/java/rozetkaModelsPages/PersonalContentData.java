package rozetkaModelsPages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import interfaces.PopUpInterface;
import io.qameta.allure.Step;
import org.junit.Assert;
import rozetkaModelsPages.pageComponents.SearchForm;

import static com.codeborne.selenide.Selenide.$;

public class PersonalContentData implements PopUpInterface {

    public SearchForm searchForm = new SearchForm();
    private SelenideElement personalContentBlock = $("[class=\"content cabinet-content\"]");
    private SelenideElement classWithRequiredAssertMessage = $("[class=\"pos-fix sprite-side message code1\"]");


    @Step
    public PersonalContentData isLoaded() {
        personalContentBlock.waitUntil(Condition.visible, 30000);
        return this;
    }

    @Step
    public PersonalContentData checkApproveMailMessage(String message) {
        Assert.assertTrue("Message <<" + message + ">> should be visible in "
                , classWithRequiredAssertMessage.getText().contains(message));
        closePopUp();
        return this;
    }

    @Override
    public void closePopUp() {
        if (Selenide.$("[class=\"popup-close\"]").isDisplayed()) {
            Selenide.$("[class=\"popup-close\"]").click();

        }
    }
}
