package org.example;

import org.example.UImanagers.MainFrameManager;
import org.example.configuration.BeenContainerApp;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext annotationContext = new AnnotationConfigApplicationContext(BeenContainerApp.class);
        annotationContext.getBean(MainFrameManager.class).mainStart();
    }

}