package com.multithreading.udemy.forkjoin.forkjoin;

import java.util.concurrent.RecursiveAction;

public class PrintTaskRecursiveAction extends RecursiveAction {

    private int start, end;

    public PrintTaskRecursiveAction(int start, int end) {
        this.start = start;
        this.end = end;
    }

    protected void compute() {

        if (end - start <= 2) {
            for (int i = start; i < end; i++) {
                System.out.println(i);
            }
            return;
        }

        int mid = (start + end) / 2;

        invokeAll(new PrintTaskRecursiveAction(start, mid), new PrintTaskRecursiveAction(mid, end)
        );
    }
}
