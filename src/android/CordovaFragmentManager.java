package com.aiesst.extentions;

import android.app.Activity;
import android.app.FragmentTransaction;

import com.webileapps.fragments.CordovaFragment;

import org.apache.cordova.CallbackContext;

/**
 * Created by ychost on 17-6-22.
 */

public class CordovaFragmentManager extends Extention {

    static CordovaFragment mFragment;

    public CordovaFragmentManager(Activity activity) {
        super(activity);
    }

    public static void setFragment(CordovaFragment fragment) {
        CordovaFragmentManager.mFragment = fragment;
    }

    public static CordovaFragment getFragment() {
        return CordovaFragmentManager.mFragment;
    }

    @Override
    boolean execute(String strParams, CallbackContext callbackContext) {
        return this.hideFragment(callbackContext);
    }

    private boolean hideFragment(CallbackContext callbackContext) {
        FragmentTransaction transaction = this.mActivity.getFragmentManager().beginTransaction();
        if (CordovaFragmentManager.mFragment == null) {
            transaction.addToBackStack(null);
        } else {
            transaction.hide(mFragment);
        }
        transaction.commit();
        callbackContext.success();
        return false;

    }


}
