package com.xiayule;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import sun.misc.BASE64Encoder;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;

public class XmlParserTest {
    public static void main(String[] args) throws IOException {
    /*    XStream xStream = new XStream(new DomDriver());
        xStream.alias("person", Person.class);
        xStream.alias("phonenumber", PhoneNumber.class);

        Person joe = new Person("joe", "Walnes");
        joe.setPhone(new PhoneNumber(123, "1234-456"));
        joe.setFax(new PhoneNumber(123, "9999"));

        String xml = xStream.toXML(joe);
        System.out.println(xml);*/


    }
}

class Person {
    private String firstName;
    private String lastName;
    private PhoneNumber phone;
    private PhoneNumber fax;

    Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public PhoneNumber getPhone() {
        return phone;
    }

    public void setPhone(PhoneNumber phone) {
        this.phone = phone;
    }

    public PhoneNumber getFax() {
        return fax;
    }

    public void setFax(PhoneNumber fax) {
        this.fax = fax;
    }
}

class PhoneNumber {
    private int code;
    private String number;

    PhoneNumber(int code, String number) {
        this.code = code;
        this.number = number;
    }
}
