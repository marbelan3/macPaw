package helpers;

import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

public class ReportWatcher extends TestWatcher {

    private final HtmlReport report = new HtmlReport();

    private boolean onFailedTest = true;
    private boolean onSucceededTest = false;

    public ReportWatcher onFailedTest(boolean onFailedTest) {
        this.onFailedTest = onFailedTest;
        return this;
    }

    public ReportWatcher onSucceededTest(boolean onSucceededTest) {
        this.onSucceededTest = onSucceededTest;
        return this;
    }

    @Override
    protected void starting(Description test) {
        this.report.start();
    }

    @Override
    protected void succeeded(Description description) {
        if (this.onSucceededTest) {
            AllureUIUtil.attachScreenshot();
            AllureUIUtil.attachPageSources();
        }
        this.report.finish();
    }

    @Override
    protected void failed(Throwable e, Description description) {
        if (this.onFailedTest) {
            AllureUIUtil.attachScreenshot();
            AllureUIUtil.attachPageSources();
        }
        this.report.finish();
    }

    @Override
    protected void finished(Description description) {
        this.report.clean();
    }

}