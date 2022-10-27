package XmlWorker;

import Entities.Human;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;

public class XmlWorker {
    public static ArrayList<Human> GetPeopleFromXml(String filePath) throws ParserConfigurationException, IOException, SAXException {
        var documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        var xmlDoc = documentBuilder.parse(filePath);
        var root = xmlDoc.getDocumentElement();
        var rootNodes = root.getChildNodes();

        var humanList = new ArrayList<Human>();

        for (var rootNodeIndex = 0; rootNodeIndex < rootNodes.getLength(); rootNodeIndex++){
            //search for "family" node
            if(rootNodes.item(rootNodeIndex).getNodeName() == "family") {

                var familyNode = rootNodes.item(rootNodeIndex).getChildNodes();
                //search for humans in family
                for (var familyNodeIndex = 0; familyNodeIndex < familyNode.getLength(); familyNodeIndex++){
                    if(familyNode.item(familyNodeIndex).getNodeName() == "human") {
                        var humanNodesList = familyNode.item(familyNodeIndex).getChildNodes();
                        var human = new Human();
                        //filling human
                        for(var humanNodeIndex = 0; humanNodeIndex < humanNodesList.getLength(); humanNodeIndex++){
                            if(humanNodesList.item(humanNodeIndex).getNodeName() == "name"){
                                human._name=humanNodesList.item(humanNodeIndex).getTextContent();
                            }

                            if(humanNodesList.item(humanNodeIndex).getNodeName() == "surname"){
                                human._surname=humanNodesList.item(humanNodeIndex).getTextContent();
                            }

                            if(humanNodesList.item(humanNodeIndex).getNodeName() == "age"){
                                human._age = Integer.parseInt(humanNodesList.item(humanNodeIndex).getTextContent());
                            }

                            if(humanNodesList.item(humanNodeIndex).getNodeName() == "sex"){
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
}