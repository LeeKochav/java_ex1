313233769_207023649
# Ex1 

In this project we present implematation of mathematical functions ,mathematical operation between functions,saving them to a file and displaying them in a graphical window
Implemented classes: polynom, monom, complex functiona and function_gui.

In mathematics, a polynom is an expression consisting of variables and coefficients and  monom is a polynom which has only one term. 
Complex function is a combination of functions and operations.

## Class Monom
This class represent a simple monom of shape ax^b where a is a real number and b is an integer. 

## Implemented functions: 
-	Public Monom(String s)- creates  a new valid monom within a given string input. A monom can be valid if his shape is: ax^b. The functions updateStringForValidation and checkValid check if the input string is valid.
	The string separates into tokens using the string tokenizer, part one is the real number – coefficient and the second part is a the integer number- power.
-	updateStringForValidation- taking care of edge corner cases where the input string is valid but the number ‘1’ does not appear before the variable x as an example “-x,x,+x” etc. These cases considered valid mathematically but can cause problems when parseing the string into number.
-	checkValid- checks if the given input string is valid using regex expression.
	A regex expression is a sequence of characters that define a search pattern. We built a pattern that represents the valid shape of monom and whenever there is a match (using java regex pattern and matcher -java.util.regex) the function returns true.
-	add- computes addition between two monoms if they have the same power level.
-	substract- computes subtraction between two monoms if they have the same power level.
-	multiply- computes multiplication between two monoms.
-	toString- returns the monom as a string
-	equals- computes logically comparation checking if the subtraction of two monom is less than epsilon.
-	copy- returns a new copy monom.
-	initFromString- returns function of a new monom created from string (usign the string moonom constractor). 
-	f- returns the value of m(X).
 
## Class Polynom
This class represent a simple polynom of shape a1x^b1+a2x^b2….
The polynom class represent as an arraylist of monoms.

##Implemented functions:
-	Polynom()-empty constractor.
-	Polynom(String)- creates  a new valid polynom within a given string input. We use the split string function to separate the polynom to monoms and check if all monoms are valid and creates a new polynom.
-	toString- returns the polynom as a string.
-	f- returns the value of p(X).
-	add-computes addition between two polynoms.
-	substract-computes subtraction between two polynoms.
-	multiply- computes multiplication between two polynoms.
-	equals- computes logically comparation checking if the subtraction of two polynoms is less than epsilon.
-	isZero- returns if a given polynom is zero, means that all coefficients of the monoms contains in the polnom are zero.
-	root- computes the root of the polynom of a given range and eps.
-	area- computes the area of the polynom of a given range and eps.
-	derivative-returns polynom that is the polynom derivative.
-	Iterator- returns an iterator of monoms.
-	copy- returns a new copy polynom.
-	initFromString- returns function of a new polynom created from string (usign the string polynom constractor). 

## Class ComplexFunction
This class represent a complex function type y=g(f1(x),f2(x)), where both f1,f2 are functions. y and x are real numbers and g is an operation: plus,mul,div,max,min,comp(f1(f2(x))).

## Implemented functions:
-	ComplexFunction()-creates a new complex function f1,f2=null, operation is None.
-	ComplexFunction(function f1)-creates a new complex function conatins only f1 operation is None.
-	ComplexFunction(operation op,fuction f1,function f2)-creates a new complex contains f1,f2 and operation.
-	ComplexFunction(String op,fuction f1,function f2)-creates a new complex contains f1,f2 and operation. Convert the stringn operation into the relevant operation from the enum type.  
-	ComplexFunction(ComplexFunction c)-copy constractor.
-	f- returns the value of  y=g(f1(x),f2(x)). g is an operation.
-	plus-Add to this complex_function the f1 complex_function.
-	mul-Multiply to this complex_function the f1 complex_function.
-	div-Divides this complex_function the f1 complex_function.
-	max-Computes the maximum over this complex_function and the f1 complex_function.
-	min-Computes the minimum over this complex_function and the f1 complex_function.
-	comp-This method wrap the f1 complex_function with this function: this.f(f1(x)).
-	left-returns the left side of the complex function - this side should always exists (should NOT be null).
-	right-returns the right side of the complex function - this side might not exists (aka equals null).
-	getOp-The complex_function oparation: plus, mul, div, max, min, comp.
-	equals- computes logically comparation, returns true if and only if these two functions returns the same value of f(x) (x is in the range [0,1000000]).
-	copy- returns a new copy complex function.
-	convert(String operation)-converts a given string as operation to an enum operation. 
-	initFromString- returns function of a new complex function created from string.
	Seperate the string into three pars: operation,fx1,fx2.
-	toString- returns the complex function as a string.

## Class Function_GuI
This class represents a collection of mathematical functions which can be presented on a GUI window and can be saved (and load) to file. 

## Implemented functions:
-	Function_GUI()-empty constractor.
-	initFromFile- Init a new collection of functions from a file.
-	SaveFromFile- Save the functions collection to a file.
-	drawFunctions(int widht,int height, Range rx,Range ry, int resolution)-  Draws all the functions in the collection in a GUI window.
-	drawFunctions(String json_file)-  Draws all the functions in the collection in a GUI window using the given JSON file ith all the parameters for the GUI window.