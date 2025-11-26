package implementations;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import exceptions.EmptyQueueException;

/**
 * XML Parser using Kitty's Algorithm to validate XML structure
 * 
 * @author Team Rauru
 * @version 1.0
 * @date November 2025
 */
public class XMLParser {

    private MyStack<XMLTag> tagStack;        // Stack for opening tags
    private MyQueue<XMLTag> errorQueue;      // Queue for missing closing tags
    private MyQueue<XMLTag> extrasQueue;     // Queue for extra closing tags
    
    /**
     * Inner class to store tag information
     */
    private class XMLTag {
        String name;
        int lineNumber;
        boolean isClosing;
        
        XMLTag(String name, int lineNumber, boolean isClosing) {
            this.name = name;
            this.lineNumber = lineNumber;
            this.isClosing = isClosing;
        }
        
        @Override
        public String toString() {
            return (isClosing ? "</" : "<") + name + ">";
        }
    }

    public XMLParser() {
        tagStack = new MyStack<>();
        errorQueue = new MyQueue<>();
        extrasQueue = new MyQueue<>();
    }

    /**
     * Parse an XML file following Kitty's Algorithm
     */
    public void parse(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            int lineNum = 1;

            while ((line = br.readLine()) != null) {
                processLine(line.trim(), lineNum);
                lineNum++;
            }

            // After EOF: Pop remaining stack items into errorQueue
            while (!tagStack.isEmpty()) {
                errorQueue.enqueue(tagStack.pop());
            }

        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }

    /**
     * Process a single line of XML
     */
    private void processLine(String line, int lineNum) {
        // Ignore processing instructions
        if (line.startsWith("<?xml")) {
            return;
        }

        int pos = 0;
        while (pos < line.length()) {
            int start = line.indexOf("<", pos);
            if (start == -1) break;

            int end = line.indexOf(">", start);
            if (end == -1) {
                System.out.println("Line " + lineNum + ": Missing '>'");
                return;
            }

            String tagContent = line.substring(start + 1, end).trim();
            
            // Remove attributes (everything after first space)
            int spaceIndex = tagContent.indexOf(" ");
            if (spaceIndex != -1) {
                tagContent = tagContent.substring(0, spaceIndex);
            }

            // Process tag according to Kitty's Algorithm
            if (tagContent.endsWith("/")) {
                // Self-closing tag: Ignore
                // e.g., <tag/>
            } 
            else if (tagContent.startsWith("/")) {
                // End tag
                String tagName = tagContent.substring(1).trim();
                XMLTag endTag = new XMLTag(tagName, lineNum, true);
                processEndTag(endTag);
            } 
            else {
                // Start tag: Push on stack
                XMLTag startTag = new XMLTag(tagContent, lineNum, false);
                tagStack.push(startTag);
            }

            pos = end + 1;
        }
    }

    /**
     * Process end tag following Kitty's Algorithm
     */
    private void processEndTag(XMLTag endTag) {
        try {
            // If matches top of stack, pop and all is well
            if (!tagStack.isEmpty()) {
                XMLTag top = tagStack.peek();
                if (top.name.equals(endTag.name)) {
                    tagStack.pop();
                    return;
                }
            }

            // Else if matches head of errorQ, dequeue and ignore
            if (!errorQueue.isEmpty()) {
                XMLTag errorHead = errorQueue.peek();
                if (errorHead.name.equals(endTag.name)) {
                    errorQueue.dequeue();
                    return;
                }
            }

            // Else if stack is empty, add to extrasQ
            if (tagStack.isEmpty()) {
                extrasQueue.enqueue(endTag);
                return;
            }

            // Else: Search stack for matching Start_Tag
            boolean foundInStack = searchStack(endTag.name);

            if (foundInStack) {
                // Pop each element from stack into errorQ until match
                while (!tagStack.isEmpty()) {
                    XMLTag popped = tagStack.pop();
                    if (popped.name.equals(endTag.name)) {
                        break; // Found the match, stop
                    }
                    errorQueue.enqueue(popped);
                }
            } else {
                // Add to extrasQ (no matching opening tag found)
                extrasQueue.enqueue(endTag);
            }
        } catch (EmptyQueueException e) {
            // Should not happen with isEmpty() checks
            System.err.println("Unexpected queue error: " + e.getMessage());
        }
    }

    /**
     * Search stack for a matching tag name
     */
    private boolean searchStack(String tagName) {
        utilities.Iterator<XMLTag> it = tagStack.iterator();
        while (it.hasNext()) {
            XMLTag tag = it.next();
            if (tag.name.equals(tagName)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Print all errors following Kitty's Algorithm
     */
    public void printErrors() {
        try {
            // If both queues are empty, document is valid
            if (errorQueue.isEmpty() && extrasQueue.isEmpty()) {
                System.out.println("XML document is constructed correctly.");
                return;
            }

            // Report errors
            System.out.println("XML document is not constructed correctly.");

            // Process queues according to Kitty's Algorithm
            while (!errorQueue.isEmpty() || !extrasQueue.isEmpty()) {
                
                // If either queue is empty (but not both), report each element
                if (errorQueue.isEmpty() && !extrasQueue.isEmpty()) {
                    XMLTag extra = extrasQueue.dequeue();
                    System.out.println("Error at line: " + extra.lineNumber + 
                                     " <" + extra.name + "> is not constructed correctly.");
                }
                else if (!errorQueue.isEmpty() && extrasQueue.isEmpty()) {
                    XMLTag missing = errorQueue.dequeue();
                    System.out.println("Error at line: " + missing.lineNumber + 
                                     " <" + missing.name + "> is not constructed correctly.");
                }
                // If both queues are not empty, peek both queues
                else {
                    XMLTag errorTag = errorQueue.peek();
                    XMLTag extraTag = extrasQueue.peek();
                    
                    // If they don't match, dequeue from errorQ and report
                    if (!errorTag.name.equals(extraTag.name)) {
                        errorQueue.dequeue();
                        System.out.println("Error at line: " + errorTag.lineNumber + 
                                         " <" + errorTag.name + "> is not constructed correctly.");
                    } 
                    // Else dequeue from both (they cancel out)
                    else {
                        errorQueue.dequeue();
                        extrasQueue.dequeue();
                    }
                }
            }
        } catch (EmptyQueueException e) {
            System.err.println("Error processing queues: " + e.getMessage());
        }
    }

    /**
     * Main entry point for Parser.jar
     */
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Usage: java -jar Parser.jar <xml-file>");
            return;
        }

        XMLParser parser = new XMLParser();
        parser.parse(args[0]);
        parser.printErrors();
    }
}