package org.example.printThreds;

public class main {
    public static void main(String[] args) {
        System.out.println("Hello, World! "+ Thread.currentThread().getName());
        for (int i=0; i<100; i++){
            printNumberTask pt = new printNumberTask(i);
            Thread t = new Thread(pt);
            t.start();
        }
    }
}
