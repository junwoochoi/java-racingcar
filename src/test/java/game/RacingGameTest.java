package game;

import domain.car.Car;
import domain.game.RacingGame;
import domain.random.RandomGenerator;
import domain.strategy.RandomMovingStrategy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("game.RacingGame 단위 테스트")
class RacingGameTest {

    private RandomMovingStrategy movingStrategy = new RandomMovingStrategy(RandomGenerator.generate());


    @Test
    @DisplayName("자동차의 이름들과, 실행할 횟수를 전하면 RacingGame을 만들 수 있다")
    void testCreate() {
        //given
        List<String> carNames = Stream.of("junwoo", "junwoochoi", "junu")
                .collect(Collectors.toList());
        int countOfTry = 5;

        //when
        RacingGame racingGame = RacingGame.newInstance(carNames, countOfTry, movingStrategy);

        //then
        assertThat(racingGame).isNotNull();
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, -5, 0})
    @DisplayName("실행할 횟수는 음수 혹은 0 될 수 없다")
    void testValidate(int countOfTry) {
        //given
        List<String> carNames = Stream.of("junwoo", "junwoochoi", "junu")
                .collect(Collectors.toList());

        //then
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                //when
                () -> RacingGame.newInstance(carNames, countOfTry, movingStrategy));
        assertThat(exception.getMessage()).isEqualToIgnoringCase("시도 횟수는 항상 0보다 커야 합니다");
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("자동차의 이름이 들어있는 List는 비어있거나, null일 수 없다")
    void testValidate(List<String> carNames) {
        //then
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                //when
                () -> RacingGame.newInstance(carNames, 5, movingStrategy));
        assertThat(exception.getMessage()).isEqualToIgnoringCase("자동차 대수는 NULL 이거나 비어있을 수 없습니다.");
    }

    @Test
    @DisplayName("이미 턴이 모두 수행된 게임은 더이상 실행할 수 없다")
    void testValidateTurns() {
        //given
        List<String> carNames = Stream.of("junwoo", "junwoochoi", "junu")
                .collect(Collectors.toList());
        final RacingGame racingGame = RacingGame.newInstance(carNames, 2, movingStrategy);

        racingGame.proceedAndGetTrack();
        racingGame.proceedAndGetTrack();
        //then
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                //when
                racingGame::proceedAndGetTrack);
        assertThat(exception.getMessage()).isEqualToIgnoringCase("모든 턴을 플레이했습니다.");
    }

    @Test
    @DisplayName("모든 턴을 플레이하면 winner를 가지고 있다.")
    void testWinner() {
        //given
        List<String> carNames = Stream.of("junwoo", "junwoochoi", "junu")
                .collect(Collectors.toList());
        final RacingGame racingGame = RacingGame.newInstance(carNames, 2, movingStrategy);

        //when
        while (!racingGame.isDone()) {
            racingGame.proceedAndGetTrack();
        }
        final List<Car> winner = racingGame.getWinner();

        //then
        assertThat(winner).isNotEmpty();
    }

    @Test
    @DisplayName("모든 턴을 플레이하지 않았으면 현재까지의 승자를 리턴한다.")
    void testEmptyWinner() {
        //given
        List<String> carNames = Stream.of("junwoo", "junwoochoi", "junu")
                .collect(Collectors.toList());
        final RacingGame racingGame = RacingGame.newInstance(carNames, 2, movingStrategy);

        //when
        final List<Car> winner = racingGame.getWinner();

        //then
        assertThat(winner).isNotEmpty();
    }


}