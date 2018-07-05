package com.bitwaysystem.main;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class ChangeFontsJr {

    private static final String PATH = "C:\\Git\\ogt\\ogt-fta\\fta-solution\\ogt-suite-jreport\\fta\\";

    private static final String ORIGINAL_FILE = "ext_do_std_jp.cls.xml";

    private static final String FINAL_FILE = "new_ext_do_std_jp.cls.xml";

    public static void main(String[] args) throws SAXException, IOException, ParserConfigurationException, TransformerFactoryConfigurationError, TransformerException {
        // TODO Auto-generated method stub
        DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
        /// Document document = docBuilder.parse(new File(PATH.concat(ORIGINAL_FILE)));
        Document document = docBuilder.parse(new FileInputStream(PATH.concat(ORIGINAL_FILE)), "UTF8");

        // BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(FINAL_FILE), "UTF8"));

        NodeList nodeList = document.getElementsByTagName("*");
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE && "FontFace".equals(node.getNodeName())) {
                // do something with the current element
                System.out.println(node.getNodeName() + node.getFirstChild().getNodeValue());
                node.getFirstChild().setNodeValue("*UniFontMedium");
            }
        }

        transFormXml(document);

    }

    public static void transFormXml(Document document) throws TransformerFactoryConfigurationError, TransformerException {
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        Result output = new StreamResult(new File(PATH.concat(FINAL_FILE)));
        // transformer.setOutputProperty(OutputKeys.METHOD, "xml");
        transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        transformer.setOutputProperty(OutputKeys.INDENT, "no");
        Source input = new DOMSource(document);
        transformer.transform(input, output);
    }

}
