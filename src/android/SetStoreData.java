package com.aiesst.extensions;

import android.app.Activity;

import org.apache.cordova.CallbackContext;

import java.util.Map;

/**
 * Created by ychost on 17-6-22.
 */
class SetStoreData extends Extension {

    public SetStoreData(Activity activity) {
        super(activity);
    }
    @Override
    boolean execute(String strParams, CallbackContext callbackContext) {
        Map<String, String> mapParams = null;
        try {
            mapParams = ExtensionStore.jsonToMap(strParams);
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
