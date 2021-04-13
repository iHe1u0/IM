package com.imorning.im.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo.State;
import android.os.StrictMode;

public class NetWorkUtils {
    private final Context mContext;
    public State wifiState = null;
    public State mobileState = null;

    public NetWorkUtils(Context context) {
        mContext = context;
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }

    /**
     * 获取当前的网络状态
     *
     * @return
     */
    public NetWorkState getConnectState() {
        ConnectivityManager manager = (ConnectivityManager) mContext
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        manager.getActiveNetworkInfo();
        wifiState = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
                .getState();
        mobileState = manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)
                .getState();
        if (wifiState != null && mobileState != null
                && State.CONNECTED != wifiState
                && State.CONNECTED == mobileState) {
            return NetWorkState.MOBILE;
        } else if (wifiState != null && mobileState != null && State.CONNECTED != wifiState) {
            return NetWorkState.NONE;
        } else if (wifiState != null && State.CONNECTED == wifiState) {
            return NetWorkState.WIFI;
        }
        return NetWorkState.NONE;
    }

    public enum NetWorkState {
        WIFI, MOBILE, NONE

    }

}
