public class TribeCook implements Runnable {
    private Pot pot;

    public TribeCook(Pot pot) {
        this.pot = pot;
    }

    @Override
    public void run() {
        while (Thread.activeCount() > 2) {
            while (pot.canEat()) {
                if (Thread.activeCount() == 2) {
                    break;
                }
            }
            System.out.println("Refilling!");
            pot.refill();
        }
    }
}
