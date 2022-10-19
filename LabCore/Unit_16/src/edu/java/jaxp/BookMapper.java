package edu.java.jaxp;

import org.xml.sax.*;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class BookMapper extends DefaultHandler {

    private List<Book> list;
    private Book book;

    public BookMapper(List<Book> list) {
        this.list = list;
    }
    private String lastElementName;
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        System.out.println("1");
        if("book".equals(qName)){
            book = (new Book());
            book.setPage(Integer.parseInt(attributes.getValue("page")));
            list.add(book);
        } else {
            lastElementName = qName;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        System.out.println("4");
        String value = new String(ch, start, length);
        System.out.println(value);
        if("author".equals(lastElementName)){
           System.out.println("-----> "+value);
        }
        if(value.trim().isEmpty()) return;
        switch (lastElementName){
            case "author":
                book.setAuthor(value);
                System.out.println(value);
                break;
            case "booktitle":
                book.setTitle(value);
                System.out.println(value);
                break;
            case "price":
                book.setPrice(Float.parseFloat(value));
                System.out.println(value);
                break;
            default:
                break;
        }
    }
}
class BookMapperTest{
    public static void main(String[] args) throws IOException, SAXException {
        XMLReader parser = XMLReaderFactory.createXMLReader();
        List<Book> list = new ArrayList<>();
        BookMapper mapper = new BookMapper(list);
//        parser.setContentHandler(mapper);
        parser.setContentHandler(new XMLFilterStripper(mapper));
//        parser.parse(new InputSource(CountElement.ElementCounter.class.getResource("Books.xml").openStream()));
        InputStream stream = CountElement.ElementCounter.class.getResource("Books.xml").openStream();
        parser.parse(new InputSource(stream));
//        System.out.println("Found -----> "+list.size());
//        list.forEach(book -> System.out.println(book));
//
//        parser.setErrorHandler(new ErrorHandler(){
//            @Override
//            public void warning(SAXParseException exception) throws SAXException {
//
//            }
//
//            @Override
//            public void error(SAXParseException exception) throws SAXException {
//
//            }
//
//            @Override
//            public void fatalError(SAXParseException exception) throws SAXException {
//                System.out.println("1 -----> "+exception.getMessage());
//            }
//        });
    }
}
