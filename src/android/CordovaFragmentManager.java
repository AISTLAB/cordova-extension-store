package com.aiesst.extentions;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.util.Log;

import com.webileapps.fragments.CordovaFragment;

import org.apache.cordova.CallbackContext;
import org.json.JSONObject;

/**
 * Created by ychost on 17-6-22.
 */

public class CordovaFragmentManager extends Extention {
    public static final String TAG = "CordovaFragmentManager";

    static CordovaFragment webFragment;
    static Fragment fromFragment;

    public CordovaFragmentManager(Activity activity) {
        super(activity);
    }


    public static CordovaFragment getFragment() {
        return CordovaFragmentManager.webFragment;
    }

    @Override
    boolean execute(String strParams, CallbackContext callbackContext) {
        return this.hideFragment(callbackContext);
    }

    private boolean hideFragment(CallbackContext callbackContext) {
        FragmentTransaction transaction = this.mActivity.getFragmentManager().beginTransaction();
        if (CordovaFragmentManager.webFragment == null) {
            transaction.addToBackStack(null);
        } else {
            transaction.hide(webFragment);
        }
        transaction.show(fromFragment);
        transaction.commit();
        fromFragment = null;
        callbackContext.success();

        return false;

    }

    /**
     * 启动支付系统
     *
     * @param activity     宿主activity
     * @param layoutId     呈现fragment的id
     * @param token        支付系统需要的token
     * @param fromFragment 启动支付系统时候所在的fragment
     */
    public static void startPaySystem(Activity activity, int layoutId, Fragment fromFragment, String token) {
        FragmentTransaction transaction = activity.getFragmentManager().beginTransaction();
        CordovaFragmentManager.fromFragment = fromFragment;
        transaction.hide(fromFragment);
        if (webFragment != null && webFragment.isAdded()) {
            transaction.show(webFragment);
            try {
                //这里是更新支付系统的token
                JSONObject json = new JSONObject();
                json.put("cmd", "token");
                json.put("data", token);
                //直接调用js的方法，将token发送过去
                ExtentionStore.jsFunction.success(json.toString());
            } catch (Exception e) {
                Log.e(TAG, "更新token失败");
            }
        } else {
            webFragment = new CordovaFragment();
            //将token放入js仓库(支付系统使用的仓库)
            JsDataStore.setData("token", token);
            transaction.add(layoutId, webFragment);
        }
        transaction.commit();
    }


}
