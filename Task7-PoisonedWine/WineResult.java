import java.util.List;

public class WineResult {
    boolean possible;
    List<List<Integer>>  schedule;
    Decoder decoder;

    public WineResult(boolean possible, List<List<Integer>> schedule, Decoder decoder) {
        this.possible = possible;
        this.schedule = schedule;
        this.decoder = decoder;
    }
}
