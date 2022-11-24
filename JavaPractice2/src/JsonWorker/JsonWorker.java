package JsonWorker;

import Entities.Human;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.util.ArrayList;

public class JsonWorker {
    public static ArrayList<Human> readPeople(String path){
        JSONParser parser = new JSONParser();

        try(FileReader reader = new FileReader(path)) {
            var obj = parser.parse(reader);
            var humanList = (JSONArray) obj;
            var outList = new ArrayList<Human>();
            humanList.forEach(human -> outList.add(parseHumanObject((JSONObject)human)));

            return outList;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static Human parseHumanObject(JSONObject human)
    {
        var outObj = new Human();

        outObj._name= (String) human.get("name");

        outObj._surname= (String) human.get("surname");

        outObj._sex= (String) human.get("sex");

        outObj._age= Integer.parseInt(human.get("age").toString());

        return outObj;
    }
}
