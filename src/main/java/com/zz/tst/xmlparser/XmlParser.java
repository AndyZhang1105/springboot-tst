package com.zz.tst.xmlparser;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class XmlParser extends DefaultHandler {

    Person user;
    List<Person> list;

    public List<Person> getUser() {
        return list;
    }

    String tagName = null;

    @Override
    public void startDocument() throws SAXException {
        list = new ArrayList<Person>();
        super.startDocument();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        tagName = qName;
        if("user".equals(tagName)) {
            user = new Person();
            user.setId(attributes.getValue(0));
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if(tagName != null) {
            if("name".equals(tagName)) {
                String str = new String(ch, start, length);
                user.setName(str);
            } else if("age".equals(tagName)) {
                String str = new String(ch, start, length);
                user.setAge(Integer.parseInt(str));
            } else if("sex".equals(tagName)) {
                String str = new String(ch, start, length);
                user.setSex(str);
            }
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if("user".equals(qName)) {
            list.add(user);
            user = null;
        }
        tagName = null;
    }

    @Override
    public void endDocument() throws SAXException {
        super.endDocument();
    }

}
