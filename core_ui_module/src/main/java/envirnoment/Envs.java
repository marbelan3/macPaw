package envirnoment;

public enum Envs {
    PROPERTIES(System.getProperty("envDomain")),
    TEST("test-test.com"),
    PROD("http://rozetka.com.ua");

    private String domain;

    Envs(String domainValue) {
        this.domain = domainValue;
    }

    @Override
    public String toString() {
        return this.domain;
    }
}
