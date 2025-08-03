package Util;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.jayway.jsonpath.JsonPath;

public class JsonPathUtilityClass {

    public static String getValueByPath(String json,String key)
    {
        JsonElement jsonElement= JsonParser.parseString(json);

        if(jsonElement.isJsonObject())
        {
            return jsonElement.getAsJsonObject().get(key).getAsString();
        }
        return null;
    }
    public static String getValueByPathJayway(String json,String key)
    {
       Object result= JsonPath.read(json,"$.id");
        return result.toString();
    }
}
