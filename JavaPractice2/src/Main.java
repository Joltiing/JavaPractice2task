import Entities.Human;
import JsonWorker.JsonWorker;
import XmlWorker.XmlWorker;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {
        //var humans = XmlWorker.GetPeopleFromXml("E:\Practice\JavaPractice2task\JavaPractice2\src\people.xml");
        var humans = XmlWorker.GetPeopleFromXml(args[0]);
        //JsonWorker.InsertHumansToJson("E:\Practice\JavaPractice2task\JavaPractice2\src\peoplejson.json",humans);
        JsonWorker.InsertHumansToJson(args[1],humans);

        //var humanlist=JsonWorker.readPeople("E:\Practice\JavaPractice2task\JavaPractice2\src\peoplejson.json");
        var humanlist=JsonWorker.readPeople(args[1]);

        Human[] arr= new Human[humanlist.size()];
        var i=0;

        for (var human:humanlist){
            arr[i]=human;
            i++;
        }

        //XmlWorker.insertHumanToXml(arr,"E:\\Practice\\JavaPractice2task\\JavaPractice2\\src\\peoplexml.xml");
        XmlWorker.insertHumanToXml(arr,args[2]);
    }
}