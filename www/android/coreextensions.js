var exec = require("cordova/exec");
var APP_PLUGIN_NAME = "CoreAndroidExtensions";

module.exports = {
    /**
    * Go to home screen
    */
    minimizeApp: function(moveBack) {
        return new Promise(function(resolve, reject) {
            exec(resolve, reject, APP_PLUGIN_NAME, "minimizeApp", [moveBack || false]);
        });
    },

    /**
    * Return app to foreground
    */
    resumeApp: function() {
        return new Promise(function(resolve, reject) {
            return exec(resolve, reject, APP_PLUGIN_NAME, "resumeApp", []);
        });
    },

    /**
     * Detect app availability
     */
    detectApp: function(packageName) {
        return new Promise(function(resolve, reject) {
            exec(resolve, reject, APP_PLUGIN_NAME, "detectApp", [packageName]);
        });
    },

    /**
     * Trigger app uninstall dialog
     */
    uninstallApp: function(packageName) {
        return new Promise(function(resolve, reject) {
            exec(resolve, reject, APP_PLUGIN_NAME, "uninstallApp", [packageName]);
        });
    },

    /**
     * Starts app intent
     */
    startApp: function(packageName, componentName) {
        return new Promise(function(resolve, reject) {
            exec(resolve, reject, APP_PLUGIN_NAME, "startApp", [packageName, componentName]);
        });
    }
};
