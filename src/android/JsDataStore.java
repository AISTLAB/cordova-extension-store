package com.aiesst.extentions;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ychost on 17-6-22.
 */

public final class JsDataStore {
    private static Map<String, String> allData = new HashMap<String, String>();

    public static void setData(String key, String value) {
        JsDataStore.allData.put(key, value);
    }

    public static String getData(String key) {
        return JsDataStore.allData.get(key);
    }

}
