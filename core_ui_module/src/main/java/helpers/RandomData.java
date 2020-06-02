package helpers;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;

import java.util.List;

public class RandomData {
    public static String getRandomItemFromListOf(List<String> list) {
        return list.get(RandomUtils.nextInt(0, list.size()));
    }

    public static String generateRandomString() {
        return RandomStringUtils.randomAlphanumeric(8);
    }

    public static String generateRandomString(int x) {
        return RandomStringUtils.randomAlphanumeric(x);
    }

    public static String getRandomEmail() {
        return generateRandomString(10) + "@" + generateRandomString(4) + ".com";
    }

    public static String getRandomPassportId() {
        return getRandomPassportId(2, 6);
    }

    public static String getRandomPassportId(int countOfChars, int countOfDigits) {
        return RandomStringUtils.random(countOfChars, "ABCDEFGHJKLMNOPQRSTUVWXYZ") + RandomStringUtils.randomNumeric(countOfDigits);
    }

    public static String getRandomNumber() {
        return getRandomNumber(9);
    }

    public static String getRandomNumber(int length) {
        return RandomStringUtils.randomNumeric(length);
    }
}
