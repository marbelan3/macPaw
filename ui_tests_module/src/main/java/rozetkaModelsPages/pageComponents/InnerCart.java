package rozetkaModelsPages.pageComponents;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import helpers.PriceHelper;
import io.qameta.allure.Step;
import org.junit.Assert;

import java.math.BigDecimal;

import static com.codeborne.selenide.Selenide.$;

public class InnerCart {
    SelenideElement innerCartBody = $("[class=\"cart-modal__inner\"]");
    SelenideElement cartProductTitle = $("[class=\"cart-modal__title\"]");
    SelenideElement cartProductPrice = $("[class=\"cart-modal__coast-digits\"]");

    @Step
    private BigDecimal getPriceFromCart() {
        return new BigDecimal(PriceHelper.getPriceFromString(cartProductPrice.getText()));
    }

    @Step
    public InnerCart isLoaded() {
        innerCartBody.waitUntil(Condition.visible, 5000).shouldBe(Condition.visible);
        return new InnerCart();
    }

    @Step
    public InnerCart compareProductPriceAndNameInCart(BigDecimal price, String productName) {
        compareProductName(productName);
        compareProductPrice(price);
        return this;
    }

    @Step
    private void compareProductName(String productName) {
        Assert.assertTrue("Search product name should be equal to name in cart", cartProductTitle.getText().contains(productName));
    }

    @Step
    private void compareProductPrice(BigDecimal price) {
        BigDecimal priceFromCart = getPriceFromCart();
        Assert.assertTrue("Search product price should be equal to price in cart", price.compareTo(priceFromCart) == 0);
    }
}
