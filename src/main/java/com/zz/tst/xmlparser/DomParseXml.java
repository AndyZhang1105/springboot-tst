package com.zz.tst.xmlparser;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class DomParseXml {

    List<Person> list;

    public List<Person> getPersonList(InputStream is) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(is);

        list = new ArrayList<Person>();
        NodeList list2 = document.getElementsByTagName("user");
        for(int i = 0; i < list2.getLength(); i++) {
            Element ele = (Element) list2.item(i);
            Person person = new Person();
            person.setId(ele.getAttribute("id"));
            NodeList childNodes = ele.getChildNodes();
            for(int j = 0; j < childNodes.getLength(); j++) {
                if(childNodes.item(j).getNodeType()  == Node.ELEMENT_NODE) {
                    Element childEle = (Element) childNodes.item(j);
                    if("name".equals(childEle.getNodeName())) {
                        person.setName(childEle.getFirstChild().getNodeValue());
                    } else if("age".equals(childEle.getNodeName())) {
                        person.setAge(Integer.parseInt(childEle.getFirstChild().getNodeValue()));
                    } else if("sex".equals(childEle.getNodeName())) {
                        person.setSex(childEle.getFirstChild().getNodeValue());
                    }
                }
            }
            list.add(person);
        }

        return list;
    }

    public static void main(String[] args) throws Exception {
        InputStream is = DomParseXml.class.getClassLoader().getResourceAsStream("static/user.xml");
        DomParseXml domParseXml = new DomParseXml();
        List<Person> personList = domParseXml.getPersonList(is);
        for (Person p : personList) {
            System.out.println(p.getAge() + p.getId() + p.getSex() + p.getName());
        }
    }
}
