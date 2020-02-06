package dto;

import domain.car.Car;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static util.Splitter.BLANK;

public class CarDto {
    private String name;
    private List<String> whiteSpaceList;

    public CarDto(Car car) {
        assert car != null;
        this.name = car.getName();
        this.whiteSpaceList = IntStream.range(0, car.getPosition())
                .mapToObj(num -> BLANK)
                .collect(Collectors.toList());
    }

    public String getName() {
        return name;
    }

    public List<String> getWhiteSpaceList() {
        return whiteSpaceList;
    }

}
