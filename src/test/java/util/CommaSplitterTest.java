package util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("쉼표를 기준으로 나누는 클래스 테스트")
class CommaSplitterTest {

    @ParameterizedTest
    @DisplayName("쉼표를 기준으로 주어진 문자열을 나눈다")
    @CsvSource({"'1,2,3,4',4", "'1,2',2"})
    void testSplit(String target, int length) {
        //when
        List<String> splitted = Splitter.splitComma(target);

        //then
        assertThat(splitted).isNotEmpty()
                .hasSize(length);
    }

}