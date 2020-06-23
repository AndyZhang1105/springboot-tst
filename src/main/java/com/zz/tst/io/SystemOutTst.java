package com.zz.tst.io;

import java.io.*;

public class SystemOutTst {

    public static void main(String[] args) {

        try {
            System.out.println("Please input something:");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            String filename = bufferedReader.readLine();
            InputStream inputStream = new FileInputStream(filename);
            System.out.println(inputStream);
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

        try {
            System.setOut(new PrintStream(new FileOutputStream("log.txt")));
            System.out.println("Now the output is redirected!");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
