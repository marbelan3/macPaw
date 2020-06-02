package rozetkaUiTests;

import browser.BaseBrowserInit;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;

import io.qameta.allure.Issue;
import io.qameta.allure.junit4.DisplayName;
import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;
import mappers.CsvMapper;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import rozetkaModelsPages.RecommendationsProductsPage;
import rozetkaModelsPages.RozetkaHomePage;
import helpers.*;

import rozetkaModelsPages.PersonalContentData;
import tempMailApi.TempMail;

import java.math.BigDecimal;
import java.util.logging.Level;
import java.util.logging.Logger;

@RunWith(JUnitParamsRunner.class)
public class RozetkaUi extends BaseBrowserInit {

    private CookieHelper cookieHelper;
    private final UserForTest userForTest = new UserForTest();

    /**!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
     * If test need retry, use rule below and change count of retry
     *
     * @Rule public RetryRule retryRule = new RetryRule(2);
     */

    @Test
    @Issue("Rozetka test for MacPaw")
    @FileParameters(value = "classpath:products.csv", mapper = CsvMapper.class)
    @DisplayName("Search IPhone test rozetka")
    public void RozetkaSearchTest(String productFromCsvFile) {

        ProductToTest productToTest = new ProductToTest(productFromCsvFile,BigDecimal.ZERO);

        AllureUIUtil.paramNameValue("Product for search is : " + productToTest.getProductName());
        Logger.getGlobal().log(Level.INFO, "Product for search is : " + productToTest.getProductName());

        Selenide.open(Configuration.baseUrl);

        RozetkaHomePage rozetkaHomePage = new RozetkaHomePage();
        rozetkaHomePage.isLoaded()
                .goToLoginRegForm()
                .loginRegistrationForm.isLoaded(20000).doRegistration(userForTest);
        TempMail tempMail = new TempMail();
        String html = tempMail.getMails(userForTest).get(0).getMailHtml();
        tempMail.writeEmailToFile(html);
        String linkForApprove = tempMail.getLinkForApprove(html);
        tempMail.attachEmail(html);
        Selenide.open(linkForApprove);
        PersonalContentData personalContentData = new PersonalContentData();
        personalContentData.isLoaded()
                .checkApproveMailMessage(new RequiredAssertMessagesByLanguage().getMessageByLang());
        /**Fill cookie obj for execute rest assured request !!!!!! fill cookie after approve link is loaded!!!!!!*/
        cookieHelper = new CookieHelper();
        /**---------------------------------------------------------------------------------------------*/

        personalContentData.searchForm.doSearch(productToTest.getProductName());

        RecommendationsProductsPage recommendationsProductsPage = new RecommendationsProductsPage();
        recommendationsProductsPage.isLoaded()
                .choseFirstProductAndSetPriceToObj(productToTest).innerCart.isLoaded()
                .compareProductPriceAndNameInCart(productToTest.getProductPrice(),productFromCsvFile);
    }


    /**
     * Remove registered test user, to avoid overflow users in DB
     */
    @After
    public void removeRozetkaAccount() {
        new RemoveUserByRestAssured().removeRozetkaAccount(cookieHelper);
    }


}
