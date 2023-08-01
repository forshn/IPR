package com.example.ipr.command;

public class CommandA implements Command{

    public CommandA(String message) {
    }

    @Override
    public void execute() {
        System.out.println("CommandA execution runs");
    }
}
