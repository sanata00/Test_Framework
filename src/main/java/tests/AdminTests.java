package tests;

import base.BaseTest;
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
import java.io.*;
import java.lang.reflect.Method;

import static org.testng.Assert.assertTrue;

public class AdminTests extends BaseTest {

    @Test (dataProvider = "xmlProvider", enabled = true)
    public void canLogin(String email, String password) {
        admin.login(email, password);
        assertTrue(admin.dashboardPage.isPageOpened());
    }

    @Test(dataProvider = "xmlProvider", enabled = true)
    public void searchForCar(String carName) {
        admin.openSubmenuOfCars("CARS");
        admin.searchForItem(carName);
        assertTrue(admin.itemsManagementPage.isInNameColumn(carName));
    }

    @DataProvider (name = "xmlProvider")
    public Object[][] provideXmlData(Method m) throws IOException, SAXException, ParserConfigurationException {
        String methodName = m.getName();

        File inputFile = new File("src/main/resources/adminData.xml");
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(inputFile);
        doc.getDocumentElement().normalize();
        NodeList tests = doc.getElementsByTagName("test");

        for (int i = 0; i < tests.getLength(); i++) {
            Node test = tests.item(i);
            Element testElement = (Element) test;
            System.out.println(testElement.getAttribute("name"));
            if (testElement.getAttribute("name").equals(methodName)){
                NodeList keys = testElement.getElementsByTagName("*");

                Object[][] returnObject = new Object[1][keys.getLength()];

                for (int j=0; j<keys.getLength(); j++) {
                    Node key = keys.item(j);
                    Element keyElement = (Element) key;
                    System.out.println(keyElement.getAttribute("value"));
                    returnObject[0][j] = keyElement.getAttribute("value");
                }
                return returnObject;
            }
        }

        return new Object[][]{};
    }

    @DataProvider(name = "json")
    public Object[][] getJsonData(Method m) {
        String methodName = m.getName();
        if (methodName.equals("canLogin")) {
            return new Object[][] {
                    new Object[] { "admin@phptravels.com", "demoadmin" },
            };
        } else if (methodName.equals("searchForCar")) {
            return new Object[][] {
                    new Object[] { "Ford Mondeo 2012" }
            };
        } else {
            return new Object[][] {};
        }
    }


}
