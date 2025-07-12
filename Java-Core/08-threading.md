### **Introduction to Threads in Java**

**Threads and Processes**  
- **Process:** A self-contained execution environment, representing a program or application.  
- **Thread:** A lightweight process that shares resources of its parent process and requires fewer resources to create.

---

### **Multithreading**  
- The ability to execute multiple threads simultaneously.  
- Achieved by dividing a program into subprograms executed in parallel.  
- Multithreading supports multitasking and is used in games, animations, etc.

**Advantages of Multithreading**  
1. Non-blocking: Ensures user interactions are uninterrupted.  
2. Efficiency: Allows simultaneous operations, saving time.  
3. Independence: One thread's execution does not affect others.

---
#### Concurrency vs. Parallelism
- **Concurrency**: Multiple execution paths appear to run simultaneously, managed by thread scheduling (may share a single CPU).
- **Parallelism**: Multiple execution paths run simultaneously, leveraging multiple CPU cores.

- Java threads execute as concurrent paths, with parallelism depending on available CPU cores.
- Check available processors: `Runtime.getRuntime().availableProcessors()`.


### **Creating Threads in Java**  
Threads can be implemented via:  
1. **Extending the `Thread` class**  
2. **Implementing the `Runnable` interface**

#### 1. Extending `Thread` Class  
```java
class Multi extends Thread {
    public void run() {
        System.out.println("Thread is running...");
    }

    public static void main(String args[]) {
        Multi t1 = new Multi();
        t1.start();
    }
}
```

#### 2. Implementing `Runnable` Interface  
- Define a class that implements `Runnable` and override the `run()` method.  
- Example:  
```java
class MyRunnable implements Runnable {
    public void run() {
        System.out.println("Thread is running...");
    }

    public static void main(String args[]) {
        MyRunnable r = new MyRunnable();
        Thread t = new Thread(r);
        t.start();
    }
}
```
#### Thread Lifecycle
Threads transition through these states (`Thread.State` enum):
- **NEW**: Thread created but not started.
     - Start it using `start()`  
     - Kill it using `stop()`  
- **RUNNABLE**: Thread is executing or ready to execute when CPU is available.
- **BLOCKED**: Thread is waiting for a monitor lock to enter a synchronized block/method.
- **WAITING**: Thread is waiting indefinitely (e.g., via `wait()`, `join()`).
- **TIMED_WAITING**: Thread is waiting with a timeout (e.g., via `sleep(timeout)`, `wait(timeout)`).
- **TERMINATED**: Thread has completed execution or been stopped.

#### Thread State Transitions
- **RUNNABLE → TIMED_WAITING**: `Thread.sleep(timeout)`, `wait(timeout)`.
- **TIMED_WAITING → RUNNABLE**: Timeout expires or interrupted.
- **RUNNABLE → WAITING**: `wait()`, `join()`.
- **WAITING → BLOCKED → RUNNABLE**: `notify()`, `notifyAll()`, or `interrupt()`.
- **Check State**: Use `Thread.getState()` to retrieve current state.


---
![Threading Picture](Thread_LifeCycle.png)

### **Key Thread Methods**  

| **Method**         | **Description**                                                                 |
|---------------------|---------------------------------------------------------------------------------|
| `start()`          | Starts thread execution by invoking `run()` method.    Cannot be called twice on the same thread (throws `IllegalThreadStateException`).|
| `run()`            | The `run()` method controls lifecycle decisions, checking interruption status or handling `InterruptedException` to decide whether to continue or terminate.|
| `sleep(int ms)`    | Suspends the thread for specified milliseconds.                                |
| `yield()`          | Pauses the current thread to allow others to execute.                         |
| `join()`           | Makes the current thread wait until another thread finishes execution.        |
| `isAlive()`        | Checks if the thread is still running.                                         |

#### Thread Interruption
- **RUNNABLE State**: Thread must check `Thread.currentThread().isInterrupted()` to detect and handle interruption.
- **WAITING/TIMED_WAITING State**: Methods like `sleep()`, `wait()`, or `join()` throw `InterruptedException`, moving the thread to RUNNABLE. The thread decides whether to terminate.
- Example:
```java
Runnable r = () -> {
    Thread ct = Thread.currentThread();
    while (!ct.isInterrupted()) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            return; // Terminate on interrupt
        }
    }
};
Thread t = new Thread(r);
t.start();
t.interrupt();
```
- **TIMED_WAITING**: Thread waits with a timeout (e.g., `sleep(timeout)`, `wait(timeout)`). Returns to RUNNABLE when the timeout expires or interrupted, but only executes when the scheduler allocates CPU time.

---

### **Thread Priorities**  
- Threads are assigned a priority (1 to 10).  
- **Default:** `NORM_PRIORITY = 5`  
- Use `setPriority(int priority)` to adjust.  
- Priority constants:  
  - `MIN_PRIORITY = 1`  
  - `NORM_PRIORITY = 5`  
  - `MAX_PRIORITY = 10`

---
### Thread Properties
- **Name**: Set via constructor (`new Thread(r, "name")`) or `setName()`.
- **ID**: Unique identifier via `getId()`.
- **Daemon Thread**: Set via `setDaemon(true)` before starting. Daemon threads terminate when all user threads finish.
- **Priority**: Set via `setPriority(int)` (1–10). Does not guarantee execution order.
- **Join**: `join()` makes a thread wait for another to terminate.
- **Example**:
```java
Runnable r = () -> {};
Thread t = new Thread(r, "MyThread");
t.setDaemon(true);
t.setPriority(Thread.NORM_PRIORITY);
t.start();
if (t.isDaemon()) {
    // Terminates when all user threads end
}
try {
    t.join(); // Wait for t to finish
} catch (InterruptedException e) {}
```

### **Synchronization**  
- Prevents concurrent threads from conflicting over shared resources.  
- **Monitors**: Any object or class can serve as a monitor to coordinate thread execution.
- **Synchronized Keyword**: Enforces mutual exclusion via an intrinsic lock. The first thread to acquire the lock stays in RUNNABLE state; others attempting to access the same lock enter BLOCKED state until the lock is released.
- **Example**:
```java
Object lock = new Object();
Runnable r = () -> {
    synchronized(lock) {
        // Critical section
    }
};
new Thread(r).start();
new Thread(r).start(); // Second thread blocks until lock is released
```


**Deadlock:** Occurs when two or more threads are waiting indefinitely for each other's resources.  

Example:  
- Thread A holds `Resource1` and waits for `Resource2`.  
- Thread B holds `Resource2` and waits for `Resource1`.  

---

- Synchronized blocks ensure a thread completes the critical section before another thread can acquire the same lock.
- **Example** (combined with above):
```java
class Some {
    void a() {}
    static void b() {}
    void c() {}
}
Some s = new Some();
Runnable r = () -> {
    s.a();
    Some.b();
    synchronized(s) { s.c(); } // Exclusive access
};
new Thread(r).start();
new Thread(r).start();
```


