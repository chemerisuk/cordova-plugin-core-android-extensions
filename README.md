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

Activates app from background

#### Parameters

- __force__: When `true` the app bypasses keyguard and turns lights on (Boolean)

### navigator.app.detectApp

Detect app availability

#### Parameters

- __packageName__: Target app package name

### navigator.app.uninstallApp

Trigger app uninstall dialog

#### Parameters

- __packageName__: Target app package name

