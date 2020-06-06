package com.zz.tst.designmode.factory;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import com.zz.util.FileUtil;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.File;

public class ReadXML2 {

    public static Object getObject() {
        try {
            DocumentBuilderFactory dFactory=DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = dFactory.newDocumentBuilder();
            Document doc = builder.parse(new File(FileUtil.getResourcePath("designmode_factory.xml")));
            NodeList nl = doc.getElementsByTagName("className");
            Node classNode = nl.item(1).getFirstChild();
            String cName = classNode.getNodeValue();
            System.out.println("新类名：" + cName);
            Class<?> c = Class.forName(cName);
            Object obj = c.newInstance();
            return obj;
        } catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
