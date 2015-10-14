package com.example.shadowspray.uitestdemo.utils;

import android.app.KeyguardManager;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.wifi.WifiManager;
import android.support.test.InstrumentationRegistry;
import android.telephony.TelephonyManager;
import android.util.Log;

import com.example.shadowspray.uitestdemo.MainActivity;

/**
 * Created by shadowspray on 10/14/15.
 */
public class DeviceHelper {
    private static final String LOG_TAG = DeviceHelper.class.getSimpleName();

    private Context mContext;

    private TelephonyManager mTelephonyManager;

    private ConnectivityManager mConnectivityManager;

    private WifiManager mWifiManager;

    public DeviceHelper() {
        mContext = InstrumentationRegistry.getContext();
        mTelephonyManager = (TelephonyManager) mContext
                .getSystemService(Context.TELEPHONY_SERVICE);
        mConnectivityManager = (ConnectivityManager) mContext
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        mWifiManager = (WifiManager) mContext.getSystemService(Context.WIFI_SERVICE);
    }

    public WifiManager getWifiManager() {
        return mWifiManager;
    }

    /**
     * Disable Keyguard by launching a dummy activity
     * <p>
     * Each time the device reboots Keyguard will be enabled again, it is a good
     * idea to include this at the beginning of each UI test case
     *
     * @throws Exception When Keyguard is secure
     */
    public void disableKeyguard() throws Exception {
        KeyguardManager km = (KeyguardManager) mContext.getSystemService(Context.KEYGUARD_SERVICE);
        if (km.isKeyguardLocked()) {
            Log.v(LOG_TAG, "Keyguard is locked, try to disable it");
            if (km.isKeyguardSecure()) {
                throw new Exception("Could not disable secure Keyguard");
            }
//            Intent intent = new Intent(Intent.ACTION_MAIN);
//            intent.addCategory(Intent.CATEGORY_LAUNCHER);
//            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            String packageName = "com.sonymobile.fotatest";
//            String className = packageName + ".utils.DisableKeyguardDummyActivity";
//            intent.setComponent(new ComponentName(packageName, className));
            Intent intent = mContext.getPackageManager().
                    getLaunchIntentForPackage(MainActivity.class.getPackage().getName());
            mContext.startActivity(intent);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (km.isKeyguardLocked()) {
                throw new Exception("Failed to disable Keyguard");
            }
        } else {
            Log.v(LOG_TAG, "Keyguard is unlocked");
        }
    }
}
