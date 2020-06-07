package com.zz.tst.classtst;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class MyClassLoader extends ClassLoader {

    private String mLoaderName;
    private String mBasePath = "";

    public MyClassLoader(){
        super();
        this.mLoaderName = "";
    }

    public MyClassLoader(String name) {
        super();
        this.mLoaderName = name;
    }

    public MyClassLoader(ClassLoader parent, String name) {
        super(parent);
        this.mLoaderName = name;
    }

    public void setBasePath(String path) {
        mBasePath = path;
    }

    public String getLoaderName(){
        return mLoaderName;
    }

    @SuppressWarnings("deprecation")
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] data = loadClassFile(name);
        System.out.println("data size = " + data.length);
        return this.defineClass(data, 0, data.length);
    }

    private byte[] loadClassFile(String fileName) {
        ByteArrayOutputStream outStream = null;
        byte[] result = null;
        FileInputStream in = null;
        outStream = new ByteArrayOutputStream();
        this.mBasePath = this.mBasePath.replace(".", "/");
        try {
            in = new FileInputStream(new File(this.mBasePath + fileName + ".class"));
            System.out.println("file path + file name = " + this.mBasePath + fileName + ".class");
            int length = 0;
            while(-1 != (length = in.read())) {
                outStream.write(length);
            }
            result = outStream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
                outStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

}
