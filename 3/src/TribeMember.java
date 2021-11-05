public class TribeMember implements Runnable {
    private Pot pot;

    public TribeMember(Pot pot) {
        this.pot = pot;
    }

    @Override
    public void run() {
        pot.eat();
        System.out.println("Member " + Thread.currentThread().getId() + " just ate");
    }
}
