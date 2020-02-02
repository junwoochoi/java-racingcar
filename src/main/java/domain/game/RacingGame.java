package domain.game;

import domain.car.Car;
import domain.car.Cars;
import domain.strategy.MovingStrategy;
import spark.utils.CollectionUtils;

import java.util.List;

public class RacingGame {
    private final Cars cars;
    private final MovingStrategy movingStrategy;
    private int countOfTurns;

    private RacingGame(List<String> carNames, int countOfTurns, MovingStrategy movingStrategy) {
        validate(carNames, countOfTurns);

        this.movingStrategy = movingStrategy;
        this.countOfTurns = countOfTurns;
        cars = Cars.createCars(carNames);
    }

    private void validate(List<String> carNames, int countOfTry) {
        if (countOfTry <= 0) {
            throw new IllegalArgumentException("시도 횟수는 항상 0보다 커야 합니다");
        }

        if (CollectionUtils.isEmpty(carNames)) {
            throw new IllegalArgumentException("자동차 대수는 NULL 이거나 비어있을 수 없습니다.");
        }
    }

    public static RacingGame newInstance(List<String> carNames, int countOfTurns, MovingStrategy movingStrategy) {
        return new RacingGame(carNames, countOfTurns, movingStrategy);
    }

    public boolean isDone() {
        return this.countOfTurns == 0;
    }

    public Track proceedAndGetTrack() {
        proceedOneTurn();
        return cars.getTracks();
    }

    private void proceedOneTurn() {
        validateLeftTurnCounts();
        cars.moveAll(movingStrategy);
        this.countOfTurns--;
    }

    private void validateLeftTurnCounts() {
        if (countOfTurns < 1) {
            throw new IllegalArgumentException("모든 턴을 플레이했습니다.");
        }
    }

    public List<Car> getWinner() {
        return cars.getWinners();
    }

    public Track getTrack() {
        return cars.getTracks();
    }
}
