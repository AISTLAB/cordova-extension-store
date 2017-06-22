package com.aiesst.extentions;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ychost on 17-6-22.
 */

public class JavaDataStore {
    private static Map<String, String> allData = new HashMap<String, String>();

    public static void setData(String key, String value) {
        JavaDataStore.allData.put(key, value);
    }

    public static String getData(String key) {
        return JavaDataStore.allData.get(key);
    }

    public static void mergeMap(Map<String, String> newMapData) {
        allData.putAll(newMapData);
    }
}
