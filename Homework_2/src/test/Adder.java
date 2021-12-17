package test;

import lists.List;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.Buffer;

public class Adder implements Runnable{

    private List list;
    private Integer start, count;
    private BufferedWriter writer;

    public Adder(Integer start, Integer count, List list, BufferedWriter writer){
        this.list = list;
        this.start = start;
        this.count = count;
        this.writer = writer;
    }

    @Override
    public void run() {
        if(count == 0) return;
        long startTime = System.currentTimeMillis();
        for(int i = 0; i < count; i++){
            list.add(start + i);
        }
        try {
            writer.write("Add time: " + ((double)(System.currentTimeMillis() - startTime)) / ((double)count) + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
