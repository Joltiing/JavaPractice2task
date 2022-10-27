package JsonWorker;
import Entities.Human;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

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
            file.write(Beautify(jsonList.toJSONString()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String Beautify(String json) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Object obj = mapper.readValue(json, Object.class);
        return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
    }
}
