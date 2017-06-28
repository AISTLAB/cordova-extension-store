package com.aiesst;

import android.app.Activity;
import android.util.Log;

import org.json.JSONObject;

/**
 * Created by ychost on 17-6-23.
 */
public final class SweetsPaySDK {

    static final String TAG = "SweetsSDK";

    /**
     * 开启支付系统界面,
     *
     * @param activity        宿主activity
     * @param fromFragmentTag 跳转界面的fragment的tag
     * @param layoutId        界面布局id
     * @param token           支付系统需要的token 需要从服务器获取
     */
    public static void StartPaymentFragment(Activity activity, String fromFragmentTag, int layoutId, String token) {
        CordovaFragmentManager.startPaySystem(activity, fromFragmentTag, layoutId, token);
    }

    /**
     * 更新token
     *
     * @param token
     */
    public static void updateToken(String token) {
        try {
            JSONObject json = new JSONObject();
            json.put("cmd", "token");
            json.put("data", token);
            //直接调用js的方法，将token发送过去
            ExtentionStore.jsFunction.success(json.toString());
        } catch (Exception e) {
            Log.e(TAG, "更新token异常");
        }
        ExtentionStore.jsFunction.error("更新token异常");
    }

    /**
     * 获取回退的消息，应该在 onHiddenChanged 里面获取，只能取一次，第二次取就为空了，通过判断为空来确定是不是支付系统返回的
     *
     * @return 支付系统的回退消息
     */
    public static String getReturnMsg() {
        String msg = JavaDataStore.getData("return_msg");
        JavaDataStore.setData("return_msg", null);
        return msg;
    }
}
