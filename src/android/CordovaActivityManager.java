package com.aiesst.extensions;

import android.app.Activity;
import android.content.Intent;

import org.apache.cordova.CallbackContext;

/**
 * Created by ychost on 17-6-21.
 */

class CordovaActivityManager extends Extension {

    public final static int CordovaReturn = 8700;

    public CordovaActivityManager(Activity activity) {
        super(activity);
    }

    public boolean execute(String strParams, CallbackContext callbackContext) {
        Intent intent = new Intent();
        intent.putExtra("params", strParams);
        this.mActivity.setResult(CordovaReturn, intent);
        callbackContext.success();
        this.mActivity.finish();
        return true;
    }
}
