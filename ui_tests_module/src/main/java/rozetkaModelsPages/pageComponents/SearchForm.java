package rozetkaModelsPages.pageComponents;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class SearchForm {
//    private SelenideElement searchField = $("[name=\"search\"]");
    private SelenideElement searchField = $$("body header input").findBy(Condition.visible);
    private SelenideElement submitSearch = $("[role=\"search\"] .button_color_green");
    private ElementsCollection dropdownProductsList = $$("[data-rz-gtm-event=\"suggestedQuery\"]");

    @Step
    public void doSearch(String productToSearch){
        this.searchField.setValue(productToSearch);
        dropdownProductsList.forEach(SelenideElement::isDisplayed);
        dropdownProductsList.findBy(Condition.text(productToSearch)).click();
        submitSearch.click();
    }
}
