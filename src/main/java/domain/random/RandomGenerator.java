package domain.random;

import java.util.Random;

public class RandomGenerator {
    private RandomGenerator(){

    }

    public static Random generate(){
        return new Random(System.currentTimeMillis());
    }


}
