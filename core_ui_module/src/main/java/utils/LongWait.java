package utils;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

public class LongWait {

    private Long timeout = 0L;

    private LongWait(Long timeout) {
        this.timeout = timeout;
    }

    /**
     * Use 10000L timeout for waiting
     *
     * @return new LongWait(10000L)
     */
    public static LongWait tenSecs() {
        return new LongWait(10000L);
    }

    /**
     * Use 20000L timeout for waiting
     *
     * @return new LongWait(20000L)
     */
    public static LongWait twentySecs() {
        return new LongWait(20000L);
    }

    /**
     * Use 40000L timeout for waiting
     *
     * @return new LongWait(40000L)
     */
    public static LongWait fortySecs() {
        return new LongWait(40000L);
    }

    /**
     * Use 40000L timeout for waiting
     *
     * @return new LongWait(45000L)
     */
    public static LongWait fortyFiveSecs() {
        return new LongWait(45000L);
    }

    /**
     * Use 60000L timeout for waiting
     *
     * @return new LongWait(60000L)
     */
    public static LongWait sixtySecs() {
        return new LongWait(60000L);
    }

    /**
     * Use 100000L timeout for waiting
     *
     * @return new LongWait(100000L)
     */
    public static LongWait hundredSecs() {
        return new LongWait(100000L);
    }

    public static LongWait hundredFiftySecs() {
        return new LongWait(150000L);
    }

    /**
     * Wait with defined timeout for visibility of element
     *
     * @param selector Use By selectors
     * @return Selenide.$(selector).waitUntil(Condition.visible, timeout);
     */
    public SelenideElement visibilityOf(By selector) {
        return Selenide.$(selector).waitUntil(Condition.visible, timeout);
    }

    /**
     * Wait with defined timeout for visibility of element
     *
     * @param selector Use By selectors
     * @return Selenide.$(selector).waitUntil(Condition.visible, timeout);
     */
    public SelenideElement hiddenOf(By selector) {
        return Selenide.$(selector).waitUntil(Condition.hidden, timeout);
    }

    /**
     * Wait with defined timeout for visibility of element
     *
     * @param element Define element for waiting
     * @return element.waitUntil(Condition.visible, timeout);
     */
    public SelenideElement hiddenOf(SelenideElement element) {
        return element.waitUntil(Condition.hidden, timeout);
    }

    /**
     * Wait with defined timeout for visibility of element
     *
     * @param selector Use By selectors
     * @return Selenide.$(selector).waitUntil(Condition.exist, timeout);
     */
    public SelenideElement exitingOf(By selector) {
        return Selenide.$(selector).waitUntil(Condition.exist, timeout);
    }

    public SelenideElement notExistingOf(SelenideElement element) {
        return element.waitUntil(Condition.not(Condition.exist), timeout);
    }

    /**
     * Wait with defined timeout for visibility of element
     *
     * @param element Define element for waiting
     * @return element.waitUntil(Condition.visible, timeout);
     */
    public SelenideElement visibilityOf(SelenideElement element) {
        return element.waitUntil(Condition.visible, timeout);
    }

    /**
     * Wait with defined timeout for visibility of element
     *
     * @param element Define element for waiting
     * @return element.waitUntil(Condition.exist, timeout);
     */
    public SelenideElement exitingOf(SelenideElement element) {
        return element.waitUntil(Condition.exist, timeout);
    }

}
