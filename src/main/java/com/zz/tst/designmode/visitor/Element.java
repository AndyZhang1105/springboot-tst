package com.zz.tst.designmode.visitor;

public interface Element {
    void accept(Visitor visitor);
}
