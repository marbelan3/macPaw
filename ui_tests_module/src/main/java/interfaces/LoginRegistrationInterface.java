package interfaces;

import helpers.UserForTest;
import io.qameta.allure.Step;

public interface LoginRegistrationInterface {

    void doLogin(UserForTest userForTest);

    void doRegistration(UserForTest userForTest);
}
