package com.example.demo.pattern.behaviour.command;

/*
encapsulate in an object all the data required for performing a given action (command), including what method to call, the method's arguments, and the object to which the method belongs.

This model allows us to decouple objects that produce the commands from their consumers

https://www.baeldung.com/java-command-pattern
 */
public class CommandDemo {
    public static void main(String[] args) {
        TextFileOperationExecutor textFileOperationExecutor
                = new TextFileOperationExecutor();

        TextFileOperation openFileCommand = new OpenTextFileOperation(new TextFile("file1.txt"));
        System.out.println(textFileOperationExecutor.executeOperation(openFileCommand));

        TextFileOperation saveFileCommand = new SaveTextFileOperation(new TextFile("file2.txt"));
        System.out.println(textFileOperationExecutor.executeOperation(saveFileCommand));
    }

}
