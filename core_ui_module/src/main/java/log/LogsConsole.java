package log;

import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import java.util.Date;

public class LogsConsole {

    public void analyzeLog() {
        LogEntries logEntries = WebDriverRunner.getWebDriver().manage().logs().get(LogType.BROWSER);
        for (LogEntry entry : logEntries) {
            System.out.println(new Date(entry.getTimestamp()) + " " + entry.getLevel() + " " + entry.getMessage());
        }
    }

}
