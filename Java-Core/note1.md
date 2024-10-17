C language was primarily designed to develop “System Softwares” like Operating Systems, Device Drivers etc .

To remove security problems with “C” language , C++  language was designed.
It is an Object Oriented Language which provides data security and can be used to solve real world problems.

Many popular softwares like Adobe Acrobat , Winamp Media Player,Internet Explorer,Mozilla Firefox etc were designed in C++




JAVA is a Technology(not only a programming language) to develop Object oriented and Platform Independent applications.

PLATFORM
    A Platform is an environment in which a program runs.
    In simple terms it is combination of Operating system and Processor.

    Windows, Windows , Linux, Mac, Mac  
    Physical Machine = 5
    Platform = 3





Important Features (8)
    Platform Independent
        Java has this capability using the concept of “bytecode” and “JVM”
        Whenever we compile a java program , the compiler never generates machine code.
        Rather it gnerates a machine independent code called the “bytecode”.
        This bytecode is not directly understandable by the platform(OS & CPU).
        So another special layer of software is required to convert these bytecode instructions to machine dependent form
        This special layer is the JVM , that converts the bytecode to underlying machine instruction set and runs it.
        Thus any such platform for which a JVM is available can be used to execute a Java application irrespective of where it has been compiled.
        This is how java makes itself “Platform Independent” and it also truly justifies java’s slogan of “WORA”(Write Once Run Anywhere)
    
    
    Automatic Memory Management
        In languages like C and C++ any dynamic memory which the programmer allocates using malloc( ) or new has to be deallocated by himself using free( ) or delete
        But Java uses runtime automatic garbage collection feature where the JVM itself deallocates any dynamic memory which our program allocated.

    Secure
        Java does not use pointers explicitly. 
        Moreover all the programs in java are run under an area known as the sand box. 
        This sandbox uses a bytecode verification process to ensure that code loaded does not violate Java security constraints.

    Robust
        Java has very strict rules which every program must compulsorily follow and if these rules are violated then JVM kills/terminates the code by generating “Exception”
    
        To understand java’s robustness , guess the output of the following C/C++ code:
        int arr[5];
        int i;
        for(i=0;i<=9;i++){
            arr[i]=i+1;
        }
        The previous code might show uncertain behaviour in C/C++ i.e. if memory is available after arr[4] , then the code will run , otherwise it will generate error at runtime.
        On the other hand if in java this code is executed, the JVM will kill the application as soon as it finds the statement arr[5]=. . .
        Reason is that in java we are not allowed to access any array beyond it’s upper/lower index

    Simple
        Java borrows most of it’s syntax from C/C++ languages. Moreover it has inherited best points from these languages and dropped others.Like it has removed pointers, multiple inheritance etc as developers of java language found these features to be security threat and confusing.
        Thus if we have basic understanding of C/C++ languages it is very easy to learn Java
    
    Object Oriented
        Java supports all important concepts of OOPs, like
        Encapsulation
        Inheritance
        Polymorphism
        Abstraction
    
    Multithreaded
        Multithreading means concurrent execution.
        In simple terms it means that we can execute more than one part of the same program parallelly/simultaneously.



        Can we say that if we are surfing the internet using our  browser and at the same time we are listening to song in winamp, the it is multithreading ?
        It is mutlitasking not multithreading


        Some practical examples where multithreading is used are:
        We can open multiple tabs in the same browser window
        When we use a media player to listen to a song , then there are multiple activities which take place parallely like moving of a slider, elapsed time being shown, volume adjustment , ability to add or remove songs from the playlist , playing of the song etc


    Distributed
        Distributed programming a program uses more than one computer.
        That is, different parts of the same program run on different computers and communicate over a network.
        But as use of Java increased , then in 1999 , SUN categorized it into 3 editions called J2SE,J2EE and J2ME .
        Later on in 2006 they changed the naming and called them as JSE,JEE and JME.
        These editions were named based on the kind of application which can be developed by learning that edition.


Java Standard Edition -> Basic Edition core java
    Used for developing desktop applications like calculators, media player, chat applications etc
Java Enterprise Edition ->
        The Java EE which stands for Java Enterprise Edition is built on top of the Java SE platform and is a collection of libraries used for building "enterprise applications" (usually web applications).
        In simple terms we can say JEE is used for developing applications which run on servers.
        Some popular applications developed using JEE are amazon.in,alibaba.com,irctc.co.in,ideacellular.com,airtel.in etc
JME(Java Micro Edition)
    Java ME is the slimmer version of Java targeted towards small devices such as mobile phones. 
    Generally people tend to think of the micro edition as the mobile edition, in reality, the micro edition is used not just for mobile phones, but for all kinds of devices, such as television sets, printers, smartcards and more.
    But as smartphone technology arrived he use of JME has reduced as Android has superseded




    JVM is an abstract machine that can execute precompiled Java programs.
    In simple terms it is the code execution component of java 
    It is designed for each platform(OS+CPU) supported by Java and this means that every platform will have a different version of JVM

    JVM is called virtual machine because it is a software layer but it behaves as if it is a complete machine(platform).
    That is all the tasks which are done by a machine while running a program in other languages like C , are actually done by JVM in Java
    .
    For example:
        Starting The Execution By Calling Main( )
        Allocating Memory For The Program
        Cleaning Up Memory cleanup etc


    JVM contains following important components
        Interpreter
        Garbage Collector


    Are java compiler and interpreter same?
        No , not at all.
    The Java compiler converts source code to bytecode and is not a part of JVM , rather it comes with JDK.
    The interpreter lives inside the JVM and converts bytecode to Machine understandable form


        JRE is an acronym for Java Runtime Environment
        It contains a JVM along with java classes/packages and set of runtime libraries.
        So the JVM , while running a java program uses the classes and other libraries supplied by JRE.
        If we do not want to write a java program , and we just want to run it then we only need a JRE

        JDK stands for Java Development Kit and is a bundle of software that we can use to develop Java based applications.
        It includes the JRE, set of library classes, Java compiler,jar and additional tools needed while developing a Java application.


    