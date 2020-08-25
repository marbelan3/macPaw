package helpers;

import com.google.common.collect.ImmutableList;
import io.qameta.allure.Step;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserForTest {

    public List<String> domains = new ArrayList<>(ImmutableList.of("@mailsac.com"));

    private static final List<String> cyrillicSlavMNames = Arrays.asList("Роман", "Андрей", "Евгений", "Эдуард", "Алексей", "Александр", "Валерий", "Игорь", "Ярослав", "Виктор", "Иван", "Олег", "Виталий", "Дмитрий", "Тарас", "Юрий", "Василий", "Вадим", "Максим");
    private static final List<String> cyrillicSlavSurnames = Arrays.asList("Шевченко", "Попов", "Емельяненко", "Петренко", "Островский", "Сытник", "Черненко", "Дацюк", "Поларуш", "Гультачук", "Яковенко", "Томачов", "Корибко", "Марченко", "Наумец", "Ситар", "Гурик", "Павлюк", "Олейник");
    private static final List<String> latMNames = Arrays.asList("James", "John", "Robbert", "David", "Joseph", "Mark", "Steven", "Brian", "Eric", "Donald", "Andrew", "Scott", "Jose", "George", "Michael", "Joshua", "Matthew", "Ethan", "Andrew", "Daniel", "Anthony", "Christopher", "Joseph", "William", "Ryan", "David", "Nicholas");
    private static final List<String> latSurnames = Arrays.asList("Wood", "Myers", "Cook", "Dixon", "Harrison", "Reed", "Patterson", "Murray", "Stone", "Fox", "Cole", "Cox", "Wilkinson", "Smith", "Johnson", "Williams", "Jones", "Brown", "Davis", "Miller", "Wilson", "Moore", "Taylor", "Anderson", "Thomas");


    private String EMAIL = generateRandomTempMailEmail();
    private String PASSWORD = RandomData.generateRandomString(10) + RandomData.getRandomNumber(5);
    private String USER_NAME = RandomData.getRandomItemFromListOf(cyrillicSlavMNames);
    private String USER_SURNAME = RandomData.getRandomItemFromListOf(cyrillicSlavSurnames);

    @Step
    private String getRandomTempMailDomain() {
        return RandomData.getRandomItemFromListOf(domains);

    }

    @Step
    private String generateRandomTempMailEmail() {
        String randStr = RandomData.getRandomNumber(5).toLowerCase();
        return RandomData.getRandomItemFromListOf(latMNames).toLowerCase() +
                RandomData.getRandomItemFromListOf(latSurnames).toLowerCase() + "_" + randStr + getRandomTempMailDomain();
    }

    @Override
    public String toString() {
        return "UserForTest{" +
                "EMAIL='" + EMAIL + '\'' +
                ", PASSWORD='" + PASSWORD + '\'' +
                ", USER_NAME='" + USER_NAME + '\'' +
                ", USER_SURNAME='" + USER_SURNAME + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserForTest that = (UserForTest) o;
        return Objects.equals(EMAIL, that.EMAIL) &&
                Objects.equals(PASSWORD, that.PASSWORD) &&
                Objects.equals(USER_NAME, that.USER_NAME) &&
                Objects.equals(USER_SURNAME, that.USER_SURNAME);
    }

    @Override
    public int hashCode() {
        return Objects.hash(EMAIL, PASSWORD, USER_NAME, USER_SURNAME);
    }

    public String getEMAIL() {
        return EMAIL;
    }

    public String getPASSWORD() {
        return PASSWORD;
    }

    public String getUSER_NAME() {
        return USER_NAME;
    }

    public String getUSER_SURNAME() {
        return USER_SURNAME;
    }

    public void setUSER_SURNAME(String USER_SURNAME) {
        this.USER_SURNAME = USER_SURNAME;
    }

    public void setEMAIL(String EMAIL) {
        this.EMAIL = EMAIL;
    }

    public void setPASSWORD(String PASSWORD) {
        this.PASSWORD = PASSWORD;
    }

    public void setUSER_NAME(String USER_NAME) {
        this.USER_NAME = USER_NAME;
    }

    public UserForTest(String EMAIL, String PASSWORD, String USER_NAME,String USER_SURNAME) {
        this.EMAIL = EMAIL;
        this.PASSWORD = PASSWORD;
        this.USER_NAME = USER_NAME;
        this.USER_SURNAME = USER_SURNAME;

    }

    public UserForTest() {
        AllureUIUtil.paramNameValue("UserForTest : \nEMAIL = " + getEMAIL() + " \nPASSWORD = " + getPASSWORD() + " \nUSER_NAME = " + getUSER_NAME() + " \nUSER_SURNAME = " + getUSER_SURNAME());
        Logger.getGlobal().log(Level.INFO, "UserForTest : \nEMAIL = " + getEMAIL() + " \nPASSWORD = " + getPASSWORD() + " \nUSER_NAME = " + getUSER_NAME() + " \nUSER_SURNAME = " + getUSER_SURNAME());
    }

    public UserForTest getValidUserForTest() {
        return new UserForTest("marbelan3@gmail.com", "Marbels1988", "Mike", "Willkinson");
    }


}
