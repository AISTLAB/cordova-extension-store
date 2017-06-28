package com.aiesst.extensions;

import android.app.Activity;

import org.apache.cordova.CallbackContext;

/**
 * Created by ychost on 17-6-22.
 */
class GetStoreData extends Extension {
    public GetStoreData(Activity activity) {
        super(activity);
    }

    @Override
    boolean execute(String strParams, CallbackContext callbackContext) {
        String javaData =JsDataStore.getData(strParams);
        if (javaData == null) {
            callbackContext.error("数据为空");
            return false;
        } else {
            callbackContext.success(javaData);
        }
        return true;
    }
}
