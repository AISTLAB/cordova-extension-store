package com.aiesst.extentions;

import android.app.Activity;

import org.apache.cordova.CallbackContext;

import java.util.Map;
import java.util.function.BiConsumer;

/**
 * Created by ychost on 17-6-22.
 */

public class setStoreData extends Extention {
    public setStoreData(Activity activity) {
        super(activity);
    }

    @Override
    boolean execute(String strParams, CallbackContext callbackContext) {
        Map<String, String> mapParams = null;
        try {
            mapParams = ExtentionStore.jsonToMap(strParams);
        } catch (Exception e) {
            callbackContext.error("参数 json 格式错误");
            return false;
        }

        if (mapParams != null && mapParams.size() > 0) {
            JavaDataStore.mergeMap(mapParams);
            callbackContext.success();
        }

        return true;
    }
}
