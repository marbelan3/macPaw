package clinone;

import clinone.components.BaseSignInSignUpForm;
import com.codeborne.selenide.Condition;
import helpers.UserForTest;
import io.qameta.allure.Step;

public class RegisterAndLoginAccount extends Header {

    public BaseSignInSignUpForm baseSignInSignUpForm = new BaseSignInSignUpForm();

    @Step
    public RegisterAndLoginAccount isLoadedSignUp() {
        baseSignInSignUpForm.registerForm.waitUntil(Condition.visible, 10000);
        return this;
    }

    @Step
    public RegisterAndLoginAccount isLoadedSignIn() {
        baseSignInSignUpForm.loginForm.waitUntil(Condition.visible, 10000);
        return this;
    }


    @Step
    public void doRegistration(UserForTest user) {
        baseSignInSignUpForm.fillUserRegEmail(user)
                .checkBox()
                .pressNextBtn("Next")
                .fillUserPassword(user)
                .fillUserFirstName(user)
                .fillUserLastName(user)
                .pressNextBtn("Create account");
    }



    @Step
    public void doLogin(UserForTest user) {
        baseSignInSignUpForm
                .fillUserLoginEmail(user)
//                .checkBox()
                .pressNextBtn("Next")
                .fillUserLoginPassword(user)
                .pressNextBtn("Sign in");
    }

}
