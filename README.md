# Polynomial-solver
Read in a polynomial and store it in variable A, B, or C. Output a polynomial using a form that clearly displays it. Add two polynomials and store the result in R. Subtract two polynomials and store the result in R. Multiply two polynomials and store the result in R. Evaluate a polynomial at some point, a, where a is a floating point constant. In other words, substitute by the given value in your polynomial. Display the result as a floating point. Clear a polynomial. Note that: a polynomial whose value is cleared or initially unset cannot be involved in an operation. It is required to implement the following interface by the core of your application. The core of the application should throw a runtime exception when it encounters any invalid input or operation.

the input format is as follows:
-----
set
A
[1,-3,2]
print
A
set
B
[1,-5,6]
print
B
set
C
[1,-9,26,-24]
print
C

-----

x^2-3x+2
x^2-5x+6
x^3-9x^2+26x-24

---------

set
A
[1,-9,26,-24]
set
B
[5,10,4,30]
add
A
B

--------

set
A
[1,-13,50,-56]
eval
A
5


-------


set
A
[1,-13,50,-56]
clear
A

--------


set
A
[20,46,-30,-78]
set
B
[5,41,-50,0]
sub
A
B

--------

set
A
[32,41,67]
set
B
[2,3,1]
mult
A
B
