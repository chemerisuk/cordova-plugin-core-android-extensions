package by.chemerisuk.cordova.coreextensions;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.view.WindowManager.LayoutParams;
import android.net.Uri;

import by.chemerisuk.cordova.support.CordovaMethod;
import by.chemerisuk.cordova.support.ReflectiveCordovaPlugin;

import org.json.JSONArray;
import org.json.JSONException;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.PluginResult;


public class CoreAndroidExtensions extends ReflectiveCordovaPlugin {
    public static final String PLUGIN_NAME = "CoreAndroidExtensions";
    public static final int UNINSTALL_REQUEST_CODE = 5523345;

    private CallbackContext uninstallCallbackContext;

    @CordovaMethod
    protected void minimizeApp(boolean moveBack, CallbackContext callbackContext) {
        // try to send it back and back to previous app
        if (moveBack) {
            moveBack = cordova.getActivity().moveTaskToBack(true);
        }
        // if not possible jump to home
        if (!moveBack) {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            cordova.getActivity().startActivity(intent);
        }

        if (callbackContext != null) {
            callbackContext.success();
        }
    }

    @CordovaMethod
    protected void resumeApp(CallbackContext callbackContext) {
        Context ctx = cordova.getActivity().getApplicationContext();

        minimizeApp(false, null); // make sure app is minimized

        Intent intent = new Intent(ctx, cordova.getActivity().getClass());
        intent.setAction(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);

        ctx.startActivity(intent);

        callbackContext.success();
    }

    @CordovaMethod
    protected void uninstallApp(String packageName, CallbackContext callbackContext) {
        Intent intent = new Intent(Intent.ACTION_UNINSTALL_PACKAGE);
        intent.setData(Uri.parse("package:" + packageName));
        intent.putExtra(Intent.EXTRA_RETURN_RESULT, true);
        cordova.startActivityForResult(this, intent, UNINSTALL_REQUEST_CODE);

        this.uninstallCallbackContext = callbackContext;
    }

    @CordovaMethod
    private void detectApp(String packageName, CallbackContext callbackContext) {
        Context ctx = cordova.getActivity().getApplicationContext();
        PackageManager pm = ctx.getPackageManager();
        boolean resultValue = false;

        try {
            // verify app is installed
            pm.getPackageInfo(packageName, PackageManager.GET_ACTIVITIES);
            // verify app is enabled
            resultValue = pm.getApplicationInfo(packageName, 0).enabled;
        } catch(PackageManager.NameNotFoundException e) {}

        callbackContext.sendPluginResult(
            new PluginResult(PluginResult.Status.OK, resultValue));
    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == UNINSTALL_REQUEST_CODE) {
            if (uninstallCallbackContext != null) {
                uninstallCallbackContext.sendPluginResult(
                    new PluginResult(PluginResult.Status.OK, resultCode == Activity.RESULT_OK));
            }
        }
    }
}