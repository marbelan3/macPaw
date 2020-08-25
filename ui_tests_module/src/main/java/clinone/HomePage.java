package clinone;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class HomePage {
    private SelenideElement openSidebarButton = $("[id=\"btn-sidebar-toggle\"]");
    private SelenideElement welcomeTitle = $("[class=\"col-md-12\"]");
    private SelenideElement addYourFirstStudy = $("[class=\"btn btn-lg btn-primary\"]");

    public HomePage isLoadedHomePage() {
        welcomeTitle.$("h2").shouldHave(Condition.text("Welcome to ClinOne"));
        return this;
    }
}
