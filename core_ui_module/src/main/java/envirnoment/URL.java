package envirnoment;

public enum URL {
    PROPERTIES(System.getProperty("envDomain")),
    CLINONE("https://www.clinone.com/"),
    CLINONE_TEST("https://test1.clinone.com/"),
    TEST("test-test.com"),
    PROD("http://rozetka.com.ua"),
    GLOBEIN("http://globein.com");

    private String domain;

    URL(String domainValue) {
        this.domain = domainValue;
    }

    @Override
    public String toString() {
        return this.domain;
    }
}
