import Entities.Human;
import JsonWorker.JsonWorker;
import XmlWorker.XmlWorker;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {
        var humans = XmlWorker.GetPeopleFromXml("C:\\Idea\\JavaPractice2\\src\\people.xml");
        JsonWorker.InsertHumansToJson("C:\\Idea\\JavaPractice2\\src\\peoplejson.json",humans);

        var humanlist=JsonWorker.readPeople("C:\\Users\\Kokor\\IdeaProjects\\JavaPractice2task\\JavaPractice2\\src\\peoplejson.json");

        Human[] arr= new Human[humanlist.size()];
        var i=0;

        for (var human:humanlist){
            arr[i]=human;
            i++;
        }

        XmlWorker.insertHumanToXml(arr,"C:\\Users\\Kokor\\IdeaProjects\\JavaPractice2task\\JavaPractice2\\src\\peoplexml.xml");
    }
}