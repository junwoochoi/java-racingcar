package domain.car;

import domain.game.TrackDto;
import domain.strategy.MovingStrategy;
import spark.utils.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Cars {
    private List<Car> cars = new ArrayList<>();

    private Cars(List<String> carNames) {
        validateCarNames(carNames);
        for (String carName : carNames) {
            final Car newCar = Car.newInstance(carName);
            this.cars.add(newCar);
        }
    }

    private void validateCarNames(List<String> carNames) {
        if (CollectionUtils.isEmpty(carNames)) {
            throw new IllegalArgumentException("자동차 대수는 NULL 이거나 비어있을 수 없습니다.");
        }
    }

    public static Cars createCars(List<String> carNames) {
        return new Cars(new ArrayList<>(carNames));
    }

    public void moveAll(MovingStrategy movingStrategy) {
        for (Car car : cars) {
            car.move(movingStrategy);
        }
    }

    private int findMaxPosition() {
        return cars.stream()
                .map(Car::getPosition)
                .max(Integer::compareTo)
                .orElse(0);
    }

    public List<Car> getWinners() {
        return cars.stream()
                .filter(car -> car.getPosition() == findMaxPosition())
                .collect(Collectors.toList());
    }

    public TrackDto getTracks() {
        return new TrackDto(new ArrayList<>(cars));
    }
}
