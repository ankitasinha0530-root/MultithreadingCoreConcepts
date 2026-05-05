package com.multithreading.udemy.structuredTaskScope;

import java.util.concurrent.*;

public class StructuredBasic {

    static String getUser() {
        sleep(2);
        return "User";
    }

    static String getOrders() {
        sleep(3);
        return "Orders";
    }

    static void sleep(int sec) {
        try {
            Thread.sleep(sec * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws Exception {

        try (var scope = new StructuredTaskScope.ShutdownOnFailure()) {

            StructuredTaskScope.Subtask<String> user = scope.fork(() -> getUser());
            StructuredTaskScope.Subtask<String> orders = scope.fork(() -> getOrders());

            scope.join();              // wait for all
            scope.throwIfFailed();     // propagate error

            String result = user.get() + " " + orders.get();

            System.out.println(result);
        }
    }
}

/**
🔹 fork()
    Future<T> subtask = scope.fork(task);
    Starts a subtask
    Usually runs on virtual thread
 🔹 join()
     scope.join();
     Waits for all subtasks
 🔹 throwIfFailed()
     If any subtask fails → throws exception
     Cancels others automatically
 🔹 resultNow()
     Non-blocking
     Only valid after join()

 Types of Structured Scope :-

1.  ShutdownOnAllSuccess (MOST IMPORTANT)

 👉 Shutdown when all the child execution completes

2.  ShutdownOnFailure (MOST IMPORTANT)

 👉 If any one task fails → cancel all others

3.  ShutdownOnSuccess

 👉 Return when first task succeeds

 Corner Cases (VERY IMPORTANT)
     ❌ 1. Forgetting join()
     scope.fork(...);
     // missing join()

 👉 Tasks may still run → unpredictable behavior

     ❌ 3. Blocking inside virtual thread
     scope.fork(() -> {
     Thread.sleep(10000);
     return "OK";
     });

 👉 This is actually OK (virtual threads handle blocking well)

     ❌ 4. Ignoring interruption

     If your task ignores interruption:

     while(true) { } // BAD

 👉 Cancellation won’t work properly

 🔄 8. Comparison with CompletableFuture
 Feature	        CompletableFuture	        StructuredTaskScope
 Model          	async graph	                structured block
 Debugging      	hard	                    easy
 Cancellation	    manual	                    automatic
 Error handling	    scattered	                centralized
 Thread leaks	    possible	                prevented

 🧠 9. Under the Hood

 Structured concurrency uses:

     Virtual threads (Project Loom)
     Scoped lifecycle
     Parent-child relationship

 👉 When scope closes:

     All unfinished tasks are cancelled

 Real-World Edge Cases
 🔥 Timeout Handling
 StructuredTaskScope doesn’t have built-in timeout → you do:

 scope.joinUntil(Instant.now().plusSeconds(2));
 🔥 Partial Failure Strategy

 If you want:

 ignore failures → custom scope
 fallback → wrap tasks
 🔥 Mixed IO + CPU

 Structured concurrency works great because:

 Virtual threads → cheap blocking
 No need for complex async chaining
 🧠 Final Mental Model

 👉 Think of Structured Concurrency like:

 Parent Thread
 ├── Task 1
 ├── Task 2
 └── Task 3
 Parent owns children - Parents starts the scope
 Children cannot outlive parent - There is always a scope defined when that scope finishes all child task is terminated
 Errors flow upward
 Cancellation flows downward
 */
