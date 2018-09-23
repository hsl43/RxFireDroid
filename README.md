# RxFireDroid 

<img src='https://travis-ci.org/hsl43/RxFireDroid.svg?branch=master' />

Drop-in replacements for Google Firebase libraries with added RxJava support.

## Releases

**Gradle** 

Project-level `build.gradle`

```groovy
allprojects {
    repositories {
        maven {
            url  "http://dl.bintray.com/labs2160"
        }
    }
}
```

App-level `build.gradle`
```groovy
dependencies {
    // Authentication
    implementation 'com.labs2160:rxfiredroid-auth:0.1.0'
    
    // Remote Configuration  
    implementation 'com.labs2160:rxfiredroid-config:0.1.0'
    
    // Realtime Database  
    implementation 'com.labs2160:rxfiredroid-database:0.1.0'
    
    // Cloud Firestore  
    implementation 'com.labs2160:rxfiredroid-firestore:0.1.0'
    
    // Cloud Messaging  
    implementation 'com.labs2160:rxfiredroid-messaging:0.1.0'
}
```

## Compatibility
### Android
* Compile SDK 27
* Min SDK 16
* Target SDK 27
* Play Services 16.0.0
* Support Libraries 27.1.1

### Firebase
* `com.google.firebase:firebase-core:16.0.3`
* `com.google.firebase:firebase-auth:16.0.3`
* `com.google.firebase:firebase-config:16.0.0`
* `com.google.firebase:firebase-database:16.0.2`
* `com.google.firebase:firebase-firestore:17.1.0`
* `com.google.firebase:firebase-messaging:17.3.1`

## Featured Libraries
- [Authentication](#authentication)
  - [FirebaseAuth](#firebase-auth)
  - [FirebaseAuthAppCompatActivity](#fireauthappcompatactivity)
    - [GitHub Sign-in](#github-sign-in)
    - [Google Sign-in](#google-sign-in)
    - [Twitter Sign-in](#twitter-sign-in)
- [Remote Configuration](#remote-configuration)
- [Realtime Database](#realtime-database)
- [Cloud Firestore](#cloud-firestore)
- [Cloud Messaging](#cloud-messaging)

## Authentication
### FirebaseAuth
A reactive version of the standard `FirebaseAuth` class. This version maintains
the same interface as the standard version and thus can be used as a drop-in 
replacement. 

Methods that return `Task`s now have reactive alternatives of the same name
prefixed with "rx". For example, the reactive alternative for:
```
public Task<AuthResult> signInWithCredential(AuthCredential credential)
```
...is:
```
public Single<AuthResult> rxSignInWithCredential(AuthCredential credential)
```

The reactive versions of:
* `add/removeAuthStateListener()`, and
* `add/removeIdTokenListener()`

...are defined as:
* `rxBindAuthState()`, and
* `rxBindIdTokenState()`

Where the `addXXXListener()` methods have symmetrical `removeXXXListener()`
methods for housekeeping purposes, the `rxBindXXXState()` methods automatically 
handle the clean-up of resources internally upon the disposal of the associated 
subscription.

### FirebaseAuthAppCompatActivity
This class is an extension of `android.support.v7.app.AppCompatActivity` and 
is equipped to handle auth via the identity providers detailed further below.

Classes extending this class inherit an instance `FirebaseAuth` ready for 
use after `onCreate()`. This instance can be used to directly invoke sign-in 
methods that do not require specialized callback handling, such as 
`rxSignInAnonymously()` and `rxSignInWithEmailAndPassword()`. 

Before invoking any of the sign-in methods, ensure that the relevant providers 
are enabled through the Firebase console.

#### GitHub Sign-in
To sign-in using GitHub auth credentials:
1. Add the following action and categories to the intent filter of the relevant 
`Activity` in AndroidManifest:
```xml
<intent-filter>
  <action android:name="android.intent.action.VIEW" />

  <category android:name="android.intent.category.DEFAULT" />
  <category android:name="android.intent.category.BROWSABLE" />

  <data android:scheme="[MY-SCHEME]" />
</intent-filter>
```

The value for *MY-SCHEME* should be the scheme of the callback URL you 
registered through the GitHub console. To find this value, go to 
*GitHub -> Settings -> Developer Settings -> OAuth Apps -> (App)*. Use the 
scheme from the URL provided in the section labeled "Authorization callback 
URL". 

As an example, the scheme of the URL `foo://bar.zee.handler` is `foo`.

2. Override `gitHubAuthConfiguration()`.
2. Override `gitHubSignInButton()` to return the view that will be clicked to 
begin the auth process.
2. Subscribe to `bindGitHubAuth(false)`. `false` indicates a desire to use a 
previously cached result if available, or to execute the auth process 
otherwise. This is ideal for surviving configuration changes. If, however, you 
would like to force another execution of the auth process then supply `true` 
instead. 

#### Google Sign-in
To sign-in using Google auth credentials:
1. Override `googleAuthConfiguration()`.
1. Override `googleSignInButton()` to return the view that will be clicked to 
begin the auth process.
2. Subscribe to `bindGoogleAuth(false)`. `false` indicates a desire to use a 
previously cached result if available, or to execute the auth process 
otherwise. This is ideal for surviving configuration changes. If, however, you 
would like to force another execution of the auth process then supply `true` 
instead. 

#### Twitter Sign-in
To sign-in using Twitter auth credentials:
1. Override `twitterAuthConfiguration()`.
1. Override `twitterSignInButton()` to return the view that will be clicked to 
begin the auth process.
2. Subscribe to `bindTwitterAuth(false)`. `false` indicates a desire to use a 
previously cached result if available, or to execute the auth process 
otherwise. This is ideal for surviving configuration changes. If, however, you 
would like to force another execution of the auth process then supply `true` 
instead. 


## Remote Configuration
*TODO*

## Realtime Database
*TODO*

## Cloud Firestore
*TODO*

## Cloud Messaging
*TODO*
