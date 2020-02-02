import domain.game.RacingGame;
import domain.random.RandomGenerator;
import domain.strategy.RandomMovingStrategy;
import view.InputView;
import view.OutputView;

import java.util.List;
import java.util.Random;

public class GameController {

    public void init() {
        final List<String> inputCarNames = InputView.inputCarNames();
        final int countOfTurns = InputView.inputCountOfTurns();


        final Random generate = RandomGenerator.generate();
        final RandomMovingStrategy movingStrategy = new RandomMovingStrategy(generate);
        final RacingGame racingGame = RacingGame.newInstance(inputCarNames, countOfTurns, movingStrategy);


        OutputView.print(racingGame);
    }
}
