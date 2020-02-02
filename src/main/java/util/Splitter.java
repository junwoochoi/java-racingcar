package util;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Splitter {

    public static final String COMMA = ",";
    public static final String BLANK = " ";

    private Splitter() {
    }

    public static List<String> splitComma(String target) {
        return Arrays.stream(target.split(COMMA))
                .collect(Collectors.toList());
    }

    public static List<String> splitBlank(String target) {
        return Arrays.stream(target.split(BLANK))
                .collect(Collectors.toList());
    }
}
