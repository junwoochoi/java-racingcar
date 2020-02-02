package domain.car;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("domain.car.Car 객체 단위 테스트")
class CarTest {

    @Test
    @DisplayName("Car는 자기자신의 이름이 주어지면 객체를 생성한다.")
    void createCarTest() {
        //when
        Car car = Car.newInstance("junwoochoi");

        //then
        assertThat(car).isNotNull();
    }


    @ParameterizedTest
    @DisplayName(" 자동차는 자기이름을 반환한다")
    @ValueSource(strings = {"junwoo", "hello?"})
    void testName(String expectedName) {
        //given
        Car car = Car.newInstance(expectedName);

        //when
        final String carName = car.getName();

        //then
        assertThat(carName).isEqualTo(expectedName);
    }


    @ParameterizedTest
    @DisplayName("domain.car.Car 는 주어진 조건이 true일 때 이동한다")
    //given
    @CsvSource({"true,1", "false,0"})
    void testMove(boolean movingCondition, int result) {
        //when
        Car car = Car.newInstance("junwoochoi");
        int beforePosition = car.getPosition();
        car.move(() -> movingCondition);
        int afterPosition = car.getPosition();

        //then
        assertThat(afterPosition-beforePosition).isEqualTo(result);
    }


}