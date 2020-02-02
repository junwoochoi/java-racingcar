package domain.random;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("domain.random 생성기 테스트")
class RandomGeneratorTest {

    @Test
    @DisplayName("랜덤 객체를 생성한다")
    void testRandomGenerate() {
        //when
        final Random random = RandomGenerator.generate();

        //then
        assertThat(random).isNotNull();
    }

}