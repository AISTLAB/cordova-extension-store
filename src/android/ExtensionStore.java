package com.aiesst.extensions;

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
public class ExtensionStore extends CordovaPlugin {

    public static CallbackContext jsFunction;

    public boolean execute(String action, JSONArray rawArgs, CallbackContext callbackContext) throws JSONException {
        Extension extension = null;
        boolean result = false;
        String strParams = rawArgs.getString(0);
        Log.d("cordova extension", "接受方法： " + action);
        Log.d("cordova extension", "接受参数: " + strParams);

        //绑定js的调回钩子，让java可以直接调用js
        if ("jsFunction".equals(action)) {
            ExtensionStore.jsFunction = callbackContext;
            return true;
            //退出持有该webview的fragment
        } else if ("hide".equals(action)) {
            extension = new CordovaFragmentManager(this.cordova.getActivity());
        }
        //退出持有该webview的Activity
        else if ("exit".equals(action)) {
            extension = new CordovaActivityManager(this.cordova.getActivity());
            //获取java的数据
        } else if ("getStoreData".equals(action)) {
            extension = new GetStoreData(this.cordova.getActivity());
            //给java传递数据
        } else if ("setStoreData".equals(action)) {
            extension = new SetStoreData(this.cordova.getActivity());
        }
        if (extension != null) {
            result = extension.execute(strParams, callbackContext);
        }
        return result;
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
