package JsonWorker;

import Entities.Human;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class JsonWorker {
    public static void InsertHumansToJson(String filePath, ArrayList<Human> humans){
        var jsonList=new JSONArray();
        for (var human : humans) {
            var jsonObj=new JSONObject();

            jsonObj.put("name",human._name);
            jsonObj.put("surname",human._surname);
            jsonObj.put("age",human._age);
            jsonObj.put("sex",human._sex);

            jsonList.add(jsonObj);
        }

        try (var file = new FileWriter(filePath)) {
            file.write(jsonList.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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
