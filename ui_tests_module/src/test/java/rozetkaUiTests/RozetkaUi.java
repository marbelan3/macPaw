package rozetkaUiTests;

import browserConfiguration.BaseBrowserInit;
import com.codeborne.selenide.Selenide;
import helpers.AllureUIUtil;
import helpers.CookieHelper;
import helpers.ProductToTest;
import helpers.UserForTest;
import io.qameta.allure.Issue;
import io.qameta.allure.junit4.DisplayName;
import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;
import mailSac.MailSacAction;
import mailSac.mailResp.MailSacResponse;
import mappers.CsvMapper;
import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import rozetkaModelsPages.PersonalContentData;
import rozetkaModelsPages.RozetkaHomePage;
import rules.RetryRule;

import java.math.BigDecimal;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@RunWith(JUnitParamsRunner.class)
public class RozetkaUi extends BaseBrowserInit {

    private CookieHelper cookieHelper;
    private final UserForTest userForTest = new UserForTest();
    /**
     * If test need retry, use rule below and change count of retry
     */
    @Rule
    public RetryRule retryRule = new RetryRule(1);


    @Test
    @Issue("Rozetka test for MacPaw")
    @FileParameters(value = "classpath:products.csv", mapper = CsvMapper.class)
    @DisplayName("Search IPhone test rozetka")
    public void RozetkaSearchTest(String productFromCsvFile) {

        ProductToTest productToTest = new ProductToTest(productFromCsvFile, BigDecimal.ZERO);
        AllureUIUtil.paramNameValue("Product for search is : " + productToTest.getProductName());
        Logger.getGlobal().log(Level.INFO, "Product for search is : " + productToTest.getProductName());
        RozetkaHomePage rozetkaHomePage = new RozetkaHomePage();
        rozetkaHomePage
                .isLoaded()
                .goToLoginRegForm()
                .loginRegistrationForm.isLoaded(20000)
                .doRegistration(userForTest);
        System.out.println(userForTest.getEMAIL() + " : user mail");
        MailSacAction mailSacAction = new MailSacAction();
        List<MailSacResponse> emails = mailSacAction.findMailSacListByEmail(userForTest.getEMAIL());
        String emailId = emails.get(0).getId();
        String singleEmailMessage = mailSacAction.getSingleEmailMessage(userForTest.getEMAIL(), emailId);
        String singleHtmlEmailMessage = mailSacAction.getSingleHtmlEmailMessage(userForTest.getEMAIL(), emailId);
        mailSacAction.writeEmailToFile(singleHtmlEmailMessage);
        String pass = mailSacAction.getPassFromMail(singleHtmlEmailMessage);
        mailSacAction.attachEmail(singleHtmlEmailMessage);

        Selenide.open("https://rozetka.com.ua/cabinet/personal-information/");
        PersonalContentData personalContentData = new PersonalContentData();
        personalContentData.isLoaded();
                //.checkApproveMailMessage(new RequiredAssertMessagesByLanguage().getMessageByLang());
//        /**Fill cookie obj for execute rest assured request !!!!!! fill cookie after approve link is loaded!!!!!!*/
//        cookieHelper = new CookieHelper();
//        /**---------------------------------------------------------------------------------------------*/
//
//        personalContentData.searchForm.doSearch(productToTest.getProductName());
//
//        RecommendationsProductsPage recommendationsProductsPage = new RecommendationsProductsPage();
//        recommendationsProductsPage.isLoaded()
//                .choseFirstProductAndSetPriceToObj(productToTest).innerCart.isLoaded()
//                .compareProductPriceAndNameInCart(productToTest.getProductPrice(), productFromCsvFile);
//        new LogsConsole().analyzeLog();
    }


    /**
     * Remove registered test user, to avoid overflow users in DB
     */
    @After
    public void removeRozetkaAccount() {
//        new RemoveUserByRestAssured().removeRozetkaAccount(cookieHelper);
    }


}
