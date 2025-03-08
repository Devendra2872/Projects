package org.example.callable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public class Sorter implements Callable<List<Integer>> {

    private List<Integer> arrayToSort;
    private ExecutorService es;

    public Sorter(List<Integer> arrayToSort, ExecutorService es){
        this.arrayToSort = arrayToSort;
        this.es = es;
    }

    @Override
    public List<Integer> call() throws Exception{
        System.out.println("Thread name is "+ Thread.currentThread().getName() +
                "Array is "+ arrayToSort.toString());

        if (arrayToSort.size() <= 1){
            return arrayToSort;
        }

        int mid = arrayToSort.size()/2;

        List<Integer> leftArray = new ArrayList<>();
        for (int i=0; i<mid; i++){
            leftArray.add(arrayToSort.get(i));
        }

        List<Integer>rightArray = new ArrayList<>();
        for (int i=mid; i<arrayToSort.size(); i++){
            rightArray.add(arrayToSort.get(i));
        }

        Sorter leftSorter = new Sorter(leftArray,es);
        Sorter rightSorter = new Sorter(rightArray,es);

        Future<List<Integer>> leftSortedArrayfuture = es.submit(leftSorter);
        Future<List<Integer>> rightSortedArrayfuture = es.submit(rightSorter);

        List<Integer> sortedArray = new ArrayList<>();
        int i=0, j=0;

        while (i < leftSortedArrayfuture.get().size() && j < rightSortedArrayfuture.get().size()){
            if(leftSortedArrayfuture.get().get(i) > rightSortedArrayfuture.get().get(j)){
                sortedArray.add(leftSortedArrayfuture.get().get(i));
                i++;
            }
            else{
                sortedArray.add(rightSortedArrayfuture.get().get(j));
                j++;
            }
        }

        while (i < leftSortedArrayfuture.get().size()){
            sortedArray.add(leftSortedArrayfuture.get().get(i));
            i++;
        }

        while (j < rightSortedArrayfuture.get().size()){
            sortedArray.add(rightSortedArrayfuture.get().get(j));
            j++;
        }

        return sortedArray;
    }
}
