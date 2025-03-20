

Arrays
    int[] x = new int[3];

    default value => 0
        only have int datatype
        homogenous
    
    declaration int[] x; recommended
        int []x;
        int x[]'
        imt x []; space ignored by compiler

    at time of array declaration not allowed to give size. 
        int[5] x; // error
    
        fixed in size
    even notation without declaring creating is valid;
        int []x;
        int []y;

    with new size is required
    in 2d, int x[][], int []x[]  valid

    int [] a, b;    // a 1D    b 1d
    int [] a, b[];   // a 1D    b 2d

    int [][]a, b;    // both 2d
    
    int [][]a, b[];    // a 2d   b 3d

    int [] a, []b; // error

    int [] a, b, []c, d[];  // invalid
    int [] a, b[], c[];  // valid


    Array construction
        Every array is object
        int [] x = new int[]'
        x is reffered array

    x.getClass().getName();
        to get class name
    
    output for above case -> [I
        bracket I
    in 2d op -> [[I

        long[] -> [L
        byte[] -> [B
        boolean[] -> [Z
    
    These available not to programmer level

    $ java Test A B C
    
    main (String[] args)
        0 size acceptable in java
    
        int[] x = new int[-3];
            error , compiler will check size given which type size so not compile time instead runtime error
            NegatigeArraySizeException;
        
    array -> int []x
    Arrays => java.util.Arrays
        for function like Arrays.sort

    int [][][] x = new int[3][][2]; error
    int [][] x = new int[4][];

    int [][] x = new int[][3];
        error invalid;
        
    first dimensions should be always be known

    int []a[], b ;    // b is 1d

    int [] a = new int[] {1,2,3}; // allowed with values
    int []a = new int [3] {1,2,3};
        not allowed combining size + initializer

    int x  =null; compile time error

    s.o.p(y.toString())   ,, s.o.p(z) // z is array
    classname@hashcode_in+hexadecimal

    java.lang package -> object class methods hash code();

    int []x;
    x = {10,20, 30};
    // invalid compile time error
    if index go out and accessing array index out of bound exception

    length vs length()
    ==================
    int arr[] = {1,2};
    String str = "h2";
    arr.length()  // error
    arr.length  // works
    str.length()  // works
    str.length  // error


    Anonymous Arrays: Just for instant use
        new int[]{10,20,30};

        like custom_fn(new int[]{10,20,30})

    Object []a = new Object[10];
    a[0] = new Object();
    a[1] = new String("Kumar");

    Runnable []r = new Runnable[10];

    int []a2 = {1,2,3};
    int []a3 = {4,5};
 
    a3 = a2; // valid , now both point to same object
    size not required to match
    only className i.e. type & dimension should match

    max array length allowed integer max_value (2, 147, 488, 687)
    however in large size still before max_value  we may get OutOfMemoryError

    Now when we create array of custom class students

    Students [] s1 = new Students[5];
    System.out.print(s1);
    => [LStudent
    L representation of non primitive only happen in array

String str;
str.concat('abc'); // NullPointerException
str.length(); // NullPointerException

Student[] students = new Student[3];
stuents[1] = new Student("namex");
stuents[2] = new Student("namey");
for (Student s: students)
    s.o.s(s.name); // Null pointer exception


    
    