var exec = require("cordova/exec");
var APP_PLUGIN_NAME = "CoreAndroidExtensions";

module.exports = {
    /**
    * Go to home screen
    */
    minimizeApp: function(moveBack) {
        return exec(null, null, APP_PLUGIN_NAME, "minimizeApp", [moveBack]);
    },

    /**
    * Return app to foreground
    */
    resumeApp: function(force) {
        return exec(null, null, APP_PLUGIN_NAME, "resumeApp", [force]);
    }
};
