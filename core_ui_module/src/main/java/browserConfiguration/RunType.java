package browserConfiguration;

public enum RunType {

    PROPERTIES(System.getProperty("runType")),
    REMOTE_SELENOID("selenoid"),
    BROWSERSTACK("browserstack"),
    LOCAL("local");

    private String type;

    RunType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return this.type;
    }

}
