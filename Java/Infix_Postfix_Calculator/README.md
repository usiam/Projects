# Shunting Yard Algorithm	

###Classes and Methods:

`
`Node.java`:\
Backbone of the LinkedList class

`LinkedList.java`:\
Contains the LinkedList class that is later used for the Stack and Queue. 
Methods: The methods that were used in the stack and queue are the addLast(), removeLast(), size(), getLast(), removeFIrst().
Other methods that were not used in the rest of the program are addFirst(), search(), printList(). These were used for testing purposes to see if the class works like it is supposed to.

`Queue.java`:\
Contains the enqueue(), dequeue(), isEmpty(), and size() methods which build off of the LinkedList class.
enqueue() is just .addLast() and dequeue is just .removeFirst() and size() just called the LinkedList's size.
Every time the Queue class is called the constructor with the LinkedList instiation is also called so we can use methods from the LinkedList class.


`Stack.java`:\
Contains the push(), pop(), isEmpty(), getTop(), and size() methods which build off of the LinkedList class.
push() is just .addLast() and pop is just .removeLast(), getTop() is just getLast() in LinkedList and size() just called the LinkedList's size.
Every time the Stack class is called the constructor with the LinkedList instiation is also called so we can use methods from the LinkedList class.


`fileIO.java`:\
Class that reads data from a .txt file to be processed using the program and also writes the answers to a separate .txt file.
readFile() method takes the file name as an argument and goes through a loop to read it into the program till there is no more line is to be read.
writingOutput() method takes the final result after the input has been processed using the algorithm to a new file until there is no more lines to be written.

`inflixCalc.java`:\
Class with the shunting yard algorithm to convert infix to postfix. *This class also contains the main method*.
It calls the readFile() method from fileIO class, stores all the strings in an ArrayList called exprStrings. The algorithm works on each element in exprStrings, and the postfix expressions are then stored in postExpr ArrayList. Then we call the eval() method from postfixCalc class and finally store the evaluated results in an output ArrayList which is then written onto a text file using writingOutput() method in our fileIO class.

Before I move on to the method with the shunting yard algorithm I'll outline some of the methods used in the algorithm itself - isOperator checks if its an operator, isDigit checks if it's a double since we are using doubles for everything in this program, precedenceVals() method with all the precendence values of the operators, rightAssociative() and leftAssociative() that checks whether the operator in question is right or left associative. These methods are very self explanatory.

`inToPostConv()` method is where the Shunting Yard Algorithm is implemented. One of the challenges was how to separate the strings properly and I decided to use rejex that I found on the internet. But also I had to figure out a way to handle the doubles with dots. Every time I found a dot, I concatenated the element before and after the dot with the dot in the middle. The output of this was saved in an ArrayList called strArrProc. The stack and queue were instantiated in this method as well. Next my program went through the algorithm and the only extra steps were for the Extra credit where we were using right associative operators alongside the left associative ones. Once an element in my strArrProc has gone through the algorithm I just concatenate the characters in the String element into one single string separating them by comma and return that string. So in my final call this inToPostConv() method is called inside a loop so that this happens to every string element in my original arraylist. 

`postfixCalc.java`:\
Class that handles the evaluation/calculation of our postfix expression. 
Essentially what it does it checks if we have a unary or a binary operator using unary() and binary() methods and evaluates the expression depending on that by using unaryEvaluation() and binaryEvaluation(). Unary evaluation only takes in two arguments - the 
unary operator and the double that is being evaluated - where as the binary evaluation method takes in three arguments - the two operands and the one binary operator.
Both these methods are called in the eval() method that takes in the postfix string that we get from our infix to postfix conversion. It splits the comma separated string into an array, checks if the first thing in the array is an error message. If it is an error message then it returns the error message. Else it moves on and does the evaluation and returns the final result as a string.

###HOW TO RUN:\
`javac -d bin -classpath src -sourcepath src src/*.java`\
`java -classpath src infixCalc .\sample_input.txt .\sample_output.txt`