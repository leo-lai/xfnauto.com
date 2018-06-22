package main.com.utils;

import org.json.JSONException;
import org.json.JSONObject;

public class CreateResJosnUtil {

    public static void createjson(JSONObject json, String res, String desc)
	    throws JSONException {
	json.put("res", res);
	if (desc != null && !desc.equals("")) {
	    json.put("desc", desc);
	}

    }
}
