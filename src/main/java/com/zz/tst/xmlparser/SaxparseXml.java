package com.zz.tst.xmlparser;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class SaxparseXml {

    public static void main(String[] args) {
        SAXParserFactory factory = SAXParserFactory.newInstance();

        try {
            SAXParser sp = factory.newSAXParser();

            XmlParser p = new XmlParser();
            InputStream is = SaxparseXml.class.getClassLoader().getResourceAsStream("static/user.xml");
            sp.parse(is, p);

            List<Person> userList = p.getUser();
            for(Person person : userList) {
                System.out.println(person.getAge() + person.getId() + person.getSex() + person.getName());
            }
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
