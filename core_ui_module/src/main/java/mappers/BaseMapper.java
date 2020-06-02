package mappers;

import junitparams.mappers.IdentityMapper;
import org.apache.commons.lang3.RandomUtils;

import java.util.Arrays;
import java.util.List;

public abstract class BaseMapper extends IdentityMapper {

    protected String[] splitLineByComa(String line) {
        return line.split(",");
    }

    protected String splitStringByWhitespaceAndGetRandomItemFrom(String value) {
        return value.split(" ")[RandomUtils.nextInt(0, value.split(" ").length)];
    }

    protected List<String> splitStringByWhitespaceAngGetAsList(String line) {
        String[] strings = line.split(" ");
        return Arrays.asList(strings);
    }

}
