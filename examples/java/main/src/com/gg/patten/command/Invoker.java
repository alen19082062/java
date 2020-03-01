package com.gg.patten.command;

public class Invoker {
    private Command command;

    public Invoker(Command command)
    {
        this.command = command;
    }

    public void action()
    {
        System.out.println("Invoker.action() , call command.exc() ");
        command.exe();
    }

}
