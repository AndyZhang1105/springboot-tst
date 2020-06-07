package com.zz.tst.designmode.memento;

public class Caretaker {

    private Memento memento;

    public void setMemento(Memento m)
    {
        memento=m;
    }

    public Memento getMemento()
    {
        return memento;
    }

}
