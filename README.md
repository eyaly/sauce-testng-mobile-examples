# testng framework to run tests on Sauce real devices
This project contains Java examples for running Appium tests on Sauce Labs real devices.

## Important information
### Environment variables for Sauce Labs
The examples in this repository that can run on Sauce Labs, use environment variables, make sure you've added the following

    # For Sauce Labs Real devices
    export SAUCE_USERNAME=********
    export SAUCE_ACCESS_KEY=*******
    
### Demo app
The Native demo app that has been used for these tests can be found [here](https://github.com/saucelabs/my-demo-app-rn/releases).

### Upload apps to Sauce Storage
* If you want to use iOS real devices and Android real devices in Sauce Labs you need to upload the apps to the Sauce Storage.
For more information on this step please visit: [Application Storage](https://wiki.saucelabs.com/display/DOCS/Application+Storage).
* In the app capability you must use `storage:<app-id>` or `storage:filename=<file-name>`. For more information on this step please visit: [Application Storage](https://wiki.saucelabs.com/display/DOCS/Application+Storage).
* Change the value of appName in the native apps tests for Android and iOS according to your app name.
### Useful Links 
* How to upload the apps to the Sauce Storage and the app capability: [Application Storage](https://wiki.saucelabs.com/display/DOCS/Application+Storage).
* Appium Capabilities for Real Device Testing: [Appium Capabilities](https://wiki.saucelabs.com/display/DOCS/Appium+Capabilities+for+Real+Device+Testing).
* Sauce Labs Data Center Endpoints: [Data Center EndPoints](https://wiki.saucelabs.com/display/DOCS/Data+Center+Endpoints).
* How to set the pass/fail status of a test: [Annotating Tests with Selenium's JavaScript Executor](https://wiki.saucelabs.com/display/DOCS/Annotating+Tests+with+Selenium%27s+JavaScript+Executor).

## Run Native App tests on Android real devices and iOS real devices in the Sauce Labs Platform
### testNG xml file
The framework uses testNG xml file for parallel executions. All the tests in the same class will run in parallel on different devices 
The testNG xml file is [myDemoApp.xml](https://github.com/eyaly/sauce-testng-mobile-examples/blob/main/src/test/resources/config/myDemoApp.xml)
The structure of this file:
* There are two tests. The first one is pointing to the [tests that run on iOS devices]() and the second one is pointing to the [tests that run on Android devices]()
* Each test contains set of parameter names and values
* the parameter name is an Appium capability name. parameter name that start with `sauce_` will be added to the `sauce:options` capability
* the parameter value is the value for the capability
* Example: 
The line
`<parameter name="appium:deviceName" value="iPhone.*"></parameter>`
will be translated to `caps.setCapability("appium:deviceName", "iPhone.*");`
The line
`<parameter name="sauce_cacheId" value="ios_app_new_123"></parameter>`
will be translated to `sauceOptions.setCapability("cacheId", "ios_app_new_123");`
* build capability. If there is no build caoability in the xml file, the code will look for system environment "BUILD_TAG". This environment can be used with CI tool to contain the job name and job number for example. 
If there is no system environment "BUILD_TAG" - a random value will be created for the build capability. 

### Execute the tests
* The command line to run the tests

      // If using the US DC
      mvn clean test -DtestngXmlFile=myDemoApp.xml -Dregion=us
    
      // If using the EU DC
      mvn clean test -DtestngXmlFile=myDemoApp.xml -Dregion=eu
    
> NOTE: Make sure you are in the folder `sauce-testng-mobile-examples` when you execute this command

