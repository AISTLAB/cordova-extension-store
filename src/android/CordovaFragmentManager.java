package com.aiesst.extensions;

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
class CordovaFragmentManager extends Extension {
    static final String TAG = "CordovaFragmentManager";
    public static final String FRAGMENT_TAG = "PaySystemFragment";
    public static String FROM_FRAGMENT_TAG = "";

    public CordovaFragmentManager(Activity activity) {
        super(activity);
    }

    @Override
    boolean execute(String strParams, CallbackContext callbackContext) {
        return this.hideFragment(callbackContext);
    }

    private boolean hideFragment(CallbackContext callbackContext) {
        FragmentTransaction transaction = this.mActivity.getFragmentManager().beginTransaction();
        Fragment webFragment = this.mActivity.getFragmentManager().findFragmentByTag(FRAGMENT_TAG);
        if (webFragment == null) {
            transaction.addToBackStack(null);
        } else {
            transaction.hide(webFragment);
        }
        Fragment fromFragment = this.mActivity.getFragmentManager().findFragmentByTag(FROM_FRAGMENT_TAG);
        if (fromFragment != null) {
            transaction.show(fromFragment);
        }
        transaction.commit();
        callbackContext.success();
        return false;

    }

    /**
     * 开启支付系统界面,
     *
     * @param activity                   宿主activity
     * @param fromFragmentTag            跳转界面的fragment的tag
     * @param layoutId                   界面布局id
     * @param token                      支付系统需要的token 需要从服务器获取
     */
    public static void startPaySystem(Activity activity, String fromFragmentTag, int layoutId, String token) {
        FragmentTransaction transaction = activity.getFragmentManager().beginTransaction();
        //用户自定义配置
//        configPaySystemTransaction.config(transaction);
        //隐藏跳转的fragment
        FROM_FRAGMENT_TAG = fromFragmentTag;
        Fragment fromFragment = activity.getFragmentManager().findFragmentByTag(fromFragmentTag);
        if (fromFragment != null) {
            transaction.hide(fromFragment);
        }
        //显示支付系统的fragment
        Fragment webFragment = activity.getFragmentManager().findFragmentByTag(FRAGMENT_TAG);
        if (webFragment != null && webFragment.isAdded()) {
            transaction.show(webFragment);
            try {
                //这里是更新支付系统的token
                JSONObject json = new JSONObject();
                json.put("cmd", "token");
                json.put("data", token);
                //直接调用js的方法，将token发送过去
                ExtensionStore.jsFunction.success(json.toString());
            } catch (Exception e) {
                Log.e(TAG, "更新token失败");
            }
        } else {
            webFragment = new CordovaFragment();
            //将token放入js仓库(支付系统使用的仓库)
            JsDataStore.setData("token", token);
            transaction.add(layoutId, webFragment, FRAGMENT_TAG);
        }
        transaction.commit();
    }

    public interface ConfigPaySystemTransaction {
        void config(FragmentTransaction transaction);
    }
}
