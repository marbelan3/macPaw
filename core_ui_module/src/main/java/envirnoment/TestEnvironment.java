package envirnoment;

public interface TestEnvironment {
    Envs globalEnvValue = Envs.PROPERTIES;

//gradle :ui_test_module:rozetka -DenvDomain=https://rozetka.com.ua

    /** to debug local, change Envs.PROPERTIES to needed enum*/
}
