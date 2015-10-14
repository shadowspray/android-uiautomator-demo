package com.example.shadowspray.uitestdemo.utils;

import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiSelector;
import android.support.test.uiautomator.UiWatcher;

/**
 * UiWatcher implementations
 */
public class DialogWatcher {

    /*
     * Watch for OK dialog
     */
    public static class OkDialogWatcher implements UiWatcher {
        protected UiObject mUiOkButton;

        @SuppressWarnings("deprecation")
        public OkDialogWatcher() {
            mUiOkButton = new UiObject(new UiSelector().text("OK").className(
                    "android.widget.Button"));
        }

        @Override
        public boolean checkForCondition() {
            if (mUiOkButton.exists()) {
                try {
                    mUiOkButton.click();
                } catch (UiObjectNotFoundException e) {
                }
                return true;
            }
            return false;
        }
    }

    /*
     * Watch for Force Close dialog
     */
    public static class ForceCloseDialogWatcher extends OkDialogWatcher {
        // TODO
    }

    /*
     * Watch for ANR dialog
     */
    public static class AnrDialogWatcher extends OkDialogWatcher {
        // TODO
    }

}