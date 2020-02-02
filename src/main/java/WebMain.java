import domain.car.Car;
import domain.game.RacingGame;
import domain.game.Track;
import domain.random.RandomGenerator;
import domain.strategy.RandomMovingStrategy;
import dto.CarDto;
import util.Splitter;

import java.util.HashMap;
import java.util.List;
import java.util.Random;

import static java.util.stream.Collectors.toList;
import static spark.Spark.get;
import static spark.Spark.post;
import static util.RenderUtil.render;
import static util.Splitter.COMMA;

public class WebMain {
    public static void main(String[] args) {
        get("/", (req, res) -> render(new HashMap<>(), "/index.html"));
        post("/name", (req, res) -> {
            HashMap<String, Object> model = new HashMap<>();
            String names = req.queryParams("names");

            List<String> splitNames = Splitter.splitBlank(names);
            model.put("names", splitNames);
            model.put("nameStrings", String.join(COMMA, splitNames));
            return render(model, "/game.html");
        });

        get("/result", (req, res) -> {
            HashMap<String, Object> model = new HashMap<>();
            int turn = Integer.parseInt(req.queryParams("turn"));
            String names = req.queryParams("names");

            final Random generate = RandomGenerator.generate();
            final RandomMovingStrategy movingStrategy = new RandomMovingStrategy(generate);
            final RacingGame racingGame = RacingGame.newInstance(Splitter.splitComma(names), turn, movingStrategy);

            while (!racingGame.isDone()) {
                racingGame.proceedAndGetTrack();
            }

            Track track = racingGame.getTrack();

            List<CarDto> carDtos = track.getCars()
                    .stream()
                    .map(CarDto::new)
                    .collect(toList());
            List<String> nameOfWinners = racingGame.getWinner()
                    .stream()
                    .map(Car::getName)
                    .collect(toList());


            model.put("cars", carDtos);
            model.put("winners", nameOfWinners);
            return render(model, "/result.html");
        });
    }
}