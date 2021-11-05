import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        Pot pot = new Pot(5);
        TribeCook cook = new TribeCook(pot);
        List<TribeMember> tribeMembers = IntStream.range(0, 30)
                .mapToObj(i -> new TribeMember(pot))
                .collect(Collectors.toCollection(ArrayList::new));
        Thread cookThread = new Thread(cook);
        tribeMembers.forEach(tribeMember -> new Thread(tribeMember).start());
        cookThread.start();
        try {
            cookThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
