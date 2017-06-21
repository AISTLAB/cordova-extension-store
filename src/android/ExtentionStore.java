package com.aiesst.extentions;

import android.util.Log;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by ychost on 17-6-21.
 */

public class ExtentionStore extends CordovaPlugin {


    public boolean execute(String action, JSONArray rawArgs, CallbackContext callbackContext) throws JSONException {
        String strParams = rawArgs.getString(0);
        Log.d("Swiftpay", "接受参数: " + strParams);
        Map<String, String> mapParams;
        try {
            mapParams = jsonToMap(strParams);
        } catch (Exception e) {
            callbackContext.error("json格式有误");
            return false;
        }
        if ("finishActivity".toUpperCase().equals(action.toUpperCase())) {
            return new FinishActivity(this.cordova.getActivity()).execute(strParams, callbackContext);
        }
        return true;
    }

    /**
     * 将普通的json数据转换成map，容错性更高
     *
     * @param jsonStr
     * @return
     * @throws Exception
     */
    public static Map<String, String> jsonToMap(String jsonStr) throws Exception {
        JSONObject jsonObj = new JSONObject(jsonStr);
        Iterator<String> nameItr = jsonObj.keys();
        String name;
        Map<String, String> outMap = new HashMap<String, String>();
        while (nameItr.hasNext()) {
            name = nameItr.next();
            outMap.put(name, jsonObj.getString(name));
        }
        return outMap;
    }
}
