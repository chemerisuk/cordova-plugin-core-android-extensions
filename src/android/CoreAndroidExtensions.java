package by.chemerisuk.cordova.coreextensions;

import org.json.JSONArray;
import org.json.JSONException;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.PluginResult;

import android.content.Context;
import android.content.Intent;


public class CoreAndroidExtensions extends CordovaPlugin {
    public static final String PLUGIN_NAME = "CoreAndroidExtensions";

    /**
     * Executes the request and returns PluginResult.
     *
     * @param action            The action to execute.
     * @param args              JSONArry of arguments for the plugin.
     * @param callbackContext   The callback context from which we were invoked.
     * @return                  A PluginResult object with a status and message.
     */
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        PluginResult.Status status = PluginResult.Status.OK;
        String result = "";

        if (action.equals("minimizeApp")) {
            this.minimizeApp();
        } else if (action.equals("resumeApp")) {
            this.resumeApp();
        }

        callbackContext.sendPluginResult(new PluginResult(status, result));
        return true;
    }

    private void minimizeApp() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        this.cordova.getActivity().startActivity(intent);
    }

    private void resumeApp() {
        Context ctx = cordova.getActivity().getApplicationContext();
        Intent intent = new Intent(ctx, cordova.getActivity().getClass());

        intent.setAction(Intent.ACTION_MAIN);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED | Intent.FLAG_FROM_BACKGROUND | Intent.FLAG_ACTIVITY_NO_ANIMATION);

        ctx.startActivity(intent);
    }
}