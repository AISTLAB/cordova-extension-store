package com.aiesst.extentions;

import android.app.Activity;

import org.apache.cordova.CallbackContext;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ychost on 17-6-22.
 */

public class GetStoreData extends Extention {
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
