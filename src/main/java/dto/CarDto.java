package dto;

import domain.car.Car;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static util.Splitter.BLANK;

public class CarDto {
    private String name;
    private int position;
    private List<String> whiteSpaceList;

    public CarDto(Car car) {
        assert car != null;
        this.name = car.getName();
        this.position = car.getPosition();
        this.whiteSpaceList = IntStream.range(0, this.position)
                .mapToObj(num -> BLANK)
                .collect(Collectors.toList());
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }


    public List<String> getWhiteSpaceList() {
        return whiteSpaceList;
    }

}
