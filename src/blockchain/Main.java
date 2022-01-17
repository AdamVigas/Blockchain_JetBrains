package blockchain;

import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


public class Main {

    private static ArrayList<Block> blockchain = new ArrayList<>();
    static int number;
    static long result;

    public static void main(String[] args) throws InterruptedException {

        number = 0;
        int processors = 5;
        ExecutorService executorMiner = Executors.newFixedThreadPool(processors);

        for(int i = 0 ; i < 5; i++) {
            int taskNumber = i;
            executorMiner.submit(() -> {
                createBlock();
            });
        }
        executorMiner.shutdownNow();

        executorMiner.awaitTermination(10, TimeUnit.SECONDS);

    }

    public static synchronized void createBlock() {
        Miner m = new Miner(blockchain,number);
        result = (new Date().getTime() - m.block.getTimeStamp()) / 1000;
        number = m.calcNumber(result);
    }



}

