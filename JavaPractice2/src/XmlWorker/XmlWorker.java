package XmlWorker;

import Entities.Human;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class XmlWorker {
    public static ArrayList<Human> GetPeopleFromXml(String filePath) throws ParserConfigurationException, IOException, SAXException {
        var documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        var xmlDoc = documentBuilder.parse(filePath);
        var root = xmlDoc.getDocumentElement();
        var rootNodes = root.getChildNodes();

        var humanList = new ArrayList<Human>();

        for (var rootNodeIndex = 0; rootNodeIndex < rootNodes.getLength(); rootNodeIndex++){
            //search for "family" node
            if(rootNodes.item(rootNodeIndex).getNodeName().equals("family")) {

                var familyNode = rootNodes.item(rootNodeIndex).getChildNodes();
                //search for humans in family
                for (var familyNodeIndex = 0; familyNodeIndex < familyNode.getLength(); familyNodeIndex++){
                    if(familyNode.item(familyNodeIndex).getNodeName().equals("human")) {
                        var humanNodesList = familyNode.item(familyNodeIndex).getChildNodes();
                        var human = new Human();
                        //filling human
                        for(var humanNodeIndex = 0; humanNodeIndex < humanNodesList.getLength(); humanNodeIndex++){
                            if(humanNodesList.item(humanNodeIndex).getNodeName().equals("name")){
                                human._name=humanNodesList.item(humanNodeIndex).getTextContent();
                            }

                            if(humanNodesList.item(humanNodeIndex).getNodeName().equals("surname")){
                                human._surname=humanNodesList.item(humanNodeIndex).getTextContent();
                            }

                            if(humanNodesList.item(humanNodeIndex).getNodeName().equals("age")){
                                human._age = Integer.parseInt(humanNodesList.item(humanNodeIndex).getTextContent());
                            }

                            if(humanNodesList.item(humanNodeIndex).getNodeName().equals("sex")){
                                human._sex = humanNodesList.item(humanNodeIndex).getTextContent();
                            }
                        }

                        humanList.add(human);
                    }
                }
            }
        }
        return humanList;
    }

    public static void insertHumanToXml(Human[] humanList, String path) {
        try {
            var documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            var xmlDoc = documentBuilder.newDocument();
            var root=xmlDoc.createElement("humans");
            xmlDoc.appendChild(root);

            Map<String,Element> familyElList=new HashMap<>();

            for(var human:humanList){
                var el=xmlDoc.createElement("family");
                el.setAttribute("id",String.valueOf(human._surname));

                if(!familyElList.containsKey(human._surname)){
                    familyElList.put(human._surname,el);
                    el.appendChild(createHumanNode(human,xmlDoc));
                } else {
                    familyElList.get(human._surname).appendChild(createHumanNode(human,xmlDoc));
                }
            }

            for(var family : familyElList.values()){
                root.appendChild(family);
            }

            FileOutputStream output = new FileOutputStream(path);
            writeXml(xmlDoc, output);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static Node createHumanNode(Human human, Document xmlDoc) {
        var node=xmlDoc.createElement("human");
        var name=xmlDoc.createElement("name");
        name.setTextContent(human._name);
        node.appendChild(name);
        var surname=xmlDoc.createElement("surname");
        surname.setTextContent(human._surname);
        node.appendChild(surname);
        var age=xmlDoc.createElement("age");
        age.setTextContent(String.valueOf(human._age));
        node.appendChild(age);
        var sex=xmlDoc.createElement("sex");
        sex.setTextContent(human._sex);
        node.appendChild(sex);

        return node;
    }


    private static void writeXml(Document doc,OutputStream output) throws TransformerException {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(output);

        transformer.transform(source, result);
    }
}