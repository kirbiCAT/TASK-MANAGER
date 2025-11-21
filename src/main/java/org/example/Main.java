package org.example;

import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;
import java.io.FileWriter;

import static java.lang.System.in;

public class Main {

    public Main(){
    }

    static void myMethod(String selector) {
        System.out.println("command selected is: " + selector);
    }
    
    public static void main(String[] args) throws IOException {
        System.out.println("select a command(add,status,list):");
        TaskControl start = new TaskControl();
        start.run();
        }
    }




