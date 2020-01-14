import java.util.Random;

public class RandomMovingStrategy implements MovingStrategy {
    public static final int BOUND = 10;
    public static final int MINIMUM_STANDARD = 4;
    private Random random;

    RandomMovingStrategy(Random random) {
        this.random = random;
    }

    @Override
    public boolean check() {
        return random.nextInt(BOUND) >= MINIMUM_STANDARD;
    }
}
