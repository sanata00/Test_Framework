package tests;

import base.BaseTest;
import models.CarExtra;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class AdminDataProviders extends BaseTest {

    private final String pathToDataFile = "src/main/resources/testData/adminData.xml";

    @DataProvider(name = "xmlProvider")
    public Object[][] provideXmlData(Method m) throws IOException, SAXException, ParserConfigurationException {
        String methodName = m.getName();

        File inputFile = new File(pathToDataFile);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(inputFile);
        doc.getDocumentElement().normalize();
        NodeList tests = doc.getElementsByTagName("test");

        for (int i = 0; i < tests.getLength(); i++) {
            Node test = tests.item(i);
            Element testElement = (Element) test;
            if (testElement.getAttribute("name").equals(methodName)){
                NodeList keys = testElement.getElementsByTagName("*");

                Object[][] returnObject = new Object[1][keys.getLength()];

                for (int j=0; j<keys.getLength(); j++) {
                    Node key = keys.item(j);
                    Element keyElement = (Element) key;
                    returnObject[0][j] = keyElement.getAttribute("value");
                }
                return returnObject;
            }
        }

        return new Object[][]{};
    }

    @DataProvider(name = "carExtraProvider")
    public Object[][] provideCarExtra(Method method) throws ParserConfigurationException, IOException, SAXException {
        String methodName = method.getName();

        File inputFile = new File(pathToDataFile);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(inputFile);
        doc.getDocumentElement().normalize();
        NodeList tests = doc.getElementsByTagName("test");

        for (int i = 0; i < tests.getLength(); i++) {
            Node test = tests.item(i);
            Element testElement = (Element) test;
            if (testElement.getAttribute("name").equals(methodName)){

                Object[][] returnObject = new Object[1][2];

                NodeList carExtraKey = testElement.getElementsByTagName("carExtra");
                Element carExtraElement = (Element) carExtraKey.item(0);
                NodeList carExtraKeys = carExtraElement.getElementsByTagName("*");

                CarExtra.Builder builder = CarExtra.newEntity();

                for (int j=0; j < carExtraKeys.getLength(); j++) {
                    Node key = carExtraKeys.item(j);
                    Element keyElement = (Element) key;
                    String tagName = keyElement.getTagName();
                    String value = keyElement.getAttribute("value");
                    if (tagName.equals("name")) {
                        builder = builder.withName(value);
                    } else if (tagName.equals("status")) {
                        builder = builder.withStatus(value);
                    } else if (tagName.equals("price")) {
                        builder = builder.withPrice(value);
                    }
                }

                returnObject[0][0] = builder.build();

                Map<String, String> otherData = new HashMap<String, String>();
                NodeList otherKeys = testElement.getElementsByTagName("*");

                for (int k=0; k<otherKeys.getLength(); k++) {
                    Node key = otherKeys.item(k);
                    Element keyElement = (Element) key;

                    if (keyElement.getTagName().equals("carExtra")) {
                        continue;
                    }

                    Element parentNode = (Element) key.getParentNode();
                    if (parentNode.getTagName().equals("carExtra")) {
                        continue;
                    }
                    otherData.put(keyElement.getTagName(), keyElement.getAttribute("value"));
                }
                returnObject[0][1] = otherData;
                return returnObject;
            }
        }
        return new Object[][]{};
    }
}
