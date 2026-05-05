package com.multithreading.udemy.coroutinesandcontinuation;

// Coroutine like behaviour
public class CoroutineSimulator {

    static class Coroutine {

        private int state = 0;

        public boolean resume() {
            switch (state) {
                case 0:
                    System.out.println("Step 1");
                    state = 1;
                    return true; // pause

                case 1:
                    System.out.println("Step 2");
                    state = 2;
                    return true; // pause

                case 2:
                    System.out.println("Step 3");
                    state = 3;
                    return false; // done

                default:
                    return false;
            }
        }
    }

    public static void main(String[] args) {

        Coroutine c = new Coroutine();

        while (c.resume()) {
            System.out.println("Paused... doing other work");
        }

        System.out.println("Finished");
    }
}

/**
🧠 Output Flow
Step 1
Paused...
Step 2
Paused...
Step 3
Finished

👉 This is true coroutine behavior

Pause
        Resume
Maintain state*/
