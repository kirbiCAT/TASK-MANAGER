package org.example;

import java.io.IOException;
import java.util.Scanner;

import static java.lang.System.in;
import static org.example.TaskProvider.printerTask;

public class TaskControl {

    EnumTask stat;
    final Scanner scanner;

    public TaskControl(){
        this.scanner = new Scanner(System.in);
    }
    public EnumTask getStat() {
        return stat;
    }
    public void setStat() {
        this.stat = stat;
    }
    public void run() throws IOException {
        this.stat = EnumTask.TODO;
        String selector;  // Read user input
        while (!(selector = scanner.nextLine().toLowerCase()).equals("exit")) {
            switch (selector) {
                case "add" -> {
                    String t = add();                    // just take input
                    TaskProvider.updateTask(t);
                }
                case "status" -> { status();
                }
                case "list" -> { printerTask(null);}

                default -> System.out.println("Invalid option!");
            }
        }
    }
//-------------DESCRIPTION------------------
    public String add() {
        //scanner.nextLine(); // flush leftover
        System.out.println("Enter task:");
        return scanner.nextLine();
    }
    public static String returnDesc(String task){
        System.out.println(task);  // Print it
        return task;                // Return it
    }

    public void status() throws IOException {
        String selector;
        System.out.println("select task status(ip,d,t,exit):");
        while (!(selector = scanner.nextLine().toLowerCase()).equals("exit")) {
            switch (selector) {
                case "ip" -> {
                    stat = EnumTask.IN_PROGRESS;
                    System.out.println(stat);
                    TaskProvider.updateEnum(stat);
                }
                case "d" -> {
                    stat = EnumTask.DONE;
                    System.out.println(stat);
                    TaskProvider.updateEnum(stat);
                }
                case "t" -> {
                    stat = EnumTask.TODO;
                    System.out.println(stat);
                    TaskProvider.updateEnum(stat);
                }
                default -> System.out.println("Invalid option!");
            }
        }
    }


}
