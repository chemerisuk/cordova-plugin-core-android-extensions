# cordova-plugin-core-android-extensions
> Useful methods for an android cordova app

## Installation

    cordova plugin add cordova-plugin-core-android-extensions

## Supported Platforms

- Android

## Methods

- `navigator.app.minimizeApp`
- `navigator.app.resumeApp`
- `navigator.app.detectApp`
- `navigator.app.uninstallApp`

### navigator.app.minimizeApp
Shows home screen and sends the app into background

#### Parameters

- __moveBack__: When `true` the app first tried to return to the previous task in current stack (Boolean)

### navigator.app.resumeApp
Brings app into foreground.

### navigator.app.detectApp
Detects an app availability.

#### Parameters

- __packageName__: Target app package name

### navigator.app.uninstallApp
Trigger an app uninstall dialog.

#### Parameters

- __packageName__: Target app package name

### navigator.app.startApp
Starts an app activity.

#### Parameters

- __packageName__: Target app package name
- __componentName__: Target app component name (optional)

Examples:
```js
// open Google Maps
navigator.app.startApp("com.google.android.apps.maps")
// open device Wi-Fi settings
navigator.app.startApp("com.android.settings", "com.android.settings.wifi.WifiSettings")
```

