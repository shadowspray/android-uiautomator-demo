package com.example.shadowspray.uitestdemo;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.Until;
import android.test.InstrumentationTestCase;

import com.example.shadowspray.uitestdemo.utils.DialogWatcher;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
@RunWith(AndroidJUnit4.class)
public class CalculatorUiTestCase extends InstrumentationTestCase {
    private static final String LOG_TAG = CalculatorUiTestCase.class.getSimpleName();

    public static String CALCULATOR_PACKAGE_NAME = "com.android.calculator2";
    public static long WAIT_ACTIVITY_TIMEOUT = 5 * 1000;
    private UiDevice mDevice;

    @Before
    public void startFromHome() throws Exception {
        // Initialize UiDevice instance
        super.setUp();
        mDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());

        // Start from the home screen
        mDevice.pressHome();
        mDevice.wait(Until.hasObject(By.pkg(mDevice.getLauncherPackageName()).depth(0)), WAIT_ACTIVITY_TIMEOUT);
    }

    @Test
    public void startCalculator() {
        Context context = InstrumentationRegistry.getContext();
        Intent startCalculatorIntent = context.getPackageManager().getLaunchIntentForPackage(CALCULATOR_PACKAGE_NAME);
        List<ApplicationInfo> appInfoList = context.getPackageManager().getInstalledApplications(PackageManager.GET_UNINSTALLED_PACKAGES);
        context.startActivity(startCalculatorIntent);

        DialogWatcher.OkDialogWatcher okDialogWatcher = new DialogWatcher.OkDialogWatcher();
        // Append UiWatcher instances
        mDevice.registerWatcher("OkDialogWatcher", okDialogWatcher);

        // Verify if calculator is started
        assertTrue("Calculator not launched after: " + WAIT_ACTIVITY_TIMEOUT + " (ms)",
                mDevice.wait(Until.hasObject(By.pkg("com.android.calculator2").depth(0)), WAIT_ACTIVITY_TIMEOUT));
    }

    @After
    public void cleanUp() throws Exception {
        super.tearDown();
        mDevice.pressHome();
    }
}