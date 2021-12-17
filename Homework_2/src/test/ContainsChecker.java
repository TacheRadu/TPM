package test;

import lists.List;

import java.io.BufferedWriter;
import java.io.IOException;

public class ContainsChecker implements Runnable{

    private List list;
    private BufferedWriter writer;

    public ContainsChecker(List list, BufferedWriter writer){
        this.list = list;
        this.writer = writer;
    }

    @Override
    public void run() {
        long startTime = System.currentTimeMillis();
        for(int i = 0; i < 100000; i+= 5){
            list.contains(i);
        }
        try {
            writer.write("Check if in list time: " + ((double)(System.currentTimeMillis() - startTime)) / 20000 + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
