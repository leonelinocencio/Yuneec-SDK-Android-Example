package com.yuneec.example.component.listeners;

import android.content.Context;
import android.util.Log;

import com.yuneec.example.component.custom_callback.OnChangeListener;
import com.yuneec.sdk.Action;
import com.yuneec.sdk.Gimbal;

/**
 * Created by sushmas on 9/15/17.
 */

public class ActionListener {

    private static Action.ResultListener actionResultListener = null;

    private static final String TAG = ActionListener.class.getCanonicalName();

    private static OnChangeListener onChangeListener;

    public static Action.ResultListener getActionResultListener(Context context) {
        onChangeListener = (OnChangeListener) context;
        if (actionResultListener == null) {
            Log.d(TAG, "Initialized action result listener");
            actionResultListener = new Action.ResultListener() {
                @Override
                public void onResultCallback(Action.Result result) {
                    Log.d(TAG, result.resultStr);
                }
            };
        }

        return actionResultListener;
    }

    public static void registerActionListener() {

        if (actionResultListener == null) {
            Log.d(TAG, "Initialized action result listener");
            actionResultListener = new Action.ResultListener() {
                @Override
                public void onResultCallback(Action.Result result) {
                    onChangeListener.publishActionResult(result.resultStr);
                    Log.d(TAG, result.resultStr);
                }
            };
        }
    }

    public static void unRegisterActionListener() {

        if (actionResultListener != null) {
            actionResultListener = null;
        }
    }
}
