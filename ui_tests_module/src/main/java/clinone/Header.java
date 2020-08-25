package clinone;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.assertj.core.api.Assertions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class Header {
    private static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(Header.class);

    private SelenideElement header = $("[class=\"top-header\"]");
    private SelenideElement headerLogo = $("[class=\"header-col left\"]");
    private SelenideElement about = $$("[class=\"main-menu\"] span").get(0);
    private SelenideElement solutionsForSites = $$("[class=\"main-menu\"] span").get(1);
    private SelenideElement solutionsForPatients = $$("[class=\"main-menu\"] span").get(2);
    private SelenideElement resources = $$("[class=\"main-menu\"] span").get(3);
    private SelenideElement newsroom = $$("[class=\"main-menu\"] span").get(4);
    private SelenideElement createAccount = $$("[class=\"main-menu\"] span").get(5);
    private SelenideElement signIn = $$("[class=\"main-menu\"] span").get(6);

    @Step
    public Header openCreateAccountTab() {
        createAccount.waitUntil(Condition.visible, 10000).click();
        return new RegisterAndLoginAccount();
    }


    public static class UserHeader {
        private SelenideElement dropdownUserMenu = $("[class=\"header-avatar\"]");

        public UserHeader clickOnUserMenu() {
            dropdownUserMenu.waitUntil(Condition.visible, 2000).click();
            return new DropDownMenu();
        }

        public static class DropDownMenu extends UserHeader {
            private final String APPROVALS = "Approvals";
            private SelenideElement dropMenuBody = $$("[class=\"dropdown-menu\"]").findBy(Condition.text(APPROVALS));
            private List<String> menuItems = Arrays.asList("Approvals","Account & profile","Contact us","Knowledge Base","Sign Out");

            public DropDownMenu isLoaded() {
                dropMenuBody.waitUntil(Condition.visible, 20000);
                return this;
            }

            public DropDownMenu checkMenuItems(){
                ElementsCollection li = dropMenuBody.$$("li");
                List<String> menuItemList = new ArrayList<>();
                for (SelenideElement selenideElement : li) {
                    menuItemList.add(selenideElement.getText());
                }
                Assertions.assertThat(menuItemList.containsAll(menuItems));
                Logger.getGlobal().log(Level.INFO,"Menu items assert is successful");
                return this;
            }

            public void clickApprovalsMenuItem(){
                new ButtonBaseCl().pressBtnByText(APPROVALS);
            }

        }
    }

}
