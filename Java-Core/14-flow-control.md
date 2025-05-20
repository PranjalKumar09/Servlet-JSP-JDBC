Flow control
    Selection: 1)if else, else if, 2)Switch
    Iterative: 1)while, 2)for , 3)do while, 4)for each
    Transfer: 1)break , 2)continue, 3)return ,4) try-catch-finally, 5)assert

else if(...)only boolean

switch
    nothing is compulsory
    int x = 10;
    switch(x){
    // nothing} // valid

    curly braces mandatory in switch only rest all place its optional
    byte, short, int , char, enum, string
        max no of cases -> int range

    long, float, double boolean   Not possible

    in case, case ... variable not allowed

    wrapper classes are also possible as switch parameter


byte b = 2;
switch (b){
    case .. // case value must be range only
}


int x = 0;
switch(x){
    case 0:
        s.o.p(0);
    case 1:
        s.o.p(1);
        break;
    case 2:
        s.o.p(2);
    default:
        s.o.p("def");
}
// 0
// 1

if x = 2
then o/p: 2
          def
fall through switch once case match then it execute all thing until break matched


default (optional) -> can place anywhere even at first


int x = 0;
switch(x){
    default:
        s.o.p("def");
    case 0:
        s.o.p(0);
    case 1:
        s.o.p(1);
        break;
    case 2:
        s.o.p(2);
}
0
// default will of 0 when no

if x = 1;
o/p: 1
     2

if x = 3:
o/p: def
      0


switch(x+10) // expression possible in parameter as it evaluates single value

case value should compile time constant other wise error
exception -> final

final int x = 3;
switch (x+1){
    default:
        s.o.p("def")
    case x+0: valid bcz its like 3+0
        ---
}

for (int i = 0; ; i++)  // true , without condition

for (s.o.s("anytime"); i< 3; s.o.s("--")){ // any statement is possible
    /// 
}

int i;
i = 0;
for (s.o.s("start"); i< 2; s.o.s("continue"))
    i++;
o/p:
start
continue
continue

all three are optional

for (;;)
   -- // infinite times rune 

for each 
    int []x = {10, 20, 30};
    for (int x1:x)
        s.o.p(x1)

    for array (normal order)
        not possible to patternize the iteration

break
    inside switch to stop full through
    inside loops to break loop execution
    labelled block

    int x = 10;
    l1:
    {
        s.o.p("begin")
    }