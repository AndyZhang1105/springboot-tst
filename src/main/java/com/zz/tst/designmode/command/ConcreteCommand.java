package com.zz.tst.designmode.command;

public class ConcreteCommand implements Command {

    private Receiver receiver;

    ConcreteCommand() {
        receiver = new Receiver();
    }

    public void execute() {
        receiver.action();
    }

}
