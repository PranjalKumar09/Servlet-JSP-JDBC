

protected variable can also be  can be read and modified from any class within the same package

nested insdide exception need another nested try catch22-


Java module naming rules:

    Allowed characters: Letters, digits, underscores (_), dollar signs ($).
    Must start with: A letter, underscore, or dollar sign.
    No dashes allowed. -
    Recommendation: Use reverse domain name pattern (e.g., com.example.mymodule) for global uniqueness.
    Avoid: Underscores and dollar signs, especially for new modules.
    Relationship to packages: Module name should relate to its principal package(s) and a package belongs to only one module. JAR named com-jenkov-mymodule.jar would result in an automatic module named com.jenkov.mymodul


Starvation occurs when a thread is frequently unable to get access to a resource because the resource is hogged by other threads most of the time



A final variable can be hidden in a subclass.
If the class declares a field (static or instance) with a certain name, then the declaration of that field is said to hide any and all accessible declarations of fields with the same name in superclasses, and superinterfaces of the class.
For example,
class Base{
   int i=10;
   static int x = 30;
}
class Sub extends Base{
  int i=20; //This i hides Base's i.   
  static int x = 40; //This x hides Base's x.   
}


The starting delimiter is """ followed by any number of white spaces followed by the ASCII LF character (i.e. the new line character). For example, """ hello """ will not compile because there is no new line character after """.

BaseClass of collection -> collection, Map






java.util.stream.Stream interface is/are used for reduction?
filter
filter(Predicate<? super T> predicate) method returns a stream consisting of the elements of this stream that match the given predicate. However, it is not a reduction operation because it does not combine the elements to produce one value.
reduce is!!
sum
This is a valid reduction operation but it is not in java.util.stream.Stream interface.
Methods to sum up the numbers in a stream are available in specialized Stream interfaces such an IntStream, LongStream, and DoubleStream
max
min and max are valid reduction operations. The Stream version of these methods take a Comparator as an argument, while the versions in specialized streams such as IntStream and DoubleStream do not take any argument.
















 java --show-module-resolution 
It shows module resolution output during startup

jmod
jmod is used for mod files, which are not on the exam. However, you might be asked about the describe option which prints module details for a jmod file. For example:

jmod describe jmods/ma.jmod
Observe that there are no hyphens (or dashes or minus sign) before describe.

It prints the modules required by the ma module. However, it does not show complete module resolution.  



. ExecutorService interface extends Executor interface. While Executor allows you to execute a Runnable, ExecutorService allows you to execute a Callable.
 Executors is a utility class that provides several static methods to create instances of ExecutorService. All such methods start with new 



 about i18n.
 You should use Locale and formatter objects such as NumberFormat and DateFormat to generate locale specific output
 Using default locale for NumberFormat and DateFormat automatically ensures that the formatted text will be localized to the location setting of the machine on which the code is run.

 NumberFormat:
NumberFormat getInstance(Locale locale)

DateFormat:
DateFormat getDateInstance(int style, Locale locale)

Note that DateFormat does not have getInstance(Locale locale) method.




The input parameter has been specified as List<E>, where E has to be some class that extends CharSequence. So ArrayList<String>, List<String>, or List<CharSequence> are all valid as reference types for 'in'.

The output type of the method has been specified as List<? super E> , which means that it is a List that contains objects of some class that is a super class of E. Here, E will be typed to whatever is being used for 'in'. For example, if you declare ArrayList<String> in, E will be String.

The important concept here is that once the method returns, there is no way to know what is the exact class of objects stored in the returned List. So you cannot declare out in a way that ties it to any particular class, not even Object.

Thus, the only way to accomplish this is to either use non-typed reference type, such as:  List result; or use the same type as the return type mentioned in the method signature i.e. List<? super String> (because E will be bound to String in this case.)
