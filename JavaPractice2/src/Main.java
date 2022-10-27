import JsonWorker.JsonWorker;
import XmlWorker.XmlWorker;

public class Main {
    public static void main(String[] args) {
        try {
            var humans = XmlWorker.GetPeopleFromXml("C:\\Idea\\JavaPractice2\\src\\people.xml");
            JsonWorker.InsertHumansToJson("C:\\Idea\\JavaPractice2\\src\\peoplejson.json",humans);
        }
        catch (Exception ex){
            System.out.println("Error");
        }
    }
}