package view;

import domain.car.Car;
import domain.game.RacingGame;
import domain.game.TrackDto;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class OutputView {

    public static final String COMMA = ",";

    private OutputView() {

    }

    public static void print(RacingGame racingGame) {
        while (!racingGame.isDone()) {
            printTracks(racingGame.proceedAndGetTrack());
            System.out.println("\n------------\n");
            sleep(1);
        }

        printWinner(racingGame.getWinner());


    }

    private static void sleep(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void printWinner(List<Car> winner) {
        final String winners = winner.stream()
                .map(Car::getName)
                .collect(Collectors.joining(COMMA));
        System.out.println("승자는  # " + winners + " # 축하합니다~");
    }

    private static void printTracks(TrackDto tracks) {
        for (Car car : tracks.getCars()) {
            final String history = IntStream.range(0, car.getPosition())
                    .mapToObj(num -> "-")
                    .collect(Collectors.joining(""));
            System.out.println(car.getName() + history);
        }
    }


}



