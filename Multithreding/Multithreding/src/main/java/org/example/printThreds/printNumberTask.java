package org.example.printThreds;

public class printNumberTask implements Runnable{
    private Integer noToPrint;

    printNumberTask(Integer x){
        this.noToPrint = x;
    }
    @Override
    public void run() {
        System.out.println("Number is " + noToPrint +" name is "+
                Thread.currentThread().getName());
    }
}
