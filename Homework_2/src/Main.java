import lists.List;
import lists.OptimisticList;
import lists.VersionedOptimisticList;
import test.Adder;
import test.ContainsChecker;
import test.Remover;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    private void test(List list, BufferedWriter writer){
            Thread t1 = new Thread(new Adder(0, 75000, list, writer));
            Thread t2 = new Thread(new Adder(75000, 25000, list, writer));
            Thread t3 = new Thread(new Remover(list, writer));
            Thread t4 = new Thread(new ContainsChecker(list, writer));
            t1.start();
            try {
                t1.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            t2.start();
            t3.start();
            t4.start();
            try {
                t2.join();
                t3.join();
                t4.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
    }


    public static void main(String[] args) {
        Main m = new Main();
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter("report.txt"));
            writer.write("Optimistic List benchmarks:\n");
            m.test(new OptimisticList<Integer>(), writer);
            writer.write("Versioned optimistic List benchmarks:\n");
            m.test(new VersionedOptimisticList<Integer>(), writer);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
