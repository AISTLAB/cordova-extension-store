package com.aiesst.extentions;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import org.apache.cordova.CallbackContext;

/**
 * Created by ychost on 17-6-21.
 */

public class FinishActivity extends Extention {

    public final static int CordovaReturn = 8700;

    public FinishActivity(Activity activity) {
        super(activity);
    }


    public boolean execute(String paramsStr, CallbackContext callbackContext) {
        Intent intent = new Intent();
        intent.putExtra("params", paramsStr);
        this.mActivity.setResult(CordovaReturn, intent);
        callbackContext.success();
        this.mActivity.finish();
        return true;
    }
}
