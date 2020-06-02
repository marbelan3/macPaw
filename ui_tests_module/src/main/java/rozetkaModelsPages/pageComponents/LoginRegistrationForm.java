package rozetkaModelsPages.pageComponents;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import helpers.UserForTest;
import interfaces.LoginRegistrationInterface;
import io.qameta.allure.Step;
import org.junit.Assert;

import static com.codeborne.selenide.Selenide.$;

public class LoginRegistrationForm implements LoginRegistrationInterface {

    private SelenideElement loginRegistrationForm = $("[class=\"modal__content\"]");
    private SelenideElement logEmailField = $("[id=\"auth_email\"]");
    private SelenideElement regEmailField = $("[formcontrolname=\"username\"]");
    private SelenideElement logPassField = $("[id=\"auth_pass\"]");
    private SelenideElement regPassField = $("[formcontrolname=\"password\"]");
    private SelenideElement userNameField = $("[formcontrolname=\"name\"]");
    private SelenideElement loginButton = $("[class=\"form__row auth-modal__form-bottom\"] button");
    private SelenideElement goToRegistrationButton = $("[class=\"form__row auth-modal__form-bottom\"] a");
    private SelenideElement submitRegLogButton = $("[class=\"modal__content\"] .button_color_green");
    private SelenideElement userNameInHeaderAfterRegistration = $("[class=\"header-topline__user-text\"]");

    @Step
    public LoginRegistrationForm isLoaded(int milliseconds) {
        loginRegistrationForm.waitUntil(Condition.visible, milliseconds);
        logEmailField.shouldBe(Condition.visible);
        return this;
    }

    @Step
    @Override
    public void doLogin(UserForTest userForTest) {
        logEmailField.sendKeys(userForTest.getEMAIL());
        logPassField.sendKeys(userForTest.getPASSWORD());
        submitRegLogButton.click();

    }

    @Step
    @Override
    public void doRegistration(UserForTest userForTest) {
        goToRegistrationButton.click();
        submitRegLogButton.waitUntil(Condition.visible, 4000);
        userNameField.sendKeys(userForTest.getUSER_NAME());
        regEmailField.sendKeys(userForTest.getEMAIL());
        regPassField.sendKeys(userForTest.getPASSWORD());
        submitRegLogButton.click();
        Assert.assertTrue("User name should be visible in home page header", userNameInHeaderAfterRegistration
                .waitUntil(Condition.visible, 20000).exists());
    }
}
