package by.chemerisuk.cordova.coreextensions;

import org.json.JSONArray;
import org.json.JSONException;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.PluginResult;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.view.WindowManager.LayoutParams;
import android.net.Uri;


public class CoreAndroidExtensions extends CordovaPlugin {
    public static final String PLUGIN_NAME = "CoreAndroidExtensions";
    public static final int UNINSTALL_REQUEST_CODE = 5523345;

    private CallbackContext uninstallCallbackContext;

    /**
     * Executes the request and returns PluginResult.
     *
     * @param action            The action to execute.
     * @param args              JSONArry of arguments for the plugin.
     * @param callbackContext   The callback context from which we were invoked.
     * @return                  A PluginResult object with a status and message.
     */
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (action.equals("minimizeApp")) {
            minimizeApp(args.optBoolean(0, false));

            callbackContext.success();
        } else if (action.equals("resumeApp")) {
            resumeApp();

            callbackContext.success();
        } else if (action.equals("uninstallApp")) {
            uninstallApp(args.getString(0), callbackContext);
        } else if (action.equals("detectApp")) {
            detectApp(args.getString(0), callbackContext);
        }

        return true;
    }

    private void minimizeApp(boolean moveBack) {
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
    }

    private void resumeApp() {
        Context ctx = cordova.getActivity().getApplicationContext();

        minimizeApp(false); // make sure app is minimized

        Intent intent = new Intent(ctx, cordova.getActivity().getClass());
        intent.setAction(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);

        ctx.startActivity(intent);
    }

    private void uninstallApp(String packageName, CallbackContext callbackContext) {
        Intent intent = new Intent(Intent.ACTION_UNINSTALL_PACKAGE);
        intent.setData(Uri.parse("package:" + packageName));
        intent.putExtra(Intent.EXTRA_RETURN_RESULT, true);
        cordova.startActivityForResult(this, intent, UNINSTALL_REQUEST_CODE);
        this.uninstallCallbackContext = callbackContext;
    }

    private void detectApp(String packageName, CallbackContext callbackContext) {
        Context ctx = cordova.getActivity().getApplicationContext();
        PackageManager pm = ctx.getPackageManager();
        boolean resultValue;

        try {
            // verify app is installed
            pm.getPackageInfo(packageName, PackageManager.GET_ACTIVITIES);
            // verify app is enabled
            resultValue = pm.getApplicationInfo(packageName, 0).enabled;
        } catch(PackageManager.NameNotFoundException e) {
            resultValue = false;
        }

        callbackContext.sendPluginResult(
            new PluginResult(PluginResult.Status.OK, resultValue));
    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == UNINSTALL_REQUEST_CODE) {
            if (uninstallCallbackContext != null) {
                if (resultCode == Activity.RESULT_OK) {
                    uninstallCallbackContext.success(1);
                } else {
                    uninstallCallbackContext.success(0);
                }
            }
        }
    }
}