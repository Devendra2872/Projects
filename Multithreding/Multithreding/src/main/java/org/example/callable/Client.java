package org.example.callable;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Client {
    public static void main(String[] args) throws Exception{
        System.out.println("Main thred name is " +Thread.currentThread().getName());
        List<Integer> arrayToSort = List.of(1,43,34,57,7,78,84,32);

        ExecutorService es = Executors.newCachedThreadPool();
        Sorter sorter = new Sorter(arrayToSort,es);

        Future<List<Integer>> sortedArrayFutures = es.submit(sorter);

        List<Integer> sortedList = sortedArrayFutures.get();

        for (Integer in: sortedList){
            System.out.println(in);
        }
        es.shutdown();
    }
}
