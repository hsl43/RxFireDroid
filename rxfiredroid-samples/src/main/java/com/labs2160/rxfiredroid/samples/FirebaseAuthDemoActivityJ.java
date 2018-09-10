//package com.labs2160.rxfiredroid.samples;
//
//import android.os.Bundle;
//import android.util.Log;
//import android.view.View;
//import com.google.firebase.FirebaseApp;
//import com.labs2160.rxfirebase.auth.FirebaseAuthAppCompatActivity;
//import com.labs2160.rxfirebase.auth.GoogleAuthConfiguration;
//import com.labs2160.rxfirebase.auth.TwitterAuthConfiguration;
//import com.twitter.sdk.android.core.identity.TwitterLoginButton;
//import io.reactivex.disposables.CompositeDisposable;
//import org.jetbrains.annotations.Nullable;
//
//public class FirebaseAuthDemoActivityJ extends FirebaseAuthAppCompatActivity {
//
//  private final CompositeDisposable disposables = new CompositeDisposable();
//
//  @Override
//  protected void onCreate(@Nullable Bundle savedInstanceState) {
//    super.onCreate(savedInstanceState);
//
//    setContentView(R.layout.firebase_auth_demo_activity);
//
////    findViewById(R.id.email_password_sign_in_button).setOnClickListener(view -> {
////      disposables.add(
////          firebaseAuth.rxSignInWithEmailAndPassword("bleep@blorp.com", "bleepblorp").subscribe(
////              authResult -> {
////                Log.d(getClass().getName(), "## Sign-in to Firebase with email and password succeeded");
////              },
////              error -> {
////                Log.e(getClass().getName(), "## Sign-in to Firebase with email and password failed", error);
////              }
////          )
////      );
////    });
////
////    disposables.addAll(
////        bindGoogleAuth().subscribe(
////            authResult -> {
////              Log.d(getClass().getName(), "## Sign-in to Firebase with Google credentials succeeded");
////            },
////            error -> {
////              Log.e(getClass().getName(), "## Sign-in to Firebase with Google credentials failed", error);
////            }
////        ),
////        bindTwitterAuth().subscribe(
////            authResult -> {
////              Log.d(getClass().getName(), "## Sign-in to Firebase with Twitter credentials succeeded");
////            },
////            error -> {
////              Log.e(getClass().getName(), "## Sign-in to Firebase with Twitter credentials failed", error);
////            }
////        )
////    );
////
////    findViewById(R.id.sign_out_button).setOnClickListener(view -> firebaseAuth.signOut());
//
//    FirebaseApp.initializeApp(this);
//  }
//
//  @Override
//  protected void onDestroy() {
//    super.onDestroy();
//
//    disposables.clear();
//  }
//
//  @Override @Nullable
//  public GoogleAuthConfiguration googleAuthConfiguration() {
//    return new GoogleAuthConfiguration(
//        "requestIdToken", // request ID token
//        false,            // request ID?
//        false,            // request email?
//        false             // request profile?
//    );
//  }
//
//  @Override @Nullable
//  public View googleSignInButton() {
//    return findViewById(R.id.google_sign_in_button);
//  }
//
//  @Override @Nullable
//  public TwitterAuthConfiguration twitterAuthConfiguration() {
//    return new TwitterAuthConfiguration(
//        "consumerKey",    // consumer key
//        "consumerSecret", // consumer secret
//        true,             // debug?
//        Log.DEBUG         // log level
//    );
//  }
//
//  @Override @Nullable
//  public TwitterLoginButton twitterLoginButton() {
//    return findViewById(R.id.twitter_sign_in_button);
//  }
//
//}