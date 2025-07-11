### **Introduction to Threads in Java**

**Threads and Processes**  
- **Process:** A self-contained execution environment, representing a program or application.  
- **Thread:** A lightweight process that shares resources of its parent process and requires fewer resources to c   reate.

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

---

### **Thread Lifecycle**  
Threads transition through the following states:  

1. **Newborn State**  
   - Thread is created but not started.  
   - Actions:  
     - Start it using `start()`  
     - Kill it using `stop()`  

2. **Runnable State**  
   - Ready for execution but waiting for CPU.  

3. **Running State**  
   - Actively executing code.  

4. **Blocked State**  
   - Temporarily inactive due to `sleep()`, `wait()`, or suspension.  

5. **Dead State**  
   - Thread completes execution or is explicitly stopped.

---
![Threading Picture](Thread_LifeCycle.png)

### **Key Thread Methods**  

| **Method**         | **Description**                                                                 |
|---------------------|---------------------------------------------------------------------------------|
| `start()`          | Starts thread execution by invoking `run()` method.                            |
| `run()`            | Contains the code to execute.                                                  |
| `sleep(int ms)`    | Suspends the thread for specified milliseconds.                                |
| `yield()`          | Pauses the current thread to allow others to execute.                         |
| `join()`           | Makes the current thread wait until another thread finishes execution.        |
| `isAlive()`        | Checks if the thread is still running.                                         |

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

### **Synchronization**  
- Prevents concurrent threads from conflicting over shared resources.  
- Achieved using the `synchronized` keyword:  
```java
synchronized (lockObject) {
    // Critical section
}
```

**Deadlock:** Occurs when two or more threads are waiting indefinitely for each other's resources.  

Example:  
- Thread A holds `Resource1` and waits for `Resource2`.  
- Thread B holds `Resource2` and waits for `Resource1`.  

---

By leveraging multithreading and synchronization techniques, Java enables efficient and concurrent program execution while managing resource conflicts effectively.

NEW KNOWLEDGE
    NEW
    RUNNABLE
    BLOCKED
    WAITING
    TIMED_WAITING
    TERMINATED

    Thread cant we started twice

parallism describe different execution paths that runs simultaneously, such as when executed    using multiple CPU cores
term concurenncy descirbe different execution pahts that look that run simultanously    , which may or may not be really case

java thread is an execution path that feels like it is performed in parallel with other execution paths. may or not be parralle depend on how much phuscial machine used

Runtime r = Runtime.getDefault();
int noOfHardWare = r.availableProcessors();


if thread is running state, its our responsibility to check interrupt signal & decide whether it should terminate or not,thread that enter waiting state     must catch interuptted execption    ,when exception thrown and control is passed to exectpion handler, thread goes back to running state and again can make descion whether to terminate or not

after timed wait expires , threda goes back back to running sate. however it would not happen at very moment, but rather when thread schueldar allocates next variable CPU time slot for this thread
Interrupted Thread
    Logic of run method is in charge lifecycle decisions
        A thread in runnable state may check if it has received an interrupt signal
        A thread that has entering waiting or timed waiting state must catch InterruptedException, which puts it back to runnable state, and then decide what it should do

    Runnable r = () -> {
        Thread ct = Thread.currentThread(); // locate thread object
        while(!ct.isInterrupted()){ // check interuppt signal when running
            try{
                Thread.sleep(1000); // waiting  time fo 1000 milliseconds
            } catch (InterruptedException ex) {
                return ;
            }
        }
    };
    Thread t = new Thread(r);
    t.start();
    t.interrupt(); // when Thread is in running state it is not forced to check this signal

Block Thread
    Monitor object helps to coordinate order of execution of threads
    Any objects or class can be used as monitor
    It allows thread to enter blocked or waiting states
    It enables mutual exclusion of threads & signalling mechanism
    keyword synchronized enforces exclusive access to block of code 
    A thread that first enters synchornized block remain in runnable state
    All other threads accessing same block enter blocked state
    When runnable thread exit synchornized block, when lock is released
    Another thread is not allowed to enter runnable  state and place a new lock

    Some s = new Some();
    Runnable r = () -> {
        s.a();
        Some.b();
        synchronized(a){
            s.c();
        }
    };
    new Thread(r).start();
    new Thread(r).start();

synchornized word enofrces exclusive, establish order accessing locked object , intrisic lock
ensure that thread of complete sequence of aciton of synchornized block before  thread is allowdd to neter this block of code

Make Thread wait until notified
    Suspend thread waiting indedinterly
    wait method put thread into waiting state against specific monitor
    no of thread can be waiting
    notify wakes up one of waiting thread
    notifyAll wakes up all of waiting thread


    Object obj = new Object();
    Runnable r = () -> {
        try{
            synchronized(obj){
                obj.wait();
            }
        } catch (InterruptedException ex){ ... }
    };
    Thread t = new Thread(r);
    t.start();
    try{
        Thread.sleep(1000);
    } catch (InterruptedException ex){...}
    synchronized(obj){
        obj.notify();
    }

void wait()
void wait(long timeoutMillisecond)
void wait(long timeoutMillisecond , int nanos)


RUNNABLE -> TIMED_WAITING   Thread.sleep(timeout)
RUNNABLE <- TIMED_WAITING   sleep method returns or is interuppted
getsTate
RUNNABLE -> WAITTING    wait()
RUNNABLE <- BLOCKED <- WAITING  notify(), notifyAll(),interrupt()
RUNNABLE -> TIMED_WAITING   <- wait(timeout)
RUNNABLE <- BLOCKED <- TIMED_WAITING timeout expires , notify(), notifyAll() , interrupt()

Common Thread Properties
    Thread could be given custom name using constuctor and set/get name method
    It has uniqueID
    It can be marked as daemon or user (default) thread
    It may wait for another thread to termite   
    it could be assigned priority


    priority not guarantee order of execution

    Runnabler  r  = () -> {};
    Thread t = new Thread(r, "My thread");
    t.setDaemon(true);
    t.start();
    long id = t.getId();

    if (t.isDaemon())
    // it will autoerminate once all user thread  have terminated
    try{
        t.join();
    }
    catch (InterruptedException ex){}
