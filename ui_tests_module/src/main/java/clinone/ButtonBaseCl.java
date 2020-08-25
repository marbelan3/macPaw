package clinone;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$$x;

public class ButtonBaseCl {

    @Step
    public void pressBtnByText(String text) {
        $$x(String.format("//*[contains(text(), '%s')]", text)).filterBy(Condition.visible).get(0).click();;
    }
}
