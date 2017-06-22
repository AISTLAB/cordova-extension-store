package com.aiesst.extentions;

import android.app.Activity;

import org.apache.cordova.CallbackContext;

import java.util.Map;

/**
 * Created by ychost on 17-6-21.
 */

public abstract class Extention {

    protected Activity mActivity;

    public Extention(Activity activity) {
        this.mActivity = activity;
    }
    abstract boolean execute(String strParams, CallbackContext callbackContext);


}
