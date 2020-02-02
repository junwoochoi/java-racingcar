package domain.car;

import domain.strategy.MovingStrategy;
import spark.utils.Assert;

public class Car {
    public static final int MOVE_DISTANCE = 1;
    public static final String PATH_MARK = "-";
    private int position = 0;
    private String name;

    private Car(String name) {
        Assert.notNull(name);
        this.name = name;
    }

    public static Car newInstance(String name) {
        return new Car(name);
    }
    

    public void move(MovingStrategy movingStrategy) {
        Assert.notNull(movingStrategy);
        if (!movingStrategy.isMovable()) {
            return;
        }

        this.position += MOVE_DISTANCE;
    }

    public String getName() {
        return this.name;
    }

    public int getPosition() {
        return this.position;
    }
}
