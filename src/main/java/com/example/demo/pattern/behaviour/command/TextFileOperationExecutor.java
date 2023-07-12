package com.example.demo.pattern.behaviour.command;

import java.util.ArrayList;
import java.util.List;

/*
Invoker: knows how to execute a given command but doesn't know how the command has been implemented.
It only knows the command's interface.
 */
public class TextFileOperationExecutor {
    private final List<TextFileOperation> textFileOperations
            = new ArrayList<>();

    public String executeOperation(TextFileOperation textFileOperation) {
        textFileOperations.add(textFileOperation);
        return textFileOperation.execute();
    }
}
