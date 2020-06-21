package com.zz.tst.io;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class SystemOutTst {

    public static void main(String[] args) {
        try{
            System.setOut(new PrintStream(new FileOutputStream("log.txt")));
            System.out.println("Now the output is redirected!");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
