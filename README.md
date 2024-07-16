# ONEAdMax SDK 1.1.2

## Overview

ONE Store AdMax SDK 1.1.2 is a service that delivers advertisements to users using the advertising system of ONE Store for products implemented in Android apps, and provides advertising revenue to developers. To bind this SDK, it is necessary to have products registered in the [ONE Store Developer Center](http://dev.onestore.co.kr/), and prior registration with [ONE AdMax](http://oneadmax.com) as a media platform is required.


## ONEAdMax SDK

### Maven Settings

Set the Maven repository address in the Top-level gradle.

```groovy
repositories {
    maven { url 'https://repo.onestore.net/repository/onestore-sdk-public' }
}
```

Add Maven dependencies to the Module-level gradle.

We are releasing two versions, 1.0.2 and 1.1.2, due to changes in the way we find connection points to the store service.<br/>
Android OS support and bug fixes will be available in 1.1.0 and later, so please use the latest version whenever possible.
Please check the exact version information of the **In-App SDK** or **App Licensing Checker SDK** and install the version of the **ONE AdMax SDK that is compatible with each.**

```groovy
dependencies {
    implementation 'com.oneadmax.sdk:sdk-ads:1.0.2'
    implementation 'com.onestorecorp.sdk:sdk-configuration-kr:1.0.0'

    // Compatible in-app libraries on the ONEstore are listed below.
    // implementation 'com.onestorecorp.sdk:sdk-iap:21.00.02'
    // implementation 'com.onestorecorp.sdk:sdk-licensing:2.0.0'
}
```

```groovy
dependencies {
    implementation 'com.oneadmax.sdk:sdk-ads:1.1.2'

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

Refer to the[[SDK guide]](http://https://one-admax-organization.gitbook.io/one-admax-sdk/oamsdk)for more information

## Change Note
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
