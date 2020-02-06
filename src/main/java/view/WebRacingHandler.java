package view;

import domain.car.Car;
import domain.game.RacingGame;
import domain.game.Track;
import domain.random.RandomGenerator;
import domain.strategy.RandomMovingStrategy;
import dto.CarDto;
import dto.RacingGameRequestDto;
import spark.Route;
import util.Splitter;

import java.util.HashMap;
import java.util.List;
import java.util.Random;

import static java.util.stream.Collectors.toList;
import static util.RenderUtil.render;
import static util.Splitter.COMMA;

public class WebRacingHandler {
    public Route handleGetName() {
        return (req, res) -> {
            HashMap<String, Object> model = new HashMap<>();
            String names = req.queryParams("names");

            List<String> splitNames = Splitter.splitBlank(names);
            model.put("names", splitNames);
            model.put("nameStrings", String.join(COMMA, splitNames));
            return render(model, "/game.html");
        };
    }

    public Route handleResult() {
        return (req, res) -> {
            final HashMap<String, Object> model = new HashMap<>();
            final RacingGameRequestDto racingGameRequestDto = new RacingGameRequestDto(Integer.parseInt(req.queryParams("turn")), req.queryParams("names"));

            final RacingGame racingGame = generateRacingGame(racingGameRequestDto);

            finishGame(racingGame);
            Track track = racingGame.getTrack();

            List<CarDto> carDtos = mapToCarDto(track);
            List<String> nameOfWinners = mapToName(racingGame);


            model.put("cars", carDtos);
            model.put("winners", nameOfWinners);
            return render(model, "/result.html");
        };
    }

    private void finishGame(RacingGame racingGame) {
        while (!racingGame.isDone()) {
            racingGame.proceedOneTurn();
        }
    }

    private RacingGame generateRacingGame(RacingGameRequestDto racingGameRequestDto) {
        final Random generate = RandomGenerator.generate();
        final RandomMovingStrategy movingStrategy = new RandomMovingStrategy(generate);
        return RacingGame.newInstance(racingGameRequestDto.getNames(), racingGameRequestDto.getTurn(), movingStrategy);
    }

    private List<String> mapToName(RacingGame racingGame) {
        return racingGame.getWinner()
                .stream()
                .map(Car::getName)
                .collect(toList());
    }

    private List<CarDto> mapToCarDto(Track track) {
        return track.getCars()
                .stream()
                .map(CarDto::new)
                .collect(toList());
    }
}
