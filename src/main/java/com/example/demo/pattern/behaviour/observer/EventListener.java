package com.example.demo.pattern.behaviour.observer;

import java.io.File;

public interface EventListener {
    void update(String eventType, File file);
}
