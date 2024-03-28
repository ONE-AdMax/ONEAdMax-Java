# ONEAdMax SDK 0.9.8

## Overview

ONE Store AdMax SDK 0.9.8 is a service that delivers advertisements to users using the advertising system of ONE Store for products implemented in Android apps, and provides advertising revenue to developers. To bind this SDK, it is necessary to have products registered in the [ONE Store Developer Center](http://dev.onestore.co.kr/), and prior registration with [ONE AdMax](http://oneadmax.com) as a media platform is required.


## ONEAdMax SDK

### Maven Settings

Set the Maven repository address in the Root Project gradle.

```groovy
repositories {
    maven { url 'https://repo.onestore.co.kr/repository/onestore-sdk-public' }
}
```

Add Maven dependencies to the project gradle.

```groovy
dependencies {
    implementation 'com.oneadmax.sdk:sdk-ads:0.9.8'
}
```

Refer to the[[SDK guide]](http://https://one-admax-organization.gitbook.io/one-admax-sdk/oamsdk)for more information

## Change Note
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
