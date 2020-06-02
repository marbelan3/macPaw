package helpers;

import java.math.BigDecimal;
import java.util.Objects;

public class ProductToTest {

    private String productName;
    private BigDecimal productPrice;

    @Override
    public String toString() {
        return "ProductToTest{" +
                "productName='" + productName + '\'' +
                ", productPrice=" + productPrice +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductToTest that = (ProductToTest) o;
        return Objects.equals(productName, that.productName) &&
                Objects.equals(productPrice, that.productPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productName, productPrice);
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }

    public ProductToTest() {
    }

    public ProductToTest(String productName, BigDecimal productPrice) {
        this.productName = productName;
        this.productPrice = productPrice;
    }
}
