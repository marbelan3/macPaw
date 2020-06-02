package rozetkaModelsPages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import rozetkaModelsPages.pageComponents.LoginRegistrationForm;
import rozetkaModelsPages.pageComponents.SearchForm;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class RozetkaHomePage {

    public LoginRegistrationForm loginRegistrationForm = new LoginRegistrationForm();
    public SearchForm searchForm = new SearchForm();
    private SelenideElement logoRozetka = $("[class=\"header__logo\"]");
    private ElementsCollection firstPageProducts = $$("[class=\"main-goods__tile\"]");
    private SelenideElement goToLoginRegForm = $("[class=\"header-topline__user-link link-dashed\"]");
    private SelenideElement rightSideCommercial = $("[id=\"rz-banner-img\"]");
    private SelenideElement rightSideCommercialClose = $("[class=\"exponea-close-cross\"]");

    @Step
    public RozetkaHomePage isLoaded() {
        logoRozetka.waitUntil(Condition.visible, 20000);
        for (SelenideElement firstPageProduct : firstPageProducts) {
            firstPageProduct.isDisplayed();
        }
        return this;
    }

    @Step
    public void closeSideCommercialIfExist(){
        if (rightSideCommercial.waitUntil(Condition.visible,15000).is(Condition.visible)){
            rightSideCommercialClose.click();
        }
    }

    @Step
    public RozetkaHomePage goToLoginRegForm(){
        closeSideCommercialIfExist();
        if (goToLoginRegForm.isDisplayed()){
            goToLoginRegForm.click();
        }
        return this;
    }
}
