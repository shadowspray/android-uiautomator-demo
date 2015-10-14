package com.example.shadowspray.uitestdemo;

import android.content.Context;
import android.net.wifi.WifiManager;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.test.InstrumentationTestCase;

import com.example.shadowspray.uitestdemo.utils.DeviceHelper;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class SampleApiTestCase extends InstrumentationTestCase {

    private static final String LOG_TAG = SampleApiTestCase.class.getSimpleName();

    private Context mContext;

    private DeviceHelper mDeviceHelper;

    @Before
    public void prepare() throws Exception {
        super.setUp();
        mContext = InstrumentationRegistry.getInstrumentation().getContext();
        mDeviceHelper = new DeviceHelper();
    }

    @After
    public void cleanup() throws Exception {
        super.tearDown();
    }

    @Test
    public void testDisableKeyguard() {
        try {
            mDeviceHelper.disableKeyguard();
        } catch (Exception e) {
            e.printStackTrace();
            fail("Could not disable keyguard. " + e.toString());
        }
    }

    @Test
    public void testEnableWifi() {
        WifiManager wm = mDeviceHelper.getWifiManager();
        assertTrue("Could not enable Wifi.", wm.setWifiEnabled(true));
    }

}
