public class Main{
    public static void main(String[] args) {

        System.out.println("Test case 1:8 testers");
        WineResult r1 = PoisonedWine.designWineTests(8);
        System.out.println("possible: " + r1.possible);
        System.out.println("schedule: " + r1.schedule);
        System.out.println("decoder:  " + r1.decoder);
        System.out.println();

        System.out.println("Test case 2: 10 testers");
        WineResult r2 = PoisonedWine.designWineTests(10);
        System.out.println("possible: " + r2.possible);
        for (int t = 0; t < r2.schedule.size(); t++) {
            System.out.println("Tester " + t + " drinks " + r2.schedule.get(t).size()
                    + " barrels, first: " + r2.schedule.get(t));
        }
        boolean[] dead1 = new boolean[10];
        dead1[0] = true; // first tester died -> the poisoned barrel is the middle one (500)
        System.out.println(r2.decoder.decode(dead1));
    }
}