package game;

import car.Car;
import spark.utils.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

public class TrackDto {
    List<Car> cars = new ArrayList<>();

    public List<Car> getCars() {
        return new ArrayList<>(cars);
    }

    public TrackDto(List<Car> cars) {
        validate(cars);
        this.cars.addAll(cars);
    }

    private void validate(List<Car> cars) {
        if (CollectionUtils.isEmpty(cars)) {
            throw new IllegalArgumentException("cars 가 비어있습니다.");
        }
    }
}
