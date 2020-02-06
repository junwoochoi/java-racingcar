package dto;

import util.Splitter;

import java.util.List;

public class RacingGameRequestDto {
    private int turn;
    private List<String> names;

    public RacingGameRequestDto(int turn,String names) {
        assert turn == 0 && names != null;
        this.names = Splitter.splitComma(names);
        this.turn = turn;
    }

    public int getTurn() {
        return turn;
    }

    public List<String> getNames() {
        return names;
    }
}
