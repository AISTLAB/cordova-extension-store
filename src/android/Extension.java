package com.aiesst.extensions;

import android.app.Activity;

import org.apache.cordova.CallbackContext;

/**
 * Created by ychost on 17-6-21.
 */
abstract class Extension {

    protected Activity mActivity;

    public Extension(Activity activity) {
        this.mActivity = activity;
    }
    abstract boolean execute(String strParams, CallbackContext callbackContext);
}
