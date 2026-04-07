---
description: Build, Deploy, and Run the Dog Image App (Section 3.9)
---

This workflow performs the full deployment lifecycle for the Dog Image App as required by the assignment.

### 1. Project Build
// turbo
1. Run the integrated Gradle build task to compile the project and generate the debug APK.
```powershell
.\gradlew.bat assembleDebug
```

### 2. Application Deployment
// turbo
2. Deploy the generated APK to the running Android Emulator.
```powershell
& "$env:LOCALAPPDATA\Android\Sdk\platform-tools\adb.exe" install -r app\build\outputs\apk\debug\app-debug.apk
```

### 3. Application Execution
// turbo
3. Launch the MainActivity and verify the application behavior.
```powershell
& "$env:LOCALAPPDATA\Android\Sdk\platform-tools\adb.exe" shell am start -n dam_a51609.dogimageapp/dam_a51609.dogimageapp.ui.MainActivity
```
