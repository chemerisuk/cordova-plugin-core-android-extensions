var exec = require("cordova/exec");
var APP_PLUGIN_NAME = "CoreAndroidExtensions";

module.exports = {
    /**
    * Go to home screen
    */
    minimizeApp: function(moveBack) {
        return exec(null, null, APP_PLUGIN_NAME, "minimizeApp", [moveBack || false]);
    },

    /**
    * Return app to foreground
    */
    resumeApp: function() {
        return exec(null, null, APP_PLUGIN_NAME, "resumeApp", []);
    },

    /**
     * Detect app availability
     */
    detectApp: function(packageName, successCallback, errorCallback) {
        return exec(successCallback, errorCallback, APP_PLUGIN_NAME, "detectApp", [packageName]);
    },

    /**
     * Trigger app uninstall dialog
     */
    uninstallApp: function(packageName, successCallback, errorCallback) {
        return exec(successCallback, errorCallback, APP_PLUGIN_NAME, "uninstallApp", [packageName]);
    },

    /**
     * Starts app intent
     */
    startApp: function(packageName, componentName, successCallback, errorCallback) {
        return exec(successCallback, errorCallback, APP_PLUGIN_NAME, "startApp", [packageName, componentName]);
    }
};
