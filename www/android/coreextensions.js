var exec = require("cordova/exec");
var APP_PLUGIN_NAME = "CoreAndroidExtensions";

module.exports = {
    /**
    * Go to home screen
    */
    minimizeApp: function() {
        return exec(null, null, APP_PLUGIN_NAME, "minimizeApp", []);
    },

    /**
    * Return app to foreground
    */
    resumeApp: function() {
        return exec(null, null, APP_PLUGIN_NAME, "resumeApp", []);
    }
}