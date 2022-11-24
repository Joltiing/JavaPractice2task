import Entities.Human;
import JsonWorker.JsonWorker;
import XmlWorker.XmlWorker;

public class Main {
    public static void main(String[] args) {
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