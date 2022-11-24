package XmlWorker;

import Entities.Human;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class XmlWorker {
    public static void insertHumanToXml(Human[] humanList, String path) {
        try {
            var documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            var xmlDoc = documentBuilder.newDocument();
            var root=xmlDoc.createElement("humans");
            xmlDoc.appendChild(root);

            ArrayList<org.w3c.dom.Element> maleList = new ArrayList<>();
            ArrayList<org.w3c.dom.Element>  femaleList = new ArrayList<>();

            for (var human : humanList) {
                var el = xmlDoc.createElement("human");

                el.setAttribute("name",human._name);
                el.setAttribute("surname",human._surname);
                el.setAttribute("age", String.valueOf(human._age));
                el.setAttribute("sex",human._sex);

                if(human._sex.equals("male")){
                    maleList.add(el);
                }

                if(human._sex.equals("female")){
                    femaleList.add(el);
                }
            }

            var males = xmlDoc.createElement("males");

            for (var el:maleList) {
                males.appendChild(el);
            }

            root.appendChild(males);

            var females = xmlDoc.createElement("females");

            for (var el:femaleList) {
                females.appendChild(el);
            }

            root.appendChild(females);

            FileOutputStream output = new FileOutputStream(path);
            writeXml(xmlDoc, output);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static void writeXml(Document doc,OutputStream output) throws TransformerException {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(output);

        transformer.transform(source, result);
    }
}