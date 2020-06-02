package rozetkaModelsPages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import helpers.AllureUIUtil;
import helpers.PriceHelper;
import helpers.ProductToTest;
import io.qameta.allure.Step;
import rozetkaModelsPages.pageComponents.InnerCart;

import java.math.BigDecimal;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class RecommendationsProductsPage {

    public InnerCart innerCart = new InnerCart();

    private SelenideElement catalogGrid = $("[class=\"catalog-grid\"]");
    private SelenideElement addProductToCart = $("[class=\"buy-button goods-tile__buy-button\"]");
    private ElementsCollection products = $$("[class=\"goods-tile__inner\"]");

    @Step
    public RecommendationsProductsPage isLoaded() {
        catalogGrid.waitUntil(Condition.visible, 30000).shouldBe(Condition.visible);
        return this;
    }

    @Step
    public RecommendationsProductsPage choseFirstProductAndSetPriceToObj(ProductToTest productToTest) {
        products.get(0).$(addProductToCart.getSearchCriteria()).click();
        String priceFromFirstProduct = products.get(0).$("[class=\"goods-tile__price-value\"]").getText();
        productToTest.setProductPrice(new BigDecimal(PriceHelper.getPriceFromString(priceFromFirstProduct)));
        AllureUIUtil.paramNameValue("Price from first recommendation product : " + priceFromFirstProduct);
        Logger.getGlobal().log(Level.INFO, "Price from first recommendation product : " + priceFromFirstProduct);
        return this;
    }

}
