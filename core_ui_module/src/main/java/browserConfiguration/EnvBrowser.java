package browserConfiguration;

public interface EnvBrowser {
    Browsers browser = Browsers.CHROME;
    RunType runType = RunType.LOCAL;

    /**Example to run test : gradle clean :ui_test_module:rozetka -Dbrowser=chrome -DrunType=local -DenvDomain=https://**********.com*/
}
