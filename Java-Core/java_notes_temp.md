Method Overriding
• A mechanism used by derived class, to change functionalities
of the same method present in base class.
• Two necessities for overriding a method
1. Inheritance
2. Prototype (Same visibility, return type and name)
• It is generally done when the derived class wants to have a
more specialized or specific version of the method inherited
from the base class.

Overloading v/s Overriding
• Although Overloading and Overriding sound similar, but are
completely different.
1. Overriding can only occur in case of inheritance, whereas
Overloading is done within the same class as well as across
inheritance.
1. Overriding occurs when prototype(return type, name,
arguments) of a method is same in both base and derived
class while, overloading occurs when name of method is
same but they differ in terms of arguments.


class Circle
{
private int rad;
public Circle(int rad)
{
this.rad=rad;
}
public int getRadius( )
{
return rad;
}
public double getArea( )
{
double area=Math.PI*rad*rad;
return area;
}
public double getCircum( )
{
double circ=2*Math.PI*rad;
return circ;
}
}
class Cylinder extends Circle
{
private int height;
public Cylinder(int r, int h)
{
super(r);
height=h;
}
public double getArea( )
{
double area=2*super.getArea( )+getCicrcum( )*height;
return area;
}
}


