"# CPRG-304Assignment2"
Data Structures and XML Parser Project Requirements
Part 1: Interfaces
Create the following interface files with method stubs and full Javadoc documentation:
1. StackADT.java
2. QueueADT.java

Each method should include:
- Pre-conditions
- Post-conditions
- Parameters
- Return values
- Expected exceptions

Note: These two files are the only requirements for Part 1 submission.
Part 2: Utility Classes
1. Import the assignment2StartingCode project into Eclipse.
2. Implement MyArrayList.java using ListADT.java and Iterator.java interfaces.
   - Use an array as the underlying data structure.
3. Test MyArrayList.java using the provided JUnit tests.
   - Do NOT modify the test files.
4. Implement MyDLL.java using ListADT.java and Iterator.java interfaces.
   - Use a linked list as the underlying data structure.
5. Create MyDLLNode.java to store each individual element in the doubly linked list.
Part 3: Stack and Queue
1. Implement MyStack.java using StackADT.java and Iterator.java.
   - Use MyArrayList.java as the underlying data structure.
2. Implement MyQueue.java using QueueADT.java, Iterator.java, and EmptyQueueException.java.
   - Use MyDLL.java as the underlying data structure.
Part 4: XML Parser
1. Write an XML parser that:
   - Reads supplied XML documents.
   - Parses for errors in XML construction.
   - Prints all lines with errors in the order they occur.

2. Program should accept XML document via command line and display results on the console.
XML Syntax Rules
- Opening tag: <tag>
- Closing tag: </tag>
- Self-closing tag: <tag/>
- Tags are case-sensitive.
- One and only one root tag.
- Ignore attributes (name="value") and processing instructions (<?xml ... ?>).
- Tags must be properly nested (no intercrossing).
Restrictions
Do NOT modify the following files:
- ListADT.java
- Iterator.java
- EmptyQueueException.java
- Provided JUnit test files

You must NOT use any classes from java.util.* or similar packages in:
- MyStack.java
- MyQueue.java
- MyArrayList.java
- MyDLL.java

Exceptions Allowed:
- Arrays.copyOf() or System.arraycopy() for resizing arrays in MyArrayList
- toArray methods and Iterator implementations
- Standard Java exceptions such as NullPointerException and NoSuchElementException
