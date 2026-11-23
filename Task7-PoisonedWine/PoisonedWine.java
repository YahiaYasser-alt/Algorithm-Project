import java.util.ArrayList;
import java.util.List;

public class PoisonedWine {

    static final int NUM_BARRELS = 1000;

    static boolean isPossible(int testers) {
        long patterns = 1;
        for (int i = 0; i < testers; i++) {
            patterns *= 2;
            if (patterns >= NUM_BARRELS) {
                return true;
            }
        }
        return false;
    }

    static void build(int l, int r, int level, List<List<Integer>> schedule){
        if(l == r)
            return;

        int mid = (l+r)/2;
        for(int barrel = mid+1; barrel <= r; barrel++){
            schedule.get(level).add(barrel);
        }

        build(l, mid, level+1, schedule);
        build(mid+1, r, level+1, schedule);
    }

    static WineResult designWineTests(int testers) {
        if (!isPossible(testers)) {
            return new WineResult(false, null, null);
        }

        List<List<Integer>> schedule = new ArrayList<>();
        for (int t = 0; t < testers; t++) {
            schedule.add(new ArrayList<>());
        }
        build(0, NUM_BARRELS - 1, 0, schedule);
        return new WineResult(true, schedule, new WineDecoder(NUM_BARRELS));
    }
}