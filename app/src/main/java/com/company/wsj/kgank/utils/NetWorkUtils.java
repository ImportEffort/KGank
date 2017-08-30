package com.company.wsj.kgank.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by wangshijia on 2017/2/3 下午4:00.
 * Copyright (c) 2017. alpha, Inc. All rights reserved.
 */

public class NetWorkUtils {

    public static boolean isNetWorkAvailable(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = connectivity != null ? connectivity.getActiveNetworkInfo() : null;
        return !(info == null || info.getState() != NetworkInfo.State.CONNECTED);
    }
}
