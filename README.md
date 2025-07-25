# ONEAdMax SDK 1.2.2

## Overview

ONE Store AdMax SDK 1.2.2 is a service that delivers advertisements to users using the advertising system of ONE Store for products implemented in Android apps, and provides advertising revenue to developers. To bind this SDK, it is necessary to have products registered in the [ONE Store Developer Center](http://dev.onestore.co.kr/), and prior registration with [ONE AdMax](http://oneadmax.com) as a media platform is required.


## ONEAdMax SDK

### Maven Settings

Set the Maven repository address in the Top-level gradle.

```groovy
repositories {
    maven { url 'https://repo.onestore.net/repository/onestore-sdk-public' }
}
```

Then, add Maven dependencies to the Module-level gradle.

Please make sure to use the latest version, 1.2.2, to ensure compatibility with Android OS support and bug fixes.

Verify the exact version of the **In-App SDK** or **App Licensing Checker SDK** to install the compatible version of the **ONE AdMax SDK**.

```groovy
dependencies {
    implementation 'com.oneadmax.sdk:sdk-ads:1.2.2'

    // Compatible in-app libraries on the ONEstore are listed below.
    // implementation 'com.onestorecorp.sdk:sdk-iap:21.01.00'
    // implementation 'com.onestorecorp.sdk:sdk-licensing:2.1.1'
}
```
Need to add `<queries>` to your `Androidmanifest.xml` file.

```xml
<manifest>
    <queries>
        <intent>
            <action android:name="com.onestore.iaa.intent.action.REWARD" />
        </intent>
    </queries>
    ...
    <application>
        ...
    </application>
</manifest>

```

Refer to the[[SDK guide]](https://one-admax-organization.gitbook.io/one-admax-sdk/oamsdk)for more information

## Change Note
* 2025-07-22
    * Mediation compatibility update. 
    * Removed AdFit mediation. 
    * Code stabilization. 
* 2024-12-18
	* Fixed crash issue during AppLovin mediation initialization
	* Ensured compatibility with AppLovin SDK version 13.0.1
	* Supported compatibility with Mezzo Media v300 (20241031)
* 2024-10-23
  	* Added API for COPPA compliance 
  	* Removed AppLovin initialization from the manifest 
* 2024-07-16 
  	* Bug fixes within header bidding
* 2024-07-04
	* Added the marketing system. 
* 2024-03-27
	* Fixed the bug causing infinite loading in the AppLovin mediation.
* 2023-12-26
	* Uploaded sample project

# License
```
Copyright 2023 One store Co., Ltd.

Licensed under the Apache License, Version 2.0 (the "License"); 
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, 
software distributed under the License is distributed on an "AS IS" BASIS, 
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
See the License for the specific language governing permissions and
limitations under the License.
