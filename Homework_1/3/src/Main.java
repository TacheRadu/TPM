import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    // Run with debug. Intellij adds another thread, and that messes my logic for the cook.
    public static void main(String[] args) {
        Pot pot = null;
        TribeCook cook;
        List<TribeMember> tribeMembers;
        Thread cookThread;
        long start;
        boolean typeValues[] = {false, true};
        int tribeSize[] = {10, 100, 1000, 10000, 10000};
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter("benchmarks.txt"));
            for (int siz : tribeSize) {
                for (boolean b : typeValues) {
                    pot = new Pot(5, b);
                    cook = new TribeCook(pot);
                    Pot finalPot = pot;
                    tribeMembers = IntStream.range(0, siz)
                            .mapToObj(i -> new TribeMember(finalPot))
                            .collect(Collectors.toCollection(ArrayList::new));
                    cookThread = new Thread(cook);
                    start = System.currentTimeMillis();
                    tribeMembers.forEach(tribeMember -> new Thread(tribeMember).start());
                    cookThread.start();
                    try {
                        cookThread.join();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (b){
                        writer.write("Point b implementation time for " + siz + " tribe members: " + (System.currentTimeMillis() - start) + "ms");
                        writer.newLine();
                    }
                    else {
                        writer.write("Point a implementation time for " + siz + " tribe members: " + (System.currentTimeMillis() - start) + "ms");
                        writer.newLine();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
