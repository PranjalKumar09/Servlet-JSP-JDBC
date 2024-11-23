Improved Program
Passing Reference

class Demo

{

private int x;

private int y;

public Demo(int i, int j)

{

x=i;

y=j;

}

public void increment(Demo D)

{

D.x++;

D.y++;

}

public void show( )

{

System.out.println(“x= ”+x);

System.out.println(“y= ”+y);

}
Passing Array Reference

class Demo

{

public void doubler(int [ ] brr)

{

for(int i=0; i<brr.length; i++)

brr[i]=brr[i]*2;

}

}

class Test

{

public static void main(String [ ] args)

{

int [] arr={10,20,30,40,50};

Demo d=new Demo( );

d.doubler(arr);

for(int i=0;i<arr.length;i++)

S.O.P(arr[i]);

}

}
Returning Array Reference

class Demo

{

public int[ ] createArray(int n)

{

int [ ] brr=new int [n];

return brr;

}

}

class Test

{

public static void main(String [ ] args)

{

Demo d=new Demo( );

int [ ] arr=d.createArray(5);

System.out.println(“Length of array is ”+arr.length);

}

}
The “this” Keyword

The “this” keyword in java is a predefined object reference
available inside every non static method of a class.

On calling a method, the java compiler transfers the address
of the object to the called method.

This address is copied inside the “this” reference. In short
“this” reference points to the object which is currently being
used to call a method.

Two major benefits of using “this” reference

1. We can use the local variables by using the same name as
that of the data members of class.

2. We can perform inter constructor call using “this”.

class Box

{

private int l,b,h;

public Box( int l, int b, int h)

{

this.l=l;

this.b=b;

this.h=h;


static data members

static methods

static blocks

static classes(Can be used only with nested class or
inner class and not the outer class)

