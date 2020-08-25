package clinone.components;

import clinone.ButtonBaseCl;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import helpers.UserForTest;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;

public class BaseSignInSignUpForm {

    private SelenideElement emailLogin = $("[id=\"UserName\"]");
    private SelenideElement passwordLogin = $("[id=\"Password\"]");
    public SelenideElement registerForm = $("[id=\"register\"]");
    public SelenideElement loginForm = $("[id=\"login\"]");
    private SelenideElement emailReg = $("[id=\"CommonRegisterViewModel_UserName\"]");
    private SelenideElement password = $("[id=\"CommonRegisterViewModel_Password\"]");
    private SelenideElement firstName = $("[id=\"CommonRegisterViewModel_FirstName\"]");
    private SelenideElement lastName = $("[id=\"CommonRegisterViewModel_LastName\"]");
    private SelenideElement checkBox = $("[type=\"checkbox\"]");



    @Step
    public BaseSignInSignUpForm fillUserLoginEmail(UserForTest user) {
        emailLogin.sendKeys(user.getValidUserForTest().getEMAIL());
        return this;
    }

    @Step
    public BaseSignInSignUpForm fillUserLoginPassword(UserForTest user) {
        passwordLogin.sendKeys(user.getValidUserForTest().getPASSWORD());
        return this;
    }



    @Step
    public BaseSignInSignUpForm fillUserRegEmail(UserForTest user) {
        emailReg.sendKeys(user.getEMAIL());
        return this;
    }


    @Step
    public BaseSignInSignUpForm fillUserPassword(UserForTest user) {
        password.waitUntil(Condition.visible, 10000).sendKeys(user.getPASSWORD());
        return this;
    }

    @Step
    public BaseSignInSignUpForm fillUserFirstName(UserForTest user) {
        firstName.waitUntil(Condition.visible, 1000).sendKeys(user.getUSER_NAME());
        return this;
    }


    @Step
    public BaseSignInSignUpForm fillUserLastName(UserForTest user) {
        lastName.waitUntil(Condition.visible, 1000).sendKeys(user.getUSER_SURNAME());
        return this;
    }

    @Step
    public BaseSignInSignUpForm checkBox() {
        checkBox.waitUntil(Condition.visible, 2000).click();
        return this;
    }


    @Step
    public BaseSignInSignUpForm pressNextBtn(String btnText) {
        new ButtonBaseCl().pressBtnByText(btnText);
        return this;
    }


}
