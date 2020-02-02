package domain.strategy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;

@DisplayName("랜덤 정책 테스트")
public class RandomMovingStrategyTest {

    @ParameterizedTest
    @DisplayName("4 이상이면 true, 4미만 이면 false를 리턴한다")
    @CsvSource({"1,false", "4,true"})
    void testRandom(int value, boolean result) {
        //given
        Random random = mock(Random.class);
        given(random.nextInt(anyInt())).willReturn(value);
        RandomMovingStrategy randomMovingStrategy = new RandomMovingStrategy(random);

        //when
        boolean check = randomMovingStrategy.isMovable();

        //then
        assertThat(check).isEqualTo(result);
    }
}