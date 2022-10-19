package edu.java.jaxp;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class XMLFilterStripper extends DefaultHandler {
    BookMapper mapper;

    public XMLFilterStripper(BookMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        System.out.println("2");
        if(qName.equals("booktitle")) return;
        mapper.startElement(uri, localName, qName, attributes);
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        System.out.println("3");
        mapper.characters(ch, start, length);
    }
}
